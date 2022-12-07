package models.entities;

public class Mestre extends Usuario {

    public Mestre(String type, String name, String username, int password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
