package com.banking.service;


import com.banking.dao.TransactionRepository;
import com.banking.dto.event.Event;
import com.banking.dto.request.PurchaseRequest;
import com.banking.entity.Transaction;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final TransactionRepository transactionRepository;
    private final KafkaTemplate<String, Event> producer;

    @Transactional(rollbackOn = Exception.class)
    public void createPurchase(PurchaseRequest request) {
        Transaction transaction = Transaction
                .builder()
                .sender(request.sender())
                .receiver(request.receiver())
                .amount(request.amount())
                .isRefund(false)
                .type(Transaction.TransactionType.PURCHASE)
                .status(Transaction.TransactionStatus.PROCESSING)
                .build();

        Transaction fromDB = transactionRepository.save(transaction);

        Event event = new Event(fromDB.getId(),
                fromDB.getSender(),
                fromDB.getReceiver(),
                fromDB.getAmount());

        producer.send("create-purchase-topic", event);


    }

    public void onFAILED(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction is not found"));
        transaction.setStatus(Transaction.TransactionStatus.FAILED);
        transactionRepository.save(transaction);
    }

    public void onSUCCESS(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction is not found"));
        transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);
    }


    public void onSUCCESSRefund(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction is not found"));
        transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
        transaction.setRefund(true);
        transactionRepository.save(transaction);
    }

    public void createRefund(Long id) {

        Transaction transaction = transactionRepository.getTransaction(id)
                .orElseThrow(() -> new RuntimeException("Transaction is not found"));

        Transaction transactionRefund = Transaction
                .builder()
                .sender(transaction.getReceiver())
                .receiver(transaction.getSender())
                .amount(transaction.getAmount())
                .isRefund(false)
                .type(Transaction.TransactionType.REFUND)
                .status(Transaction.TransactionStatus.PROCESSING)
                .purchase(transaction)
                .build();

        Transaction fromDB = transactionRepository.save(transactionRefund);


        Event event = new Event(
                fromDB.getId(),
                fromDB.getSender(),
                fromDB.getReceiver(),
                fromDB.getAmount()
        );

        producer.send("create-refund-topic", event);

    }


}
