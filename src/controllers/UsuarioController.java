package controllers;

import models.entities.*;

import java.util.ArrayList;

public class UsuarioController {
    public static Usuario criarUsuario(ArrayList<Usuario> usuarios, String type, String name, String username, int password){
        Usuario usuario = UsuarioFactory.getUsuario(type, name, username, password);
        usuarios.add(usuario);
        int usuario_id = usuarios.indexOf(usuario);
        usuario.setID(usuario_id);
        System.out.println("Usuário criado com sucesso!");
        return usuario;
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

    public static void mostrarUsuariosTipo(ArrayList<Usuario> usuarios, ArrayList<String> tipos){
        for (Usuario usuario : usuarios){
            if (tipos.contains(usuario.getType())){
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
