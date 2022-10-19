package models.entities;
import java.util.*;
import java.time.LocalDateTime;

public class Projeto {
    int ID;
    String descricao;
    String status;
    LocalDateTime data_hora_comeco;
    LocalDateTime data_hora_fim;
    int cordinator_id;
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Atividade> atividades = new ArrayList<Atividade>();
    Map<Double, Usuario> valores_bolsa = new HashMap<>();
    int periodo_bolsa;

    public Projeto(int ID, String descricao, String status, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, int cordinator_id, ArrayList<Usuario> usuarios, ArrayList<Atividade> atividades, Map<Double, Usuario> valores_bolsa, int periodo_bolsa) {
        this.ID = ID;
        this.descricao = descricao;
        this.status = status;
        this.data_hora_comeco = data_hora_comeco;
        this.data_hora_fim = data_hora_fim;
        this.cordinator_id = cordinator_id;
        this.usuarios = usuarios;
        this.atividades = atividades;
        this.valores_bolsa = valores_bolsa;
        this.periodo_bolsa = periodo_bolsa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getData_hora_comeco() {
        return data_hora_comeco;
    }

    public void setData_hora_comeco(LocalDateTime data_hora_comeco) {
        this.data_hora_comeco = data_hora_comeco;
    }

    public LocalDateTime getData_hora_fim() {
        return data_hora_fim;
    }

    public void setData_hora_fim(LocalDateTime data_hora_fim) {
        this.data_hora_fim = data_hora_fim;
    }

    public int getCordinator_id() {
        return cordinator_id;
    }

    public void setCordinator_id(int cordinator_id) {
        this.cordinator_id = cordinator_id;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Map<Double, Usuario> getValores_bolsa() {
        return valores_bolsa;
    }

    public void setValores_bolsa(Map<Double, Usuario> valores_bolsa) {
        this.valores_bolsa = valores_bolsa;
    }

    public int getPeriodo_bolsa() {
        return periodo_bolsa;
    }

    public void setPeriodo_bolsa(int periodo_bolsa) {
        this.periodo_bolsa = periodo_bolsa;
    }

    public void addUser(Usuario usuario){
        this.usuarios.add(usuario);
    }

    public void addValorBolsa(Double valor, Usuario usuario){
        this.valores_bolsa.put(valor, usuario);
    }

    public void addAtividade(Atividade atividade){
        this.atividades.add(atividade);
    }
}
