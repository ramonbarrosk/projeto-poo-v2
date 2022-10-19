package models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Atividade {
    int ID;
    String descricao;
    LocalDateTime data_hora_comeco;
    LocalDateTime data_hora_fim;
    Usuario responsavel;
    ArrayList<Usuario> usuarios;
    Map<String, Usuario> tarefas;

    public Atividade(int ID, String descricao, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, Usuario responsavel, ArrayList<Usuario> usuarios, Map<String, Usuario> tarefas) {
        this.ID = ID;
        this.descricao = descricao;
        this.data_hora_comeco = data_hora_comeco;
        this.data_hora_fim = data_hora_fim;
        this.responsavel = responsavel;
        this.usuarios = usuarios;
        this.tarefas = tarefas;
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
}
