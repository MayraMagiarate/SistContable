/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistcontable;

import gestores.GestorArticulo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Articulo;

/**
 *
 * @author MAYFER
 */
public class SistContable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GestorArticulo ga = new GestorArticulo();
            /*ga.IniciarTransaccion();
            Articulo arti = new Articulo();
            arti.setCod(333);
            arti.setNombre("Ladrilli");
            arti.setDescrip("Que se yi");
            arti.setStockMin(1000);
            arti.setStockMax(100000);
            ga.Guardar(arti);
            ga.EjecutarTransaccion();*/
            ArrayList<Articulo> artis = ga.BuscarPorFiltroConOrden("cod >= 333", "descrip", false);
            if (artis.size() > 0) {
                System.out.println(artis.size());
            }
        } catch (Exception ex) {
            Logger.getLogger(SistContable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
