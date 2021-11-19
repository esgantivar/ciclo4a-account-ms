package com.misitontic.account.controllers;

import com.misitontic.account.models.Account;
import com.misitontic.account.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    List<Account> getAllAccounts(@RequestParam("u") Optional<String> username) {
        if (username.isPresent()) {
            return this.accountRepository.findByRegexUsername(username.get());
        } else {
            return this.accountRepository.findAll();
        }
    }

    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username) {
        return this.accountRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario " + username + " no existe")
        );
    }

    @DeleteMapping("/accounts/{username}")
    void deleteAccount(@PathVariable String username) {
        this.accountRepository.deleteById(username);
    }

    @PostMapping("/accounts")
    Account createAccount(@RequestBody Account account) {
        return this.accountRepository.save(account);
    }
}
