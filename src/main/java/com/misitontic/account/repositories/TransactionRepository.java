package com.misitontic.account.repositories;

import com.misitontic.account.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByOriginAccount(String originAccount);
    List<Transaction> findByDestinyAccount(String destinyAccount);
}
