package com.btg.pactual.pqrs.repository;

import com.btg.pactual.pqrs.customRepository.PqrRepositoryCustom;
import com.btg.pactual.pqrs.model.Pqr;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PqrRepository extends MongoRepository<Pqr, String>, PqrRepositoryCustom {
}
