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
import vista.VistaEntradas;

/**
 *
 * @author fernando.borovsak
 */
public class ControladorEntrada implements ActionListener {
    private VistaEntradas vista;
    private Entrada entrada;
    private GestorEntrada gestorEntrada;
    private GestorEntradaArt gestorEntradaArt;
    
    public ControladorEntrada(VistaEntradas vista, Entrada entrada){
        this.vista = vista;
        this.entrada = entrada;
        
        this.vista.setVisible(true);
        this.vista.jComboBox1.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnGuardarTodo.addActionListener(this);
        
        this.gestorEntrada = new GestorEntrada(); // aca ya se instancio el gestor (podemos utilizarlo en toda la clase)
        this.gestorEntradaArt = new GestorEntradaArt();
    }
    
    public void iniciarVista() throws Exception {
        vista.setLocationRelativeTo(null);
        cargarArticulosEnCombo();
        cargarEntradaArticulosEnTabla();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(this.vista.jComboBox1)) {
                cargarArticuloSeleccionado();
            }
            if (e.getSource().equals(this.vista.txtCostoUnit)) {
                System.out.println(e.getActionCommand());
            }
        } catch (Exception ex) {
            try {
                gestorEntrada.DeshacerTransaccion();
            } catch (Exception ex1) {
                Logger.getLogger(ControladorArticulo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void cargarArticulosEnCombo() throws Exception {
        GestorArticulo ga = new GestorArticulo();
        ArrayList<Articulo> artis = ga.BuscarTodos();
        for (Articulo arti : artis) {
            vista.jComboBox1.addItem(arti);
        }
    }
    
    private void cargarArticuloSeleccionado() {
        Articulo arti = (Articulo)vista.jComboBox1.getSelectedItem();
        vista.txtArticulo.setText(arti.getNombre());
    }
    
    private void verificarCampos() throws Exception {
        if (vista.txtFactura.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un número de factura");
        }
        if (vista.jComboBox1.getSelectedIndex()< 0) {
            throw new Exception("Se debe seleccionar un código");
        }
        if (vista.txtCantidad.getText().isEmpty()) {
            throw new Exception("Se debe ingresar una cantidad");
        } else {
            if (!EsEnteroPositivoMayorACero(vista.txtCantidad.getText())) {
                throw new Exception("La cantidad debe ser un valor numerico positivo");
            }
        }
        if (vista.txtCostoUnit.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un costo unitario");
        } else {
            if (!EsDecimalPositivoMayorACero(vista.txtCostoUnit.getText())) {
                throw new Exception("El costo unitario debe ser un valor decimal positivo");
            }
        }
    }
    
    private boolean EsEnteroPositivoMayorACero(String valor) {
        try {
            int val = Integer.parseInt(valor);
            if (val > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    private boolean EsDecimalPositivoMayorACero(String valor) {
        try {
            double val = Double.parseDouble(valor);
            if (val > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    private void agregarArticulo() {
        EntradaArt entradaArt = new EntradaArt();
        
    }
    
    private void quitarArticulo() throws Exception {
        int indiceArticulo = 0; // TODO: cambiar
        entrada.getEntradas().remove(indiceArticulo);
        cargarEntradaArticulosEnTabla();
    }
    
    private void modificarArticulo() throws Exception {
        GestorArticulo ga = new GestorArticulo();
        ArrayList<Articulo> artis = ga.BuscarTodos();
    } 
    
    private void cargarEntradaArticulosEnTabla() throws Exception {
        
    }
    
    private void guardarEntrada() throws Exception {
        gestorEntrada.IniciarTransaccion();
        GestorArticulo ga = new GestorArticulo();
        for(EntradaArt entradaArt : entrada.getEntradas()) {
            
        }
        gestorEntrada.Guardar(entrada);
        gestorEntrada.EjecutarTransaccion();
    }

    
}
