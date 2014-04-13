/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.comparador.mundo.comun;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author FabianEduardo
 */
public class LectorPropiedades {

    private static LectorPropiedades instance;
    private static Properties properties = new Properties();

    private LectorPropiedades() {
        load();
    }

    public static LectorPropiedades getInstance() {
        if (instance == null) {
            instance = new LectorPropiedades();
        }
        return instance;
    }

    /**
     * Metodo para cargar el archivo de propiedades
     *
     * @return
     */
    public static Properties load() {
        try {
            ClassLoader claslLoader = LectorPropiedades.class.getClassLoader();
            InputStream inputStream = claslLoader.getResourceAsStream(Constantes.PROPERTIES_FILE_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Error cargando archivo de propiedades " + e.getMessage());
        }
        return properties;
    }

    /**
     * Metodo para obtener una propiedad del archivo
     *
     * @param prop nombre de la propiedad
     * @return valor de la propiedad
     */
    public String getProperty(String prop) {
        String pro = properties.getProperty(prop);
        if (pro == null) {
            System.err.println("error cargando propiedad " + prop);
        }
        return pro;
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getPropertyNames() {
        return properties.propertyNames();
    }
}
