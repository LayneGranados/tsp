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
    

    
//INICIO_METODO
    public Clase() 
    {
        this.estado="H";
        this.lineas=new ArrayList<Linea>();
        this.lineasAgregadas=new ArrayList<Linea>();
        this.lineasEliminadas=new ArrayList<Linea>();
    }
//FIN_METODO

    
//INICIO_METODO
    public Clase(String nombre, String ruta, int cantidadLineasPropias) 
    {
        this.nombre = nombre;
        this.ruta = ruta;
        this.rutaRelativa = ruta.split("src/")[1];
        this.estado="H";
        this.lineas=new ArrayList<Linea>();
        this.lineasAgregadas=new ArrayList<Linea>();
        this.lineasEliminadas=new ArrayList<Linea>();
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
                Linea l = new Linea(contador,linea);
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
    private boolean validarLinea(String linea )
    {
        boolean validez=true;
        return validez;
    }
//FIN_METODO
    
//INICIO_METODO
    public String getNombreMetodo(String cabeceraMetodo)
    {
        String nombreMetodo="";
        String aux="";
        boolean noBuscarMas=false;
        ArrayList<String> palabras= new ArrayList<String>();
        for(int i=0;i<cabeceraMetodo.length();i++)
        {
            if(aux.length()==0&&cabeceraMetodo.charAt(i)==' ')
            {   
            }
            else
                if(aux.length()>0&&(cabeceraMetodo.charAt(i)==' '||cabeceraMetodo.charAt(i)==')' ))
                {
                    palabras.add(aux);
                    aux="";
                }
            else
                if(cabeceraMetodo.charAt(i)!=' ')
                {
                    aux+=cabeceraMetodo.charAt(i);
                }            
        }
        for(int i=0;i<palabras.size()&&!noBuscarMas;i++)
        {
            String palabra = palabras.get(i);
            if(palabra.contains("("))
            {
                noBuscarMas=true;
                String[] pal;
                pal = palabra.split("\\(");
                nombreMetodo=pal[0];
            }
        }
        return nombreMetodo;
    }

    //FIN_METODO
   

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
    
    
    
    
}
