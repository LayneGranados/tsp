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
    
    private int numLinea;
    private String contenido;

    public Linea() {
    }

    public Linea(int numLinea, String contenido) {
        this.contenido = contenido;
        this.numLinea = numLinea; 
    }
    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
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
