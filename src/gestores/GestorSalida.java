package gestores;

import java.util.ArrayList;
import modelo.Salida;

/**
 *
 * @author MAYFER
 */
public class GestorSalida extends GestorGral {
    public void Guardar(Salida object) throws Exception {
        super.Guardar(object);
    }
    
    public void Modificar(Salida object) throws Exception {
        super.Modificar(object);
    }
    
    public void Eliminar(Salida object) throws Exception {
        super.Eliminar(object);
    }
    
    public Salida BuscarPorId(long id) throws Exception {
        return (Salida)super.BuscarPorId(Salida.class, id);
    }
    
    public ArrayList<Salida> BuscarTodos() throws Exception {
        return new ArrayList(super.BuscarTodos(Salida.class));
    }
    
    public ArrayList<Salida> BuscarPorFiltro(String filtro) throws Exception {
        return new ArrayList(super.BuscarPorFiltro(Salida.class, filtro));
    }
    
    public ArrayList<Salida> BuscarPorFiltroConOrden(String filtro, String columnaOrden, boolean ordenDescendente) throws Exception {
        return new ArrayList(super.BuscarPorFiltroConOrden(Salida.class, filtro, columnaOrden, ordenDescendente));
    }
}
