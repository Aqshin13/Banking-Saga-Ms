package com.banking.controller;


import com.banking.dto.request.PurchaseRequest;
import com.banking.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {



    private final TransactionService service;


    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@Valid @RequestBody PurchaseRequest request){
     service.createPurchase(request);

     return ResponseEntity.ok().body("Operation is being process");
    }


}
