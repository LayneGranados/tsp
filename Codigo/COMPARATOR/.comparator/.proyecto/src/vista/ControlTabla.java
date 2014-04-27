package vista;

import comparator.mundo.comun.HistoricoVersionDTO;
import comparator.mundo.utilidades.UtilidadesArchivo;
import java.util.ArrayList;

/*
 * Javier Abell�n. 26 de octubre de 2003
 *
 * ControlTabla.java
 *
 * Clase para el ejemplo de uso del JTable.
 *
 */

/**
 * Clase para modificar el modelo de la tabla.
 */
public class ControlTabla
 {
     /**
      * Constructor. Se le pasa el modelo, al que a�ade varios elementos y
      * se lo guarda.
      */
    
    /** El modelo de la tabla */
     private ModeloTabla modelo = null;
     
     /** Numero que nos servir� para construir personas distintas para la 
      tabla */
     private static int numero = 0;
    
     public ControlTabla(ModeloTabla modelo, String rutaProyecto)
     {
         this.modelo = modelo;
         ArrayList<HistoricoVersionDTO> hist = new ArrayList<HistoricoVersionDTO>();
         String separator= System.getProperty("file.separator");
         String rutaHistorico=rutaProyecto+separator+".comparator"+separator+"historico.txt";
         ArrayList<String> lineasHistorico = UtilidadesArchivo.leerLineasArchivo(rutaHistorico); 
         for(int i=0;i<lineasHistorico.size();i++){
             HistoricoVersionDTO h = new  HistoricoVersionDTO(lineasHistorico.get(i));
            hist.add(h);
         }
         
         if(hist!=null)
         for(int  i=0;i<hist.size();i++){
             modelo.agregaVersion(hist.get(i));
         }
     }
     
     /**
      * A�ade una fila en el modelo, al final del mismo
      */
     public void anhadeFila ()
     {
         // Crea una nueva Persona, construy�ndola a base del atributo 
         // numero.
//         Persona dato = new Persona (
//            "Nombre " + Integer.toString (numero),
//            "Apellido " + Integer.toString (numero), 
//            numero);
//         
//         modelo.anhadePersona (dato);
//         
//         // Incrementa numero para que la siguiente Persona a a�adir sea
//         // distinta.
//         numero++;
     }
     
     /** Elimina la primera fila del modelo */
     public void borraFila ()
     {
         if (modelo.getRowCount() > 0)
            modelo.borraVersion(0);
     }
     
     
}
