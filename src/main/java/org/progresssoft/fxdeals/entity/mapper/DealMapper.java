package org.progresssoft.fxdeals.entity.mapper;

import org.mapstruct.Mapper;
import org.progresssoft.fxdeals.dto.requests.DealRequestDTO;
import org.progresssoft.fxdeals.dto.responses.DealResponseDTO;
import org.progresssoft.fxdeals.entity.Deal;

@Mapper(
        componentModel = "spring"
)
public interface DealMapper {

    Deal toEntity(DealRequestDTO req);
    DealResponseDTO toResponseDTO(Deal entity);

}
