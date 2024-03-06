package com.myproject.accounts.model.db;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "customer")  //uncomment if a different collection name is wanted
public class CustomerDetails {

    @Id
    String customerId;

    @NotNull (message = "The firstName property is missing. Please provide a value for it.")
    @Pattern (regexp= "[a-zA-Z.-/ ]{3,100}", message = "The firstName value is not valid.")
    String firstName;

    @NotNull (message = "The lastName property is missing. Please provide a value for it.")
    @Pattern (regexp= "[a-zA-Z.-/ ]{3,100}", message = "The lastName value is not valid.")
    String lastName;

    @NotNull (message = "The email property is missing. Please provide a value for it.")
    @Indexed(unique = true)
    @Pattern (regexp= "^(?=.{1,64}@)[A-Za-z0-9_\\-+]+(\\.[A-Za-z0-9_\\-+]+)*@[^-][A-Za-z0-9-+]+(\\.[A-Za-z0-9-+]+)*(\\.[A-Za-z]{2,})$", message = "The email value is not valid.") // Allows numbers, uppercase and lowercase letters, special characters (_ - .), max length = 64
    String email;

    @NotNull (message = "The phoneNo property is missing. Please provide a value for it.")
    //@Indexed(unique = true)
    @Pattern (regexp= "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "The phoneNo value is not valid.") // Allows prefix, parenthesis, dashes and dots
    String phoneNo;

    @Pattern (regexp= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$", message = "The password value is not valid.") // At least one small letter and one capital letter, one digit, one special character, min length = 8
    String password;
    String createdDate;
    String updatedDate;
    boolean isActivated;
    String activatedDate;
    String status;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(String activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", isActivated=" + isActivated +
                ", activatedDate='" + activatedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
