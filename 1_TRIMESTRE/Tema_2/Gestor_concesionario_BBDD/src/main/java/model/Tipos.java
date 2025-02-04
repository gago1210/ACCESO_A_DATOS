package model;

public enum Tipos {
    // Es el tipo de empleado segun contrato que voy a tener EXTERNO , INDEFINIDO, BECARIO;
    EXTERNO(1,"EXT", "trabajador externo"),
    INDEFINIDO(2, "IND","trabajador indefinido"),
    BECARIO(3,"BEC","trabajador becario");

    private int id;
    private String siglas, descripcion;

    //me hago el constructor:
    Tipos(int id, String siglas, String descripcion) {
        this.id = id;
        this.siglas = siglas;
        this.descripcion = descripcion;
    }

    //hago los getter y setter de cada uno para poder acceder a ellos:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
