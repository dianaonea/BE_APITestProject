package com.myproject.accounts.controller;

import com.myproject.accounts.exceptions.ResourceNotFoundException;
import com.myproject.accounts.model.db.CustomerDetails;
import com.myproject.accounts.model.request.RegisterCustomerRequest;
import com.myproject.accounts.model.response.CustomersResponse;
import com.myproject.accounts.repository.AccountsRepository;
import com.myproject.accounts.service.AccountsService;
import com.myproject.accounts.utils.EmailUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @RequestMapping(value = "/accounts/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomersResponse registerAccount (@RequestBody RegisterCustomerRequest request) {
        CustomersResponse customersResponse = new CustomersResponse();
        customersResponse.setData(new CustomerDetails[]{accountsService.registerNewCustomer(request.getData())});
        try {
            EmailUtils.sendActivationEmail(request.getData().getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return customersResponse;
    }

    @RequestMapping(value = "/accounts/{customerId}/activate", method = RequestMethod.PATCH)
    public CustomersResponse activateCustomer (@PathVariable("customerId") String customerId, @RequestBody RegisterCustomerRequest request){
        CustomersResponse customersResponse = new CustomersResponse();
        customersResponse.setData(new CustomerDetails[]{accountsService.activateCustomer(customerId, request.getData().getPassword())});
        return customersResponse;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public CustomersResponse getAllCustomers () {
        List<CustomerDetails> customers = accountsService.getAllCustomers();
        CustomersResponse customersResponse = new CustomersResponse();
        customersResponse.setData(customers.toArray(new CustomerDetails[customers.size()]));
        return customersResponse;
    }

    @RequestMapping(value = "/accounts/{customerId}", method = RequestMethod.GET)
    public CustomersResponse getCustomer (@PathVariable("customerId") String customerId) {
        CustomerDetails customer = accountsService.getCustomer(customerId);
        CustomersResponse customersResponse = new CustomersResponse();
        customersResponse.setData(new CustomerDetails[]{customer});
        return customersResponse;
    }

    @RequestMapping(value = "/accounts/{customerId}/update", method = RequestMethod.PUT)
    public CustomersResponse updateCustomer (@PathVariable("customerId") String customerId, @RequestBody RegisterCustomerRequest request){
        CustomersResponse customersResponse = new CustomersResponse();
        customersResponse.setData(new CustomerDetails[]{accountsService.updateCustomer(customerId, request.getData())});
        return customersResponse;
    }

    @RequestMapping(value = "/accounts/{customerId}/delete", method = RequestMethod.DELETE)
    public void deleteCustomer (@PathVariable("customerId") String customerId){
        String emailAddress = accountsService.getEmailById(customerId);
        accountsService.deleteCustomer(customerId);
        try {
            EmailUtils.sendDeletionEmail(emailAddress);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
