package com.example.demo.controller;

import com.example.demo.dto.CreateAccountRequestDto;
import com.example.demo.dto.CreateTransactionRequestDto;
import com.example.demo.dto.ServiceResponse;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get account details", description = "Fetch details of a specific account by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<ServiceResponse> getAccountDetails(@PathVariable Long accountId) throws AccountNotFoundException {

        return ResponseEntity.ok(new ServiceResponse(HttpStatus.OK.name(), true,
                accountService.getAccount(accountId)));
    }

    @Operation(summary = "Create a new account", description = "Registers a new account in the system")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @PostMapping("/accounts")
    public ResponseEntity<ServiceResponse> createAccount(@RequestBody CreateAccountRequestDto request){
        return ResponseEntity.ok(new ServiceResponse(HttpStatus.OK.name(), true,
                accountService.createAccount(request)));
    }

    @Operation(summary = "Create a transaction", description = "Records a new financial transaction for an account")
    @ApiResponse(responseCode = "200", description = "Transaction processed successfully")
    @PostMapping("/transactions")
    public ResponseEntity<ServiceResponse> createTransaction(@RequestBody CreateTransactionRequestDto request){
        return  ResponseEntity.ok(new ServiceResponse(HttpStatus.OK.name(), true,
                transactionService.createTransaction(request)));
    }

}
