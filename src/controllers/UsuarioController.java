package controllers;

import models.entities.*;

import java.util.ArrayList;

public class UsuarioController {
    public static void criarUsuario(ArrayList<Usuario> usuarios, String type, String name, String username, int password){
        switch (type) {
            case "Doutor":
                Usuario doutor = new Doutor(type, name, username, password);
                usuarios.add(doutor);
                break;
            case "Graduando":
                Usuario graduando = new Graduando(type, name, username, password);
                usuarios.add(graduando);
                break;
            case "Mestre":
                Usuario mestre = new Mestre(type, name, username, password);
                usuarios.add(mestre);
                break;
            case "Pesquisador":
                Usuario pesquisador = new Pesquisador(type, name, username, password);
                usuarios.add(pesquisador);
                break;
            case "Professor":
                Usuario professor = new Professor(type, name, username, password);
                usuarios.add(professor);
                break;
            case "Profissional":
                Usuario profissional = new Profissional(type, name, username, password);
                usuarios.add(profissional);
                break;
            case "Tecnico":
                Usuario tecnico = new Tecnico(type, name, username, password);
                usuarios.add(tecnico);
                break;
        }
        System.out.println("Usuário criado com sucesso!");
    }
    public static Usuario buscarUsuario(ArrayList<Usuario> usuarios, int ID){
        for (Usuario usuario : usuarios) {
            if (usuario.getID() == ID)
                return usuario;
        }

        return null;
    }
    public static void removerUsuario(ArrayList<Usuario> usuarios, int ID){
        for (Usuario usuario : usuarios) {
            if (usuario.getID() == ID)
                usuarios.set(ID, null);
        }
    }

    public static void mostrarUsuario(Usuario usuario){
        System.out.println("ID: " + usuario.getID() + "\nNome: " + usuario.getName() + "\nTipo: " + usuario.getType());
    }

    public static void mostrarUsuarios(ArrayList<Usuario> usuarios){
        for (Usuario usuario : usuarios){
            mostrarUsuario(usuario);
        }
    }

    public static void mostrarUsuariosTipo(ArrayList<Usuario> usuarios, String tipo){
        for (Usuario usuario : usuarios){
            if (usuario.getType() == tipo){
                mostrarUsuario(usuario);
            }
        }
    }

    public static Usuario buscarUsuarioUsername(ArrayList<Usuario> usuarios, String username){
        for (Usuario usuario : usuarios){
            if (usuario.getUsername() == username){
                return usuario;
            }
        }
        return null;
    }
}
