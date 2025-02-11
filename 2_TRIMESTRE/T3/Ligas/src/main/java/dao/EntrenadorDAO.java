package dao;

import database.HibernateUtil;
import model.Entrenador;
import org.hibernate.Session;

public class EntrenadorDAO {
    private Session session;

    public void agregarEntrenador(Entrenador entrenador){ // quiero agregar un entrenador a la bbdd
        session = new HibernateUtil().getSessionFactory().getCurrentSession(); //saco la sesion
        session.beginTransaction(); // inicializo la transaccion -->ABRO
        session.persist(entrenador); //ejecuto el metodo persist y persisto el entrenador.
        session.getTransaction().commit(); //a traves de la transaccion que estoy ejecutando, hago un commit -->GARANTIZO
        session.close(); //cierro la sesion -->CIERRO
    }//asi es como agrego, guardo un dato en la bbdd

    public Entrenador obtenerEntranador(int id){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Entrenador entrenador = session.get(Entrenador.class,1);
        session.getTransaction().commit();
        session.close();
        return entrenador;
    }
}