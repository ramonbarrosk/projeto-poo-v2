package views;

import controllers.*;
import models.entities.Atividade;
import models.entities.Projeto;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.*;


public class Runner {
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Atividade> atividades = new ArrayList<Atividade>();
    ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    public Runner(){
        UsuarioController.criarUsuario(usuarios,"MESTRE", "Ramon", "ramonbarros", 123);
        UsuarioController.criarUsuario(usuarios,"DOUTOR", "Felipe", "felipinho", 123);
        UsuarioController.criarUsuario(usuarios,"PROFESSOR", "Diego", "diego", 123);
        this.run();
    }

    private void run(){
        int opcao;
        Scanner scan = new Scanner(System.in);

        int opcao_menu = 0;
        Boolean status = false;
        String usuario_corrente = "";
        String tipo_corrente = "";

        while (opcao_menu != 4){
            cadastro();
            try{
                opcao_menu = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Digite uma opção válida!");
                this.run();

            }
            switch (opcao_menu){
                case 1:
                    try {

                        System.out.print("Digite seu usuário: ");
                        String username = scan.nextLine();
                        ValidacaoController.validaString(username);
                        System.out.print("Digite sua senha: ");
                        int password = scan.nextInt();
                        ValidacaoController.validaString(Integer.toString(password));

                        Boolean statusLogin = AutenticaoController.loginUsuario(usuarios, username, password);
                        if (statusLogin == false){
                            System.out.println("Usuário ou senha incorreta");
                        }
                        else{
                            usuario_corrente = username;
                            opcao_menu = 4;
                            status = true;
                        }
                        scan.nextLine();
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("Usuário ou senha não pode ser vázio!");
                        this.run();
                    }
                case 2:
                    try {
                        System.out.print("Digite o nome: ");
                        String new_name = scan.nextLine();
                        ValidacaoController.validaString(new_name);
                        System.out.print("Digite o tipo de usuário: ");
                        String new_type = scan.nextLine().toUpperCase();
                        ValidacaoController.validaString(new_type);
                        System.out.print("Digite o usuário: ");
                        String new_username = scan.nextLine();
                        ValidacaoController.validaString(new_username);
                        System.out.print("Digite a senha: ");

                        int new_password = scan.nextInt();

                        ValidacaoController.validaString(Integer.toString(new_password));

                        UsuarioController.criarUsuario(usuarios, new_type, new_name, new_username, new_password);
                        usuario_corrente = new_username;
                        status = true;
                        break;

                    } catch (IllegalArgumentException e){
                        System.out.println("Não pode existir campo vázio!");
                        this.run();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Senha inválida, digite apenas números!");
                        this.run();
                    }
                case 3:
                    System.out.print("Digite seu usuário: ");
                    String novo_username = scan.nextLine();
                    Boolean status_recuperar = AutenticaoController.recuperarSenha(usuarios, novo_username);
                    if (status_recuperar){
                        System.out.println("Senha recuperada com sucesso!");
                    }
                    else{
                        System.out.println("Usuário não encontrado ou nova senha incorreta!");
                    }
                    break;
            }
        }

        while (status){
            opcao = 7;
            try {
                menu(usuario_corrente);
                System.out.print("Digite uma opção: ");
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Digite uma opção válida!");
                this.run();
            }
            switch(opcao){
                case 1:
                    System.out.println("Escolha uma opção:\n 1- Criar Projeto\n 2- Editar Projeto\n 3- Remover Projeto\n 4- Listar Projetos");
                    System.out.print("Digite uma opção: ");
                    try {
                        opcao = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException e){
                        System.out.println("Digite uma opção válida!");
                        break;
                    }
                    try {
                        if (opcao == 1) {

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

                            mostrarResponsaveis(usuarios);

                            System.out.print("Informe o ID do coordenador do projeto: ");
                            int id_cordinator = scan.nextInt();
                            scan.nextLine();

                            Usuario coordenador = UsuarioController.buscarUsuario(usuarios, id_cordinator);

                            System.out.print("Informe o período da bolsa (quantidade de meses): ");
                            int scholarship_period = scan.nextInt();
                            scan.nextLine();

                            Projeto projeto = new Projeto(description, datetime_begin, datetime_end, coordenador.getID(), scholarship_period);

                            mostrarAlunos(usuarios);

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


                        } else if (opcao == 2) {

                            ProjetoController.mostrarProjetos(projetos);
                            System.out.print("Informe o ID do projeto que deseja editar: ");
                            int projeto_id = scan.nextInt();
                            scan.nextLine();
                            Projeto project = ProjetoController.buscarProjeto(projetos, projeto_id);
                            System.out.println("Escolha uma opção:\n 1- Editar descrição\n 2- Editar data de início\n 3 - Editar data de término\n 4 - Editar coordenador\n 5 - Editar status\n 6 - Editar período da bolsa\n 7 - Editar usuários associados a atividade\n 8 - Editar valor da bolsa\n 9 - Editar atividades");
                            int option_edit = scan.nextInt();
                            scan.nextLine();

                            if (option_edit == 1) {
                                System.out.print("Digite a nova descrição: ");
                                String new_description = scan.nextLine();
                                ValidacaoController.validaString(new_description);
                                project.setDescricao(new_description);
                                System.out.println("Descrição atualizado com sucesso!");
                            } else if (option_edit == 2) {
                                System.out.print("Informe a nova data de início do projeto (YYYY-MM-DD): ");
                                String date_begin_input = scan.nextLine();
                                ValidacaoController.validaString(date_begin_input);
                                System.out.print("Informe a nova hora de início do projeto (HH:MM): ");
                                String time_begin_input = scan.nextLine();
                                ValidacaoController.validaString(time_begin_input);
                                String datetime_begin_input = date_begin_input + "T" + time_begin_input + ":00";
                                ValidacaoController.validaString(datetime_begin_input);
                                LocalDateTime datetime_begin = LocalDateTime.parse(datetime_begin_input);
                                project.setData_hora_comeco(datetime_begin);
                                System.out.println("Data de início atualizada com sucesso!");
                            } else if (option_edit == 3) {
                                System.out.print("Informe a nova data de término do projeto (YYYY-MM-DD): ");
                                String date_end_input = scan.nextLine();
                                System.out.print("Informe a nova hora de término do projeto (HH:MM): ");
                                String time_end_input = scan.nextLine();
                                ValidacaoController.validaString(time_end_input);
                                String datetime_end_input = date_end_input + "T" + time_end_input + ":00";
                                ValidacaoController.validaString(datetime_end_input);
                                LocalDateTime datetime_end = LocalDateTime.parse(datetime_end_input);
                                project.setData_hora_fim(datetime_end);
                                System.out.println("Data de término atualizada com sucesso!");
                            } else if (option_edit == 4) {
                                mostrarResponsaveis(usuarios);
                                System.out.print("Digite o ID do novo coordenador: ");
                                int user_id = scan.nextInt();
                                Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
                                project.setCordinator_id(user.getID());
                                System.out.println("Coordenador atualizado com sucesso!");
                            } else if (option_edit == 5) {
                                String new_status = scan.nextLine();
                                ValidacaoController.validaString(new_status);
                                Usuario usuario_logado = UsuarioController.buscarUsuarioUsername(usuarios, usuario_corrente);
                                if (usuario_logado.getType() == "COORDENADOR") {
                                    System.out.print("Digite o novo status do projeto: ");
                                    project.setStatus(new_status);
                                    System.out.println("Status do projeto atualizado com sucesso!");
                                } else {
                                    System.out.println("Apenas coordenador pode atualizar o status do projeto!");
                                }
                            } else if (option_edit == 6) {
                                System.out.print("Digite o novo período da bolsa (quantidade em meses): ");
                                int new_period = scan.nextInt();
                                scan.nextLine();
                                project.setPeriodo_bolsa(new_period);
                                System.out.println("Período da bolsa atualizado com sucesso!");
                            } else if (option_edit == 7) {
                                project.editarUsuarios(usuarios);
                            } else if (option_edit == 8) {
                                mostrarAlunos(usuarios);
                                System.out.print("Digite o ID do usuário: ");
                                int user_id = scan.nextInt();
                                Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
                                System.out.println("Digite o novo valor da bolsa: ");
                                Double new_value = scan.nextDouble();
                                project.setValores_bolsa(new_value, user);

                            } else if (option_edit == 9) {
                                project.editarAtividades(atividades);
                            }
                        } else if (opcao == 3) {
                            System.out.print("Informe o ID do projeto que deseja remover: ");
                            int id = scan.nextInt();
                            scan.nextLine();
                            ProjetoController.removerProjeto(projetos, id);
                            System.out.println("Projeto removido com sucesso!");
                        } else if (opcao == 4) {
                            ProjetoController.mostrarProjetos(projetos);
                        }

                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("Nenhum campo pode ser vázio!");
                        this.run();
                    } catch (InputMismatchException e){
                        System.out.println("Algum campo está inválido!");
                        this.run();
                    }
                case 2:
                    try {
                        System.out.println("Escolha uma opção:\n 1- Criar Atividade\n 2- Editar Atividade\n 3- Remover Atividade\n 4- Listar Atividades");
                        opcao = scan.nextInt();
                        scan.nextLine();
                        if (opcao == 1) {
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
                            mostrarResponsaveis(usuarios);
                            System.out.print("Informe o ID do usuário responsável pela atividade: ");
                            int user_id = scan.nextInt();
                            scan.nextLine();
                            Usuario manager = UsuarioController.buscarUsuario(usuarios, user_id);
                            Atividade atividade = AtividadeController.criarAtividade(atividades, description, datetime_begin, datetime_end, manager);
                            mostrarAlunos(usuarios);
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
                        } else if (opcao == 2) {
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
                                mostrarResponsaveis(usuarios);
                                System.out.print("Digite o ID do novo responsável: ");
                                int user_id = scan.nextInt();
                                scan.nextLine();
                                Usuario user = UsuarioController.buscarUsuario(usuarios, user_id);
                                atividade.setResponsavel(user);
                                System.out.println("Responsável atualizado com sucesso!");
                            } else if (option_edit == 5) {
                                atividade.editarUsuarios(usuarios);
                            }
                        } else if (opcao == 3) {
                            System.out.print("Informe o ID da ativitidade que deseja remover: ");
                            int id = scan.nextInt();
                            AtividadeController.removerAtividade(atividades, id);
                            System.out.println("Atividade removida com sucesso!");
                        } else if (opcao == 4) {
                            AtividadeController.mostrarAtividades(atividades);
                        }
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("Nenhum campo pode ser vázio!");
                        this.run();
                    } catch (InputMismatchException e){
                        System.out.println("Algum campo está inválido!");
                        this.run();
                    }
                case 3:
                    try {
                        System.out.println("Escolha uma opção:\n 1- Criar Usuário\n 2- Editar Usuário\n 3- Remover Usuário\n 4- Listar Usuários");
                        opcao = scan.nextInt();
                        scan.nextLine();

                        if (opcao == 1) {
                            System.out.print("Informe o nome do usuário: ");
                            String name = scan.nextLine();
                            System.out.print("Informe o tipo do usuário: ");
                            String type = scan.nextLine();
                            System.out.print("Informe o username do usuário: ");
                            String username = scan.nextLine();
                            System.out.print("Informe a senha do usuário: ");
                            int password = scan.nextInt();
                            scan.nextLine();
                            Usuario usuario = UsuarioController.criarUsuario(usuarios, type.toUpperCase(), name, username, password);
                            System.out.println("Usuário criado com sucesso!");

                        } else if (opcao == 2) {
                            UsuarioController.mostrarUsuarios(usuarios);
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
                        } else if (opcao == 3) {
                            UsuarioController.mostrarUsuarios(usuarios);
                            System.out.print("Informe o ID do usuário que deseja remover: ");
                            int user_id = scan.nextInt();
                            scan.nextLine();
                            UsuarioController.removerUsuario(usuarios, user_id);
                            System.out.println("Usuário removido com sucesso!");
                        } else if (opcao == 4) {
                            UsuarioController.mostrarUsuarios(usuarios);
                        }
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("Nenhum campo pode ser vázio!");
                        this.run();
                    } catch (InputMismatchException e){
                        System.out.println("Algum campo está inválido!");
                        this.run();
                    }
                case 4:
                    System.out.println("Todos os projetos cadastrados da unidade Acadêmica:");
                    for (Projeto project : projetos) {
                        ProjetoController.mostrarProjeto(project);
                    }
                    break;
                case 5:
                    System.out.println("Controle de pagamento de bolsa:");

                    for (Projeto project : projetos) {
                        System.out.println("------------------------");
                        System.out.println("Projeto sobre " + project.getDescricao());
                        for (var entry : project.getValores_bolsa().entrySet()) {
                            System.out.println("Aluno: " + entry.getValue().getName() + " - Bolsa: " + entry.getKey());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Você saiu do menu!");
                    status = false;
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

    private void mostrarAlunos(ArrayList<Usuario> usuarios){
        ArrayList<String> alunos = new ArrayList<String>(Arrays.asList("DOUTOR", "GRADUANDO", "MESTRE", "PROFISSIONAL", "TECNICO"));
        UsuarioController.mostrarUsuariosTipo(usuarios, alunos);
    }

    private void mostrarResponsaveis(ArrayList<Usuario> usuarios){
        ArrayList<String> responsaveis = new ArrayList<String>(Arrays.asList("PROFESSOR", "PESQUISADOR"));
        UsuarioController.mostrarUsuariosTipo(usuarios, responsaveis);
    }
}
