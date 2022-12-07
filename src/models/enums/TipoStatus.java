package models.enums;

public enum TipoStatus {
    CRIACAO("Em processo de criação"),
    INICIADO("Iniciado"),
    ANDAMENTO("Em andamento"),
    CONCLUÍDO("Concluído");

    private String tipo;

    TipoStatus(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
}
