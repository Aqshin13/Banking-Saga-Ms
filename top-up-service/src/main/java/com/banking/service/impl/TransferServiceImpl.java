package com.banking.service.impl;


import com.banking.dao.TransferTransactionRepository;
import com.banking.dto.event.Event;
import com.banking.dto.request.TransferRequest;
import com.banking.entity.TransferTransaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl {


    private final TransferTransactionRepository repository;

    private final KafkaTemplate<String, Event> producer;


    @Transactional(rollbackOn = Exception.class)
    public void createTopUpRequest(TransferRequest request) {
        TransferTransaction transferTransaction = TransferTransaction.builder()
                .sender(request.sender())
                .amount(request.amount())
                .receiver(request.receiver())
                .type(TransferTransaction.TransactionType.TOP_UP)
                .status(TransferTransaction.TransactionStatus.PROCESSING)
                .build();

        TransferTransaction fromDB = repository.save(transferTransaction);
        Event event = new Event(fromDB.getId(),
                fromDB.getSender(),
                fromDB.getReceiver(),
                fromDB.getAmount());
        producer.send("create-transfer-topic", event);
    }



    public void onFAILED(Long id){
       TransferTransaction transferTransaction=  repository.findById(id).orElseThrow(
                ()->new RuntimeException("Transaction is not found")
        );
       transferTransaction.setStatus(TransferTransaction.TransactionStatus.FAILED);
       repository.save(transferTransaction);

    }



    public void onSUCCESS(Long id){
        TransferTransaction transferTransaction=  repository.findById(id).orElseThrow(
                ()->new RuntimeException("Transaction is not found")
        );
        transferTransaction.setStatus(TransferTransaction.TransactionStatus.SUCCESS);
        repository.save(transferTransaction);
    }



}
