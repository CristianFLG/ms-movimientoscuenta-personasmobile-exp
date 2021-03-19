package msmovimientoscuentapersonasmobileexp.controller;

import msmovimientoscuentapersonasmobileexp.model.Person;
import msmovimientoscuentapersonasmobileexp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/allpersons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("person/{id}")
    public Person getPerson(@PathVariable String id) {
        return personService.getPerson(id);
    }

    @PostMapping("/addperson")
    public String savePerson(@RequestBody Person person) {
        personService.savePerson(person);
        return "Persona agregada con Id: "+person.getId();
    }

    @DeleteMapping("person/{id}/delete")
    public String deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
        return "Persona eliminada con Id: "+id;
    }

}
