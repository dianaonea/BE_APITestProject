package com.myproject.accounts.service;

import com.myproject.accounts.model.db.CustomerDetails;

import java.util.List;

public interface AccountsService {

    List<CustomerDetails> getAllCustomers();

    CustomerDetails getCustomer(String customerId);

    String getEmailById (String customerId);

    CustomerDetails registerNewCustomer(CustomerDetails account);

    CustomerDetails activateCustomer(String customerId, String password);

    CustomerDetails updateCustomer(String customerId, CustomerDetails account);

    void deleteCustomer (String customerId);
}
