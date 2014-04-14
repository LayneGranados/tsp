/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.utilidades;

import java.io.IOException;

/**
 *
 * @author laynegranadosmogollon
 */
public class Fachada {
    
    public String[] calcularComparacion(String rutaAntigua, String rutaNueva) throws IOException{
        Comparator c = new Comparator(rutaAntigua,rutaNueva);
        return c.realizarCalculos();
    }
    
}
