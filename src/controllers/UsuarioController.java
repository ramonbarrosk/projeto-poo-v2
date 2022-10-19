package controllers;

import models.entities.*;

import java.util.ArrayList;

public class UsuarioController {
    public static void criarUsuario(ArrayList<Usuario> usuarios, String type, String name, String username, int password){
        switch (type) {
            case "DOUTOR":
                Usuario doutor = new Doutor(type.toUpperCase(), name, username, password);
                usuarios.add(doutor);
                break;
            case "GRADUANDO":
                Usuario graduando = new Graduando(type.toUpperCase(), name, username, password);
                usuarios.add(graduando);
                break;
            case "MESTRE":
                Usuario mestre = new Mestre(type.toUpperCase(), name, username, password);
                usuarios.add(mestre);
                break;
            case "PESQUISADOR":
                Usuario pesquisador = new Pesquisador(type.toUpperCase(), name, username, password);
                usuarios.add(pesquisador);
                break;
            case "PROFESSOR":
                Usuario professor = new Professor(type.toUpperCase(), name, username, password);
                usuarios.add(professor);
                break;
            case "PROFISSIONAL":
                Usuario profissional = new Profissional(type.toUpperCase(), name, username, password);
                usuarios.add(profissional);
                break;
            case "TECNICO":
                Usuario tecnico = new Tecnico(type.toUpperCase(), name, username, password);
                usuarios.add(tecnico);
                break;
            default:
                System.out.println("Tipo de usuário inválido!");

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
