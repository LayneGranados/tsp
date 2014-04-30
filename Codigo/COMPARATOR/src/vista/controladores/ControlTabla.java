package vista.controladores;

import vista.controladores.ModeloTabla;
import comparator.mundo.comun.HistoricoVersionDTO;
import comparator.mundo.utilidades.UtilidadesArchivo;
import java.util.ArrayList;

/**
 * Clase para modificar el modelo de la tabla.
 */
public class ControlTabla
 {
     /**
      * Constructor. Se le pasa el modelo, al que añade varios elementos y
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
     
     
}
