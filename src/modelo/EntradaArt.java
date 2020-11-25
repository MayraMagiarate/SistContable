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
public class EntradaArt extends EntidadBasica implements Serializable {

    @Column
    private int cantidad;
    @Column
    private int existencia;
    @Column
    private double costoUnit;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "entradaId")
    private Entrada entrada;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "articuloId")
    private Articulo articulo;

    public EntradaArt() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getCostoUnit() {
        return costoUnit;
    }

    public void setCostoUnit(double costoUnit) {
        this.costoUnit = costoUnit;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

}
