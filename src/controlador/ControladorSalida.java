/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import gestores.GestorArticulo;
import gestores.GestorEntradaArt;
import gestores.GestorSalida;
import gestores.GestorSalidaArt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Articulo;
import modelo.EntradaArt;
import modelo.Salida;
import modelo.SalidaArt;
import vista.VistaVentas;

/**
 *
 * @author fernando.borovsak
 */
public class ControladorSalida implements ActionListener {

    private Salida salida;
    private VistaVentas vista;
    private GestorSalida gestorSalida;
    private int indiceEnEdicion = -1;
    private final String SIST_PEPS = "PEPS";
    private final String SIST_UEPS = "UEPS";
    private final String SIST_PROM = "PROM";
    private final double GANANCIA = 0.35;

    public ControladorSalida(VistaVentas vista, Salida salida) {
        this.salida = salida;
        this.vista = vista;
        this.vista.setVisible(true);
        this.vista.cmbArticulo.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnGuardarTodo.addActionListener(this);
        this.vista.rdbPeps.addActionListener(this);
        this.vista.rdbUeps.addActionListener(this);
        this.vista.rdbPromedio.addActionListener(this);
        this.gestorSalida = new GestorSalida();
    }

    public void iniciarVista() throws Exception {
        vista.setLocationRelativeTo(null);
        cargarArticulosEnCombo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(this.vista.cmbArticulo)) {
                cargarArticuloSeleccionado();
            } else if (e.getSource().equals(this.vista.rdbPeps)) {
                vista.txtPrecioTotal.setText(calcularPrecioTotal());
            } else if (e.getSource().equals(this.vista.rdbUeps)) {
                vista.txtPrecioTotal.setText(calcularPrecioTotal());
            } else if (e.getSource().equals(this.vista.rdbPromedio)) {
                vista.txtPrecioTotal.setText(calcularPrecioTotal());
            } else if (e.getSource().equals(this.vista.txtCantidad)) {
                vista.txtPrecioTotal.setText(calcularPrecioTotal());
            } else if (e.getSource().equals(this.vista.btnAgregar)) {
                verificarCamposItem();
                agregarArticulo();
                limpiarDatosSalidaArticulo();
                cargarSalidaArticulosEnTabla();
            } else if (e.getSource().equals(this.vista.btnModificar)) {
                modificarArticulo();
            } else if (e.getSource().equals(this.vista.btnEliminar)) {
                quitarArticulo();
            } else if (e.getSource().equals(this.vista.btnGuardarTodo)) {
                verificarCamposGeneral();
                guardarSalida();
                limpiarTodo();
                cargarSalidaArticulosEnTabla();
            }
        } catch (Exception ex) {
            try {
                gestorSalida.DeshacerTransaccion();
            } catch (Exception ex1) {
                Logger.getLogger(ControladorArticulo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void cargarArticulosEnCombo() throws Exception {
        GestorArticulo ga = new GestorArticulo();
        vista.cmbArticulo.addItem(new Articulo());
        ArrayList<Articulo> artis = ga.BuscarPorFiltro("stock > 0");
        for (Articulo arti : artis) {
            vista.cmbArticulo.addItem(arti);
        }
    }

    private void cargarArticuloSeleccionado() throws Exception {
        Articulo arti = (Articulo) vista.cmbArticulo.getSelectedItem();
        vista.txtArticulo.setText(arti.getDescrip());
        calcularPrecioTotal();
    }

    private void verificarCamposGeneral() throws Exception {
        if (vista.dateFecha.getCalendar() == null) {
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
        if (salida.getVentas().isEmpty()) {
            throw new Exception("Se debe ingresar al menos una salida de artículo");
        }
    }

    private void verificarCamposItem() throws Exception {
        if (vista.cmbArticulo.getSelectedIndex() <= 0) {
            throw new Exception("Se debe seleccionar un código");
        }
        if (vista.txtCantidad.getText().isEmpty()) {
            throw new Exception("Se debe ingresar una cantidad");
        } else {
            if (!EsEnteroPositivoMayorACero(vista.txtCantidad.getText())) {
                throw new Exception("La cantidad debe ser un valor numerico positivo");
            }
        }
        if (!vista.rdbPeps.isSelected() && !vista.rdbUeps.isSelected() && !vista.rdbPromedio.isSelected()) {
            throw new Exception("Debe seleccionar un método de cálculo de precio");
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

    private void agregarArticulo() {
        SalidaArt salidaArt = new SalidaArt();
        salidaArt.setArticulo((Articulo) vista.cmbArticulo.getSelectedItem());
        salidaArt.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
        salidaArt.setPrecioTotal(Double.parseDouble(vista.txtPrecioTotal.getText()));
        salidaArt.setSistema(obtenerSistemaUsado());
        salidaArt.setSalida(salida);

        if (indiceEnEdicion == -1) {
            salida.getVentas().add(salidaArt);
        } else {
            salida.getVentas().set(indiceEnEdicion, salidaArt);
            indiceEnEdicion = -1;
        }
    }

    private String obtenerSistemaUsado() {
        String sistema = "";
        if (vista.rdbPeps.isSelected()) {
            sistema = SIST_PEPS;
        } else if (vista.rdbUeps.isSelected()) {
            sistema = SIST_UEPS;
        } else if (vista.rdbPromedio.isSelected()) {
            sistema = SIST_PROM;
        }
        return sistema;
    }

    private void quitarArticulo() throws Exception {
        int indiceArticulo = 0; // TODO: cambiar
        salida.getVentas().remove(indiceArticulo);
        cargarSalidaArticulosEnTabla();
    }

    private void modificarArticulo() throws Exception {
        int indiceArticulo = vista.tListaArticulos.getSelectedRow();
        if (indiceArticulo >= 0) {
            SalidaArt sali = salida.getVentas().get(indiceArticulo);
            vista.cmbArticulo.setSelectedItem(sali.getArticulo());
            vista.txtCantidad.setText(String.valueOf(sali.getCantidad()));
            if (sali.getSistema().equals(SIST_PEPS)) {
                vista.rdbPeps.setSelected(true);
            } else if (sali.getSistema().equals(SIST_UEPS)) {
                vista.rdbUeps.setSelected(true);
            } else if (sali.getSistema().equals(SIST_PROM)) {
                vista.rdbPromedio.setSelected(true);
            }
            indiceEnEdicion = indiceArticulo;
        } else {
            throw new Exception("Debe seleccionar una salida que desee modificar");
        }
    }

    private void limpiarDatosSalidaArticulo() {
        vista.cmbArticulo.setSelectedIndex(0);
        vista.txtCantidad.setText("");
        vista.buttonGroup1.clearSelection();
    }

    private void limpiarTodo() {
        vista.dateFecha.setCalendar(null);
        vista.txtFactura.setText("");
        limpiarDatosSalidaArticulo();
        indiceEnEdicion = -1;
        salida = new Salida();
    }

    private void cargarSalidaArticulosEnTabla() throws Exception {
        String col[] = {"Cod.", "Articulo", "Cantidad", "Precio total", "Sist."};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        vista.tListaArticulos.setModel(tableModel);
        for (SalidaArt salArt : salida.getVentas()) {
            Object[] fila = {
                salArt.getArticulo().getCod(),
                salArt.getArticulo().getNombre(),
                salArt.getCantidad(),
                salArt.getPrecioTotal(),
                salArt.getSistema()
            };
            tableModel.addRow(fila);
        }
    }

    private void guardarSalida() throws Exception {
        salida.setFecha(vista.dateFecha.getCalendar());
        salida.setFactura(vista.txtFactura.getText());
        GestorEntradaArt gea = new GestorEntradaArt();
        try {
            gestorSalida.IniciarTransaccion();
            for (SalidaArt salidaArt : salida.getVentas()) {
                if (salidaArt.getArticulo().getStock() > salidaArt.getCantidad()) {
                    ArrayList<EntradaArt> entraArtis = cargarEntradaArticulosPorSistema(salidaArt.getArticulo().getId(), salidaArt.getSistema());
                    int cantidad = salidaArt.getCantidad();
                    for (EntradaArt entArt : entraArtis) {
                        if (entArt.getExistencia() <= cantidad) {
                            cantidad = cantidad - entArt.getExistencia();
                            entArt.setExistencia(0);
                        } else {
                            entArt.setExistencia(entArt.getExistencia() - cantidad);
                            cantidad = 0;
                        }
                        gea.Modificar(entArt);
                        if (cantidad == 0) {
                            break;
                        }
                    }
                    salidaArt.getArticulo().setStock(salidaArt.getArticulo().getStock() - salidaArt.getCantidad());
                } else {
                    throw new Exception("El articulo '" + salidaArt.getArticulo().getCod() + " - " + salidaArt.getArticulo().getNombre() + "' no posee stock suficiente");
                }
            }
            gestorSalida.Guardar(salida);
            gestorSalida.EjecutarTransaccion();
        } catch (Exception ex) {
            gestorSalida.DeshacerTransaccion();
            throw ex;
        }
    }

    private ArrayList<EntradaArt> cargarEntradaArticulosPorSistema(long articuloId, String sistema) throws Exception {
        GestorEntradaArt gea = new GestorEntradaArt();
        switch (sistema) {
            case SIST_PEPS:
                return gea.BuscarPorFiltroOrdenadoPorFecha("articuloId = " + articuloId, false);
            case SIST_UEPS:
                return gea.BuscarPorFiltroOrdenadoPorFecha("articuloId = " + articuloId, true);
            default:
                return gea.BuscarPorFiltroConOrden("articuloId = " + articuloId, "costoUnit", false);
        }
    }

    private String calcularPrecioTotal() throws Exception {
        if (EsEnteroPositivoMayorACero(vista.txtCantidad.getText())) {
            String sistema = obtenerSistemaUsado();
            Articulo articulo = (Articulo) vista.cmbArticulo.getSelectedItem();
            int cantidad = Integer.parseInt(vista.txtCantidad.getText());
            switch (sistema) {
                case SIST_PEPS:
                    return String.valueOf(calcularPrecioTotalPorPeps(articulo, cantidad));
                case SIST_UEPS:
                    return String.valueOf(calcularPrecioTotalPorUeps(articulo, cantidad));
                case SIST_PROM:
                    return String.valueOf(calcularPrecioTotalPorPromedio(articulo, cantidad));
                default:
                    return "";
            }
        } else {
            return "";
        }
    }

    private double calcularPrecioTotalPorPeps(Articulo articulo, int cantidad) throws Exception {
        GestorEntradaArt gea = new GestorEntradaArt();
        ArrayList<EntradaArt> entraArtis = gea.BuscarPorFiltroOrdenadoPorFecha("articuloId = " + articulo.getId() + " AND existencia > 0", false);
        double precioTotal = 0;
        for (EntradaArt entArt : entraArtis) {
            if (entArt.getExistencia() <= cantidad) {
                precioTotal = precioTotal + (entArt.getExistencia() * entArt.getCostoUnit());
                cantidad = cantidad - entArt.getExistencia();
            } else {
                precioTotal = precioTotal + (cantidad * entArt.getCostoUnit());
                cantidad = 0;
            }
            if (cantidad == 0) {
                break;
            }
        }
        return precioTotal * (1 + GANANCIA);
    }

    private double calcularPrecioTotalPorUeps(Articulo articulo, int cantidad) throws Exception {
        GestorEntradaArt gea = new GestorEntradaArt();
        ArrayList<EntradaArt> entraArtis = gea.BuscarPorFiltroOrdenadoPorFecha("articuloId = " + articulo.getId() + " AND existencia > 0", true);
        double precioTotal = 0;
        for (EntradaArt entArt : entraArtis) {
            if (entArt.getExistencia() <= cantidad) {
                precioTotal = precioTotal + (entArt.getExistencia() * entArt.getCostoUnit());
                cantidad = cantidad - entArt.getExistencia();
            } else {
                precioTotal = precioTotal + (cantidad * entArt.getCostoUnit());
                cantidad = 0;
            }
            if (cantidad == 0) {
                break;
            }
        }
        return precioTotal * (1 + GANANCIA);
    }

    private double calcularPrecioTotalPorPromedio(Articulo articulo, int cantidad) throws Exception {
        GestorEntradaArt gea = new GestorEntradaArt();
        ArrayList<EntradaArt> entraArtis = gea.BuscarPorFiltro("articuloId = " + articulo.getId() + " AND existencia > 0");
        double existenciaTotal = 0;
        double precioTotal = 0;
        for (EntradaArt entArt : entraArtis) {
            precioTotal = precioTotal + (entArt.getExistencia() * entArt.getCostoUnit());
            existenciaTotal = existenciaTotal + entArt.getExistencia();
        }
        double costoPromedioPorUnidad = precioTotal / existenciaTotal;
        return cantidad * costoPromedioPorUnidad * (1 + GANANCIA);
    }
}
