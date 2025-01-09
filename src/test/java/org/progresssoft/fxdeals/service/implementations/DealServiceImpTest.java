package org.progresssoft.fxdeals.service.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.progresssoft.fxdeals.dto.requests.DealRequestDTO;
import org.progresssoft.fxdeals.dto.responses.DealResponseDTO;
import org.progresssoft.fxdeals.entity.Deal;
import org.progresssoft.fxdeals.entity.mapper.DealMapper;
import org.progresssoft.fxdeals.repository.DealRepository;
import org.progresssoft.fxdeals.service.contracts.DealService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class DealServiceImpTest {

    @Mock
    private DealRepository repository;

    @Mock private DealMapper mapper;

    private DealService service;
    private DealRequestDTO requestDTO;
    private Deal deal;

    @BeforeEach
    void setUp() {
        String id = UUID.randomUUID().toString();
        service = new DealServiceImp(repository, mapper);

        requestDTO = new DealRequestDTO(
                "deal-1", Currency.getInstance("USD"), Currency.getInstance("MAD"), LocalDateTime.now(), BigDecimal.valueOf(1548.5)
        );

        deal = Deal.builder()
                .dealUniqueId(requestDTO.dealUniqueId())
                .fromCurrency(requestDTO.fromCurrency())
                .toCurrency(requestDTO.toCurrency())
                .dealTimestamp(requestDTO.dealTimestamp())
                .dealAmount(requestDTO.dealAmount())
                .build();
    }

    @Test
    void givenValidFxDealRequestDTO_whenCreate_thenFxDealIsCreated() {
        given(mapper.toEntity(requestDTO)).willReturn(deal);
        given(repository.existsByDealUniqueId(requestDTO.dealUniqueId())).willReturn(false);
        given(repository.save(deal)).willReturn(deal);
        given(mapper.toResponseDTO(deal)).willReturn(
                new DealResponseDTO(
                        deal.getId(), deal.getDealUniqueId(), deal.getFromCurrency(), deal.getToCurrency(), deal.getDealTimestamp(), deal.getDealAmount()
                )
        );

        DealResponseDTO responseDTO = service.create(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.id()).isEqualTo(deal.getId());
        verify(repository).save(any(Deal.class));
    }

    @Test
    void givenNonValidFxDealRequestDTO_WhenCreate_thenAnExceptionIsThrown() {
        given(mapper.toEntity(requestDTO)).willReturn(deal);
        given(repository.existsByDealUniqueId(requestDTO.dealUniqueId())).willReturn(true);

        assertThrows(RuntimeException.class, () -> service.create(requestDTO));
        verify(repository).existsByDealUniqueId(requestDTO.dealUniqueId());
    }

}