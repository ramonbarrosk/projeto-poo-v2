package models.enums;

public enum TipoUsuario {
    DOUTOR("Doutor"),
    GRADUANDO("Graduando"),
    MESTRE("Mestre"),
    PESQUISADOR("Pesquisador"),
    PROFESSOR("Professor"),
    PROFISSIONAL("Profissional"),
    TECNICO("Tecnico");

    private String tipo;

    TipoUsuario(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
}
