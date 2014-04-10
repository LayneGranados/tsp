/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.util.Objects;

/**
 *
 * @author laynegranadosmogollon
 */
public class Linea {
    
    private int numeroLinea;
    private String contenido;
    private String estado;// puede ser "A" de agregada, "E" de eliminada y "M" de modificada o "N" de normal

    public Linea() {
    }

    public Linea(int numeroLinea, String contenido, String estado) {
        this.contenido = contenido;
        this.numeroLinea = numeroLinea; 
        this.estado = estado; 
        
        
    }
    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.contenido);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Linea other = (Linea) obj;
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
