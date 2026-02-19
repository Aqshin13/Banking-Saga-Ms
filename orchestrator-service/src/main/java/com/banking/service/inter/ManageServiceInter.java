package com.banking.service.inter;

import com.banking.dto.event.Event;

public interface ManageServiceInter {

    void handleTopUpCreate(Event event);
    void handleTransferEnd(Long id);
    void handleTransferCancel(Long id);
    void handlePurchaseCreate(Event event);
    void handlePurchaseEnd(Long event);
    void handlePurchaseFailed(Long id);

}
