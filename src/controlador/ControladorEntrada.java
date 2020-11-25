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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    private int indiceEnEdicion = -1;

    public ControladorEntrada(VistaEntradas vista, Entrada entrada) {
        this.vista = vista;
        this.entrada = entrada;

        this.vista.setVisible(true);
        this.vista.cmbCodigo.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnGuardarTodo.addActionListener(this);

        this.gestorEntrada = new GestorEntrada(); // aca ya se instancio el gestor (podemos utilizarlo en toda la clase)
    }

    public void iniciarVista() throws Exception {
        vista.setLocationRelativeTo(null);
        cargarArticulosEnCombo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(this.vista.cmbCodigo)) {
                cargarArticuloSeleccionado();
            } else if (e.getSource().equals(this.vista.btnAgregar)) {
                verificarCamposItem();
                agregarArticulo();
                limpiarDatosEntradaArticulo();
                cargarEntradaArticulosEnTabla();
            } else if (e.getSource().equals(this.vista.btnModificar)) {
                modificarArticulo();
            } else if (e.getSource().equals(this.vista.btnEliminar)) {
                quitarArticulo();
            } else if (e.getSource().equals(this.vista.btnGuardarTodo)) {
                verificarCamposGeneral();
                guardarEntrada();
                limpiarTodo();
                cargarEntradaArticulosEnTabla();
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
        vista.cmbCodigo.addItem(new Articulo());
        ArrayList<Articulo> artis = ga.BuscarTodos();
        for (Articulo arti : artis) {
            vista.cmbCodigo.addItem(arti);
        }
    }

    private void cargarArticuloSeleccionado() {
        Articulo arti = (Articulo) vista.cmbCodigo.getSelectedItem();
        vista.txtArticulo.setText(arti.getDescrip());
    }

    private void verificarCamposGeneral() throws Exception {
        if (vista.dateFecha.getCalendar()== null) {
            throw new Exception("Se debe ingresar una fecha");
        } else {
            Calendar fechaHoy = Calendar.getInstance();
            if (fechaHoy.compareTo(vista.dateFecha.getCalendar()) < 0) {
                throw new Exception("La fecha seleccionada debe ser igual o anterior al día de hoy");
            }
        }
        if (vista.txtFactura.getText().isEmpty()) {
            throw new Exception("Se debe ingresar un número de factura");
        }
        if (entrada.getEntradas().isEmpty()) {
            throw new Exception("Se debe ingresar al menos una entrada de artículo");
        }
    }

    private void verificarCamposItem() throws Exception {
        if (vista.cmbCodigo.getSelectedIndex() <= 0) {
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
        entradaArt.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
        entradaArt.setExistencia(entradaArt.getCantidad());
        entradaArt.setCostoUnit(Double.parseDouble(vista.txtCostoUnit.getText()));
        entradaArt.setArticulo((Articulo) vista.cmbCodigo.getSelectedItem());
        entradaArt.setEntrada(entrada);

        if (indiceEnEdicion == -1) {
            entrada.getEntradas().add(entradaArt);
        } else {
            entrada.getEntradas().set(indiceEnEdicion, entradaArt);
            indiceEnEdicion = -1;
        }

    }

    private void quitarArticulo() throws Exception {
        int indiceArticulo = vista.tListaArticulos.getSelectedRow();
        if (indiceArticulo >= 0) {
            entrada.getEntradas().remove(indiceArticulo);
            cargarEntradaArticulosEnTabla();
        } else {
            throw new Exception("Debe seleccionar una entrada que desee eliminar");
        }
        if (indiceEnEdicion == indiceArticulo) {
            indiceEnEdicion = -1;
        }
    }

    private void modificarArticulo() throws Exception {
        int indiceArticulo = vista.tListaArticulos.getSelectedRow();
        if (indiceArticulo >= 0) {
            EntradaArt entra = entrada.getEntradas().get(indiceArticulo);
            vista.cmbCodigo.setSelectedItem(entra.getArticulo());
            vista.txtCantidad.setText(String.valueOf(entra.getCantidad()));
            vista.txtCostoUnit.setText(String.valueOf(entra.getCostoUnit()));
            indiceEnEdicion = indiceArticulo;
        } else {
            throw new Exception("Debe seleccionar una entrada que desee modificar");
        }
    }

    private void cargarEntradaArticulosEnTabla() throws Exception {
        String col[] = {"Cod.", "Articulo", "Cantidad", "Costo unit."};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        vista.tListaArticulos.setModel(tableModel);
        for (EntradaArt entArt : entrada.getEntradas()) {
            Object[] fila = {
                entArt.getArticulo().getCod(),
                entArt.getArticulo().getNombre(),
                entArt.getCantidad(),
                entArt.getCostoUnit()
            };
            tableModel.addRow(fila);
        }
    }

    private void limpiarDatosEntradaArticulo() {
        vista.cmbCodigo.setSelectedIndex(0);
        vista.txtCantidad.setText("");
        vista.txtCostoUnit.setText("");
    }
    
    private void limpiarTodo() {
        vista.dateFecha.setCalendar(null);
        vista.txtFactura.setText("");
        limpiarDatosEntradaArticulo();
        indiceEnEdicion = -1;
        entrada = new Entrada();
    }

    private void guardarEntrada() throws Exception {
        entrada.setFecha(vista.dateFecha.getCalendar());
        entrada.setFacturaProveedor(vista.txtFactura.getText());
        try {
            gestorEntrada.IniciarTransaccion();
            for (EntradaArt entradaArt : entrada.getEntradas()) {
                Articulo arti = entradaArt.getArticulo();
                int nuevoStock = arti.getStock() + entradaArt.getCantidad();
                if (nuevoStock > arti.getStockMax()) {
                    throw new Exception("La cantidad ingresada para el artículo '" + arti.getCod() + " - " + arti.getNombre() + "' supera el stock máximo. " + 
                            "Solo se pueden ingresar " + (arti.getStockMax() - arti.getStock()) + " unidades como máximo");
                } else {
                    arti.setStock(nuevoStock);
                }
            }
            gestorEntrada.Guardar(entrada);
            gestorEntrada.EjecutarTransaccion();
        } catch (Exception ex) {
            gestorEntrada.DeshacerTransaccion();
            throw ex;
        }

    }

}
