/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comparator.mundo.utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author layne
 */
public class EscrituraArchivo {
    
    public static boolean crearCarpeta(String ruta){
        File folder = new File(ruta);
        boolean exitoso=false;
        if(folder.exists()){
            exitoso = folder.mkdirs();
        }   
        return exitoso;
    }
    
    public static boolean crearArchivo(String rutaArchivo){
        File folder = new File(rutaArchivo);
        boolean exitoso=false;
        if(folder.exists()){
            try {
                exitoso = folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(EscrituraArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        return exitoso;
    }
    
    public static void escribirNuevaLineaEnArchivo(String rutaArchivo, String nuevaLinea){
        try {
            FileWriter writer = new FileWriter(new File(rutaArchivo), false);
            try 
            {
                writer.write(nuevaLinea);
                writer.flush();
                writer.close();
            } catch (NumberFormatException e) {
            }
        } catch (IOException ex) {
        }
    }
    
}
