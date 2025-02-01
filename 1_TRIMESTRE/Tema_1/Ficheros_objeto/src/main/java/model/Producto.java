package model;

/*
lombok es una libreria que me permite con dos lineas de codigo, la implementamos en pom.xml, y recargamos el proyecto, con esto consigo
si yo quiero llamar al getter y setter de producto, lo escribo con @getter, @setter (ver mas abajo el codigo), @noargsconstructor,
 tengo el constructor creado, @allargsconstructor , ya tengo el constructor con todo, asi es como lo llamamos. De esta manera.
si yo me vuelvo a entrada ya me deja escribir y construir con todo o con nada dependiendo de la opcion de @ que coja.
*/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {

/*SERIALIZABLE --> si yo quiero guardar un objeto que es un producto con unos datos. Esto no se puede guardar a piñon porque no puedo
    guardar un objeto a capón. Este producto se va a guardar a cachitos, es decir, se va a serializar.
    ¿CÓMO SE SERIALIZA? --> guarda en espacio un dato, en otro espacio otro dato, en otro espacio otro y asi, es decir, se guarda en
     trocitos, en bytes. Para que cuando yo lo pueda recuperar, cojo esos cachitos y los DESERIALIZO para asi tener el producto y
     poderlo tratar como la entidad en si.
     En resumen serializar es coger un objeto, hacerlo cachos y despues volver a unirlos para poder acceder a ellos*/

    private static final long serialVersionUID =  23456L;
    private int id;
    private String title;
    private double price;
    private int stock;
    private int discont;
    private String brand;
    public void mostrarDatos(){
        System.out.println("serialVersionUID = " + serialVersionUID);
        System.out.println("id = " + id);
        System.out.println("title = " + title);
        System.out.println("price = " + price);
        System.out.println("stock = " + stock);
    }
}