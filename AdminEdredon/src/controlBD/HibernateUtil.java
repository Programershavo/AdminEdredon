package controlBD;
import javax.swing.JOptionPane;
//HIBERNATE
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        try{
            if (sessionFactory == null) {
                //Carga la configuración y los mapeos
                Configuration configuration = new Configuration().configure();
                ServiceRegistry serviceRegistry = 
                        new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();              
                
                //Esto construye un session factory del registro de servicios
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            return sessionFactory;
        }catch(Throwable e){ 
            JOptionPane.showMessageDialog(null, "Creacion del SessionFactory fallida. "+"\n"+e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
}
