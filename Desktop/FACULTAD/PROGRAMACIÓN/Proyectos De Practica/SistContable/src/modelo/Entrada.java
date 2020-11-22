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
public class Entrada extends EntidadBasica implements Serializable {
    private Calendar fecha;
    private String facturaProveedor;
    private List <EntradaArt> entradas;

    public Entrada() {
    }

   

    public List<EntradaArt> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaArt> entradas) {
        this.entradas = entradas;
    }

  

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(String facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }
    
    
}
