package com.intuit.demo.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionDTO {
    private String message;
    private String timestamp;
}
