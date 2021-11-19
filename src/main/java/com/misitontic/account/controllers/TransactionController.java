package com.misitontic.account.controllers;

import com.misitontic.account.models.Account;
import com.misitontic.account.models.Transaction;
import com.misitontic.account.repositories.AccountRepository;
import com.misitontic.account.repositories.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TransactionController {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionController(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transaction")
    Transaction createTransaction(@RequestBody Transaction transaction) {
        Account originAccount = this.accountRepository.findById(transaction.getOriginAccount()).orElse(null);
        Account destinyAccount = this.accountRepository.findById(transaction.getDestinyAccount()).orElse(null);

        if (originAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta de origen no existe");
        }
        if (destinyAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta de destino no existe");
        }
        if (transaction.getValue() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor de la transacción debe ser positivos");
        }
        if (originAccount.getBalance() < transaction.getValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo de la cuenta de origen no es suficiente");
        }

        transaction.setBalanceOrigin(originAccount.getBalance());
        transaction.setBalanceDestiny(destinyAccount.getBalance());

        Date currentDate = new Date();

        originAccount.setBalance(originAccount.getBalance() - transaction.getValue());
        originAccount.setLastChange(currentDate);
        this.accountRepository.save(originAccount);

        destinyAccount.setBalance(destinyAccount.getBalance() + transaction.getValue());
        destinyAccount.setLastChange(currentDate);
        this.accountRepository.save(destinyAccount);

        transaction.setDate(currentDate);
        return this.transactionRepository.save(transaction);
    }

    @GetMapping("/transactions/{username}")
    List<Transaction> accountTransaction(@PathVariable String username) {
        Account account = this.accountRepository.findById(username).orElse(null);

        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe");
        }

        List<Transaction> originTransactions = this.transactionRepository.findByOriginAccount(account.getUsername());
        List<Transaction> destinyTransactions = this.transactionRepository.findByDestinyAccount(account.getUsername());

        List<Transaction> transactions = Stream.concat(originTransactions.stream(), destinyTransactions.stream()).collect(Collectors.toList());

        return transactions;
    }
}
