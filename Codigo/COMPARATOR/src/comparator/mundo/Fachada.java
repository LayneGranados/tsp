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
 * @author ClickIT
 */
public class Fachada {
    
    Comparator comparator;

    /**
     * Método que inicializa los atributos de la clase comparator.
     */
    public Fachada() {
       this.comparator=new Comparator();
    }

    
    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
    
    /**
     * Método que inicial el objeto comparator dada la ruta del nuevo proyecto
     * @param rutaNueva ruta del nuevo proyecto de donde se deben tomar las clases
     */
    public void inicializarComparator(String rutaNueva){
        this.comparator = new Comparator(rutaNueva);
    }
    
    /**
     * Metodo que llama a un método de la clase comparator que permite realizar los calculos de las comparaciones
     * @param rutaNueva ruta del nuevo proyecto el cual se quiere comparar con la version anterior.
     * @return calculos realizados en la comparacion.
     * @throws IOException excepcion que se puede generar de la lectura de archivos en el disco
     */
    public Object[] calcularComparacion(String rutaNueva) throws IOException{
        return this.comparator.compararProyectos();
    }
    
    /**
     * Metodo que busca un objeto ClaseDTO dada la ruta del archivo seleccionado directamente en la interfaz
     * @param rutaArchivoSeleccionado ruta del archivo seleccionado en el arbol de directorios
     * @return objeto de tipo ClaseDTO
     * @throws IOException excepcion generada de la lectura de archivos en el disco
     */
    public ClaseDTO buscarClasdeRuta(String rutaArchivoSeleccionado) throws IOException{
        return this.comparator.buscarClaseDeArchivoSeleccionado(rutaArchivoSeleccionado);
    }
    
    /**
     * Método que orquesta los metodos creados en la clase comparator para comparar dos objetos de ClaseDTO
     * @param claseNuevaSeleccionada objeto de tipo ClaseDTO que va a ser comparado con su versión antigua
     * @return los calculos obtenidos de la comparación realizada
     */
    public String[] compararDosClases(ClaseDTO claseNuevaSeleccionada){
        try {
            return this.comparator.compararDosClasesBuscandoAntigua(claseNuevaSeleccionada);
        } catch (IOException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Metodo que orquesta los metodos de la clase comparator para crear y modificar los archivos y carpetas necesarios en una version.
     * @param usuario nombre del usuario que realiza el cambio
     * @param comentario razon o motivo del cambio
     * @param comparator objeto de tipo comparator que posee toda la informacion para realizar los calculos.
     * @return true si se creo la version y false si fallo el procedimiento
     */
    public boolean crearVersion(String usuario, String comentario, Comparator comparator){
        return comparator.agregarVersion(usuario, comentario);
    }
    
    /**
     * Metodo que permite obtener los detalles de una version especifica
     * @param version numero de la version de la cual se quieren obtener los detalles de los cambios
     * @return detalle de los cambios con formato html
     */
    public String getDetalleVersion(int version){
        return this.comparator.getDetalleVersion(version);
    }
    
    
}
