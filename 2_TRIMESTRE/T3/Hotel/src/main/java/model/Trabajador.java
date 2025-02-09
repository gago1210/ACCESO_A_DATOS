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

@NamedQuery(name = "Trabajador.findAll",query = "FROM Trabajador")
@NamedQuery(name = "Trabajador.findAllByLocalidad"
        ,query = "FROM Trabajador t WHERE t.direccion.localidad=:localidad")
@NamedQuery(name = "Trabajador.findAllByProvincia",query = "FROM Trabajador t WHERE t.direccion.provincia=:provincia")
@NamedQuery(name = "Trabajador.updateName",query = "UPDATE Trabajador t SET t.nombre =:nombre WHERE t.apellido=:apellido")

@Entity //una anotacion para indicar que estos datos van a una tabla
@Table (name = "empleados") //anotacion para indicar que estos datos van a la tabla con el nombre de la clase.
public class Trabajador implements Serializable {
    /*a partir de aqui le indico a donde van cada uno de los atributos en la tabla. Si el nombre de la columna y el atributo es el mismo,
    no hago nada (solo pongo @column), si el nombre es diferente, le pongo @Column(name="jsdfj").

     El identificador tiene un tratamiento especial, y es que tiene su propio @id. Esta columna es una @id porque indica que es primary
     key. El @GeneratedValue nos indica que el elemento generado automaticamente en bbdd, si no lo encuentra, lo va a crear. Ademas le
     indica que la forma de generacion es de tipo strategy, que indica que es un identificados autoincrementable y primary key.*/

    //Todo lo que yo configuro en BBDD lo puedo configurar aqui

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Embedded
    private Direccion direccion;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "localidad",column = @Column(name = "localidad_2")),
            @AttributeOverride(name = "provincia",column = @Column(name = "provincia_2"))

    })
    private Direccion direccion2;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @ManyToMany(mappedBy = "listaTrabajadores")
    private List<Cliente> listaClientes;


    @Column
    private int telefono;

    public Trabajador(String nombre, String apellido, Direccion direccion, Direccion direccion2, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.direccion2 = direccion2;
        this.telefono = telefono;
    }

    public Trabajador(String nombre, String apellido, Direccion direccion, Direccion direccion2, Habitacion habitacion, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.direccion2 = direccion2;
        this.habitacion = habitacion;
        this.telefono = telefono;
    }

    public Trabajador(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion=" + direccion +
                ", direccion2=" + direccion2 +
                ", telefono=" + telefono +
                '}';
    }
}