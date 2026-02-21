package com.banking.dto.response;

import com.banking.entity.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private Long id;
    private Transaction.TransactionType type;
    private BigDecimal amount;
    private Long sender;
    private Long receiver;
    private Transaction.TransactionStatus status;
}
