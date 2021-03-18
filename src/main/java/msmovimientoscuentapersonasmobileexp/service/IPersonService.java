package msmovimientoscuentapersonasmobileexp.service;

import msmovimientoscuentapersonasmobileexp.model.Person;

import java.util.List;

public interface IPersonService {

    public List<Person> findAll();

    public Person findById(long id);
}
