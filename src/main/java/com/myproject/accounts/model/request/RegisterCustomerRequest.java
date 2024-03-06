package com.myproject.accounts.model.request;

import com.myproject.accounts.model.db.CustomerDetails;

public class RegisterCustomerRequest {
    CustomerDetails data;

    public RegisterCustomerRequest() {
    }
    public RegisterCustomerRequest(CustomerDetails data) {
        this.data = data;
    }

    public CustomerDetails getData() {
        return data;
    }

    public void setData(CustomerDetails data) {
        this.data = data;
    }
}
