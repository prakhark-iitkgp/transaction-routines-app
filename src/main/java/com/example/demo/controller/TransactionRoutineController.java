package com.example.demo.controller;

import com.example.demo.dto.CreateAccountRequestDto;
import com.example.demo.dto.CreateTransactionRequestDto;
import com.example.demo.dto.ServiceResponse;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionRoutineController {

    private final AccountService accountService;

    private final TransactionService transactionService;

    public TransactionRoutineController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<ServiceResponse> getAccountDetails(@PathVariable Long accountId) throws AccountNotFoundException {

        return ResponseEntity.ok(new ServiceResponse(HttpStatus.OK.name(), true,
                accountService.getAccount(accountId)));
    }

    @PostMapping("/accounts")
    public ResponseEntity<ServiceResponse> createAccount(@RequestBody CreateAccountRequestDto request){
        return ResponseEntity.ok(new ServiceResponse(HttpStatus.OK.name(), true,
                accountService.createAccount(request)));
    }

    @PostMapping("/transactions")
    public ResponseEntity<ServiceResponse> createTransaction(@RequestBody CreateTransactionRequestDto request){
        return  ResponseEntity.ok(new ServiceResponse(HttpStatus.OK.name(), true,
                transactionService.createTransaction(request)));
    }

}
