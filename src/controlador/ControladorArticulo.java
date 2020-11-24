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
import javax.swing.table.DefaultTableModel;
import modelo.Articulo;
import vista.VistaArticulo;

/**
 *
 * @author fernando.borovsak
 */
public class ControladorArticulo implements ActionListener {
    private VistaArticulo vista;
    private Articulo articulo;
    private GestorArticulo gestorArticulo;
    
    public ControladorArticulo(VistaArticulo vista, Articulo articulo){
        this.vista = vista;
        this.articulo = articulo;
        
        this.vista.setVisible(true);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        
        this.gestorArticulo = new GestorArticulo(); // aca ya se instancio el gestor (podemos utilizarlo en toda la clase)
    }
    
    public void iniciarVista() throws Exception {
        vista.setLocationRelativeTo(null);
        cargarArticulosEnTabla();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(this.vista.btnAgregar)) {
                verificarCampos();
                setearModelo();
                guardarArticulo();
                limpiarArticulo();
                cargarArticulosEnTabla();
            }
        } catch (Exception ex) {
            try {
                gestorArticulo.DeshacerTransaccion();
            } catch (Exception ex1) {
                Logger.getLogger(ControladorArticulo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private boolean existeArticuloConCodigo(int cod) throws Exception {
        ArrayList<Articulo> artis = gestorArticulo.BuscarPorFiltro("cod = " + Integer.toString(cod));
        if (artis.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    private void verificarCampos() throws Exception {
        if (vista.txtCodigo.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un código");
        } else {
            if (!EsEnteroPositivoMayorACero(vista.txtCodigo.getText())) {
                throw new Exception("El código debe ser un valor numerico positivo");
            }
        }
        if (vista.txtNombre.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un nombre");
        }
        if (vista.txtDescrip.getText().isEmpty()) {
            throw new Exception("Se debe ingresar una descripción");
        }
        if (vista.txtStockMin.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un stock mínimo");
        } else {
            if (!EsEnteroPositivoMayorACero(vista.txtCodigo.getText())) {
                throw new Exception("El stock mínimo debe ser un valor numerico positivo");
            }
        }
        if (vista.txtStockMax.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un stock máximo");
        } else {
            if (!EsEnteroPositivoMayorACero(vista.txtCodigo.getText())) {
                throw new Exception("El stock mínimo debe ser un valor numerico positivo");
            }
        }
    }
    
    private void setearModelo() throws Exception {
        articulo.setCod(Integer.parseInt(vista.txtCodigo.getText()));
        if (existeArticuloConCodigo(articulo.getCod())) {
            throw new Exception("Ya existe un artículo con el código ingresado");
        }
        articulo.setNombre(vista.txtNombre.getText());
        articulo.setDescrip(vista.txtDescrip.getText());
        articulo.setStockMin(Integer.parseInt(vista.txtStockMin.getText()));
        articulo.setStockMax(Integer.parseInt(vista.txtStockMax.getText()));
        if (articulo.getStockMin() >= articulo.getStockMax()) {
            throw new Exception("El stock mínimo no puede ser mayor al stock máximo");
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
    
    private void guardarArticulo() throws Exception {
        gestorArticulo.IniciarTransaccion();
        gestorArticulo.Guardar(articulo);
        gestorArticulo.EjecutarTransaccion();
    }
    
    private void modificarArticulo() throws Exception {
        gestorArticulo.IniciarTransaccion();
        gestorArticulo.Modificar(articulo);
        gestorArticulo.EjecutarTransaccion();
    }
    
    private void eliminarArticulo() throws Exception {
        gestorArticulo.IniciarTransaccion();
        gestorArticulo.Eliminar(articulo);
        gestorArticulo.EjecutarTransaccion();
    }
    
    private void cargarArticulosEnTabla() throws Exception {
        String col[] = {"Cod.","Nombre","Descrp.", "Stock min.", "Stock Max.", "Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        vista.tListaArticulos.setModel(tableModel);
        ArrayList<Articulo> artis = gestorArticulo.BuscarPorFiltroConOrden("", "cod", false);
        for (Articulo arti : artis) {
            Object[] fila = {
                arti.getCod(),
                arti.getNombre(),
                arti.getDescrip(),
                arti.getStockMin(),
                arti.getStockMax(),
                arti.getStock()
            };
            tableModel.addRow(fila);
        }
    }
    
    private void limpiarArticulo() {
        articulo = new Articulo();
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtDescrip.setText("");
        vista.txtStockMin.setText("");
        vista.txtStockMax.setText("");
    }
}
