package models.entities;

public class Tecnico extends Usuario {

    public Tecnico(String type, String name, String username, int password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
