package msmovimientoscuentapersonasmobileexp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@ToString
@Document(collection = "person")
public class Person {

    @Id
    private String id;
    private String name;
    private String city;
    private String mail;
    private int age;

    public Person(){}

    public Person(String id, String name, String city, String mail, int age) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.mail = mail;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
