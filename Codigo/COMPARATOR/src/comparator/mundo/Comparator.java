/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo;

import comparator.mundo.comun.ClaseDTO;
import comparator.mundo.comun.HistoricoVersionDTO;
import comparator.mundo.comun.LineaDTO;
import comparator.mundo.utilidades.LecturaArchivo;
import comparator.mundo.utilidades.UtilidadesArchivo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ClickIT
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
    
    /**
     * Método que crea los objetos de las clases en la lectura de archivos directamente en el disco.
     */
    public void crearClases(){
        LecturaArchivo l = new LecturaArchivo();
        File proyecto = new File(this.rutaNueva);
        File [] hijos = proyecto.listFiles();
        File carpetaProyecto=null;
        //INICIA PARA VERIFICAR SI LA CARPETA SE ENCUENTRA VERSIONADA O NO
        for(int i=0;i<hijos.length&&carpetaProyecto==null;i++){
            if(hijos[i].getName().equalsIgnoreCase(".comparator")){
                File [] carpetaComparator= hijos[i].listFiles();
                for(int j=0;j<carpetaComparator.length;j++){
                    if(carpetaComparator[j].getName().equalsIgnoreCase(".proyecto")){
                        carpetaProyecto=carpetaComparator[j];
                    }
                }
                
            }
        }
        //TERMINA PARA VERIFICAR SI LA CARPETA SE ENCUENTRA VERSIONADA O NO
        if(carpetaProyecto==null){
            versionado=false;
            l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
        }
        else{
            this.rutaAntigua =  carpetaProyecto.getAbsolutePath();
            l.buscarEnHijo(this.rutaAntigua,this.clasesVersionAntigua);
            l.buscarEnHijo(this.rutaNueva,this.clasesVersionNueva);
            versionado=true;
        }
        
    }
    
    /**
     * Método que permite orquestar los metodos creados que permite realizar la comparacion de archivos.
     * @return calculos obtenidos de la comparacion.
     * @throws IOException excepcion generada de la lectura de archivos en el disco
     */
    public Object[] compararProyectos() throws IOException{
        this.crearClases();
        Object [] resultados = new Object[2];
        resultados [1]=this.compararClases(clasesVersionAntigua, clasesVersionNueva);
        resultados [0]=versionado;
        return resultados;
    }
    
    /**
     * Metodo que permite comparar las clases de una proyecto calculando las clases agregadas, eliminadas y modificadas mostrando la cantidad de lineas totales agregadas, eliminadas, modificadas y la cantidad de lineas lógicas totales que componen el nuevo proyecto.
     * @param antigua compilado de las clases de la version anterior que se quiere comparar
     * @param nueva compilado de las clases de la version nueva que se quiere comparar
     * @return texto con formato html que muestra el resumen de las comparaciones entre dos veriones
     * @throws IOException excepcion que puede ser generada de la lectura de archivos directamente del disco
     */
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
                    if(claseNew.isEsTextoPlano()){
                        claseNew.guardarMisLineas();
                        l.modificarEstadoLineas(claseOld, claseNew, true);
                    }
                    if(claseOld.isEsTextoPlano()){
                        claseOld.guardarMisLineas();
                        l.modificarEstadoLineas(claseNew, claseOld, false);
                    }
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
    
    /**
     * Metodo que permite buscar una clase en la version guardada del proyecto dada la nueva clase  y realizar las comparaciones correspondientes calculando sus lineas agregadas, eliminadas y modificadas.
     * @param nueva Clase del nuevo proyecto la cual permitirá buscar su clase en la version anterior para hacer la comparacio
     * @return calculos de la comparacion de las lineas entre dos clases.
     * @throws IOException excepcion que se genera en la lectura de archivos directamente en el disco 
     */
    public String[] compararDosClasesBuscandoAntigua(ClaseDTO nueva) throws IOException{
        String[] calculos = new String[8];
        for(ClaseDTO a:this.clasesVersionAntigua){
            if(a.getRutaRelativa().equalsIgnoreCase(nueva.getRutaRelativa())){// arreglar para que tambien compare rutas relativas
                calculos[0]=nueva.getRuta();
                calculos[1]=nueva.getContenidoHTML();
                calculos[2]=a.getRuta();
                calculos[3]=a.getContenidoHTML();
                int[] cantidadCalculadaNueva = nueva.getCantidadTiposDeLinea();
                int[] cantidadCalculadaAntigua = a.getCantidadTiposDeLinea();
                calculos[4]=String.valueOf(cantidadCalculadaNueva[0]);
                calculos[5]=String.valueOf(cantidadCalculadaAntigua[1]);
                calculos[6]=String.valueOf(cantidadCalculadaNueva[2]);
                calculos[7]=String.valueOf(nueva.getCantidadLineasLogicas());
            }
        }   
        return calculos;
    }
    
    /**
     * Método que permite buscar una clase (que representa un archivo de cualquier tipo dentro del proyecto)
     * @param rutaArchivoSeleccionado Ruta del archivo seleccionado desde la interfaz por la cual se buscará la clase
     * @return ClaseDTO encontrada a partir de la ruta dada como parametro
     */
    public ClaseDTO buscarClaseDeArchivoSeleccionado(String rutaArchivoSeleccionado){
        ClaseDTO seleccionada=null;
        for(int i=0;i<this.clasesVersionNueva.size()&&seleccionada==null;i++){
            ClaseDTO c = this.clasesVersionNueva.get(i);
            if(c.getRuta().equalsIgnoreCase(rutaArchivoSeleccionado))
                seleccionada = c;
        }
        return seleccionada;
    }
    
    /**
     * Metodo que orquesta los diferentes metodos creados para agregar una nueva versión.
     * @param usuario nombre del usuario que realizó el cambio de version
     * @param comentario informacion o razón para realizar el cambio.
     * @return true si el procedimiento fue ejecutado correctamente.
     */
    public boolean agregarVersion(String usuario, String comentario){
        if(!this.versionado){
            this.crearPrimeraVersion();
        }
        this.guardarHistoricoVersion(usuario, comentario);
        this.actualizarVersion();
        
        return true;
    }
    
    /**
     * Metodo que actualiza los archivos del proyecto y guarda la nueva version modificada la cual servirá de referencia para una futura comparacion
     * @return true si el procedimiento se ejecuto correctamente y false si no lo hizo
     */
    private boolean actualizarVersion(){
        String separator= System.getProperty("file.separator");
        String rutaProyecto=this.rutaNueva+separator+".comparator"+separator+".proyecto";
        UtilidadesArchivo.copiarCarpeta(this.rutaNueva, rutaProyecto);
        return true;
    }
    
    /**
     * Metodo que permite guardar en el archivo historico.txt una nueva linea que representa el resumen de los cambios realizados en una version.
     * @param usuario nombre o usuario que realiza la actualizacion de la versión del proyecto en el programa COMPARATOR
     * @param comentario Información o razón por la cual se esta haciendo la actualizacion o cambion de version
     * @return true si el procedimiento se ejecuto correctamente y false si no lo hizo
     */
    private boolean guardarHistoricoVersion(String usuario, String comentario){
        String separator= System.getProperty("file.separator");
        String rutaHistorico=this.rutaNueva+separator+".comparator"+separator+"historico.txt";
        String rutaDetallado=this.rutaNueva+separator+".comparator"+separator+"detallado.txt";
        ArrayList<String> lineasHistorico = UtilidadesArchivo.leerLineasArchivo(rutaHistorico);
        int version = 1;
        if(lineasHistorico.size()>0){
            String linea = lineasHistorico.get(lineasHistorico.size()-1);
            HistoricoVersionDTO h = new HistoricoVersionDTO(linea);
            version = h.getVersion()+1;
        }
        Date d = new Date();
        String fecha = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();
        HistoricoVersionDTO h = new HistoricoVersionDTO(version,fecha,usuario,comentario,this.locAdicionados,this.locEliminados,this.locModificados,this.locTotales);
        UtilidadesArchivo.escribirNuevaLineaEnArchivo(rutaHistorico, h.linea());
        UtilidadesArchivo.escribirNuevaLineaEnArchivo(rutaDetallado, this.crearLineaHtmlCambiosDetalladosDeVersion());
        return true;
    }
    
    /**
     * Método que crea los archivos y carpetas que permitiran guardar la informacion de las versiones agregadas y comparadas, en la primer versión se crea la carpeta .comparator, la carpeta .proyecto y los archivos detallado.txt e historico.txt
     * @return true si la creacion fue correcta y false si fallo
     */
    private boolean crearPrimeraVersion(){
        String separator= System.getProperty("file.separator");
        String rutaComparador=this.rutaNueva+separator+".comparator";
        if(!this.versionado){
          UtilidadesArchivo.crearCarpeta(rutaComparador+System.getProperty("file.separator")+".proyecto");
          File f = new File (rutaComparador);
          UtilidadesArchivo.crearArchivo(f.getAbsolutePath()+separator+"historico.txt");
          UtilidadesArchivo.crearArchivo(f.getAbsolutePath()+separator+"detallado.txt");
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
    
    /**
     * Metodo que permite crear una cadena caracteres con formato html que muestra el detalle de los cambios de una version
     * @return cadena de caracteres con formato html que muestra el detalle de los cambios realizados en una version discriminados por las clases agregadas, eliminadas y modificada y en las clases modificadas las lineas que fueron agregadas, eliminadas y modificadas.
     */
    public String crearLineaHtmlCambiosDetalladosDeVersion(){
        String separator= System.getProperty("file.separator");
        String rutaHistorico=this.rutaNueva+separator+".comparator"+separator+"historico.txt";
        ArrayList<String> lineasHistorico = UtilidadesArchivo.leerLineasArchivo(rutaHistorico);
        int version = 1;
        if(lineasHistorico.size()>0){
            String linea = lineasHistorico.get(lineasHistorico.size()-1);
            HistoricoVersionDTO h = new HistoricoVersionDTO(linea);
            version = h.getVersion();
        }
        String html ="<html><p>MODIFICACIONES EN LA VERSION NUMERO "+version+"<br />";
        try {
            String cambiosGenerales =  this.compararClases(clasesVersionAntigua, clasesVersionNueva);
            cambiosGenerales=cambiosGenerales.replaceAll("\n", "<br />");
            html+=cambiosGenerales+"<br />"+"DIFERENCIAS ENTRE ARCHIVOS "+"<br />";
            for(ClaseDTO clase : this.clasesVersionNueva){
                if(clase.getEstado().equalsIgnoreCase("H")){
                    if(!clase.isEsTextoPlano()){
                         html+="<B>"+clase.getRutaRelativa()+"</B><br />El archivo no posee un formato comparable"+"<br /<br />";
                    }
                    else{
                    
                        int a=0, e=0, m=0;
                        String agregada="", eliminada="", modificada="";
                        for(int i=0;i<clase.getLineas().size();i++){
                            LineaDTO l = clase.getLineas().get(i);

                            if(l.getEstado().equalsIgnoreCase("A")){
                                a+=1;
                                agregada+="<B>["+l.getNumeroLinea()+"]</B>"+" "+l.getContenido().replaceAll("\"", "&#34;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;").replaceAll(">", "&#62;").replaceAll("<", "&#60;")+"<br />";
                            }   
                            if(l.getEstado().equalsIgnoreCase("E")){
                                e+=1;
                                eliminada+="<B>["+l.getNumeroLinea()+"]</B>"+" "+l.getContenido().replaceAll("\"", "&#34;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;").replaceAll(">", "&#62;").replaceAll("<", "&#60;")+"<br />";
                            }
                            if(l.getEstado().equalsIgnoreCase("M")){
                                m+=1;
                                modificada+="<B>["+l.getNumeroLinea()+"]</B>"+" "+l.getContenido().replaceAll("\"", "&#34;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;").replaceAll(">", "&#62;").replaceAll("<", "&#60;")+"<br />";
                            }
                        }
                        if(a>0)
                            agregada="Líneas Agregadas: "+a+"<br />"+agregada+"<br />";
                        if(e>0)
                            eliminada="Líneas Eliminadas: "+e+"<br />"+eliminada+"<br />";
                        if(m>0)
                            modificada="Líneas Modificadas: "+m+"<br />"+modificada+"<br />";
                        if(a>0||e>0||m>0){
                            html+="<B>"+clase.getRutaRelativa()+"</B><br />"+agregada+eliminada+modificada;
                        }
                        else{
                            html+="<B>"+clase.getRutaRelativa()+"</B>";
                        }
                            
                    }
                }
            }
            html +="</p></html>";
            return html;
        } catch (IOException ex) {
            Logger.getLogger(Comparator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return html;
    }
    
    /**
     * Metodo que permite obtener el detalle de los cambios de una version del archivo detallado.txt
     * @param version de la cual se quiere obtener el detalle de los cambios realizados.
     * @return detlle de los cambios de una version
     */
    public String getDetalleVersion(int version){
        String separator= System.getProperty("file.separator");
        String rutaHistorico=this.rutaNueva+separator+".comparator"+separator+"detallado.txt";
        ArrayList<String> lineasDetallado = UtilidadesArchivo.leerLineasArchivo(rutaHistorico); 
        return lineasDetallado.get(version);
    }
    
}
