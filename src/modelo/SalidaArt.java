/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author MAYFER
 */
@Entity
public class SalidaArt extends EntidadBasica implements Serializable{

    @Column
    private int cantidad;
    @Column
    private double precioTotal;
    @ManyToOne(cascade=CascadeType.PERSIST, optional = false)
    @JoinColumn(name="salidaId")
    private Salida salida;
    @ManyToOne(cascade=CascadeType.PERSIST, optional = false)
    @JoinColumn(name="articuloId")
    private Articulo articulo;

    public SalidaArt() {
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Salida getSalida() {
        return salida;
    }

    public void setSalida(Salida salida) {
        this.salida = salida;
    }
    
    
}
