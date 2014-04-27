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
       this.comparator=new Comparator();
    }

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
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
    
    public boolean crearVersion(String usuario, String comentario, Comparator comparator){
        return comparator.agregarVersion(usuario, comentario);
    }
    
    public String getDetalleVersion(int version){
        return this.comparator.getDetalleVersion(version);
    }
    
    
}
