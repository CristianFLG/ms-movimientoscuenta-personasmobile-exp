package msmovimientoscuentapersonasmobileexp.controller;

import msmovimientoscuentapersonasmobileexp.model.Person;
import msmovimientoscuentapersonasmobileexp.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping("/listar")
    public List<Person> listarTodos(){
        return personService.findAll();
    }

    @GetMapping("buscar/{id}")
    public Person buscarId(@PathVariable long id){
        return personService.findById(id);
    }
}
