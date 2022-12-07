package models.entities;

public class Profissional extends Usuario {

    public Profissional(String type, String name, String username, int password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
