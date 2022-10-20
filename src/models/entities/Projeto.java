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

    public Projeto(String descricao, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, int cordinator_id, int periodo_bolsa) {
        this.descricao = descricao;
        this.data_hora_comeco = data_hora_comeco;
        this.data_hora_fim = data_hora_fim;
        this.cordinator_id = cordinator_id;
        this.periodo_bolsa = periodo_bolsa;
        this.valores_bolsa = new HashMap<Double, Usuario>();
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

    public void editarUsuarios(ArrayList<Usuario> usuarios) {
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
            for (Usuario usuario : usuarios){
                System.out.println("----------------------");
                System.out.println("ID: " + usuario.getID() + "\nNome: " + usuario.getName() + "\nTipo: "+ usuario.getType());
            }
            System.out.println("Digite o ID do aluno que deseja inserir no projeto: ");
            int user_sub = scan.nextInt();
            scan.nextLine();
            Usuario user = usuarios.get(user_sub);
            this.usuarios.add(user);
            System.out.println("Usuário substituido com sucesso!");
        }
    }

    public void setValores_bolsa(Double value, Usuario user) {
        for (var entry : this.valores_bolsa.entrySet()) {
            if (entry.getValue().getName() == user.getName()){
                this.valores_bolsa.put(value, user);
            }
        }
    }

    public void editarAtividades(ArrayList<Atividade> atividades) {
        Scanner scan = new Scanner(System.in);
        for (Atividade activity: this.atividades){
            System.out.println("----------------------");
            System.out.println("ID: " + activity.ID + "\nDescrição: " + activity.getDescricao());
        }
        System.out.println("Digite 1 para remover ou 2 para substituir: ");
        int op = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite o ID da atividade: ");
        int activity_id = scan.nextInt();
        scan.nextLine();
        if (op==1){
            this.atividades.remove(activity_id);
            System.out.println("Atividade removida do projeto com sucesso!");
        }
        else if(op==2){
            for (Atividade activity: atividades){
                System.out.println("----------------------");
                System.out.println("ID: " + activity.ID + "\nDescrição: " + activity.getDescricao());
            }
            System.out.println("Digite o ID da atividade que deseja inserir no projeto: ");
            int activity_sub = scan.nextInt();
            scan.nextLine();
            Usuario user = usuarios.get(activity_sub);
            this.usuarios.add(user);
            System.out.println("Atividade substituida com sucesso!");
        }
    }
}
