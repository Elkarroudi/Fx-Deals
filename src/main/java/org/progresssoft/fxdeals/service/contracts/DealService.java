package org.progresssoft.fxdeals.service.contracts;

import org.progresssoft.fxdeals.dto.requests.DealRequestDTO;
import org.progresssoft.fxdeals.dto.responses.DealResponseDTO;

public interface DealService {

    DealResponseDTO create(DealRequestDTO dto);

}
