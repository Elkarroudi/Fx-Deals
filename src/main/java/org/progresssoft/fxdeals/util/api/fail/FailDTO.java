package org.progresssoft.fxdeals.util.api.fail;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public record FailDTO(

        LocalDateTime timestamp,
        boolean success,
        FailType type,
        String message,
        Map<String, Object> errors

)  implements Serializable {

    public FailDTO(int httpStatus, FailType type, Map<String, Object> errors) {
        this( LocalDateTime.now(),  false, type, null, errors );
    }

    public FailDTO(int httpStatus, FailType type, String message) {
        this( LocalDateTime.now(),  false, type, message, null );
    }

}
