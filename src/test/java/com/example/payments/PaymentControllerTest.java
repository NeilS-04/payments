package com.example.payments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    PaymentRepository repo;            // fake repository, no database needed

    @InjectMocks
    PaymentController controller;      // real controller, with the fake repo injected

    @Test
    void createSavesPaymentWithCreatedStatus() {
        // repo.save(...) returns whatever it is given
        when(repo.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

        Payment result = controller.create(new CreatePaymentRequest("M-1", new BigDecimal("10.00")));

        assertThat(result.getStatus()).isEqualTo("CREATED");
        assertThat(result.getMerchantRef()).isEqualTo("M-1");
        verify(repo).save(any(Payment.class));
    }

    @Test
    void getReturns404WhenPaymentMissing() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        var response = controller.get(99L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}