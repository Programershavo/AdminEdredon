package controlBD;



import javax.swing.JOptionPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class PersonFactory {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            return new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch(Throwable e){ 
            JOptionPane.showMessageDialog(null, "Creacion del SessionFactory fallida. "+"\n"+e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
