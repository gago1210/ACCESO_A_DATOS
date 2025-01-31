package resources;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PeticionJSON {

    public void procesarPeticion() {

        // URL -> HTTPCONNECTION -> BUFFEREADER
        String urlString = "https://dummyjson.com/products";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //abrimos la conexion
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //lee la peticion de un flujo entrante
            String linea = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((linea = bufferedReader.readLine()) != null) { //hacemos una lectura recurrente de los datos, con esto se hace la lectura si o si
                stringBuffer.append(linea);
            }

            //tenemos el objeto que lo convertimos en JSON, el cual vamos a ir analizando haciendo preguntas para asi obtener los datos
            JSONObject peticionProducto = new JSONObject(stringBuffer.toString()); //accedo a la lista
            JSONArray listaProductos = peticionProducto.getJSONArray("products"); //accedo a la lista y le pregunto lo que quiero saber, identico a las claves(nombres) de los json
            ArrayList<String> categorias = new ArrayList<>();
            for (Object item : listaProductos) { //iteraccion objeto tipo
                // item es un JSONObject -> YO LO SÉ
                JSONObject producto = (JSONObject) item;
                System.out.println("Titulo: " + producto.getString("title"));
                System.out.println("Precio: " + producto.getDouble("price"));
                JSONArray tags = producto.getJSONArray("tags"); //te pregunto a ti que eres el producto y que me des el tags
                System.out.println("Las categorias del producto son:");
                for (Object tag : tags) {  //me recorres el array tags
                    //System.out.println("\t"+tag);
                    categorias.add(tag.toString());
                }
            }


        } catch (MalformedURLException e) {
            System.out.println("No es una web, por favor intentalo de nuevo");
        } catch (IOException e) {
            System.out.println("Error en la pagina, no responde");
        }
    }

    public void metodoMenu() {

        int opcion = 0;
        Scanner sc = new Scanner(System.in); //metodo para entrar
        // BufferReader br -> lectura por teclar

        do {
            System.out.println("1. Leer");
            System.out.println("2. Buscar");
            System.out.println("3. Filtar");
            System.out.println("4. Exportar");
            System.out.println("5. Salir");
            System.out.println("Que opcion quieres realizar");
            opcion = sc.nextInt();

            switch (opcion) { //que nos se nos olvide poner el brake tras cada opcion para que pare el bucle !!!!!!!!
                                //si yo declaro algo dentro del switch, no se puede acceder directamente porque el case no me deja, solo me deja si
                                    //entro en su ambito que es dentro del bucle del switch, sino, no entra porque no estas entrando dentro del bucle

                case 1:
                    System.out.println("Leer JSON");
                    try {
                        URL url = new URL("https://dummyjson.com/products");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //abro la peticion
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // aqui el bufferedreader va a marcar lo que tu metes dentro de el, vas a hacer una lectura a traves de lo que diga ahi, es como un scanner
                        JSONObject jsonObject = new JSONObject(bufferedReader.readLine()); //la abro
                        JSONArray jsonArray = jsonObject.getJSONArray("products");

                        //si hablamos de variables, estan las primitivas y las complejas:
                        // primitivas-> son las que guardan en memoria un valor, es solo un dato, no puedo hacer nada respecto/con a ellas.
                                        // a, 7, false --> minusculas
                        // complejas -> son las que permiten dar funcionalidad, permiten usarlas, no las estoy tratando como variable primitiva,
                                        // (9,99 + metodos) --> mayusculas
                        //es importante poner la mayusculas porque es el indicativo del tipo de dato y se que es complejo porque apunta a un tipo de dato para asi poder usarlo

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            String title = product.getString("title");
                            String description = product.getString("description");
                            Double price = product.getDouble("price");
                            int stock = product.getInt("stock");
                            System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                                    , title, price, description);
                        }

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.println("Buscar elementos");
                    System.out.println("Cual es el id del elemento que buscas");
                    int id = sc.nextInt();
                    filtrarPorId(id);
                    break;
                case 3:
                    System.out.println("Filtrar elementos");
                    System.out.printf("Introduce precio max");
                    int maxA = sc.nextInt();
                    System.out.printf("Introduce precio min");
                    int minA = sc.nextInt();
                    filtrarPrecio(minA, maxA);
                    break;
                case 4:
                    System.out.println("Exportar elementos");
                    exportarDatos();
                    break;
            }
        } while (opcion != 5);
    }


    // los pasos para exportar datos son:
    //File --> FileWriter --> PrintWriter
    // Leer JSON -> iterar por producto -> escribe una linea
    private void exportarDatos() {
        // File -> FileWriter -> PrintWriter
        File file = new File("src/main/java/resources/productos.txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            // Leer JSON -> iterar por producto -> escribe una linea
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //si falla esta linea, va a captar cualquiera de las dos excepciones catch a continuacion y despues ejecutarse el finally.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            //VOY A PONER PRODUCTO 1, 2, 3 , ETC
            // [p1,p2,p3,p4,p5] ->5
            // binding
            // 0,1,2,3,4 POSICION DE CADA PRODUCTO EN EL ARRAY, PRODUCTO 1, POSICION 0, ETC

            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject object = jsonArray.getJSONObject(i);
                Producto producto = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),Producto.class);
                // System.out.println("title: "+title+", price:"+ 23.23+"', stick:"+ 23);
                // System.out.printf("title:%s, price:%.2f, stick:%d",title,price,stock);
                String exportacionProducto = String.format("title:%s price:%.2f stock:%d", producto.getTitle(),
                        producto.getPrice(), producto.getStock());
                // printWriter.println(exportacionProducto);
                System.out.println(exportacionProducto);
            }

            System.out.println("Exportacion completada");

            // primitivas->a, 7, false
            // complejas -> (9,99 + metodos)


        } catch (MalformedURLException e) {
            System.out.println("Error en URL");
        } catch (IOException e) {
            System.out.println("Error en la creacion de la escritura");
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    private void filtrarPorId(int id) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            // primitivas->a, 7, false
            // complejas -> (9,99 + metodos)
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject product = jsonArray.getJSONObject(i);
                if (id == product.getInt("id")) {
                    String title = product.getString("title");
                    String description = product.getString("description");
                    Double price = product.getDouble("price");
                    int stock = product.getInt("stock");
                    System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                            , title, price, description);
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void filtrarPrecio(int min, int max) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            // primitivas->a, 7, false
            // complejas -> (9,99 + metodos)
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject product = jsonArray.getJSONObject(i);
                double price = product.getDouble("price");
                if (price < max && price > min) {
                    String title = product.getString("title");
                    String description = product.getString("description");

                    int stock = product.getInt("stock");
                    System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                            , title, price, description);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}