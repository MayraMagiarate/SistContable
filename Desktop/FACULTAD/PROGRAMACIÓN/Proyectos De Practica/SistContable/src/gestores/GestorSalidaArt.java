package gestores;

import java.util.ArrayList;
import modelo.SalidaArt;

/**
 *
 * @author MAYFER
 */
public class GestorSalidaArt extends GestorGral {
    public void Guardar(SalidaArt object) throws Exception {
        super.Guardar(object);
    }
    
    public void Modificar(SalidaArt object) throws Exception {
        super.Modificar(object);
    }
    
    public void Eliminar(SalidaArt object) throws Exception {
        super.Eliminar(object);
    }
    
    public SalidaArt BuscarPorId(long id) throws Exception {
        return (SalidaArt)super.BuscarPorId(SalidaArt.class, id);
    }
    
    public ArrayList<SalidaArt> BuscarTodos() throws Exception {
        return new ArrayList(super.BuscarTodos(SalidaArt.class));
    }
    
    public ArrayList<SalidaArt> BuscarPorFiltro(String filtro) throws Exception {
        return new ArrayList(super.BuscarPorFiltro(SalidaArt.class, filtro));
    }
    
    public ArrayList<SalidaArt> BuscarPorFiltroConOrden(String filtro, String columnaOrden, boolean ordenDescendente) throws Exception {
        return new ArrayList(super.BuscarPorFiltroConOrden(SalidaArt.class, filtro, columnaOrden, ordenDescendente));
    }
}
