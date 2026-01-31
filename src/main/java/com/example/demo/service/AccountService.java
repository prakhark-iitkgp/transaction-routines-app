package com.example.demo.service;

import com.example.demo.dto.Account;
import com.example.demo.dto.CreateAccountRequestDto;
import com.example.demo.entity.AccountEntity;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Transactional
    public AccountEntity createAccount(CreateAccountRequestDto accountDto){
        return accountRepository.save(new AccountEntity(accountDto.getDocumentNumber()));
    }

    public Account getAccount(String accountId) throws AccountNotFoundException {
        Optional<AccountEntity> accountEntity = accountRepository.findByAccountId(accountId);
        Account account = new Account();

        if(accountEntity.isEmpty()){
            throw new AccountNotFoundException("no account present with id: " + accountId);
        }

        account.setAccountId(accountEntity.get().getAccountId());
        account.setDocumentNumber(accountEntity.get().getDocumentNumber());

        return account;
    }


}
