package models.entities;

public abstract class Usuario {
    public int ID;
    public String type;
    public String name;
    public String username;
    public int password;

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

    public void recebeNotificao(String status){
        System.out.println("Usuario: " + this.name + " Tipo: " + this.type + " recebeu a notificação da alteração do status para " + status);
    }

}
