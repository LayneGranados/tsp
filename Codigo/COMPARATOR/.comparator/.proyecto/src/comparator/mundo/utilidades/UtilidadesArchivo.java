/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comparator.mundo.utilidades;

import comparator.mundo.comun.LineaDTO;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author layne
 */
public class UtilidadesArchivo {
    
    public static boolean crearCarpeta(String ruta){
        File folder = new File(ruta);
        boolean exitoso=false;
        if(!folder.exists()){
            exitoso = folder.mkdirs();
        }   
        return exitoso;
    }
    
    public static boolean crearArchivo(String rutaArchivo){
        File folder = new File(rutaArchivo);
        boolean exitoso=false;
        if(!folder.exists()){
            try {
                exitoso = folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(UtilidadesArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        return exitoso;
    }
    
    public static void escribirNuevaLineaEnArchivo(String rutaArchivo, String nuevaLinea){
        try {
            FileWriter writer = new FileWriter(new File(rutaArchivo), true);
            try 
            {
                writer.write(nuevaLinea+"\n");
                writer.flush();
                writer.close();
            } catch (NumberFormatException e) {
            }
        } catch (IOException ex) {
        }
    }
    
    public static ArrayList<String> leerLineasArchivo(String rutaArchivo){
        ArrayList<String> lineas = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        File archivo = new File(rutaArchivo);
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) 
            {   
                lineas.add(linea);
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        } finally 
        {
            try {
                if (null != fr) 
                {
                    fr.close();
                }
            } 
            catch (Exception e2) 
            {
                e2.printStackTrace();
            }
        }
        return lineas;
    }
    
    public static void copiarCarpeta(String carpetaOrigen, String carpetaDestino){
        File FOrigen=new File(carpetaOrigen);  
        //Fichero donde se van a copiar los datos  
        File FDestino=new File(carpetaDestino);  
        int [] entraInicial = new int[1];
        entraInicial[0]=0;
        copyDirectory(FOrigen,FDestino,entraInicial);  
        
    }
    
    /* Método que copia completamente una carpeta usando recursividad 
 *PARAMETRO1:FOrigen:Fichero o carpeta que se desea copiar 
 *PARAMETRO2:FDestino:Carpeta destino 
 */  
public static void copyDirectory(File srcDir, File dstDir,int [] entraInicial){
        try{
            if (srcDir.isDirectory()) {
                if (!dstDir.exists()) {
                    dstDir.mkdir();
                }

                String[] children = srcDir.list();
                for (int i=0;i<children.length; i++) {
                    String child = children[i];
                    if(child.equalsIgnoreCase(".comparator")){
                        entraInicial[0]=1;
                    }
                    if(!child.equalsIgnoreCase(".comparator")){
                        copyDirectory(new File(srcDir, child),new File(dstDir, child),entraInicial);
                    }
                }
            } else {
                copyFile(srcDir, dstDir);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * Copia un solo archivo
     * @param s
     * @param t
     * @throws IOException
     */
    public static void copyFile(File s, File t)
    {
        try{
              FileChannel in = (new FileInputStream(s)).getChannel();
              FileChannel out = (new FileOutputStream(t)).getChannel();
              in.transferTo(0, s.length(), out);
              in.close();
              out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * Copia un archivo y busca y sustituye un String dado
     * @param source_file
     * @param destination_file
     * @param toFind
     * @param toReplace
     * @throws IOException
     */
    public static void copyFindAndReplace(String source_file, String destination_file, String toFind, String toReplace)
    {
        String str;
        try{
            FileInputStream fis2 = new FileInputStream(source_file);
            DataInputStream input = new DataInputStream (fis2);
            FileOutputStream fos2 = new FileOutputStream(destination_file);
            DataOutputStream output = new DataOutputStream (fos2);

            while (null != ((str = input.readLine())))
            {
                String s2=toFind;
                String s3=toReplace;

                int x=0;
                int y=0;
                String result="";
                while ((x=str.indexOf(s2, y))>-1) {
                    result+=str.substring(y,x);
                    result+=s3;
                    y=x+s2.length();
                }
                result+=str.substring(y);
                str=result;

                if(str.indexOf("'',") != -1){
                    continue;
                }
                else{
                    str=str+"\n";
                    output.writeBytes(str);
                }
            }
        }
        catch (IOException ioe)
        {
            System.err.println ("I/O Error - " + ioe);
        }
    }
    
}
