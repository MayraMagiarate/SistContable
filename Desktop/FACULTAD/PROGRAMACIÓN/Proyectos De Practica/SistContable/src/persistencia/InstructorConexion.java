/*
 * Esta clase Lee los parametros de configuracion escritos en el archivo hibernate.cfg.xml
y se las entrega el motor de hibernate para que hibernate sepa como trabajar (le da las especificaciones de trabajo)
 */
package persistencia;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author MAYFER
 */
public class InstructorConexion {
    /*Creo de que manera va a generarse la Session*/
     private static ServiceRegistry serviceRegistry;/* Para poder crear una sesion yo debo configurar el modo 
                                                       o de que manera quiero que hibernate maneje esa sesion,
                                                       para ello debo crear una variable de tipo ServiceRegistry.*/
     
   /*Creo la variable Sesion que voy a usar para usar la Base de Datos /Conectarme a la base de datos */
     private static final SessionFactory sessionFactory; /* sessionFactory es una variable que sirve para generar sesiones de uso a la base de datos
                                                            ( o sea para usar cualquier cosa yo deberia iniciar sesion)*/

    static {// Construye un constructor estatico cuyo fin es que solo pueda instanciarse una unica vez. Este se invoca solo la primera vez que se invoca el objeto.
        try {
            Configuration configuracion = new Configuration();
            configuracion.configure("persistencia/hibernate.cfg.xml");         
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuracion.getProperties()).build();
            sessionFactory = configuracion.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Falló la creacion del objeto sessionFactory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

  public static SessionFactory getSessionFactory(){
  return sessionFactory;
  }  
}