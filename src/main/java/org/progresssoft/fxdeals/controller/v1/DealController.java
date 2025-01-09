package org.progresssoft.fxdeals.controller.v1;

import lombok.RequiredArgsConstructor;
import org.progresssoft.fxdeals.dto.group.OnCreate;
import org.progresssoft.fxdeals.dto.requests.DealRequestDTO;
import org.progresssoft.fxdeals.dto.responses.DealResponseDTO;
import org.progresssoft.fxdeals.service.contracts.DealService;
import org.progresssoft.fxdeals.util.api.success.SuccessDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deals")
public class DealController {

    private final DealService service;

    @PostMapping
    public ResponseEntity<SuccessDTO<DealResponseDTO>> save(@RequestBody @Validated(OnCreate.class) DealRequestDTO dto) {
        DealResponseDTO createdDeal = service.create(dto);

        return new ResponseEntity<>(
                new SuccessDTO<>(
                        HttpStatus.CREATED.value(),
                        "Fx Deal created successfully",
                        createdDeal
                ),
                HttpStatus.CREATED
        );
    }

}
