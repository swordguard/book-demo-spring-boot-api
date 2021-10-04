package com.example.demo.exceptions;

import lombok.Data;

@Data
public class ViolationErrors {
    private final String fieldName;
    private final String message;
}
