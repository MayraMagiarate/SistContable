package gestores;

import java.util.ArrayList;
import modelo.Articulo;

/**
 *
 * @author MAYFER
 */
public class GestorArticulo extends GestorGral {
    public void Guardar(Articulo object) throws Exception {
        super.Guardar(object);
    }
    
    public void Modificar(Articulo object) throws Exception {
        super.Modificar(object);
    }
    
    public void Eliminar(Articulo object) throws Exception {
        super.Eliminar(object);
    }
    
    public Articulo BuscarPorId(long id) throws Exception {
        return (Articulo)super.BuscarPorId(Articulo.class, id);
    }
    
    public ArrayList<Articulo> BuscarTodos() throws Exception {
        return new ArrayList(super.BuscarTodos(Articulo.class));
    }
    
    public ArrayList<Articulo> BuscarPorFiltro(String filtro) throws Exception {
        return new ArrayList(super.BuscarPorFiltro(Articulo.class, filtro));
    }
    
    public ArrayList<Articulo> BuscarPorFiltroConOrden(String filtro, String columnaOrden, boolean ordenDescendente) throws Exception {
        return new ArrayList(super.BuscarPorFiltroConOrden(Articulo.class, filtro, columnaOrden, ordenDescendente));
    }
}
