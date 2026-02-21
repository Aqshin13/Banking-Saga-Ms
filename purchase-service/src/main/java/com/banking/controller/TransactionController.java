package com.banking.controller;


import com.banking.dto.request.PurchaseRequest;
import com.banking.dto.response.PurchaseResponse;
import com.banking.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {



    private final TransactionService service;


    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@Valid @RequestBody PurchaseRequest request){
     PurchaseResponse  response= service.createPurchase(request);

     return ResponseEntity.ok().body(response);
    }


    @PostMapping("/purchase/{id}/refund")
    public ResponseEntity<?> refund(@PathVariable Long id){
        service.createRefund(id);

        return ResponseEntity.ok().body("Operation is being process");
    }


}
