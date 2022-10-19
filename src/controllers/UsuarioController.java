package controllers;

import models.entities.*;

import java.util.ArrayList;

public class UsuarioController {
    public static Usuario criarUsuario(ArrayList<Usuario> usuarios, String type, String name, String username, int password){
        switch (type) {
            case "DOUTOR":
                Usuario doutor = new Doutor(type.toUpperCase(), name, username, password);
                usuarios.add(doutor);
                System.out.println("Usuário criado com sucesso!");
                return doutor;
            case "GRADUANDO":
                Usuario graduando = new Graduando(type.toUpperCase(), name, username, password);
                usuarios.add(graduando);
                System.out.println("Usuário criado com sucesso!");
                return graduando;
            case "MESTRE":
                Usuario mestre = new Mestre(type.toUpperCase(), name, username, password);
                usuarios.add(mestre);
                System.out.println("Usuário criado com sucesso!");
                return mestre;
            case "PESQUISADOR":
                Usuario pesquisador = new Pesquisador(type.toUpperCase(), name, username, password);
                usuarios.add(pesquisador);
                System.out.println("Usuário criado com sucesso!");
                return pesquisador;
            case "PROFESSOR":
                Usuario professor = new Professor(type.toUpperCase(), name, username, password);
                usuarios.add(professor);
                System.out.println("Usuário criado com sucesso!");
                return professor;
            case "PROFISSIONAL":
                Usuario profissional = new Profissional(type.toUpperCase(), name, username, password);
                usuarios.add(profissional);
                System.out.println("Usuário criado com sucesso!");
                return profissional;
            case "TECNICO":
                Usuario tecnico = new Tecnico(type.toUpperCase(), name, username, password);
                usuarios.add(tecnico);
                System.out.println("Usuário criado com sucesso!");
                return tecnico;
            default:
                System.out.println("Tipo de usuário inválido!");
                return null;

        }

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
