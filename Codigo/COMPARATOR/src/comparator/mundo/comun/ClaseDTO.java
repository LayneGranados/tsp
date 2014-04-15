/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.comun;

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
    
    private String nombre;
    private String ruta;
    private String rutaRelativa;
    private String rutaProyecto;
    private ArrayList<LineaDTO> lineas;
    private String estado;
    private String formato;
    private boolean esTextoPlano;
    //private int cantidadLineasLogicas;
    

    
//INICIO_METODO
    public ClaseDTO() 
    {
        this.estado="H";
        this.lineas=new ArrayList<LineaDTO>();
        this.esTextoPlano=false;
        this.rutaProyecto="";
        this.rutaRelativa="";
        //this.cantidadLineasLogicas=0;
    }
//FIN_METODO

    
//INICIO_METODO
    public ClaseDTO(String nombre, String ruta, String rutaProyecto) 
    {
        this.nombre = nombre;
        this.ruta = ruta;
        this.estado="H";
        this.lineas=new ArrayList<LineaDTO>();
        this.rutaProyecto = rutaProyecto;
        this.esTextoPlano=false;
        this.rutaRelativa="";
        //this.cantidadLineasLogicas=0;
    }
//FIN_METODO

//INICIO_METODO
    public String getNombre() 
    {
        return nombre;
    }
//FIN_METODO

//INICIO_METODO
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
//FIN_METODO

    
//INICIO_METODO
    public String getRuta() 
    {
        return ruta;
    }
//FIN_METODO

//INICIO_METODO
    public void setRuta(String ruta) 
    {
        this.ruta = ruta;
    }
//FIN_METODO

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
    
//INICIO_METODO
    public void guardarMisLineas()
    {
        this.realizarConteo(this.getRuta());
    }
//FIN_METODO    

//INICIO_METODO    
    private void realizarConteo(String ruta)
    {
        FileReader fr = null;
        BufferedReader br = null;
        File archivo = new File(ruta);
        
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            int contador=0;
            while ((linea = br.readLine()) != null) 
            {
                contador++;
                LineaDTO l = new LineaDTO(contador,linea, "N");
                this.lineas.add(l);
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
    }
//FIN_METODO
    
//INICIO_METODO
    private boolean validarLinea(String linea)
    {
        boolean validez=true;
        return validez;
    }
//FIN_METODO
      
//INICIO_METODO
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
//FIN_METODO  
    //F2A7A7 rojo eliminadas
    //A7F2BE verde modificadas
    //F7FD8B amarillo adicionadas
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
                
            html+="<p>"+auxHTMLAbre+l.getNumeroLinea()+auxContenido+l.getContenido().replaceAll("\"", "&#34;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;").replaceAll(">", "&#62;").replaceAll("<", "&#60;")+
                    auxHTMLCierra+"</p>";
        }
        html+="</html>";
        return html;
    }
    
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

    
    
    
    
}
