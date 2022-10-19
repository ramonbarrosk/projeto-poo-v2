package controllers;

import models.entities.Atividade;
import models.entities.Doutor;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class AtividadeController {
    public static void criarAtividade(ArrayList<Atividade> atividades, String descricao, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, Usuario responsavel, ArrayList<Usuario> usuarios, Map<String, Usuario> tarefas){
        Atividade atividade = new Atividade(descricao, data_hora_comeco, data_hora_fim, responsavel, usuarios, tarefas);
        atividades.add(atividade);
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
        System.out.println("ID: " + atividade.getID() + "Descrição: " + atividade.getDescricao() + "Data de início: " + atividade.getData_hora_comeco() + "Data de término: " + atividade.getData_hora_fim() + "Responsável: " + atividade.getResponsavel());
        UsuarioController.mostrarUsuarios(atividade.getUsuarios());
    }

    public static void mostrarAtividades(ArrayList<Atividade> atividades){
        for (Atividade atividade : atividades) {
            mostrarAtividade(atividade);
        }
    }
}
