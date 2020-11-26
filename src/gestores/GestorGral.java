package gestores;
import java.util.List;
import modelo.EntidadBasica;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import persistencia.InstructorConexion;

/**
 *
 * @author MAYFER
 */
public abstract class GestorGral {

    private static Session sesion = null;
    private static Transaction tx = null;
    
    protected Session getSession() {
        Session s = InstructorConexion.getSessionFactory().getCurrentSession();
        if (!s.isOpen()) {
            s = InstructorConexion.getSessionFactory().openSession();
        }
        if (!s.getTransaction().isActive()) {
            s.beginTransaction();
        }
        return s;
    }
    
    protected Transaction getTransaction() {
        Session s = getSession();
        Transaction tx = s.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
        return tx;
    }

    public void IniciarTransaccion() throws Exception {
        try {
            sesion = getSession();
            tx = getTransaction();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void EjecutarTransaccion() throws Exception {
        try {
            if (tx.isActive()) {
                tx.commit();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void DeshacerTransaccion() throws Exception {
        try {
            if (tx.isActive()) {
                tx.rollback();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    protected void Guardar(EntidadBasica object) throws Exception {
        try {
            sesion.save(object);
            System.out.println("objeto Guardado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    protected void Eliminar(EntidadBasica object) throws Exception {
        try {
            sesion.delete(object);
            System.out.println("objeto eliminado.");

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    protected void Modificar(EntidadBasica object) throws Exception {
        try {
            sesion.update(object);
            System.out.println("objeto Modificado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    protected Object BuscarPorId(Class clase, long id) throws Exception {
        try {
            Session se = getSession(); // inicio Sesion
            Object object = se.get(clase, id); // creo un objeto donde se carga lo que le paso por parametro, que seria otro objeto.
            return object;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    protected List<Object> BuscarTodos(Class clase) throws Exception {
        try {
            Session se = getSession(); // inicio Sesion
            List objetos = se.createCriteria(clase).list();
            /* createCriteria es un metodo que utiliza hql (lenguaje de queries de hibernate)
                                                                                            equivale al FROM de un SELECT * FROM */
            return objetos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    protected List<Object> BuscarPorFiltro(Class clase, String filtro) throws Exception {
        try {
            Session se = getSession(); // inicio Sesion
            Criteria cr = se.createCriteria(clase);
            cr.add(Restrictions.sqlRestriction(filtro));
            List objetos = cr.list();
            return objetos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    protected List<Object> BuscarPorFiltroConOrden(Class clase, String filtro, String columnaOrden, boolean ordenDescendente) throws Exception {
        try {
            Session se = getSession(); // inicio Sesion
            Criteria cr = se.createCriteria(clase);
            cr.add(Restrictions.sqlRestriction(filtro));
            if (ordenDescendente == false) {
                cr.addOrder(Order.asc(columnaOrden));
            } else {
                cr.addOrder(Order.desc(columnaOrden));
            }
            List objetos = cr.list();
            return objetos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
