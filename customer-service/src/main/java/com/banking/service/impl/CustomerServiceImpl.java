package com.banking.service.impl;

import com.banking.dao.CustomerRepository;
import com.banking.dto.event.Event;
import com.banking.dto.request.CustomerSaveRequest;
import com.banking.dto.response.BalanceResponse;
import com.banking.dto.response.CustomerResponse;
import com.banking.entity.Customer;
import com.banking.exception.CustomerNotFoundException;
import com.banking.mapper.CustomerMapper;
import com.banking.service.inter.CustomerServiceInter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerServiceInter {

    private final KafkaTemplate<String, Long> producer;
    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse save(CustomerSaveRequest request) {
        return customerMapper.mapToResponseFromEntity(
                repository.save(customerMapper.mapToEntityFromRequest(request))
        );
    }

    private void transferMoney(Event event){
        Customer sender = repository.findById(event.senderId())
                .orElseThrow(() -> new RuntimeException("Customer is not found"));
        Customer receiver = repository.findById(event.receiverId())
                .orElseThrow(() -> new RuntimeException("Customer is not found"));
        if (event.amount().compareTo(sender.getBalance()) > 0) {
            throw new RuntimeException("Balance is not enough");
        }
        sender.setBalance(sender.getBalance().subtract(event.amount()));
        receiver.setBalance(receiver.getBalance().add(event.amount()));
        repository.saveAll(List.of(sender, receiver));
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void processTransfer(Event event) {

        try {
            transferMoney(event);
            producer.send("top-up-end-topic",event.transactionId());
        } catch (Exception exception) {
            producer.send("top-up-failed-topic",event.transactionId());
        }

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void processPurchase(Event event) {
        try {
            transferMoney(event);
            producer.send("purchase-end-topic",event.transactionId());
        } catch (Exception exception) {
            producer.send("purchase-failed-topic",event.transactionId());
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void processRefund(Event event) {
         try{
             transferMoney(event);
             producer.send("refund-end-topic",event.transactionId());
         }catch (Exception exception){
             producer.send("refund-failed-topic",event.transactionId());

         }
    }

    @Override
    public BalanceResponse getBalance(Long id) {
       Customer customer= repository.findById(id)
               .orElseThrow(()->new CustomerNotFoundException("Customer is not found"));
        return customerMapper.mapToBalanceResponseFromEntity(customer);
    }
}
