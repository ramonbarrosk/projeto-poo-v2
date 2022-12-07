package test;

import models.entities.Atividade;
import models.entities.Projeto;
import models.entities.Usuario;
import models.entities.UsuarioFactory;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Factory {
    public static void gerarFactories(ArrayList<Usuario> usuarios, ArrayList<Projeto> projetos, ArrayList<Atividade> atividades){
        LocalDateTime data = LocalDateTime.parse("2022-10-10T20:20");

        Usuario usuario = UsuarioFactory.getUsuario("Professor", "RAMON", "ramon", 123);
        usuarios.add(usuario);
        int usuario_id = usuarios.indexOf(usuario);
        usuario.setID(usuario_id);

        Usuario usuario2 = UsuarioFactory.getUsuario("Doutor", "luana", "luana", 123);
        usuarios.add(usuario2);
        int usuario2_id = usuarios.indexOf(usuario2);
        usuario2.setID(usuario2_id);

        Usuario usuario3 = UsuarioFactory.getUsuario("Mestre", "gordo", "gordo", 123);
        usuarios.add(usuario3);
        int usuario3_id = usuarios.indexOf(usuario3);
        usuario3.setID(usuario3_id);

        Atividade atividade = new Atividade("Teste",data, data, usuario);
        atividade.setUsuarios(usuarios);
        atividades.add(atividade);

        Projeto projeto = new Projeto("Teste", data, data, usuario.getID(), 100);
        projeto.setAtividades(atividades);
        projeto.setUsuarios(usuarios);
        projetos.add(projeto);
    }
}
