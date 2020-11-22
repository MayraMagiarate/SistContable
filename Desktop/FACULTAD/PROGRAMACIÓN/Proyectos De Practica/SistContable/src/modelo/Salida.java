/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MAYFER
 */
public class Salida extends EntidadBasica implements Serializable{
    private Calendar fecha;
    private String factura;
    private List<SalidaArt> ventas;

    public Salida() {
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public List<SalidaArt> getVentas() {
        return ventas;
    }

    public void setVentas(List<SalidaArt> ventas) {
        this.ventas = ventas;
    }
    
    
    
    
}
