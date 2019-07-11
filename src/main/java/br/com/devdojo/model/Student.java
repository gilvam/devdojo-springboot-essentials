package br.com.devdojo.model;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class Student extends AbstractEntity {

    @NotEmpty(message = "Campon nome do estudante é obrigatório")
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotEmpty
    @Email
    private String email;

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

