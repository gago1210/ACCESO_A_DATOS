package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Jugador.findNacionalidad",query = "FROM Jugador j WHERE j.nacionalidad = :nacionalidad")
@NamedQuery(name = "Jugador.findAll",query = "FROM Jugador")
@Entity
@Table(name = "jugadores")
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column (name = "valor_mercado")
    private int valor;
    @Column
    private String nacionalidad;
    @Column
    private int goles;

    @ManyToOne(cascade = CascadeType.ALL) /*es una relacion muchos a uno (bidireccional). muchos jugadores pueden estar asociados a una sola
        posicion y, una sola posicion puede estar asociada a muchos jugadores*/
    @JoinColumn(name = "id_posicion") /*es una relacion marcada con una columna cuyo nombre es id posicion, lo marco aqui porque es la entidad
        dominante capaz de sacar el id_posicion a traves de esto*/
    private Posicion posicion; //nos quedamos con posicion

    /*esto es otra relacion: un jugador puede estar en un equipo y en un equipo puede haber muchos jugadores. En este caso es muchos jugadores
    estan en un equipo */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipo") //la columna de donde saco la direccion
    private Equipo equipo; //esto es un atributo de equipo

    public Jugador(String nombre, int valor, String nacionalidad, int goles) {
        this.nombre = nombre;
        this.valor = valor;
        this.nacionalidad = nacionalidad;
        this.goles = goles;
    }

    public Jugador(String nombre, int valor, String nacionalidad) {
        this.nombre = nombre;
        this.valor = valor;
        this.nacionalidad = nacionalidad;
    }
}