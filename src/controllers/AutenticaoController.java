package controllers;

import models.entities.Usuario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AutenticaoController {
    public static Boolean loginUsuario(ArrayList<Usuario> usuarios, String username, int password){
        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(username) && usuario.getPassword() == password){
                return true;
            }

        }
        return false;
    }

    public static Boolean recuperarSenha(ArrayList<Usuario> usuarios, String username){
        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(username)) {
                try {
                    System.out.print("Digite sua nova senha: ");
                    Scanner scan = new Scanner(System.in);
                    int nova_senha = scan.nextInt();
                    ValidacaoController.validaString(Integer.toString(nova_senha));
                    Usuario usuario_encontrado = UsuarioController.buscarUsuario(usuarios, usuario.getID());
                    usuario_encontrado.setPassword(nova_senha);
                    return true;
                } catch (IllegalArgumentException e){
                    return false;
                }
                catch (InputMismatchException e){
                    return false;
                }
            }
        }
        return false;
    }
}
