package com.btg.pactual.pqrs.repository;

import com.btg.pactual.pqrs.customRepository.UserRepositoryCustom;
import com.btg.pactual.pqrs.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
}
