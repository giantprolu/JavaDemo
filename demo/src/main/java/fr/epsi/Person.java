package fr.epsi;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable{
    
    @Id
    private Integer id;
    private String name;
    private String prenom;
    private int age;

    public Person() {
    }

    public Person(String name, String prenom, int age) {
        this.name = name;
        this.prenom = prenom;
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPrenom() {
        return prenom;
    }
}
