package msmovimientoscuentapersonasmobileexp.service;

import msmovimientoscuentapersonasmobileexp.dto.PersonDto;
import msmovimientoscuentapersonasmobileexp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonDto personDto;

    @Override
    public List<Person> findAll() {
        return (List<Person>) personDto.findAll();
    }

    @Override
    public Person findById(long id) {
        return personDto.findOne(id);
    }
}
