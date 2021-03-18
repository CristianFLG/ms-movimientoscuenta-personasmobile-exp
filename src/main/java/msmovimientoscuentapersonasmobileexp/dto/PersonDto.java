package msmovimientoscuentapersonasmobileexp.dto;

import msmovimientoscuentapersonasmobileexp.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDto extends CrudRepository<Person,Long> {
}
