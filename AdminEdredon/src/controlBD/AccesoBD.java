package controlBD;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class AccesoBD {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public AccesoBD() {
    }

    public TableModel retornaModelo(String nombreTabla, String consulta) {
        //Creo el modelo para cargarlo en el Grid
        TableModel tableModel = new TableModel(nombreTabla, select(consulta));
        return tableModel;
    }

    public List select(String HQL) {
        List listaObjetos;
        try {
            iniciaSF();
            listaObjetos = session.createQuery(HQL).list();
            return listaObjetos;
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "error: " + e, "Ha ocurrido un error", 0);
        } finally {
            cierraSF();
        }
        return null;
    }

    public void add(Object objeto) {
        try {
            iniciaSF();
            session.save(objeto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e, "Ha ocurrido un error", 0);
        } finally {
            cierraSF();
        }
    }

    public void Update(Object objeto) {
        try {
            iniciaSF();
            session.update(objeto);
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "error: " + e, "Ha ocurrido un error", 0);
        } finally {
            cierraSF();
        }
    }

    public void delete(Object objeto) {
        try {
            iniciaSF();
            session.delete(objeto);
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "error: " + e, "Ha ocurrido un error", 0);
        } finally {
            cierraSF();
        }

    }

    public void iniciaSF() {
        try {
            session = null;
            sessionFactory = PersonFactory.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "error: " + e + "\n"
                    + "En el controlador de producto.", "Error al iniciar Session",
                    0);
        }

    }

    public void cierraSF() {
        try {
            transaction.commit();
            session.flush();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "error: " + e + "\n"
                    + "En el controlador de producto.", "Error al cerrar Session",
                    0);
        }
    }
}
