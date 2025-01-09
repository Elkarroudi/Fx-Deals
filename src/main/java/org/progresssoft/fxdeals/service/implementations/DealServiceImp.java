package org.progresssoft.fxdeals.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.progresssoft.fxdeals.dto.requests.DealRequestDTO;
import org.progresssoft.fxdeals.dto.responses.DealResponseDTO;
import org.progresssoft.fxdeals.entity.Deal;
import org.progresssoft.fxdeals.entity.mapper.DealMapper;
import org.progresssoft.fxdeals.repository.DealRepository;
import org.progresssoft.fxdeals.service.contracts.DealService;
import org.progresssoft.fxdeals.util.exception.DuplicateFxDealIdException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealServiceImp implements DealService {
    
    private final DealRepository repository;
    private final DealMapper mapper;

    @Override
    public DealResponseDTO create(DealRequestDTO dto) {
        log.info("Attempting to save Fx deal with ID: {}", dto.dealUniqueId());
        Deal fxDeal = mapper.toEntity(dto);

        if ( repository.existsByDealUniqueId( fxDeal.getDealUniqueId() ) ) {
            log.warn("Duplicate Fx deal ID detected: {}. Operation aborted.", dto.dealUniqueId());
            throw new DuplicateFxDealIdException("Fx Deal with unique id: " + fxDeal.getDealUniqueId() + " already exists");
        }

        Deal savedFxDeal = repository.save(fxDeal);
        log.info("Deal saved successfully with ID: {}", savedFxDeal.getId());

        return mapper.toResponseDTO(savedFxDeal);
    }

}
