package models.entities;


public class Professor extends Usuario {
    public Professor(String type, String name, String username, int password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
