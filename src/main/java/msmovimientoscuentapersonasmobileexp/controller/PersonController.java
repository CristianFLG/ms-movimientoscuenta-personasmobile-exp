package msmovimientoscuentapersonasmobileexp.controller;

import msmovimientoscuentapersonasmobileexp.model.Person;
import msmovimientoscuentapersonasmobileexp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/allpersons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("person/{id}")
    public Person getPerson(long id) {
        return personService.getPerson(id);
    }

    @GetMapping("/addperson")
    public String savePerson(Person person) {
        personService.savePerson(person);
        return "Persona agregada con Id: "+person.getId();
    }

    @GetMapping("person/{id}/delet")
    public String deletePerson(long id) {
        personService.deletePerson(id);
        return "Persona eliminada con Id: "+id;
    }

}
