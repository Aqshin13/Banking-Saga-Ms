package com.banking.dto.error;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;
    private int status;
    private String path;
    private long time;
    private Map<String,String> validationError;



}
