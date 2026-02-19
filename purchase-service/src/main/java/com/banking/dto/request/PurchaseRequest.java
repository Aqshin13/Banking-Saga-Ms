package com.banking.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PurchaseRequest(@PositiveOrZero(message = "menfi ola bilmez")
                              @NotNull(message = "Bos ola bilmez")
                              BigDecimal amount,
                              @NotNull(message = "Bos ola bilmez")
                              Long sender,
                              @NotNull(message = "Bos ola bilmez")
                              Long receiver) {
}
