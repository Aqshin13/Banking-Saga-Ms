package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gsmNumber;
    private BigDecimal balance;
    @Version
    private Long version;


}
