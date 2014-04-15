/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author laynegranadosmogollon
 */
public class EscrituraArchivo {
    
    public static boolean createFolder(String ruta){
        File folder = new File(ruta);
        if(!folder.exists()){
            boolean resultado = folder.mkdir();
            return resultado;
        }
        return false;
    }
    
    public static boolean createFile(String ruta) throws IOException{
        File archivo=new File(ruta);
        boolean resultado = archivo.createNewFile();
        return resultado;
    }
    
    public static void writeOnFile(String archivo, String linea){
        
            File f = new File(archivo);
            if(f.isFile()){
                try{
                    BufferedWriter out = new BufferedWriter(new FileWriter(archivo, true));
                    out.write(linea);
                    out.close();
                } catch (IOException e) {
                }
        } 
    }
    
}
