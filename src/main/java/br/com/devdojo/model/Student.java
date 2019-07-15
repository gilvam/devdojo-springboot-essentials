package br.com.devdojo.model;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class Student extends AbstractEntity {

    @NotEmpty(message = "Campo nome do estudante é obrigatório")
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotEmpty(message = "Digite um email válido")
    @Email
    private String email;

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Student(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

