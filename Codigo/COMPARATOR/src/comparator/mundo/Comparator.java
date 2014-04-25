/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo;

import comparator.mundo.comun.ClaseDTO;
import comparator.mundo.comun.HistoricoVersion;
import comparator.mundo.utilidades.LecturaArchivo;
import comparator.mundo.utilidades.UtilidadesArchivo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
    private int locAdicionados;
    private int locEliminados;
    private int locModificados;
    private int locTotales;
    

    public Comparator() {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
        this.versionado=false;
        this.locAdicionados=0;
        this.locEliminados=0;
        this.locModificados=0;
        this.locTotales=0;
    }
    
    public Comparator(String rutaNueva) {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
        this.rutaNueva=rutaNueva;
        this.versionado=false;
        this.locAdicionados=0;
        this.locEliminados=0;
        this.locModificados=0;
        this.locTotales=0;
    }
    
    public void crearClases(){
        LecturaArchivo l = new LecturaArchivo();
        File proyecto = new File(this.rutaNueva);
        File [] hijos = proyecto.listFiles();
        File carpetaComparator=null;
        for(int i=0;i<hijos.length&&carpetaComparator==null;i++){
            if(hijos[i].getName().equalsIgnoreCase(".comparator"+System.getProperty("file.separator")+".proyecto")){
                carpetaComparator= hijos[i];
            }
        }
        if(carpetaComparator==null){
            versionado=false;
            l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
        }
        else{
            this.rutaAntigua =  carpetaComparator.getAbsolutePath();
            l.buscarEnHijo(this.rutaAntigua,this.clasesVersionAntigua);
            l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
            versionado=true;
        }
        
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
    
    public Object[] compararProyectos() throws IOException{
        this.crearClases();
        Object [] resultados = new Object[2];
        resultados [0]=versionado;
        resultados [1]=this.compararClases(clasesVersionAntigua, clasesVersionNueva);
        return resultados;
    }
    
    public String compararClases(ArrayList<ClaseDTO> antigua, ArrayList<ClaseDTO> nueva) throws IOException{
    this.locAdicionados=0;
    this.locEliminados=0;
    this.locModificados=0;
    this.locTotales=0;
    String noVersionado="Este proyecto no cuenta con una version anterior, por lo "+"\n"+"tanto no podrán realizarse comparaciones. "+"\n"
                      + "Si desea agrega la primer versión del proyecto haciendo click "+"\n"+"en el botón 'Agregar Versión'. ";
    
    if(this.versionado){
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
            this.locAdicionados+=cantidadCalculada[0];
            this.locModificados+=cantidadCalculada[2];
            this.locTotales+=claseNew.getCantidadLineasLogicas();
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
            this.locEliminados+=cantidadCalculada[1];
        }
        resultadoEliminacion+=contadorE+"\n";
        return resultadoAdicion+cuerpoA+"\n"+resultadoEliminacion+cuerpoE+"\n"+"\n"+"Cantidad Lineas Agregadas del Programa: "+this.locAdicionados+"\n"+"Cantidad Lineas Eliminadas del Programa: "+this.locEliminados+"\n"+"Cantidad Lineas Modificadas del Programa: "+this.locModificados+"\n"+"Cantidad Total de Lineas Lógicas del Programa: "+this.locTotales;
    }
    else{
        for (ClaseDTO claseNew : nueva) {
            claseNew.guardarMisLineas();
            this.locTotales+=claseNew.getCantidadLineasLogicas();
        }
    }
        return "Cantidad total de Líneas Lógicas del Programa: "+this.locTotales+"\n"+"\n"+noVersionado;
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
    
    public boolean agregarVersion(){
        if(!versionado){
            this.crearPrimeraVersion();
        }
        this.guardarHistoricoVersion("layne.granados", "se hizo un cambio");
        this.actualizarVersion();
        
        return true;
    }
    
    private boolean actualizarVersion(){
        String separator= System.getProperty("file.separator");
        String rutaProyecto=this.rutaNueva+separator+".comparator"+separator+".proyecto";
        UtilidadesArchivo.copiarCarpeta(this.rutaNueva, rutaProyecto);
        return true;
    }
    
    private boolean guardarHistoricoVersion(String usuario, String comentario){
        String separator= System.getProperty("file.separator");
        String rutaHistorico=this.rutaNueva+separator+".comparator"+separator+"historico.txt";
        ArrayList<String> lineasHistorico = UtilidadesArchivo.leerLineasArchivo(rutaHistorico);
        int version = 1;
        if(lineasHistorico.size()>0){
            String linea = lineasHistorico.get(lineasHistorico.size()-1);
            HistoricoVersion h = new HistoricoVersion(linea);
            version = h.getVersion()+1;
        }
        Date d = new Date();
        String fecha = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();
        HistoricoVersion h = new HistoricoVersion(version,fecha,usuario,comentario,this.locAdicionados,this.locEliminados,this.locModificados,this.locTotales);
        UtilidadesArchivo.escribirNuevaLineaEnArchivo(rutaHistorico, h.linea());
        return true;
    }
    
    private boolean crearPrimeraVersion(){
        String separator= System.getProperty("file.separator");
        String rutaComparador=this.rutaNueva+separator+".comparator";
        if(!this.versionado){
          UtilidadesArchivo.crearCarpeta(rutaComparador+System.getProperty("file.separator")+".proyecto");
          File f = new File (rutaComparador);
          UtilidadesArchivo.crearArchivo(f.getAbsolutePath()+separator+"historico.txt");
        }
        return true;
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
