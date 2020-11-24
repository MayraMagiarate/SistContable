/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import gestores.GestorArticulo;
import gestores.GestorEntrada;
import gestores.GestorEntradaArt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Articulo;
import modelo.Entrada;
import modelo.EntradaArt;

/**
 *
 * @author fernando.borovsak
 */
public class ControladorEntrada implements ActionListener{
    // private VistaEntrada vista;
    private Entrada entrada;
    private GestorEntrada gestorEntrada;
    private GestorEntradaArt gestorEntradaArt;
    
    public ControladorEntrada(/*VistaEntrada vista, */Entrada entrada){
        this.entrada = entrada;
        
        this.gestorEntrada = new GestorEntrada(); // aca ya se instancio el gestor (podemos utilizarlo en toda la clase)
        this.gestorEntradaArt = new GestorEntradaArt();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
        } catch (Exception ex) {
            try {
                gestorEntrada.DeshacerTransaccion();
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
        EntradaArt entradaArt = new EntradaArt();
        
    }
    
    private void QuitarArticulo() throws Exception {
        int indiceArticulo = 0; // TODO: cambiar
        entrada.getEntradas().remove(indiceArticulo);
        CargarEntradaArticulosEnTabla();
    }
    
    private void ModificarArticulo() throws Exception {
        // TODO
    } 
    
    private void CargarEntradaArticulosEnTabla() throws Exception {
        // TODO
    }
    
    private void GuardarEntrada() throws Exception {
        gestorEntrada.IniciarTransaccion();
        GestorArticulo ga = new GestorArticulo();
        for(EntradaArt entradaArt : entrada.getEntradas()) {
            
        }
        gestorEntrada.Guardar(entrada);
        gestorEntrada.EjecutarTransaccion();
    }
}
