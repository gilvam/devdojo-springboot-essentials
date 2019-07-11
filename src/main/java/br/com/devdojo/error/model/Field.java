package br.com.devdojo.error.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {
    private String name;
    private String objectName;
    private List<String> messages = new ArrayList<>();

    public Field(String name, String objectName, String messages) {
        this.name = name;
        this.objectName = objectName;
        this.addMessage(messages);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages); // nao deixar modificar a lista por esse método
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
