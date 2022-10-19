package models.entities;
import models.interfaces.User;

public class Usuario implements User{
    private int ID;
    String type;
    private String name;
    private String username;
    private int password;

    public Usuario(int ID, String type, String name, String username, int password) {
        this.ID = ID;
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
