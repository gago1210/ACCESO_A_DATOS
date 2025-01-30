package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestorFicheros {
    //este metodo es publico, no devuelve nada y para funcionar necesito que me de un path que es el camino que quiero leer

    //EJERCICIOS PROPUESTOS:
    //1- obtener todos los nombres de los ficheros del directorio llamado directorio

    //2- crear una subcarpeta en el directorio llamado directorio y crear en ella un fichero, despues, obtener todos los nombres de los ficheros
    //del directorio llamado directorio y el subdirectorio
    //3- listar el nombre de todos los ficheros del sistema (windows, busqueda recursiva- C:/User o /Users)


    public void lecturaDirectorios(String ){
        //1- obtener todos los nombres de los ficheros del directorio llamado directorio
        File file = new File(path);
        String[] nombres = file.list();
        File[] ficheros = file.listFiles();
        for (File item : ficheros) {
            if (!item.isHidden()) {
                System.out.println(item.getName());
            }
        }
    }

    //RECURSIVIDAD: metodos recursivos
    public void lecturaRecursiva(String path) {
        File file = new File(path); //paso a un fichero logico y fisico
        File[] ficheros = file.listFiles();
        for (File item : ficheros) {
            System.out.println(item.getName());
            //pregunto si es directorio
                //si saco todos los ficheros del directorio y los muestro --> no se cuantas veces pregunto

        }
    }
    //Recursividad --> ejecucion que se llama a ella misma, con cuidado tenga una salida
    //esto solo en caso de subdirectorios, es la manera correcta de no hacer un bucle infinito:
    private void lecturaSubdirectorios(File fichero) {
        for (File file : fichero.listFiles()) {
            System.out.println(file.getName());
            if (file.isDirectory()) {
                lecturaSubdirectorios(file);
            }
        }
    }

    public void lecturaTextoPlano(String path) {
        //creo el file:
        File file = new File(path);
        FileReader fileReader = null;

        //pregunto si existe y si ademas el fichero es file porque si es directorio es que ni va a entrar en el bucle:
        if (file.exists() && file.isFile()) {
            //existe y ademas en un fichero, procedemos a su lectura, para ello creamos un fichero file reader y lo inicializo (arriba), como
            //el objeto depende del filereader, lo inicializo. Pongo en modo lectura el file que yo quiero trabajar:
            //tendre que manejar la excepcion, para ello tratamos el posible error.
            //cuando yo trato una excepcion tengo dos opciones, la primera es tratarla inmediatamente en la linea despues de if..., mediante
            //un metodo try donde meto la parte del codigo que puede dar fallo y la capturo con catch, imprimo el fallo de la lectura del fichero.
            //el try catch es muy importante que siempre vaya acompañado del bloque finally porque todo el flujo de datos, cuando se
            //terminan, se cierran, si no se cierra el flujo de datos, el fichero se queda incompleto y en las escrituras da error, es como
            //si no hubieramos guardado un archivo, se pierde info quiza

            try{
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                System.out.println("Fallo en la lectura");
                System.out.println(e.getMessage());

                //bloque optativo: que es el bloque finally, se ejecuta si o si, siempre:
            }finally {
                try{
                    if (fileReader != null) //Nullpointexception
                        fileReader.close(); //nos da error, lo subsanamos metiendo al finally un try
                } catch (IOException e) {
                    System.out.println("Error en el cerrado del flujo");
                }

            }
            fileReader = new FileReader(file);
        }
    }

}

