package views;

import controllers.*;
import models.entities.Atividade;
import models.entities.Projeto;
import models.entities.Usuario;
import models.entities.UsuarioFactory;
import test.Factory;

import java.time.LocalDateTime;
import java.util.*;


public class Runner {

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Atividade> atividades = new ArrayList<Atividade>();
    ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    public Runner(){
        Factory.gerarFactories(usuarios, projetos, atividades);
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
            Menu.cadastro();
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
                        UsuarioController.criarUsuario(usuarios, scan);
                        Usuario usuario = usuarios.get(usuarios.size() - 1);
                        usuario_corrente = usuario.getUsername();
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
                Menu.principal(usuario_corrente);
                System.out.print("Digite uma opção: ");
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Digite uma opção válida!");
                this.run();
            }
            switch(opcao){
                case 1:
                    Menu.opcoesProjeto();
                    try {
                        opcao = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException e){
                        System.out.println("Digite uma opção válida!");
                        break;
                    }
                    try {
                        if (opcao == 1) {

                        ProjetoController.criarProjeto(projetos, scan, usuarios, atividades);


                        } else if (opcao == 2) {

                            ProjetoController.mostrarProjetos(projetos, usuarios);
                            System.out.print("Informe o ID do projeto que deseja editar: ");
                            int projeto_id = scan.nextInt();
                            scan.nextLine();
                            Projeto project = ProjetoController.buscarProjeto(projetos, projeto_id);
                            Menu.opcoesEditarProjeto();
                            int option_edit = scan.nextInt();
                            scan.nextLine();
                            ProjetoController.editarProjeto(usuarios, project, option_edit, scan, usuario_corrente, atividades);


                        } else if (opcao == 3) {
                            ProjetoController.mostrarProjetos(projetos, usuarios);
                            System.out.print("Informe o ID do projeto que deseja remover: ");
                            int id = scan.nextInt();
                            scan.nextLine();
                            ProjetoController.removerProjeto(projetos, id);
                            System.out.println("Projeto removido com sucesso!");
                        } else if (opcao == 4) {
                            ProjetoController.mostrarProjetos(projetos, usuarios);
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
                        System.out.print("Digite uma opção: ");
                        opcao = scan.nextInt();
                        scan.nextLine();
                        if (opcao == 1) {
                            AtividadeController.criarAtividade(atividades, scan, usuarios);
                        } else if (opcao == 2) {
                            AtividadeController.editarAtividade(atividades, scan, usuarios);
                        } else if (opcao == 3) {
                            AtividadeController.mostrarAtividades(atividades);
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
                        System.out.print("Digite uma opção: ");
                        opcao = scan.nextInt();
                        scan.nextLine();

                        if (opcao == 1) {

                            UsuarioController.criarUsuario(usuarios, scan);

                        } else if (opcao == 2) {
                            UsuarioController.editarUsuario(usuarios, scan);

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
                        Usuario manager = UsuarioController.buscarUsuario(usuarios, project.getCordinator_id());
                        ProjetoController.mostrarProjeto(project, manager);
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
}
