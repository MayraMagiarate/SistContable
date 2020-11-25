/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Articulo;
import modelo.Entrada;
import modelo.EntradaArt;
import modelo.Salida;
import modelo.SalidaArt;
import vista.VistaArticulo;
import vista.VistaEntradas;
import vista.VistaPrincipal;
import vista.VistaVentas;

/**
 *
 * @author MAYFER
 */
public class ControladorPrincipal implements ActionListener {
    private VistaPrincipal vistaP=new VistaPrincipal();
     public ControladorPrincipal(VistaPrincipal vistaP) {
        this.vistaP = vistaP;
        this.vistaP.setVisible(true);

        this.vistaP.btnVentas.addActionListener(this);
        this.vistaP.btnIngresoMerca.addActionListener(this);
        this.vistaP.btnIngresoArtNuevo.addActionListener(this);
       
        
       

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaP.btnVentas)) {
          VistaVentas vv = new VistaVentas();
           Salida venta = new Salida();
            ControladorSalida controlSalida = new ControladorSalida(vv,venta);

        }
        else if (e.getSource().equals(this.vistaP.btnIngresoMerca)) {
             VistaEntradas vi = new VistaEntradas();
            Entrada ea = new Entrada();
            ControladorEntrada controlEntradas = new ControladorEntrada(vi, ea);

        }
        else if (e.getSource().equals(this.vistaP.btnIngresoArtNuevo)) {
           VistaArticulo va = new VistaArticulo();
            Articulo art = new Articulo();
            ControladorArticulo controlArticulo = new ControladorArticulo(va,art);

        }
       
        

    }
   
    
}
