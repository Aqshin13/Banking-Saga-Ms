package com.banking.dto.response;

import java.math.BigDecimal;

public record BalanceResponse(Long id,String name, BigDecimal balance) {
}
