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
import javax.swing.JOptionPane;
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
            ga.IniciarTransaccion();
            ga.EjecutarTransaccion();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se puede iniciar la aplicacion: \n" + ex.getMessage());
        }
        
    }
    
}