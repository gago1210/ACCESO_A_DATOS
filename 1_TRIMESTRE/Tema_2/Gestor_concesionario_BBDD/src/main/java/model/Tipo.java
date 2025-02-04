package model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Tipo {
    private int id;
    private String siglas, descripcion;

    public Tipo(String siglas, String descripcion) {
        this.siglas = siglas;
        this.descripcion = descripcion;
    }
}
