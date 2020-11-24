/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import gestores.GestorArticulo;
import gestores.GestorSalida;
import gestores.GestorSalidaArt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Articulo;
import modelo.Salida;
import modelo.SalidaArt;

/**
 *
 * @author fernando.borovsak
 */
public class ControladorSalida implements ActionListener{
    // private VistaEntrada vista;
    private Salida salida;
    private GestorSalida gestorSalida;
    private GestorSalidaArt gestorSalidaArt;
    
    public ControladorSalida(/*VistaSalida vista, */Salida salida) {
        this.salida = salida;
        
        this.gestorSalida = new GestorSalida();
        this.gestorSalidaArt = new GestorSalidaArt();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
        } catch (Exception ex) {
            try {
                gestorSalida.DeshacerTransaccion();
            } catch (Exception ex1) {
                Logger.getLogger(ControladorArticulo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void CargarArticulosEnCombo() throws Exception {
        GestorArticulo ga = new GestorArticulo();
        ArrayList<Articulo> artis = ga.BuscarTodos();
        // TODO
    }
    
    private void VerificarCampos() throws Exception {
        
    }
    
    private void AgregarArticulo() {
        SalidaArt entradaArt = new SalidaArt();
        
    }
    
    private void QuitarArticulo() throws Exception {
        int indiceArticulo = 0; // TODO: cambiar
        salida.getVentas().remove(indiceArticulo);
        CargarSalidaArticulosEnTabla();
    }
    
    private void ModificarArticulo() throws Exception {
        // TODO
    } 
    
    private void CargarSalidaArticulosEnTabla() throws Exception {
        // TODO
    }
    
    private void GuardarSalida() throws Exception {
        gestorSalida.IniciarTransaccion();
        GestorArticulo ga = new GestorArticulo();
        for(SalidaArt salidaArt : salida.getVentas()) {
            
        }
        gestorSalida.Guardar(salida);
        gestorSalida.EjecutarTransaccion();
    }
}
