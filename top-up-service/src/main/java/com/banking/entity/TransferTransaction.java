package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransferTransaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(nullable = false)
    private BigDecimal amount;
    private Long sender;
    private Long receiver;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    public enum TransactionType {
        TOP_UP
    }

    public enum TransactionStatus {
        FAILED, SUCCESS, PROCESSING
    }

}
