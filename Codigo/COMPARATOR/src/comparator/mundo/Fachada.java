/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo;

import comparator.mundo.comun.ClaseDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laynegranadosmogollon
 */
public class Fachada {
    
    Comparator comparator;

    public Fachada() {
    }

    
    public void inicializarComparator(String rutaNueva){
        this.comparator = new Comparator(rutaNueva);
    }
    
    public Object[] calcularComparacion(String rutaNueva) throws IOException{
        return this.comparator.compararProyectos();
    }
    
    public ClaseDTO buscarClasdeRuta(String rutaArchivoSeleccionado) throws IOException{
        return this.comparator.buscarClaseDeArchivoSeleccionado(rutaArchivoSeleccionado);
    }
    
    public String[] compararDosClases(ClaseDTO claseNuevaSeleccionada){
        try {
            return this.comparator.compararDosClasesBuscandoAntigua(claseNuevaSeleccionada);
        } catch (IOException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean crearVersion(){
        return this.comparator.agregarVersion();
    }
}
