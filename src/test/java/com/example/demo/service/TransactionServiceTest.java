package com.example.demo.service;

import com.example.demo.dto.CreateTransactionRequestDto;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void createTransaction_Success() {
        Long accountId = 1L;
        Integer operationTypeId = 4;
        BigDecimal amount = new BigDecimal("123.45");

        CreateTransactionRequestDto request = new CreateTransactionRequestDto();
        request.setAccountId(accountId);
        request.setOperationTypeId(operationTypeId);
        request.setAmount(amount);

        TransactionEntity savedEntity = new TransactionEntity(100L, accountId, operationTypeId, amount, null);

        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(savedEntity);

        TransactionEntity result = transactionService.createTransaction(request);

        assertNotNull(result);
        assertEquals(100L, result.getTransactionId());
        assertEquals(accountId, result.getAccountId());
        assertEquals(operationTypeId, result.getOperationTypeId());
        assertEquals(amount, result.getAmount());
    }
}