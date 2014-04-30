/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.comun;

import comparator.mundo.utilidades.UtilidadesArchivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author laynegranadosmogollon
 */
public class ClaseDTO 
{
    
    private String nombre;// Nombre de la clase archivo
    private String ruta;// ruta que se usa para capturar el contenido del archivo. Ruta absoluta o ruta del disco
    private String rutaRelativa; // ruta que tiene el archivo dentro del proyecto
    private String rutaProyecto;//ruta del proyecto al que pertenece
    private ArrayList<LineaDTO> lineas; // líneas que componen la clase u archivo
    private String estado;//
    private String formato;
    private boolean esTextoPlano;
    private int cantidadLineasLogicas;
    

    public ClaseDTO() 
    {
        this.estado="H";
        this.lineas=new ArrayList<LineaDTO>();
        this.esTextoPlano=false;
        this.rutaProyecto="";
        this.rutaRelativa="";
        this.cantidadLineasLogicas=0;
    }

    
    public ClaseDTO(String nombre, String ruta, String rutaProyecto) 
    {
        this.nombre = nombre;
        this.ruta = ruta;
        this.estado="H";
        this.lineas=new ArrayList<LineaDTO>();
        this.rutaProyecto = rutaProyecto;
        this.esTextoPlano=false;
        this.rutaRelativa="";
        this.cantidadLineasLogicas=0;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getRuta() 
    {
        return ruta;
    }

    public void setRuta(String ruta) 
    {
        this.ruta = ruta;
    }

    public String getRutaRelativa() {
        return rutaRelativa;
    }

    public void setRutaRelativa(String rutaRelativa) {
        this.rutaRelativa = rutaRelativa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<LineaDTO> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<LineaDTO> lineas) {
        this.lineas = lineas;
    }

    public String getRutaProyecto() {
        return rutaProyecto;
    }

    public void setRutaProyecto(String rutaProyecto) {
        this.rutaProyecto = rutaProyecto;
    }

    public boolean isEsTextoPlano() {
        return esTextoPlano;
    }

    public void setEsTextoPlano(boolean esTextoPlano) {
        this.esTextoPlano = esTextoPlano;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getCantidadLineasLogicas() {
        return cantidadLineasLogicas;
    }

    public void setCantidadLineasLogicas(int cantidadLineasLogicas) {
        this.cantidadLineasLogicas = cantidadLineasLogicas;
    }
    
    /**
     * Método que módifica los valores iniciales de los objetos y llama a un método para realizar el conteo
     */
    public void guardarMisLineas()
    {
        this.lineas=null;
        this.lineas = new ArrayList<LineaDTO>();
        this.setCantidadLineasLogicas(0);
        this.realizarConteo(this.getRuta());
    }
    
    /**
     * Metodo que permite guardar en el ArrayList<LineaDTO> las líneas que posee la clase. Si el archivo es de un tipo permitido
     * el método lee el contenido directamente del disco y lo guarda en el ArrayList.
     * @param ruta de donde se debe leer el archivo
     */
    private void realizarConteo(String ruta)
    {
        if(this.isEsTextoPlano()){
            ArrayList<String> lin = UtilidadesArchivo.leerLineasArchivo(ruta);
            try {
                int contador=0;
                for (String linea : lin){
                    contador++;
                    boolean isLogica=this.validarLinea(linea.trim());
                    LineaDTO l = new LineaDTO(contador,linea, "N", isLogica);
                    if(isLogica){
                        int cant = cantidadLineasLogicas;
                        cantidadLineasLogicas = cant+1;
                    }
                    this.lineas.add(l);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            this.setCantidadLineasLogicas(0);
    }
    
    /**
     * Metodo que permite comparar dos clases por medio de su nombre y su ruta dento del proyecto.
     * @param obj Clase que se desea comparar
     * @return true si las clases son iguales o false si son diferentes
     */
      
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClaseDTO other = (ClaseDTO) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.rutaRelativa, other.rutaRelativa)) {
            return false;
        }
        return true;
    }
    
    /**
     * Método que permite calcular las cantidades de líneas agregadas, eliminadas y modificadas que posee una clase.
     * @return vector de 3 posicion con enteros en donde la  posicion 0: cantidad lineas agregadas, 
     * posicion 1: cantidad lineas eliminadas, posicion 2: cantidad lineas modificadas
     */
    public int [] getCantidadTiposDeLinea(){
        int [] cantidadTipoLineas = new int[3];
        cantidadTipoLineas[0]=0;
        cantidadTipoLineas[1]=0;
        cantidadTipoLineas[2]=0;
        if(this.isEsTextoPlano()){
            for(LineaDTO l : this.lineas){
                int aux=0;
                if(l.getEstado().equalsIgnoreCase("A")){
                    aux = cantidadTipoLineas[0];
                    cantidadTipoLineas[0] = aux+1;
                }
                else if(l.getEstado().equalsIgnoreCase("E")){
                    aux = cantidadTipoLineas[1];
                    cantidadTipoLineas[1] = aux+1;
                }
                else if(l.getEstado().equalsIgnoreCase("M")){
                    aux = cantidadTipoLineas[2];
                    cantidadTipoLineas[2] = aux+1;
                }
            }
        }
        return cantidadTipoLineas;
    }
    
    /**
     * Método que permite mostrar las lineas que contiene una clase. Si la linea esta sombreada de color amarillo (F7FD8B) indica que se
     * adicionó en la nueva versión, si la línea está sombreada en verde (A7F2BE), indica que la linea de modificó en la nueva versión y si
     * la línea esta sombreada en rojo (F2A7A7), indica que la linea fue eliminada en la nuva versión.
     * @return cadena de texto con los resultados en html
     */
    public String getContenidoHTML(){
        String html="<html>";
        String auxHTMLAbre="";
        String auxHTMLCierra="";
        String auxContenido="";
        for(int i=0;i<this.getLineas().size();i++){
            LineaDTO l = this.getLineas().get(i);
                
            if(l.getEstado().equalsIgnoreCase("A")){
                auxContenido="++ ";
                auxHTMLAbre="<b style=\"background:#F7FD8B\">";
                auxHTMLCierra="</b>";
            }   
            if(l.getEstado().equalsIgnoreCase("E")){
                auxContenido="-- ";
                auxHTMLAbre="<b style=\"background:#F2A7A7\">";
                auxHTMLCierra="</b>";
            }
            if(l.getEstado().equalsIgnoreCase("M")){
                auxHTMLAbre="<b style=\"background:#A7F2BE\">";
                auxHTMLCierra="</b>";
                auxContenido="m ";
            }
            if(l.getEstado().equalsIgnoreCase("N")){
                auxHTMLAbre="";
                auxHTMLCierra="";
                auxContenido="== ";
            }
                
            html+="<p>"+auxHTMLAbre+l.getNumeroLinea()+auxContenido+l.getContenido()
                    .replaceAll("\"", "&#34;")
                    .replaceAll("\\(", "&#40;")
                    .replaceAll("\\)", "&#41;")
                    .replaceAll(">", "&#62;")
                    .replaceAll("<", "&#60;")+
                    auxHTMLCierra+"</p>";
        }
        html+="</html>";
        return html;
    }
    
    /**
     * Metodo que permmite validar si el tipo de archivo que se quiere evaluar es permitido para evaluar dichas validaciones.
     */
    public void isFormatValid(){
        try{
            boolean valido = false;
            String [] formatos = {"java","txt","sql","properties","html","xml"};
            String [] partes = this.getNombre().split("\\.");
            this.setFormato(partes[partes.length-1]);
            for(int i=0;i<formatos.length&&!valido;i++){
                if(this.getFormato().equalsIgnoreCase(formatos[i]))
                    valido=true;
            }
            this.setEsTextoPlano(valido);
        }catch(Exception e){
            this.setEsTextoPlano(false);
        }
    }

    /**
     * Permite evaluar si una linea de la clase corresponde a una línea lógica. Las validaciones difieren de los archivos .java 
     * y los otros otros formatos establecidos
     * @param linea, cadena de caracteres a evaluar
     * @return true si la linea es lógica y false si no lo es
    */
    private boolean validarLinea(String linea)
    {
        boolean logica=true;
        if(this.formato.equalsIgnoreCase("java")){
            if(linea.trim().equalsIgnoreCase("{")
                    ||linea.trim().equalsIgnoreCase("}")
                    ||linea.trim().equalsIgnoreCase("")
                    ||linea.trim().equalsIgnoreCase("//")
                    ||linea.trim().equalsIgnoreCase("/*")
                    ||linea.trim().equalsIgnoreCase("*/")
                    ||linea.trim().equalsIgnoreCase("*")
                    ||linea.trim().equalsIgnoreCase("/**")
                    ||linea.trim().equalsIgnoreCase("**/")){
                    logica=false;
            }
        }else{
            if(linea.trim().equalsIgnoreCase("")){
                    logica=false;
            }
        }   
        return logica;
    }

    
    
    
    
}
