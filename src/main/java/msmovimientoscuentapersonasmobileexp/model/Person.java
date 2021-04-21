package msmovimientoscuentapersonasmobileexp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Document(collection = "person")
@Builder
public class Person {

    @Id
    private String id;
    private String name;
    private String city;
    private String mail;
    private int age;
    private LocalDateTime fecha;

    public Person(){}

    public Person(String id, String name, String city, String mail, int age, LocalDateTime fecha) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.mail = mail;
        this.age = age;
        this.fecha = fecha;
    }
}
