package controllers;

import models.entities.Atividade;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AtividadeController {
    public static void criarAtividade(ArrayList<Atividade> atividades, Scanner scan, ArrayList<Usuario> usuarios){
        System.out.print("Informe a descrição da atividade: ");
        String description = scan.nextLine();
        System.out.print("Informe a data de início do projeto (YYYY-MM-DD): ");
        String date_begin_input = scan.nextLine();
        System.out.print("Informe a hora de início do projeto (HH:MM): ");
        String time_begin_input = scan.nextLine();
        String datetime_begin_input = date_begin_input + "T" + time_begin_input + ":00";
        LocalDateTime datetime_begin = LocalDateTime.parse(datetime_begin_input);
        System.out.print("Informe a data de término do projeto (YYYY-MM-DD): ");
        String date_end_input = scan.nextLine();
        System.out.print("Informe a hora de término do projeto (HH:MM): ");
        String time_end_input = scan.nextLine();
        String datetime_end_input = date_begin_input + "T" + time_begin_input + ":00";
        LocalDateTime datetime_end = LocalDateTime.parse(datetime_end_input);
        ArrayList<String> responsaveis = new ArrayList<String>(Arrays.asList("Professor", "Pesquisador"));
        UsuarioController.mostrarUsuariosTipo(usuarios, responsaveis);
        System.out.print("Informe o ID do usuário responsável pela atividade: ");
        int user_id = scan.nextInt();
        scan.nextLine();
        Usuario manager = UsuarioController.buscarUsuario(usuarios, user_id);
        Atividade atividade = new Atividade(description, datetime_begin, datetime_end, manager);
        ArrayList<String> alunos = new ArrayList<String>(Arrays.asList("Doutor", "Graduando", "Mestre", "Profissional", "Tecnico"));
        UsuarioController.mostrarUsuariosTipo(usuarios, alunos);
        System.out.print("Quantos usuários vão participar dessa atividade? ");
        int size = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < size; i++) {
            System.out.print("Informe o ID do usuário: ");
            int id = scan.nextInt();
            scan.nextLine();
            Usuario usuario = UsuarioController.buscarUsuario(usuarios, id);
            atividade.addUsuario(usuario);
        }

        ArrayList<Usuario> users_activity = atividade.getUsuarios();

        for (Usuario usuario : users_activity) {
            System.out.print("Qual tarefa " + usuario.getName() + " vai realizar? ");
            String task = scan.nextLine();
            atividade.addTarefa(task, usuario);
        }
        int atv_id = atividades.indexOf(atividade);
        atividade.setID(atv_id);
        System.out.println("Atividade criada com sucesso!");
    }

    public static void editarAtividade(ArrayList<Atividade> atividades, Scanner scan, ArrayList<Usuario> usuarios){
        AtividadeController.mostrarAtividades(atividades);
        System.out.print("Informe o ID da atividade na qual deseja editar as informações: ");
        int id = scan.nextInt();
        scan.nextLine();
        Atividade atividade = AtividadeController.buscarAtividade(atividades, id);
        System.out.println("Escolha uma opção:\n 1- Editar descrição\n 2- Editar data de início\n 3 - Editar data de término\n 4 - Editar responsável\n 5 - Editar usuários");
        int option_edit = scan.nextInt();
        scan.nextLine();
        if (option_edit == 1) {
            System.out.print("Digite a nova descrição: ");
            String new_description = scan.nextLine();
            atividade.setDescricao(new_description);
            System.out.println("Descrição atualizado com sucesso!");
        } else if (option_edit == 2) {
            System.out.print("Informe a nova data de início do projeto (YYYY-MM-DD): ");
            String date_begin_input = scan.nextLine();
            System.out.print("Informe a nova hora de início do projeto (HH:MM): ");
            String time_begin_input = scan.nextLine();
            String datetime_begin_input = date_begin_input + "T" + time_begin_input + ":00";
            LocalDateTime datetime_begin = LocalDateTime.parse(datetime_begin_input);
            atividade.setData_hora_comeco(datetime_begin);
            System.out.println("Data de início atualizada com sucesso!");
        } else if (option_edit == 3) {
            System.out.print("Informe a nova data de término do projeto (YYYY-MM-DD): ");
            String date_end_input = scan.nextLine();
            System.out.print("Informe a nova hora de término do projeto (HH:MM): ");
            String time_end_input = scan.nextLine();
            String datetime_end_input = date_end_input + "T" + time_end_input + ":00";
            LocalDateTime datetime_end = LocalDateTime.parse(datetime_end_input);
            atividade.setData_hora_fim(datetime_end);
            System.out.println("Data de término atualizada com sucesso!");
        } else if (option_edit == 4) {
            ArrayList<String> responsaveis = new ArrayList<String>(Arrays.asList("Professor", "Pesquisador"));
            UsuarioController.mostrarUsuariosTipo(usuarios, responsaveis);
            System.out.print("Digite o ID do novo responsável: ");
            int user_id = scan.nextInt();
            scan.nextLine();
            Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
            atividade.setResponsavel(user);
            System.out.println("Responsável atualizado com sucesso!");
        } else if (option_edit == 5) {
            atividade.editarUsuarios(usuarios);
        }
    }

    public static Atividade buscarAtividade(ArrayList<Atividade> atividades, int ID){
        for (Atividade atividade : atividades) {
            if (atividade.getID() == ID)
                return atividade;
        }

        return null;
    }

    public static void removerAtividade(ArrayList<Atividade> atividades, int ID){
        for (Atividade atividade : atividades) {
            if (atividade.getID() == ID)
                atividades.set(ID, null);
        }
    }

    public static void mostrarAtividade(Atividade atividade){
        System.out.println("ID: " + atividade.getID() + "\nDescrição: " + atividade.getDescricao() + "\nData de início: " + atividade.getData_hora_comeco() + "\nData de término: " + atividade.getData_hora_fim() + "\nResponsável: " + atividade.getResponsavel().getName());
        UsuarioController.mostrarUsuarios(atividade.getUsuarios());
    }

    public static void mostrarAtividades(ArrayList<Atividade> atividades){
        for (Atividade atividade : atividades) {
            mostrarAtividade(atividade);
        }
    }
}
