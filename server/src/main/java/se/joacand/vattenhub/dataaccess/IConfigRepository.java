package se.joacand.vattenhub.dataaccess;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.joacand.vattenhub.domain.Config;

import java.util.Optional;

public interface IConfigRepository extends MongoRepository<Config, String> {
    Optional<Config> findById(String id);
}
