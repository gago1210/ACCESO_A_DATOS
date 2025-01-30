import controller.GestorFicheros;
import controller.GestionFicherosEscritura;
import controller.GestorFicherosConjuntos;
import controller.GestorFicherosLectura;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Entrada {
    //en el main van las instancias y llamadas a metodos
    public static void main(String[] args) throws IOException {
        //FILE ->el objeto de tipo file es un fichero de tipo logico porque no sabemos si apunta a algo o no.(fisico o no)
            //no te va a dar error pero te va a indicar que no hay nada, que esta vacio

        //fichero logico, estoy creando aqui el puntero
        File ficheroSinPuntero = new File("C:\\Users\\crist\\OneDrive\\Documentos\\GitHub\\ACCESO_A_DATOS\\1_TRIMESTRE\\Tema_1\\Fichero\\src\\resources\\directorio\\ejemplo_fichero.md") //si yo quiero acceder a ruta absoluta del fichero del directorio
        System.out.println(ficheroSinPuntero.getName()); //nos da el nombre del fichero
        System.out.println(ficheroSinPuntero.getParent()); //nos da la carpeta en la cual esta metido
        System.out.println(ficheroSinPuntero.length()); //nos da la longitud
        System.out.println(ficheroSinPuntero.exists()); //nos dice si existe y si es logico, si no, es que no esta en fisico

         //no es recomendable tratar con rutas absolutas, es mejor tratar con rutas relativas:
        File ficheroSinPuntero = new File("src/resources/directorio/ejemplo_fichero.md"); //ruta relativa

        //podemos obtener informacion como:
        System.out.println(ficheroSinPuntero.getName()); //nos da el nombre del fichero
        System.out.println(ficheroSinPuntero.getParent()); //nos da la carpeta en la cual esta metido
        System.out.println(ficheroSinPuntero.length()); //nos da la longitud
        System.out.println(ficheroSinPuntero.exists()); //nos dice si existe y si es logico, si no, es que no esta en fisico
        System.out.println(ficheroSinPuntero.isDirectory()); // le pregunto si es directorio
        System.out.println(ficheroSinPuntero.isFile()); // le pregunto si es fichero
        System.out.println(Arrays.toString(ficheroSinPuntero.listFiles())); //nos indica lo que hay dentro, nos devuelve un string array con los ficheros que estan dentro del directorio
        System.out.println(Arrays.toString(ficheroSinPuntero.list())); //nos indica lo que hay dentro, nos devuelve un string array, con todas las rutas de los ficheros que estan dentro del directorio

        GestorFicheros gestorFicheros = new GestorFicheros();
        gestorFicheros.lecturaTextoPlano("src/resources/ficheros/lectura.txt");


        //o me interesa crear el fichero en caso de que sea solo logico, para ello primero preguntamos si existe o no para que si no, lo cree:
        //te lo crea si no esta creado, si es que existe, no entra en el bucle
        if (!ficheroSinPuntero.exists()) {
            try {
                ficheroSinPuntero.createNewFile();
            } catch (IOException e) {
                System.out.println("Fallo en la creación del fichero");
            }
            //si la ruta no existe, voy a intentar crear algo donde no esta, se crearia automaticamente para poderlo meter.
        }

        GestorFicherosConjuntos gestorFicherosConjuntos = new GestorFicherosConjuntos();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Introduce que quieres hacer");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    gestorFicherosConjuntos.cifrado("src/resources/ficheros/cifradoCodigo.txt");
                    break;
                case 2:
                    gestorFicherosConjuntos.descifrarMensajeCodigo("src/resources/ficheros/cifradoCodigo.txt");
                    break;
            }
        } while (opcion!=0);


    }
}
