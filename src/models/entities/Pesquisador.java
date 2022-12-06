package models.entities;

public class Pesquisador extends Usuario {
    public Pesquisador(String type, String name, String username, int password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
