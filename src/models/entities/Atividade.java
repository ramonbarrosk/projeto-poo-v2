package models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Atividade {
    int ID;
    String descricao;
    LocalDateTime data_hora_comeco;
    LocalDateTime data_hora_fim;
    Usuario responsavel;
    ArrayList<Usuario> usuarios;
    Map<String, Usuario> tarefas;

    public Atividade(String descricao, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, Usuario responsavel) {
        this.descricao = descricao;
        this.data_hora_comeco = data_hora_comeco;
        this.data_hora_fim = data_hora_fim;
        this.responsavel = responsavel;
    }

    public int getID() {
        return ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData_hora_comeco() {
        return data_hora_comeco;
    }

    public LocalDateTime getData_hora_fim() {
        return data_hora_fim;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Map<String, Usuario> getTarefas() {
        return tarefas;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData_hora_comeco(LocalDateTime data_hora_comeco) {
        this.data_hora_comeco = data_hora_comeco;
    }

    public void setData_hora_fim(LocalDateTime data_hora_fim) {
        this.data_hora_fim = data_hora_fim;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setTarefas(Map<String, Usuario> tarefas) {
        this.tarefas = tarefas;
    }

    public void addUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }

    public void addTarefa(String tarefa, Usuario usuario){
        this.tarefas.put(tarefa, usuario);
    }

    public void editarUsuarios(ArrayList<Usuario> users) {
        Scanner scan = new Scanner(System.in);
        for (Usuario user : this.usuarios){
            System.out.println("----------------------");
            System.out.println("ID: " + user.getID() + "\nNome: " + user.getName() + "\nTipo: "+ user.type);
        }
        System.out.println("Digite 1 para remover ou 2 para substituir: ");
        int op = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite o ID do usuário: ");
        int user_id = scan.nextInt();
        scan.nextLine();
        if (op==1){
            this.usuarios.remove(user_id);
            System.out.println("Usuário removido do projeto com sucesso!");
        }
        else if(op==2){
            for (Usuario user : users){
                System.out.println("----------------------");
                System.out.println("ID: " + user.getID() + "\nNome: " + user.getName() + "\nTipo: "+ user.getType());
            }
            System.out.println("Digite o ID do aluno que deseja inserir no projeto: ");
            int user_sub = scan.nextInt();
            scan.nextLine();
            Usuario user = users.get(user_sub);
            this.usuarios.add(user);
            System.out.println("Usuário substituido com sucesso!");
        }
    }
}
