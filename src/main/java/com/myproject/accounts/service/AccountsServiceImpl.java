package com.myproject.accounts.service;

import com.myproject.accounts.exceptions.ResourceNotFoundException;
import com.myproject.accounts.model.CustomerStatus;
import com.myproject.accounts.model.db.CustomerDetails;
import com.myproject.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class AccountsServiceImpl implements AccountsService{

    @Autowired
    private AccountsRepository repository;


    @Override
    public List<CustomerDetails> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public CustomerDetails getCustomer(String customerId) {
        CustomerDetails customer = repository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("The customer "+customerId+" was not found"));
        return customer;
    }

    @Override
    public String getEmailById(String customerId) {
        CustomerDetails customer = repository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("The customer "+customerId+" was not found"));
        return customer.getEmail();
    }

    @Override
    public CustomerDetails registerNewCustomer(CustomerDetails customerDetails) {
        CustomerDetails customer = new CustomerDetails();
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNo(customerDetails.getPhoneNo());
        UUID uuid = UUID. randomUUID();
        customer.setCustomerId(uuid.toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        customer.setCreatedDate(dtf.format(now));
        customer.setUpdatedDate(dtf.format(now));
        customer.setActivated(false);
        // activatedDate and password should remain null until the email action api is called
        customer.setStatus(CustomerStatus.NEW.toString());
        repository.save(customer);
        return customer;
    }

    @Override
    public CustomerDetails activateCustomer(String customerId, String password) {
        CustomerDetails customerToActivate = getCustomer(customerId);
        customerToActivate.setActivated(true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        customerToActivate.setActivatedDate(dtf.format(LocalDateTime.now()));
        customerToActivate.setPassword(password);
        customerToActivate.setStatus(CustomerStatus.ACTIVATED.toString());
        repository.save(customerToActivate);
        return customerToActivate;
    }

    @Override
    public CustomerDetails updateCustomer(String customerId, CustomerDetails newCustomer) {
        CustomerDetails customer = getCustomer(customerId);
        if (newCustomer.getFirstName() != null && !newCustomer.getFirstName().isEmpty())
            customer.setFirstName(newCustomer.getFirstName());
        if (newCustomer.getLastName() != null && !newCustomer.getLastName().isEmpty())
            customer.setLastName(newCustomer.getLastName());
        if (newCustomer.getEmail() != null && !newCustomer.getEmail().isEmpty())
            customer.setEmail(newCustomer.getEmail());
        if (newCustomer.getPhoneNo() != null && !newCustomer.getPhoneNo().isEmpty())
            customer.setPhoneNo(newCustomer.getPhoneNo());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        customer.setUpdatedDate(dtf.format(LocalDateTime.now()));
        repository.save(customer);
        return customer;

    }

    @Override
    public void deleteCustomer(String customerId) {
        CustomerDetails customer = getCustomer(customerId);
        repository.delete(customer);
    }
}
