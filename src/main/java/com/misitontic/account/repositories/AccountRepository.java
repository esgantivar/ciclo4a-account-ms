package com.misitontic.account.repositories;

import com.misitontic.account.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{'username': {'$regex': ?0, '$options': 'i'}}")
    List<Account> findByRegexUsername(String term);
}
