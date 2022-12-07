package controllers;

import models.entities.*;
import models.enums.TipoUsuario;
import views.Runner;

import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioController {
    public static void criarUsuario(ArrayList<Usuario> usuarios, Scanner scan) {
        System.out.print("Informe o nome do usuário: ");
        String name = scan.nextLine();
        System.out.println("1- DOUTOR\n2- GRADUANDO\n3- MESTRE\n4- PESQUISADOR\n5- PROFESSOR\n6- PROFISSIONAL\n7- TECNICO");
        System.out.print("Informe o tipo do usuário: ");
        int type = scan.nextInt();
        String tipo_usuario = "";
        scan.nextLine();
        switch (type){
            case 1:
                tipo_usuario = TipoUsuario.DOUTOR.getTipo();
                break;
            case 2:
                tipo_usuario = TipoUsuario.GRADUANDO.getTipo();
                break;
            case 3:
                tipo_usuario = TipoUsuario.MESTRE.getTipo();
                break;
            case 4:
                tipo_usuario = TipoUsuario.PESQUISADOR.getTipo();
                break;
            case 5:
                tipo_usuario = TipoUsuario.PROFESSOR.getTipo();
                break;
            case 6:
                tipo_usuario = TipoUsuario.PROFISSIONAL.getTipo();
                break;
            case 7:
                tipo_usuario = TipoUsuario.TECNICO.getTipo();
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
                Runner runner = new Runner();
                break;

        }
        System.out.print("Informe o username do usuário: ");
        String username = scan.nextLine();
        System.out.print("Informe a senha do usuário: ");
        int password = scan.nextInt();
        scan.nextLine();

        Usuario usuario = UsuarioFactory.getUsuario(tipo_usuario, name, username, password);
        usuarios.add(usuario);
        int usuario_id = usuarios.indexOf(usuario);
        usuario.setID(usuario_id);
        System.out.println("Usuário criado com sucesso!");
    }

    public static void editarUsuario(ArrayList<Usuario> usuarios, Scanner scan){
        mostrarUsuarios(usuarios);
        System.out.print("Informe o ID do usuário o qual deseja editar as informações: ");
        int id = scan.nextInt();
        scan.nextLine();
        Usuario user = UsuarioController.buscarUsuario(usuarios, id);
        System.out.println("Escolha uma opção:\n 1- Editar nome\n 2- Editar tipo");
        int option_edit = scan.nextInt();
        scan.nextLine();
        if (option_edit == 1) {
            System.out.print("Digite o novo nome: ");
            String new_name = scan.nextLine();
            user.setName(new_name);
            System.out.println("Nome atualizado com sucesso!");
        } else if (option_edit == 2) {
            System.out.print("Digite o novo tipo: ");
            String new_type = scan.nextLine();
            user.setType(new_type);
            System.out.println("Tipo atualizado com sucesso!");
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

    public static void mostrarUsuariosTipo(ArrayList<Usuario> usuarios, ArrayList<String> tipos){
        for (Usuario usuario : usuarios){
            if (tipos.contains(usuario.getType())){
                mostrarUsuario(usuario);
            }
        }
    }

    public static Usuario buscarUsuarioUsername(ArrayList<Usuario> usuarios, String username){
        for (Usuario usuario : usuarios){
            if (usuario.getUsername().equals(username)){
                return usuario;
            }
        }

        return null;
    }
}
