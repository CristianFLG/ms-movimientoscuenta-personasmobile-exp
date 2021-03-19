package msmovimientoscuentapersonasmobileexp.service;

import msmovimientoscuentapersonasmobileexp.model.Person;
import msmovimientoscuentapersonasmobileexp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService{

    @Autowired
    private PersonRepository personRepository;


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPerson(long id) {
        return personRepository.findOne(id);
    }

    public String savePerson(Person person) {
        personRepository.save(person);
        return "Persona agregada con Id: "+person.getId();
    }

    public String deletePerson(long id) {
        personRepository.delete(id);
        return "Persona eliminada con Id: "+id;
    }
}

