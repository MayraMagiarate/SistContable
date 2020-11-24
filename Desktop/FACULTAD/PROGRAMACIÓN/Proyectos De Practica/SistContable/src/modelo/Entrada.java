/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MAYFER
 */
@Entity
public class Entrada extends EntidadBasica implements Serializable {

    @Basic
    @Temporal(TemporalType.DATE)
    @Column
    private Calendar fecha;
    @Column
    private String facturaProveedor;
    @OneToMany(mappedBy = "entrada")
    private List<EntradaArt> entradas;

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
