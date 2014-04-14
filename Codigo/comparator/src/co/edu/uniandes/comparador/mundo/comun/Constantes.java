/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.comparador.mundo.comun;

/**
 *
 * @author FabianEduardo
 */
public class Constantes {

    /**
     * Ruta y nombre del archivo de propiedades 
     */
    public static String RUTA_ARCHIVO_PROPIEDADES = "comparador-config.properties";
    
    /**
     * Cargar las propiedades
     */
    public static final LectorPropiedades propertiesLoader = LectorPropiedades.getInstance();
    
    /**
     * Label de lineas agregadas
     */
    public static String LABEL_LINEA_AGREGADA = propertiesLoader.getProperty("comparator.addline");
    
    /**
     * Label de lineas eliminadas
     */
    public static String LABEL_LINEA_BORRADA = propertiesLoader.getProperty("comparator.deleteline"); 

    
}
