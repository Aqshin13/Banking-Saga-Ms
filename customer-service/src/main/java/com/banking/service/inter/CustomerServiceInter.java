package com.banking.service.inter;

import com.banking.dto.event.Event;
import com.banking.dto.request.CustomerSaveRequest;
import com.banking.dto.response.CustomerResponse;
import com.banking.entity.Customer;

public interface CustomerServiceInter {


    CustomerResponse save(CustomerSaveRequest request);

     void  processTransfer(Event event);

    void processPurchase(Event event);
}
