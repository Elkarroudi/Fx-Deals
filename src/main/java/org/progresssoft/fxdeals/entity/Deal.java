package org.progresssoft.fxdeals.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deals")
public class Deal {

    @Id
    private String id;

    private String dealUniqueId;
    private Currency fromCurrency;
    private Currency toCurrency;
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;

    @CreatedDate
    private LocalDateTime createdAt;

}
