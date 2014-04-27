package vista;

/*
 * Javier Abell�n. 26 de octubre de 2003
 *
 * PanelCompleto.java
 *
 * Ejemplo de manejo de JTable y TableModel
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Panel con toda la parte visual del ejemplo. Crea un JScrollPane con el JTable
 * en su interior y dos JButton para a�adir y borrar elementos de la tabla.
 */
public class PanelCompleto extends JPanel{
    
     /** Modelo de la tabla */
     private ModeloTabla modelo = null;
     
     /** Para modificar el modelo */
     private ControlTabla control = null;
     /**
      * Constructor que recibe el modelo de la tabla y el control. Guarda ambos
      * y llama al metodo construyeVentana() que se encarga de crear los
      * componentes.
      */
     public PanelCompleto(ModeloTabla modelo, ControlTabla control)
     {
         super (new GridBagLayout());
         this.modelo = modelo;
         this.control = control;
         construyeVentana();
     }
     
     /**
      * Crea los componentes de este panel.
      * Un JScrollPane, el JTable que va dentro y dos JButton para a�adir y
      * quitar elementos del JTable.
      */
     private void construyeVentana()
     {
         // Para poner las restricciones a los componentes (posicioes dentro
         // del panel)
         GridBagConstraints restricciones = new GridBagConstraints();
         
         // Un JScrollPane con el JTable dentro.
         // Las restricciones...
         restricciones.gridx = 0;
         restricciones.gridy = 0;
         restricciones.gridwidth = GridBagConstraints.REMAINDER;
         restricciones.fill = GridBagConstraints.BOTH;
         restricciones.weightx = 1.0;
         restricciones.weighty = 1.0;
         
         // Se crea el JScrollPane, el JTable y se pone la cabecera...
         JScrollPane scroll = new JScrollPane();
         JTable tabla = new JTable (modelo);
         scroll.setViewportView(tabla);
         scroll.setColumnHeaderView (tabla.getTableHeader());
         
         // ... y se a�ade todo al panel
         this.add(scroll, restricciones);
         
         // Un bot�n para a�adir nuevas filas.
         // Su posici�n en el panel ...
         restricciones.gridy = 1;
         restricciones.gridwidth = 1;
         restricciones.weighty = 0.0;
         restricciones.fill = GridBagConstraints.NONE;
         restricciones.anchor = GridBagConstraints.WEST;
         
         // Se crea el bot�n 
         JButton botonAnhadir = new JButton ("A�adir");
         
         // Se a�ade al panel
         this.add (botonAnhadir, restricciones);
         
         // y se le pone la acci�n a ejecutar cuando se pulse.
         botonAnhadir.addActionListener(new ActionListener() {
             public void actionPerformed (ActionEvent evento)
             {
                 control.anhadeFila();
             }
         });
         
         // Un bot�n para borrar filas.
         // La posici�n en el panel...
         restricciones.gridx = 3;
         restricciones.weightx = 0.0;
         restricciones.anchor = GridBagConstraints.EAST;
         
         // se crea el bot�n
         JButton botonBorrar = new JButton ("Borrar");
         
         // se a�ade al panel
         this.add (botonBorrar, restricciones);
         
         // y se le a�ade la acci�n a ajecutar.
         botonBorrar.addActionListener(new ActionListener() {
             public void actionPerformed (ActionEvent evento)
             {
                 control.borraFila();
             }
         });
     }
     
    
}
