package model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/*voy a hacer dos constructores, uno para modificar mis datos de empleado y otro para que pueda añadir otro empleado nuevo y le asigne un id*/
public class Empleado {
    //los escribo en orden en el que los puse en la bbdd para que cuando se me cree el constructor ya estén ordenados
    private int id;
    private String nombre, apellido, correo;
    private int telefono;
    private Tipo tipo; //lo añado con las novedades de la BBDD aqui y en el apdo tipo

    public Empleado (String nombre, String apellido, String correo, int telefono, Tipo tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
    }

}
