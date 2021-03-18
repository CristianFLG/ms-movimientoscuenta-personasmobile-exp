package msmovimientoscuentapersonasmobileexp.repository;

import msmovimientoscuentapersonasmobileexp.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person,Long> {
}
