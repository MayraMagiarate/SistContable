/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author MAYFER
 */
public class Articulo extends EntidadBasica implements Serializable{
   
    private int cod;
    private String nombre;
    private String descrip;
    private int stock;
    private int stockMin;
    private int strockMax;

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

    public int getStrockMax() {
        return strockMax;
    }

    public void setStrockMax(int strockMax) {
        this.strockMax = strockMax;
    }
    

    
}
