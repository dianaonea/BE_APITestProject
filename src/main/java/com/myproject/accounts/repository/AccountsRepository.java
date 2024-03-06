package com.myproject.accounts.repository;

import com.myproject.accounts.model.db.CustomerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountsRepository extends MongoRepository<CustomerDetails, String> { // can use JpaRepository or CrudRepository for classic DBs, MongoRepository for Mongo DB

}
