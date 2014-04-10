/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author laynegranadosmogollon
 */
public class Clase 
{
    
    private String nombre;
    private String ruta;
    private String rutaRelativa;
    private ArrayList<Linea> lineasAgregadas;
    private ArrayList<Linea> lineasEliminadas;
    private ArrayList<Linea> lineas;
    private String estado;
    //private int cantidadLineasLogicas;
    

    
//INICIO_METODO
    public Clase() 
    {
        this.estado="H";
        this.lineas=new ArrayList<Linea>();
        this.lineasAgregadas=new ArrayList<Linea>();
        this.lineasEliminadas=new ArrayList<Linea>();
        //this.cantidadLineasLogicas=0;
    }
//FIN_METODO

    
//INICIO_METODO
    public Clase(String nombre, String ruta) 
    {
        this.nombre = nombre;
        this.ruta = ruta;
        this.rutaRelativa = ruta.split("src/")[1];
        this.estado="H";
        this.lineas=new ArrayList<Linea>();
        this.lineasAgregadas=new ArrayList<Linea>();
        this.lineasEliminadas=new ArrayList<Linea>();
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

    public ArrayList<Linea> getLineasAgregadas() {
        return lineasAgregadas;
    }

    public void setLineasAgregadas(ArrayList<Linea> lineasAgregadas) {
        this.lineasAgregadas = lineasAgregadas;
    }

    public ArrayList<Linea> getLineasEliminadas() {
        return lineasEliminadas;
    }

    public void setLineasEliminadas(ArrayList<Linea> lineasEliminadas) {
        this.lineasEliminadas = lineasEliminadas;
    }

    public ArrayList<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Linea> lineas) {
        this.lineas = lineas;
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
                Linea l = new Linea(contador,linea, "N");
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
        final Clase other = (Clase) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.rutaRelativa, other.rutaRelativa)) {
            return false;
        }
        return true;
    }
//FIN_METODO  
    
    public String getContenidoHTML(){
        String html="<html>";
        String auxHTMLAbre="";
        String auxHTMLCierra="";
        String auxContenido="";
        for(int i=0;i<this.getLineas().size();i++){
            Linea l = this.getLineas().get(i);
            if(!l.getEstado().equalsIgnoreCase("N")){
                auxHTMLAbre="<b><i><u style=\"background: #FF00\">";
                auxHTMLCierra="</u></i></b>";
            }
            else{
                auxHTMLAbre="";
                auxHTMLCierra="";
            }
                
            if(l.getEstado().equalsIgnoreCase("A"))
                auxContenido="++ ";
            if(l.getEstado().equalsIgnoreCase("E"))
                auxContenido="-- ";
            if(l.getEstado().equalsIgnoreCase("M"))
                auxContenido="m";
            if(l.getEstado().equalsIgnoreCase("N"))
                auxContenido="== ";
            html+="<p>"+auxHTMLAbre+l.getNumeroLinea()+auxContenido+l.getContenido().replaceAll("\"", "&#34;")+auxHTMLCierra+"</p>";
        }
        html+="</html>";
        return html;
    }
    
    
    
    
}
