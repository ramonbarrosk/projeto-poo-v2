package controllers;

import models.entities.Atividade;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AtividadeController {
    public static Atividade criarAtividade(ArrayList<Atividade> atividades, String descricao, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, Usuario responsavel){
        Atividade atividade = new Atividade(descricao, data_hora_comeco, data_hora_fim, responsavel);
        atividades.add(atividade);

        return atividade;
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
        System.out.println("ID: " + atividade.getID() + "\nDescrição: " + atividade.getDescricao() + "\nData de início: " + atividade.getData_hora_comeco() + "\nData de término: " + atividade.getData_hora_fim() + "\nResponsável: " + atividade.getResponsavel());
        UsuarioController.mostrarUsuarios(atividade.getUsuarios());
    }

    public static void mostrarAtividades(ArrayList<Atividade> atividades){
        for (Atividade atividade : atividades) {
            mostrarAtividade(atividade);
        }
    }
}
