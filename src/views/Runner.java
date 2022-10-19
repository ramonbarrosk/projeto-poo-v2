package views;

import controllers.AtividadeController;
import controllers.AutenticaoController;
import controllers.ProjetoController;
import controllers.UsuarioController;
import models.entities.Atividade;
import models.entities.Projeto;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public Runner(){
        this.run();
    }

    private void run(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        ArrayList<Projeto> projetos = new ArrayList<Projeto>();
        cadastro();
        int opcao;
        Scanner scan = new Scanner(System.in);

        int opcao_menu = 0;
        Boolean status = false;
        String usuario_corrente = "";
        String tipo_corrente = "";

        while (opcao_menu != 4){
            cadastro();
            opcao_menu = scan.nextInt();
            scan.nextLine();
            switch (opcao_menu){
                case 1:
                    System.out.print("Digite seu usuário: ");
                    String username = scan.nextLine();
                    System.out.print("Digite sua senha: ");
                    int password = scan.nextInt();
                    scan.nextLine();
                    Boolean statusLogin = AutenticaoController.loginUsuario(usuarios, username, password);
                    if (statusLogin == false){
                        System.out.println("Usuário ou senha incorreta");
                    }
                    else{
                        usuario_corrente = username;
                        opcao_menu = 4;
                        status = true;
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome: ");
                    String new_name = scan.nextLine();
                    System.out.print("Digite o tipo de usuário: ");
                    String new_type = scan.nextLine();
                    System.out.print("Digite o usuário: ");
                    String new_username = scan.nextLine();
                    System.out.print("Digite a senha: ");
                    int new_password = scan.nextInt();
                    scan.nextLine();

                    UsuarioController.criarUsuario(usuarios, new_type, new_name, new_username, new_password );
                    usuario_corrente = new_username;
                    status = true;
                    break;
                case 3:
                    System.out.println("Digite seu usuário: ");
                    String novo_username = scan.nextLine();
                    System.out.println("Digite sua nova senha ");
                    int nova_senha = scan.nextInt();
                    AutenticaoController.recuperarSenha(usuarios, novo_username, nova_senha);
                    break;
            }
        }

        while (status){
            menu(usuario_corrente);
            opcao = scan.nextInt();
            scan.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Escolha uma opção:\n 1- Criar Projeto\n 2- Editar Projeto\n 3- Remover Projeto\n 4- Listar Projetos");
                    opcao = scan.nextInt();
                    scan.nextLine();

                    if (opcao == 1){
                        System.out.print("Informe a descrição do projeto: ");
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

                        UsuarioController.mostrarUsuariosTipo(usuarios, "COORDENADOR");

                        System.out.print("Informe o ID do coordenador do projeto: ");
                        int id_cordinator = scan.nextInt();
                        scan.nextLine();

                        Usuario coordenador = UsuarioController.buscarUsuario(usuarios, id_cordinator);

                        System.out.print("Informe o período da bolsa (quantidade de meses): ");
                        int scholarship_period = scan.nextInt();
                        scan.nextLine();

                        Projeto projeto = new Projeto(description, datetime_begin, datetime_end, coordenador.getID(), scholarship_period);

                        UsuarioController.mostrarUsuariosTipo(usuarios, "ALUNO");

                        System.out.print("Quantos usuários vão participar desse projeto? ");
                        int size = scan.nextInt();
                        scan.nextLine();
                        for (int i = 0; i < size; i++){
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
                        for (int i = 0; i < size_activity; i++){
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
                        int ID = projetos.indexOf(projeto);
                        projeto.setID(ID);
                        System.out.println("Projeto criado com sucesso!");
                    }

                    else if (opcao ==2){
                        ProjetoController.mostrarProjetos(projetos);
                        System.out.print("Informe o ID do projeto que deseja editar: ");
                        int ID = scan.nextInt();
                        scan.nextLine();
                        Projeto project = ProjetoController.buscarProjeto(projetos, ID);
                        System.out.println("Escolha uma opção:\n 1- Editar descrição\n 2- Editar data de início\n 3 - Editar data de término\n 4 - Editar coordenador\n 5 - Editar status\n 6 - Editar período da bolsa\n 7 - Editar usuários associados a atividade\n 8 - Editar valor da bolsa\n 9 - Editar atividades");
                        int option_edit = scan.nextInt();
                        scan.nextLine();

                        if (option_edit==1){
                            System.out.print("Digite a nova descrição: ");
                            String new_description = scan.nextLine();
                            project.setDescricao(new_description);
                            System.out.println("Descrição atualizado com sucesso!");
                        }

                        else if (option_edit==2){
                            System.out.print("Informe a nova data de início do projeto (YYYY-MM-DD): ");
                            String date_begin_input = scan.nextLine();
                            System.out.print("Informe a nova hora de início do projeto (HH:MM): ");
                            String time_begin_input = scan.nextLine();
                            String datetime_begin_input = date_begin_input + "T" + time_begin_input + ":00";
                            LocalDateTime datetime_begin = LocalDateTime.parse(datetime_begin_input);
                            project.setData_hora_comeco(datetime_begin);
                            System.out.println("Data de início atualizada com sucesso!");
                        }
                        else if (option_edit==3){
                            System.out.print("Informe a nova data de término do projeto (YYYY-MM-DD): ");
                            String date_end_input = scan.nextLine();
                            System.out.print("Informe a nova hora de término do projeto (HH:MM): ");
                            String time_end_input = scan.nextLine();
                            String datetime_end_input = date_end_input + "T" + time_end_input + ":00";
                            LocalDateTime datetime_end = LocalDateTime.parse(datetime_end_input);
                            project.setData_hora_fim(datetime_end);
                            System.out.println("Data de término atualizada com sucesso!");
                        }

                        else if (option_edit==4){
                            UsuarioController.mostrarUsuariosTipo(usuarios, "COORDENADOR");
                            System.out.print("Digite o ID do novo coordenador: ");
                            int user_id = scan.nextInt();
                            Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
                            project.setCordinator_id(user.getID());
                            System.out.println("Coordenador atualizado com sucesso!");
                        }

                        else if (option_edit==5){
                            String new_status = scan.nextLine();
                            Usuario usuario_logado = UsuarioController.buscarUsuarioUsername(usuarios, usuario_corrente);
                            if (usuario_logado.getType()=="COORDENADOR"){
                                System.out.print("Digite o novo status do projeto: ");
                                project.setStatus(new_status);
                                System.out.println("Status do projeto atualizado com sucesso!");
                            }
                            else{
                                System.out.println("Apenas coordenador pode atualizar o status do projeto!");
                            }
                        }
                        else if (option_edit==6){
                            System.out.print("Digite o novo período da bolsa (quantidade em meses): ");
                            int new_period = scan.nextInt();
                            scan.nextLine();
                            project.setPeriodo_bolsa(new_period);
                            System.out.println("Período da bolsa atualizado com sucesso!");
                        }
                        else if (option_edit==7){
                            project.editarUsuarios(usuarios);
                        }
                        else if (option_edit==8){
                            UsuarioController.mostrarUsuariosTipo(usuarios, "ALUNO");
                            System.out.print("Digite o ID do usuário: ");
                            int user_id = scan.nextInt();
                            Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
                            System.out.println("Digite o novo valor da bolsa: ");
                            Double new_value = scan.nextDouble();
                            project.setValores_bolsa(new_value, user);

                        }
                        else if (option_edit==9){
                            project.editarAtividades(atividades);
                        }
                    }
                    else if(opcao==3){
                        System.out.print("Informe o ID do projeto que deseja remover: ");
                        int id = scan.nextInt();
                        scan.nextLine();
                        ProjetoController.removerProjeto(projetos, id);
                        System.out.println("Projeto removido com sucesso!");
                    }
                    else if(opcao==4){
                        ProjetoController.mostrarProjetos(projetos);
                    }

                    break;

            }

        }

    }

    private void menu(String usuario){
        System.out.println("Bem vindo " + usuario);
        System.out.println("Qual entidade deseja modificar: \n 1- Projeto\n 2- Atividade \n 3- Usuário \n 4- Relatório\n 5- Pagamento de Bolsa\n 6- Sair do menu");
    }

    private void cadastro(){
        System.out.println("Bem vindo ao sistema de gerenciamento de projetos!");
        System.out.println("\n1 - Login\n2 - Cadastrar\n3 - Esqueci minha senha\n4 - Sair do sistema");
        System.out.print("Escolha uma opção: ");
    }
}
