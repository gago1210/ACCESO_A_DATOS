package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posiciones") //es una tabla que se va a ir al name 'posiciones'
public class Posicion implements Serializable { /*le decimos los atributos que tiene y le decimos serializable porque asi me permite hacer
    la transaccion de llevar y traer un objeto en trocitos*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //cuidado con los identificadores porque suelen ser longs
    @Column
    private String posicion;
    @OneToMany(mappedBy = "posicion") /*una posicion puede estar asociada a muchos jugadores. estoy en la entidad no dominante por lo que me
        voy a mapear primero*/
    private List<Jugador> jugadores; /*obtengo una lista porque no obtengo objeto, asi cuando yo pida que me de el programa todos los
        jugadores que sean delanteros, me dará una lista de ellos*/
    //de aqui pasamos al mapeo de en hibernate.cfg.xml
}