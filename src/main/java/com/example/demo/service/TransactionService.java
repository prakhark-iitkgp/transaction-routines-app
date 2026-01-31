package com.example.demo.service;


import com.example.demo.dto.CreateTransactionRequestDto;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionEntity createTransaction(CreateTransactionRequestDto transactionRequestDto) {
        return transactionRepository.save(new TransactionEntity(null, transactionRequestDto.getAccountId(),
                transactionRequestDto.getOperationTypeId(), transactionRequestDto.getAmount(), null));
    }

}
