package controllers;

public class ValidacaoController {
    public static void validaString(String string){
        if (string.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
