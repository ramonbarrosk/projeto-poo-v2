package controllers;

import models.entities.Atividade;
import models.entities.Projeto;
import models.entities.Usuario;
import models.enums.TipoStatus;
import models.enums.TipoUsuario;
import views.Runner;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class ProjetoController {
    public static void criarProjeto(ArrayList<Projeto> projetos, Scanner scan, ArrayList<Usuario> usuarios, ArrayList<Atividade> atividades){
        System.out.print("Informe a descrição do projeto: ");
        String description = scan.nextLine();
        ValidacaoController.validaString(description);
        System.out.print("Informe a data de início do projeto (YYYY-MM-DD): ");
        String date_begin_input = scan.nextLine();
        ValidacaoController.validaString(date_begin_input);
        System.out.print("Informe a hora de início do projeto (HH:MM): ");
        String time_begin_input = scan.nextLine();
        ValidacaoController.validaString(time_begin_input);
        String datetime_begin_input = date_begin_input + "T" + time_begin_input + ":00";
        ValidacaoController.validaString(datetime_begin_input);
        LocalDateTime datetime_begin = LocalDateTime.parse(datetime_begin_input);
        System.out.print("Informe a data de término do projeto (YYYY-MM-DD): ");
        String date_end_input = scan.nextLine();
        ValidacaoController.validaString(date_end_input);
        System.out.print("Informe a hora de término do projeto (HH:MM): ");
        String time_end_input = scan.nextLine();
        ValidacaoController.validaString(time_end_input);
        String datetime_end_input = date_begin_input + "T" + time_begin_input + ":00";
        ValidacaoController.validaString(datetime_end_input);
        LocalDateTime datetime_end = LocalDateTime.parse(datetime_end_input);

        ArrayList<String> responsaveis = new ArrayList<String>(Arrays.asList("Professor", "Pesquisador"));
        UsuarioController.mostrarUsuariosTipo(usuarios, responsaveis);

        System.out.print("Informe o ID do coordenador do projeto: ");
        int id_cordinator = scan.nextInt();
        scan.nextLine();

        Usuario coordenador = UsuarioController.buscarUsuario(usuarios, id_cordinator);

        System.out.print("Informe o período da bolsa (quantidade de meses): ");
        int scholarship_period = scan.nextInt();
        scan.nextLine();

        Projeto projeto = new Projeto(description, datetime_begin, datetime_end, coordenador.getID(), scholarship_period);

        ArrayList<String> alunos = new ArrayList<String>(Arrays.asList("Doutor", "Graduando", "Mestre", "Profissional", "Tecnico"));
        UsuarioController.mostrarUsuariosTipo(usuarios, alunos);

        System.out.print("Quantos usuários vão participar desse projeto? ");
        int size = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < size; i++) {
            System.out.print("Informe o ID do usuário: ");
            int user_id = scan.nextInt();
            scan.nextLine();
            Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
            projeto.addUser(user);
        }

        AtividadeController.mostrarAtividades(atividades);

        System.out.print("Quantas atividades vão participar desse projeto? ");
        int size_activity = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < size_activity; i++) {
            System.out.print("Informe o ID da atividade: ");
            int activity_id = scan.nextInt();
            scan.nextLine();
            Atividade activity = AtividadeController.buscarAtividade(atividades, activity_id);
            projeto.addAtividade(activity);
        }

        for (Usuario user : projeto.getUsuarios()) {
            System.out.print("Qual valor da bolsa do usuário " + user.getName() + "? ");
            Double value = scan.nextDouble();
            scan.nextLine();
            projeto.addValorBolsa(value, user);
        }
        projeto.setStatus("Em processo de criação");
        projetos.add(projeto);
        int proj_id = projetos.indexOf(projeto);
        projeto.setID(proj_id);
        System.out.println("Projeto criado com sucesso!");

        Projeto project = new Projeto(description, datetime_begin, datetime_end, id_cordinator, scholarship_period);
        projetos.add(project);
    }

    public static Projeto buscarProjeto(ArrayList<Projeto> projetos, int ID){
        for (Projeto projeto : projetos) {
            if (projeto.getID() == ID)
                return projeto;
        }

        return null;
    }

    public static void editarProjeto(ArrayList<Usuario> usuarios, Projeto projeto, int opcao, Scanner scan, String usuario_corrente, ArrayList<Atividade> atividades){
        if (opcao == 1) {
            System.out.print("Digite a nova descrição: ");
            String new_description = scan.nextLine();
            ValidacaoController.validaString(new_description);
            projeto.setDescricao(new_description);
            System.out.println("Descrição atualizado com sucesso!");
        } else if (opcao == 2) {
            System.out.print("Informe a nova data de início do projeto (YYYY-MM-DD): ");
            String date_begin_input = scan.nextLine();
            ValidacaoController.validaString(date_begin_input);
            System.out.print("Informe a nova hora de início do projeto (HH:MM): ");
            String time_begin_input = scan.nextLine();
            ValidacaoController.validaString(time_begin_input);
            String datetime_begin_input = date_begin_input + "T" + time_begin_input + ":00";
            ValidacaoController.validaString(datetime_begin_input);
            LocalDateTime datetime_begin = LocalDateTime.parse(datetime_begin_input);
            projeto.setData_hora_comeco(datetime_begin);
            System.out.println("Data de início atualizada com sucesso!");
        } else if (opcao == 3) {
            System.out.print("Informe a nova data de término do projeto (YYYY-MM-DD): ");
            String date_end_input = scan.nextLine();
            System.out.print("Informe a nova hora de término do projeto (HH:MM): ");
            String time_end_input = scan.nextLine();
            ValidacaoController.validaString(time_end_input);
            String datetime_end_input = date_end_input + "T" + time_end_input + ":00";
            ValidacaoController.validaString(datetime_end_input);
            LocalDateTime datetime_end = LocalDateTime.parse(datetime_end_input);
            projeto.setData_hora_fim(datetime_end);
            System.out.println("Data de término atualizada com sucesso!");
        } else if (opcao == 4) {
            ArrayList<String> responsaveis = new ArrayList<String>(Arrays.asList("Professor", "Pesquisador"));
            UsuarioController.mostrarUsuariosTipo(usuarios, responsaveis);
            System.out.print("Digite o ID do novo coordenador: ");
            int user_id = scan.nextInt();
            Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
            projeto.setCordinator_id(user.getID());
            System.out.println("Coordenador atualizado com sucesso!");
        } else if (opcao == 5) {

            Usuario usuario_logado = UsuarioController.buscarUsuarioUsername(usuarios, usuario_corrente);
            ArrayList<String> responsaveis = new ArrayList<String>(Arrays.asList("Professor", "Pesquisador"));

            if (responsaveis.contains(usuario_logado.getType())) {
                System.out.println("1- Em processo de criação\n2- Iniciado\n3- Em andamento\n4- Concluído");
                System.out.print("Para qual status deseja atualizar: ");
                int opcao_status = scan.nextInt();
                scan.nextLine();
                String new_status = "";
                switch (opcao_status){
                    case 1:
                        new_status = TipoStatus.CRIACAO.getTipo();
                        break;
                    case 2:
                        new_status = TipoStatus.INICIADO.getTipo();
                        break;
                    case 3:
                        new_status = TipoStatus.ANDAMENTO.getTipo();
                        break;
                    case 4:
                        new_status = TipoStatus.CONCLUÍDO.getTipo();
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");
                        break;

                }

                ValidacaoController.validaString(new_status);
                projeto.setStatus(new_status);
                System.out.println("Status do projeto atualizado com sucesso!");
            } else {
                System.out.println("Apenas coordenador pode atualizar o status do projeto!");
            }
        } else if (opcao == 6) {
            System.out.print("Digite o novo período da bolsa (quantidade em meses): ");
            int new_period = scan.nextInt();
            scan.nextLine();
            projeto.setPeriodo_bolsa(new_period);
            System.out.println("Período da bolsa atualizado com sucesso!");
        } else if (opcao == 7) {
            projeto.editarUsuarios(usuarios);
        } else if (opcao == 8) {
            ArrayList<String> alunos = new ArrayList<String>(Arrays.asList("Doutor", "Graduando", "Mestre", "Profissional", "Tecnico"));
            UsuarioController.mostrarUsuariosTipo(usuarios, alunos);
            System.out.print("Digite o ID do usuário: ");
            int user_id = scan.nextInt();
            Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
            System.out.println("Digite o novo valor da bolsa: ");
            Double new_value = scan.nextDouble();
            projeto.setValores_bolsa(new_value, user);

        } else if (opcao == 9) {
            projeto.editarAtividades(atividades);
        }
    }

    public static void removerProjeto(ArrayList<Projeto> projetos, int ID){
        for (Projeto projeto : projetos) {
            if (projeto.getID() == ID)
                projetos.set(ID, null);
        }
    }

    public static void mostrarProjeto(Projeto projeto, Usuario manager){
        System.out.println("ID: " + projeto.getID() + "\nDescrição: " + projeto.getDescricao() + "\nStatus: " + projeto.getStatus() + "\nData de início: " + projeto.getData_hora_comeco() + "\nData de término: " + projeto.getData_hora_fim() + "\nPeríodo da bolsa: " + projeto.getPeriodo_bolsa() + "\nCoordenador: " + manager.getName());
        UsuarioController.mostrarUsuarios(projeto.getUsuarios());
        AtividadeController.mostrarAtividades(projeto.getAtividades());
    }

    public static void mostrarProjetos(ArrayList<Projeto> projetos, ArrayList<Usuario> usuarios){
        for (Projeto projeto : projetos) {
            Usuario manager = UsuarioController.buscarUsuario(usuarios, projeto.getCordinator_id());
            mostrarProjeto(projeto, manager);
        }
    }


}
