/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author MAYFER
 */
@Entity
public class Articulo extends EntidadBasica implements Serializable {

    @Column(unique = true)
    private int cod;
    @Column
    private String nombre;
    @Column
    private String descrip;
    @Column
    private int stock;
    @Column
    private int stockMin;
    @Column
    private int stockMax;

    public Articulo() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int strockMax) {
        this.stockMax = strockMax;
    }

    @Override
    public String toString() {
        if (id > 0) {
            return cod + "-" + nombre;
        } else {
            return "-";
        }
    }
    
    

}
