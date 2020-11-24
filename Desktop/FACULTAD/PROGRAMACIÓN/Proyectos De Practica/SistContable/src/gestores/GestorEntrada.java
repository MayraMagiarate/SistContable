package gestores;

import java.util.ArrayList;
import modelo.Entrada;

/**
 *
 * @author MAYFER
 */
public class GestorEntrada extends GestorGral {
    public void Guardar(Entrada object) throws Exception {
        super.Guardar(object);
    }
    
    public void Modificar(Entrada object) throws Exception {
        super.Modificar(object);
    }
    
    public void Eliminar(Entrada object) throws Exception {
        super.Eliminar(object);
    }
    
    public Entrada BuscarPorId(long id) throws Exception {
        return (Entrada)super.BuscarPorId(Entrada.class, id);
    }
    
    public ArrayList<Entrada> BuscarTodos() throws Exception {
        return new ArrayList(super.BuscarTodos(Entrada.class));
    }
    
    public ArrayList<Entrada> BuscarPorFiltro(String filtro) throws Exception {
        return new ArrayList(super.BuscarPorFiltro(Entrada.class, filtro));
    }
    
    public ArrayList<Entrada> BuscarPorFiltroConOrden(String filtro, String columnaOrden, boolean ordenDescendente) throws Exception {
        return new ArrayList(super.BuscarPorFiltroConOrden(Entrada.class, filtro, columnaOrden, ordenDescendente));
    }
}
