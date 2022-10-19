package controllers;

import models.entities.Atividade;
import models.entities.Projeto;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class ProjetoController {
    public static void criarProjeto(ArrayList<Projeto> projetos, String descricao, String status, LocalDateTime data_hora_comeco, LocalDateTime data_hora_fim, int cordinator_id, ArrayList<Usuario> usuarios, ArrayList<Atividade> atividades, Map<Double, Usuario> valores_bolsa, int periodo_bolsa){
        Projeto projeto = new Projeto(descricao, data_hora_comeco, data_hora_fim, cordinator_id, periodo_bolsa);
        projetos.add(projeto);
    }

    public static Projeto buscarProjeto(ArrayList<Projeto> projetos, int ID){
        for (Projeto projeto : projetos) {
            if (projeto.getID() == ID)
                return projeto;
        }

        return null;
    }

    public static void removerProjeto(ArrayList<Projeto> projetos, int ID){
        for (Projeto projeto : projetos) {
            if (projeto.getID() == ID)
                projetos.set(ID, null);
        }
    }

    public static void mostrarProjeto(Projeto projeto){
        System.out.println("ID: " + projeto.getID() + "Descrição: " + projeto.getDescricao() + "Status: " + projeto.getStatus() + "Data de início: " + projeto.getData_hora_comeco() + "Data de término: " + projeto.getData_hora_fim() + "Período da bolsa: " + projeto.getPeriodo_bolsa());
        UsuarioController.mostrarUsuarios(projeto.getUsuarios());
        AtividadeController.mostrarAtividades(projeto.getAtividades());
    }

    public static void mostrarProjetos(ArrayList<Projeto> projetos){
        for (Projeto projeto : projetos) {
            mostrarProjeto(projeto);
        }
    }


}
