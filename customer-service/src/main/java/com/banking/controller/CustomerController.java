package com.banking.controller;


import com.banking.dto.request.CustomerSaveRequest;
import com.banking.dto.response.CustomerResponse;
import com.banking.service.inter.CustomerServiceInter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerServiceInter customerServiceInter;


    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest request) {
        CustomerResponse customerResponse = customerServiceInter.save(request);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(customerResponse);

    }


}
