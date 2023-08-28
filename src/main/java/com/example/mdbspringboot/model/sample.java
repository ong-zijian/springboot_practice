package com.example.mdbspringboot.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//collection name
@Document(collection = "sampleCollection")
public class sample {

    @Id
    private String id;

    private String name;
    private int age;
    private String email;


    // Getter methods
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }

    // Setter method
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public sample(String id, String name, int age, String email) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}