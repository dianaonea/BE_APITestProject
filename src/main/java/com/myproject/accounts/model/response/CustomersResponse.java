package com.myproject.accounts.model.response;

import com.myproject.accounts.model.db.CustomerDetails;

public class CustomersResponse {

    //StatusResponse status;
    CustomerDetails[] data;

   /* public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }*/

    public CustomerDetails[] getData() {
        return data;
    }

    public void setData(CustomerDetails[] data) {
        this.data = data;
    }
}
