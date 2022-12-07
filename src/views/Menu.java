package views;

public class Menu {

    public static void opcoesProjeto(){
        System.out.println("Escolha uma opção:\n 1- Criar Projeto\n 2- Editar Projeto\n 3- Remover Projeto\n 4- Listar Projetos");
        System.out.print("Digite uma opção: ");
    }

    public static void opcoesEditarProjeto(){
        System.out.println("" +
                "Escolha uma opção:\n" +
                " 1- Editar descrição\n" +
                " 2- Editar data de início\n" +
                " 3 - Editar data de término\n" +
                " 4 - Editar coordenador\n" +
                " 5 - Editar status\n" +
                " 6 - Editar período da bolsa\n" +
                " 7 - Editar usuários associados a atividade\n" +
                " 8 - Editar valor da bolsa\n" +
                " 9 - Editar atividades");
        System.out.print("Digite uma opção: ");
    }

    public static void cadastro(){
        System.out.println("Bem vindo ao sistema de gerenciamento de projetos!");
        System.out.println("\n1 - Login\n2 - Cadastrar\n3 - Esqueci minha senha\n4 - Sair do sistema");
        System.out.print("Escolha uma opção: ");
    }

    public static void principal(String username){
        System.out.println("Bem vindo " + username);
        System.out.println("Qual entidade deseja modificar: \n 1- Projeto\n 2- Atividade \n 3- Usuário \n 4- Relatório\n 5- Pagamento de Bolsa\n 6- Sair do menu");
    }
}
