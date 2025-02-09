package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/*esto es una entidad pero no es una entidad que yo vaya a llevar a bbdd de forma directa. Esto va a llegar a bbdd a traves de la clase
trabajador (por el orm). Por eso en vez de poner @entity sino @embeddable, el cual indica que esta clase se va a embeber dentro de
otra entidad que va a ir a una tabla. Es un decorador nuevo*/
@Embeddable
public class Direccion implements Serializable {

    @Column
    private String localidad;
    @Column
    private String provincia;

    @Override
    public String toString() {
        return "Direccion{" +
                "localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}