package dao;

import database.HibernateUtil;
import model.Habitacion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.mapping.UnionSubclass;
import org.hibernate.query.Query;

import java.util.List;

public class TrabajadorDAO {
/* para poder transaccionar contra bbdd, necesito una sesion porque la conexion la crea hibernateUtil, el cual me permite crear una
sessionFactory y este me da una session. Lo unico que necesito es un objeto Session */
    private Session session; //creo el objeto session


    public TrabajadorDAO(){ //creo el constructor
        /*inicializo la sesion -> nunca lo hagamos porque la sesion es diferente en el momento en que se crea el dao respecto al
        momento en el que se realiza la transaccion. Este constructor siempre tiene que estar vacio ya que ya la inicializamos cada
        vez que llamo a una transaccion*/
    }

    //inicializamos la transacciones:
    public void insertarTrabajador(Trabajador trabajador) throws ConstraintViolationException {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction(); //obtengo la transaccion
        session.persist(trabajador);
        session.getTransaction().commit();
        session.close(); //cierro la transaccion
    }
    public void seleccionHabitacionTrabajador(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Trabajador trabajador= session.get(Trabajador.class,id);
        System.out.println(trabajador.getHabitacion().getNumero());
        session.getTransaction().commit();
        session.close();
    }
    public void seleccionarTodos(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();;
        session.beginTransaction();
        String querySTR = "FROM Trabajador";
        Query<Trabajador> query = session.createQuery(querySTR,Trabajador.class);
        List<Trabajador> listaTrabajadores = query.list();
        for (Trabajador t: listaTrabajadores ) {
            System.out.println(t);
        }
        session.getTransaction().commit();
        session.close();
    }
    public void seleccionarTodosByLocalidad(String localidad){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();;
        session.beginTransaction();
        String querySTR = "FROM Trabajador t WHERE t.direccion.localidad=:localidad";
        Query<Trabajador> query = session.createQuery(querySTR,Trabajador.class);
        query.setParameter("localidad",localidad);
        List<Trabajador> listaTrabajadores = query.list();
        for (Trabajador t: listaTrabajadores ) {
            System.out.println(t);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void seleccionarTodosByProvincia(String provincia){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Trabajador> query = session.createNamedQuery("Trabajador.findAllByProvincia",Trabajador.class);
        query.setParameter("provincia",provincia);
        List<Trabajador> listaTrabajadores = query.list();
        for (Trabajador trabajador: listaTrabajadores) {
            System.out.println(trabajador);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void actualizarNombre(String nombre){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //         String querySTR = "UPDATE Trabajador SET direccion.provincia = :direccion WHERE nombre = :nombre";
        String querySTR = "UPDATE Trabajador SET nombre=:nombre WHERE apellido = :apellido";
        Query query = session.createQuery(querySTR);
        query.setParameter("nombre","BorjaNuevo");
        query.setParameter("apellido","Martin");
        int row = query.executeUpdate();
        System.out.println("El numero de filas afectadas es de "+row);

        session.getTransaction().commit();
        session.close();
    }

    public void insertarTrabajador(Trabajador trabajador, int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(trabajador);
        Habitacion habitacion = session.get(Habitacion.class,id);
        trabajador.setHabitacion(habitacion);
        session.merge(trabajador);
        session.getTransaction().commit();
        session.close();
    }

    public void insertarTrabajador(Trabajador trabajador, Habitacion habitacion){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        trabajador.setHabitacion(habitacion);
        session.persist(trabajador);
        session.getTransaction().commit();
        session.close();
    }




}