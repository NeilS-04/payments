package com.example.payments;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreatePaymentRequest(
        @NotBlank String merchantRef,
        @NotNull @Positive BigDecimal amount) { }