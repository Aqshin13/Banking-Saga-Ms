package com.banking.controller;


import com.banking.dto.request.CustomerSaveRequest;
import com.banking.dto.response.BalanceResponse;
import com.banking.dto.response.CustomerResponse;
import com.banking.service.inter.CustomerServiceInter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerServiceInter customerServiceInter;


    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest request) {
        CustomerResponse customerResponse = customerServiceInter.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerResponse);
    }


    @GetMapping("/{id}/balance")
    public ResponseEntity<BalanceResponse> getBalance( @PathVariable Long id) {
        BalanceResponse balance
                = customerServiceInter.getBalance(id);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(balance);
    }

}
