package controller;

import java.io.File;

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

}

