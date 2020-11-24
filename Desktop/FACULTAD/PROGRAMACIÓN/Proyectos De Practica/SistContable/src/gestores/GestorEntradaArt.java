package gestores;

import java.util.ArrayList;
import modelo.EntradaArt;

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
        return (EntradaArt)super.BuscarPorId(EntradaArt.class, id);
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
}
