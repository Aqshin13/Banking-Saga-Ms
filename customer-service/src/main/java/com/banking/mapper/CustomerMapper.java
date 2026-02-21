package com.banking.mapper;

import com.banking.dto.request.CustomerSaveRequest;
import com.banking.dto.response.BalanceResponse;
import com.banking.dto.response.CustomerResponse;
import com.banking.entity.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class CustomerMapper {


    public CustomerResponse mapToResponseFromEntity(Customer customer) {
        return CustomerResponse.builder()
                .gsmNumber(customer.getGsmNumber())
                .name(customer.getName())
                .balance(customer.getBalance())
                .surname(customer.getSurname())
                .birthDate(customer.getBirthDate())
                .build();

    }


    public Customer mapToEntityFromRequest(CustomerSaveRequest customer) {
        return Customer.builder()
                .gsmNumber(customer.getGsmNumber())
                .name(customer.getName())
                .balance(BigDecimal.valueOf(100))
                .surname(customer.getSurname())
                .birthDate(customer.getBirthDate())
                .build();

    }


    public BalanceResponse mapToBalanceResponseFromEntity(Customer customer) {
        return new BalanceResponse(customer.getId(),
                customer.getName(),
                customer.getBalance());

    }


}
