package org.progresssoft.fxdeals.repository;

import org.progresssoft.fxdeals.entity.Deal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DealRepository extends MongoRepository<Deal, String> {

    boolean existsByDealUniqueId(String dealUniqueId);

}
