package com.example.demo.service;

import com.example.demo.dto.Account;
import com.example.demo.dto.CreateAccountRequestDto;
import com.example.demo.entity.AccountEntity;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private AccountEntity mockEntity;
    private final Long accountId = 123L;
    private final String documentNumber = "12345678";

    @BeforeEach
    void setUp() {
        mockEntity = new AccountEntity(documentNumber);
        mockEntity.setAccountId(accountId);
    }

    @Test
    @DisplayName("Should successfully create an account")
    void createAccount_Success() {
        CreateAccountRequestDto request = new CreateAccountRequestDto();
        request.setDocumentNumber(documentNumber);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(mockEntity);

        AccountEntity result = accountService.createAccount(request);

        assertNotNull(result);
        assertEquals(documentNumber, result.getDocumentNumber());
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Should return account DTO when account exists")
    void getAccount_Success() throws AccountNotFoundException {
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(mockEntity));

        Account result = accountService.getAccount(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
        assertEquals(documentNumber, result.getDocumentNumber());
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when account does not exist")
    void getAccount_NotFound() {
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.empty());

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccount(accountId);
        });

        assertEquals("no account present with id: " + accountId, exception.getMessage());
    }
}