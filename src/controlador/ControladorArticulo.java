/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import gestores.GestorArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Articulo;

/**
 *
 * @author fernando.borovsak
 */
public class ControladorArticulo implements ActionListener {
    // private VistaArticulo vista;
    private Articulo articulo;
    private GestorArticulo gestorArticulo;
    
    public ControladorArticulo(/*VistaArticulo vista, */Articulo articulo){
        this.articulo = articulo;
        
        this.gestorArticulo = new GestorArticulo(); // aca ya se instancio el gestor (podemos utilizarlo en toda la clase)
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
        } catch (Exception ex) {
            try {
                gestorArticulo.DeshacerTransaccion();
            } catch (Exception ex1) {
                Logger.getLogger(ControladorArticulo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private boolean ExisteArticuloConCodigo(int cod) throws Exception {
        ArrayList<Articulo> artis = gestorArticulo.BuscarPorFiltro("cod = " + Integer.toString(cod));
        if (artis.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    private void VerificarCampos() throws Exception {
        
    }
    
    private void GuardarArticulo() throws Exception {
        gestorArticulo.IniciarTransaccion();
        gestorArticulo.Guardar(articulo);
        gestorArticulo.EjecutarTransaccion();
    }
    
    private void ModificarArticulo() throws Exception {
        gestorArticulo.IniciarTransaccion();
        gestorArticulo.Modificar(articulo);
        gestorArticulo.EjecutarTransaccion();
    }
    
    private void EliminarArticulo() throws Exception {
        gestorArticulo.IniciarTransaccion();
        gestorArticulo.Eliminar(articulo);
        gestorArticulo.EjecutarTransaccion();
    }
    
    private void CargarArticulosEnTabla() throws Exception {
        ArrayList<Articulo> artis = gestorArticulo.BuscarPorFiltroConOrden("", "cod", false);
        // TODO
    }
    
    private void LimpiarArticulo() {
        articulo = new Articulo();
    }
}
