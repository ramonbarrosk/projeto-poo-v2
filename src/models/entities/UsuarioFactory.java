package models.entities;

public class UsuarioFactory {
    public static Usuario getUsuario(String tipo, String name, String username, int password){
        switch (tipo){
            case "DOUTOR":
                return new Doutor(tipo, name, username, password);
            case "GRADUANDO":
                return new Graduando(tipo, name, username, password);
            case "MESTRE":
                return new Mestre(tipo, name, username, password);
            case "PESQUISADOR":
                return new Pesquisador(tipo, name, username, password);
            case "PROFESSOR":
                return new Professor(tipo, name, username, password);
            case "PROFISSIONAL":
                return new Profissional(tipo, name, username, password);
            case "TECNICO":
                return new Tecnico(tipo, name, username, password);
        }
        return null;
    }
}
