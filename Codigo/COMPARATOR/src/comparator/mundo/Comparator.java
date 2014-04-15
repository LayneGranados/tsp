/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo;

import comparator.mundo.comun.ClaseDTO;
import comparator.mundo.utilidades.LecturaArchivo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author laynegranadosmogollon
 */
public class Comparator {
    
    private ArrayList<ClaseDTO> clasesVersionAntigua;
    private ArrayList<ClaseDTO> clasesVersionNueva;
    private String rutaAntigua;
    private String rutaNueva;
    

    public Comparator() {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
    }
    
    public Comparator(String rutaNueva) {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
        this.rutaNueva=rutaNueva;
    }
    
    private void crearClases(){
        LecturaArchivo l = new LecturaArchivo();
        File proyecto = new File(this.rutaNueva);
        File [] hijos = proyecto.listFiles();
        File carpetaComparator=null;
        for(int i=0;i<hijos.length&&carpetaComparator==null;i++){
            if(hijos[i].getName().equalsIgnoreCase(".comparator")){
                carpetaComparator= hijos[i];
            }
        }
        this.rutaAntigua =  carpetaComparator.getAbsolutePath();
        l.buscarEnHijo(this.rutaAntigua,this.clasesVersionAntigua);
        l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
    }
    
    private void crearClasesNuevas(){
        LecturaArchivo l = new LecturaArchivo();
        l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
        
    }
    
    private void crearClasesAntiguas(){
        LecturaArchivo l = new LecturaArchivo();
        l.buscarEnHijo(this.rutaAntigua,this.clasesVersionAntigua);
    }
    
    public String[] realizarCalculos() throws IOException{
        String[] calculos = new String[5];
        this.crearClases();
        LecturaArchivo l = new LecturaArchivo();
        calculos [0]= l.compararClases(clasesVersionAntigua, clasesVersionNueva);
        l.compararDocumentos(clasesVersionAntigua, clasesVersionNueva);
        for(ClaseDTO n : this.clasesVersionNueva){
            if(n.getEstado().equalsIgnoreCase("H")){
                for(ClaseDTO a:this.clasesVersionAntigua){
                    if(a.getNombre().equalsIgnoreCase(n.getNombre())){// arreglar para que tambien compare rutas relativas
                        
                        calculos[1]=n.getRuta();
                        calculos[2]=n.getContenidoHTML();
                        calculos[3]=a.getRuta();
                        calculos[4]=a.getContenidoHTML();
                    }
                }
            }
        }
        return calculos;
    }
    
    
    
}
