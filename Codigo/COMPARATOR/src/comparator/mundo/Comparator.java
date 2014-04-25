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
    private boolean versionado;
    

    public Comparator() {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
        this.versionado=false;
    }
    
    public Comparator(String rutaNueva) {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
        this.rutaNueva=rutaNueva;
        this.versionado=false;
    }
    
    public void crearClases(){
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
        calculos [0]= this.compararClases(clasesVersionAntigua, clasesVersionNueva);
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
        return this.compararClases(clasesVersionAntigua, clasesVersionNueva);
    }
    
    public String compararClases(ArrayList<ClaseDTO> antigua, ArrayList<ClaseDTO> nueva) throws IOException{
    int sumatoriaLineasAgregadas=0;
    int sumatoriaLineasEliminadas=0;
    int sumatoriaLineasModificadas=0;
    int sumatoriaLineasLogicas=0;
    
    String resultadoAdicion="CLASES ADICIONADAS: ";
    String cuerpoA ="";
    int contadorA=0;
    LecturaArchivo l = new LecturaArchivo();
    for(int i=0;i<nueva.size();i++){
        ClaseDTO claseNew = nueva.get(i);
        boolean existe=false;
        for(int j=0;j<antigua.size()&&!existe;j++){
            ClaseDTO claseOld = antigua.get(j);
            if(claseNew.equals(claseOld)){
                existe=true;
                claseNew.guardarMisLineas();
                claseOld.guardarMisLineas();
                l.modificarEstadoLineas(claseOld, claseNew, true);
                l.modificarEstadoLineas(claseNew, claseOld, false);
            }
        }
        if(!existe){
            contadorA++;
            cuerpoA+="- "+claseNew.getRutaRelativa()+"\n";
            claseNew.setEstado("A");
            nueva.set(i, claseNew);
        }
        int[] cantidadCalculada = claseNew.getCantidadTiposDeLinea();
        sumatoriaLineasAgregadas+=cantidadCalculada[0];
        sumatoriaLineasModificadas+=cantidadCalculada[2];
        sumatoriaLineasLogicas+=claseNew.getCantidadLineasLogicas();
    }
    resultadoAdicion+=contadorA+"\n";
    
    String resultadoEliminacion="CLASES   ELIMINADAS: ";
    String cuerpoE ="";
    int contadorE=0;
    for(int i=0;i<antigua.size();i++){
        ClaseDTO claseOld = antigua.get(i);
        boolean existe=false;
        for(int j=0;j<nueva.size()&&!existe;j++){
            ClaseDTO claseNew = nueva.get(j);
            if(claseNew.equals(claseOld)){
                existe=true;
            }
        }
        if(!existe){
            contadorE++;
            cuerpoE+="- "+claseOld.getRutaRelativa()+"\n";
            claseOld.setEstado("E");
            antigua.set(i, claseOld);
        }
        int[] cantidadCalculada = claseOld.getCantidadTiposDeLinea();
        sumatoriaLineasEliminadas+=cantidadCalculada[1];
    }
    resultadoEliminacion+=contadorE+"\n";
    
    return resultadoAdicion+cuerpoA+"\n"+resultadoEliminacion+cuerpoE+"\n"+"\n"+"Cantidad Lineas Agregadas del Programa: "+sumatoriaLineasAgregadas+"\n"+"Cantidad Lineas Eliminadas del Programa: "+sumatoriaLineasEliminadas+"\n"+"Cantidad Lineas Modificadas del Programa: "+sumatoriaLineasModificadas+"\n"+"Cantidad Total de Lineas Lógicas del Programa: "+sumatoriaLineasLogicas;
}
    
    public String[] compararDosClasesDadas(ClaseDTO a, ClaseDTO n) throws IOException{
        String[] calculos = new String[4];
        LecturaArchivo l = new LecturaArchivo();
        
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
        return calculos;
    }
    
    public String[] compararDosClasesBuscandoAntigua(ClaseDTO n) throws IOException{
        String[] calculos = new String[8];
        for(ClaseDTO a:this.clasesVersionAntigua){
            if(a.getRutaRelativa().equalsIgnoreCase(n.getRutaRelativa())){// arreglar para que tambien compare rutas relativas
                calculos[0]=n.getRuta();
                calculos[1]=n.getContenidoHTML();
                calculos[2]=a.getRuta();
                calculos[3]=a.getContenidoHTML();
                int[] cantidadCalculadaNueva = n.getCantidadTiposDeLinea();
                int[] cantidadCalculadaAntigua = a.getCantidadTiposDeLinea();
                calculos[4]=String.valueOf(cantidadCalculadaNueva[0]);
                calculos[5]=String.valueOf(cantidadCalculadaAntigua[1]);
                calculos[6]=String.valueOf(cantidadCalculadaNueva[2]);
                calculos[7]=String.valueOf(n.getCantidadLineasLogicas());
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

    public ArrayList<ClaseDTO> getClasesVersionAntigua() {
        return clasesVersionAntigua;
    }

    public void setClasesVersionAntigua(ArrayList<ClaseDTO> clasesVersionAntigua) {
        this.clasesVersionAntigua = clasesVersionAntigua;
    }

    public ArrayList<ClaseDTO> getClasesVersionNueva() {
        return clasesVersionNueva;
    }

    public void setClasesVersionNueva(ArrayList<ClaseDTO> clasesVersionNueva) {
        this.clasesVersionNueva = clasesVersionNueva;
    }
    
}
