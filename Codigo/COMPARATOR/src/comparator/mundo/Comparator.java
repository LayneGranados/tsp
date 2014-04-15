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
    
    public String compararProyectos() throws IOException{
        this.crearClases();
        LecturaArchivo l = new LecturaArchivo();
        return l.compararClases(clasesVersionAntigua, clasesVersionNueva);
    }
    
    public String[] compararDosClases(ClaseDTO n) throws IOException{
        String[] calculos = new String[4];
        LecturaArchivo l = new LecturaArchivo();
        
        for(ClaseDTO a:this.clasesVersionAntigua){
            if(a.getRutaRelativa().equalsIgnoreCase(n.getRutaRelativa())){// arreglar para que tambien compare rutas relativas
                n.guardarMisLineas();
                a.guardarMisLineas();
                l.modificarEstadoLineas(a, n, true);
                l.modificarEstadoLineas(n, a, false);
                calculos[0]=n.getRuta();
                calculos[1]=n.getContenidoHTML();
                calculos[2]=a.getRuta();
                calculos[3]=a.getContenidoHTML();
            }
        }   
        return calculos;
    }
    
    public ClaseDTO buscarClaseDeArchivoSeleccionado(String rutaArchivoSeleccionado){
        ClaseDTO seleccionada=null;
        for(int i=0;i<this.clasesVersionNueva.size()&&seleccionada==null;i++){
            ClaseDTO c = this.clasesVersionNueva.get(i);
            if(c.getRuta().equalsIgnoreCase(rutaArchivoSeleccionado))
                seleccionada = c;
        }
        return seleccionada;
    }
    
    
    
}
