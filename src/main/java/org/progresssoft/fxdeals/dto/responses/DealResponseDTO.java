package org.progresssoft.fxdeals.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record DealResponseDTO(

        String id,
        String dealUniqueId,
        Currency fromCurrency,
        Currency toCurrency,
        LocalDateTime dealTimestamp,
        BigDecimal dealAmount

) {
}
