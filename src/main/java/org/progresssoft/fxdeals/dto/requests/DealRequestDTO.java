package org.progresssoft.fxdeals.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.progresssoft.fxdeals.dto.group.OnCreate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record DealRequestDTO(

        @NotBlank(
                groups = OnCreate.class,
                message = "Deal Unique ID is required"
        )
        String dealUniqueId,

        @NotNull(
                groups = OnCreate.class,
                message = "From Currency is required"
        )
        Currency fromCurrency,

        @NotNull(
                groups = OnCreate.class,
                message = "To Currency is required"
        )
        Currency toCurrency,

        @NotNull(
                groups = OnCreate.class,
                message = "Deal Timestamp is required"
        )
        LocalDateTime dealTimestamp,

        @NotNull(
                groups = OnCreate.class,
                message = "Deal Amount is required"
        )
        @Positive(
                groups = OnCreate.class,
                message = "Deal Amount must be positive"
        )
        BigDecimal dealAmount

) {
}
