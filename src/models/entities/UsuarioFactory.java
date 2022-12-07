package models.entities;

public class UsuarioFactory {
    public static Usuario getUsuario(String tipo, String name, String username, int password){
        switch (tipo){
            case "Doutor":
                return new Doutor(tipo, name, username, password);
            case "Graduando":
                return new Graduando(tipo, name, username, password);
            case "Mestre":
                return new Mestre(tipo, name, username, password);
            case "Pesquisador":
                return new Pesquisador(tipo, name, username, password);
            case "Professor":
                return new Professor(tipo, name, username, password);
            case "Profissional":
                return new Profissional(tipo, name, username, password);
            case "Tecnico":
                return new Tecnico(tipo, name, username, password);
            default:
                System.out.println("Tipo de usuário inválido!");
        }
        return null;
    }
}
