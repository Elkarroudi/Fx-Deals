package org.progresssoft.fxdeals.util.api.success;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public record SuccessDTO<RES>(

        LocalDateTime timestamp,
        boolean success,
        String message,
        RES data

) implements Serializable {

    public SuccessDTO(int httpStatus, String message, RES data) {
        this(
                LocalDateTime.now(),
                true,
                message,
                data
        );
    }

}
