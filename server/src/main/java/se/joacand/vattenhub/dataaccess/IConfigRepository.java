package se.joacand.vattenhub.dataaccess;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.joacand.vattenhub.domain.Config;


public interface IConfigRepository extends MongoRepository<Config, String> {
	public Optional<Config> findById(String id);
}
