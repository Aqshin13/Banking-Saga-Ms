package com.banking.controller;


import com.banking.dto.request.TransferRequest;
import com.banking.service.impl.TransferServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class MoneyTransferController {


    private final TransferServiceImpl service;

    @PostMapping
    public ResponseEntity<?> sendMoney(@RequestBody @Valid TransferRequest request){

     service.createTopUpRequest(request);


     return ResponseEntity.ok().body("Transfer is being processing");
    }





}
