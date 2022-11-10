package controllers;

import models.entities.Atividade;
import models.entities.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ValidacaoController {
    public static void validaString(String string){
        if (string.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
