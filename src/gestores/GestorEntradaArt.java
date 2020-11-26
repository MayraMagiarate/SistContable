package gestores;

import java.util.ArrayList;
import java.util.List;
import modelo.EntradaArt;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import persistencia.InstructorConexion;

/**
 *
 * @author MAYFER
 */
public class GestorEntradaArt extends GestorGral {

    public void Guardar(EntradaArt object) throws Exception {
        super.Guardar(object);
    }

    public void Modificar(EntradaArt object) throws Exception {
        super.Modificar(object);
    }

    public void Eliminar(EntradaArt object) throws Exception {
        super.Eliminar(object);
    }

    public EntradaArt BuscarPorId(long id) throws Exception {
        return (EntradaArt) super.BuscarPorId(EntradaArt.class, id);
    }

    public ArrayList<EntradaArt> BuscarTodos() throws Exception {
        return new ArrayList(super.BuscarTodos(EntradaArt.class));
    }

    public ArrayList<EntradaArt> BuscarPorFiltro(String filtro) throws Exception {
        return new ArrayList(super.BuscarPorFiltro(EntradaArt.class, filtro));
    }

    public ArrayList<EntradaArt> BuscarPorFiltroConOrden(String filtro, String columnaOrden, boolean ordenDescendente) throws Exception {
        return new ArrayList(super.BuscarPorFiltroConOrden(EntradaArt.class, filtro, columnaOrden, ordenDescendente));
    }

    public ArrayList<EntradaArt> BuscarPorFiltroOrdenadoPorFecha(String filtro, boolean ordenDescendente) {
        Session se = getSession(); // inicio Sesion
        Criteria cr = se.createCriteria(EntradaArt.class, "entradaArt");
        cr.createAlias("entradaArt.entrada", "entrada");
        cr.add(Restrictions.sqlRestriction(filtro));
        if (ordenDescendente == false) {
            cr.addOrder(Order.asc("entrada.fecha"));
        } else {
            cr.addOrder(Order.desc("entrada.fecha"));
        }
        List objetos = cr.list();
        return new ArrayList(objetos);
    }
}
