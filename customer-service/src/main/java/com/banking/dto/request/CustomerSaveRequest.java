package com.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSaveRequest {

    @NotBlank(message = "Bos ola bilmez")
    private String name;
    @NotBlank(message = "Bos ola bilmez")
    private String surname;
    @NotNull(message = "Bos ola bilmez")
    private LocalDate birthDate;
    @NotBlank(message = "Bos ola bilmez")
    private String gsmNumber;

}
