package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

/*hacemos los mismos metodos que antes cuando haciamos la conexion en DBConnection, aqui mediante la libreria sessionFactory implemento
    los nuevos metodos, basados en hibernate y su libreria. Creamos pues un singleton, para que nadie pueda duplicar el objeto*/


    // getSession
    public SessionFactory getSessionFactory(){

        if(sessionFactory==null){
            createSessionFactory(); //si es nulo, me lo creas
        }

        return sessionFactory;
    }

    // createSession
    private void createSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory(); //nueva configuracion de hibernate
    }


    // closeSession
    public void closeSessionFactory(){
        sessionFactory.close();
        sessionFactory = null;
    }
}