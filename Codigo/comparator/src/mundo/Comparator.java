/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author laynegranadosmogollon
 */
public class Comparator {
    
    private ArrayList<Clase> clasesVersionAntigua;
    private ArrayList<Clase> clasesVersionNueva;
    private String rutaAntigua;
    private String rutaNueva;
    

    public Comparator() {
        this.clasesVersionAntigua= new ArrayList<Clase>();
        this.clasesVersionNueva= new ArrayList<Clase>();
    }
    
    public Comparator(String rutaAntigua, String rutaNueva) {
        this.clasesVersionAntigua= new ArrayList<Clase>();
        this.clasesVersionNueva= new ArrayList<Clase>();
        this.rutaAntigua= rutaAntigua;
        this.rutaNueva=rutaNueva;
    }
    
    private void crearClases(){
        Leer l = new Leer();
        l.buscarEnHijo(this.rutaAntigua,this.clasesVersionAntigua);
        l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
    }
    
    public String[] realizarCalculos() throws IOException{
        String[] calculos = new String[5];
        this.crearClases();
        Leer l = new Leer();
        calculos [0]= l.compararClases(clasesVersionAntigua, clasesVersionNueva);
        l.compararDocumentos(clasesVersionAntigua, clasesVersionNueva);
        for(Clase n : this.clasesVersionNueva){
            if(n.getEstado().equalsIgnoreCase("H")){
                for(Clase a:this.clasesVersionAntigua){
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
