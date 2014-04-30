/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.utilidades;

import java.io.File;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author FabiánEduardo
 */
public class UtilidadesArchivoTest {
    
    public UtilidadesArchivoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crearCarpeta method, of class UtilidadesArchivo.
     */
    @Test
    public void testCrearCarpeta() {
        System.out.println("crearCarpeta");
        String ruta = "D:\\PruebasCrearCarpeta";
        boolean expResult = true;
        boolean result = UtilidadesArchivo.crearCarpeta(ruta);
        assertEquals(expResult, result);
        System.out.println("Carpeta creada correctamente");
    }
    
    /**
     * Test of crearCarpeta method, of class UtilidadesArchivo.
     */
    @Test
    public void testCrearCarpetaError() {
        System.out.println("crearCarpeta");
        String ruta = "/**/)";
        boolean expResult = false;
        boolean result = UtilidadesArchivo.crearCarpeta(ruta);
        assertEquals(expResult, result);
        System.out.println("Carpeta creada correctamente");
    }

    /**
     * Test of crearArchivo method, of class UtilidadesArchivo.
     */
    @Test
    public void testCrearArchivo() {
        System.out.println("crearArchivo");
        String rutaArchivo = "D:\\PruebasCrearCarpeta\\pruebaarchivo.java";
        boolean expResult = true;
        boolean result = UtilidadesArchivo.crearArchivo(rutaArchivo);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of crearArchivo method, of class UtilidadesArchivo.
     */
    @Test
    public void testCrearArchivoError() {
        System.out.println("crearArchivo");
        String rutaArchivo = "D:\\CarpetaInexistente\\archivo.java";
        boolean expResult = false;
        boolean result = UtilidadesArchivo.crearArchivo(rutaArchivo);
        assertEquals(expResult, result);
    }

    /**
     * Test of escribirNuevaLineaEnArchivo method, of class UtilidadesArchivo.
     */
    @Test
    public void testEscribirNuevaLineaEnArchivo() {
        System.out.println("escribirNuevaLineaEnArchivo");
        String rutaArchivo = "D:\\PruebasCarpetaOrigen\\Prueba.java";
        String nuevaLinea = "primera linea";
        UtilidadesArchivo.escribirNuevaLineaEnArchivo(rutaArchivo, nuevaLinea);
    }

    /**
     * Test of leerLineasArchivo method, of class UtilidadesArchivo.
     */
    @Test
    public void testLeerLineasArchivo() {
        System.out.println("leerLineasArchivo");
        String rutaArchivo = "D:\\PruebasCarpetaOrigen\\Prueba.java";
        ArrayList expResult = new ArrayList();
        expResult.add("primera linea");
        ArrayList result = UtilidadesArchivo.leerLineasArchivo(rutaArchivo);
        assertEquals(expResult, result);
    }

    /**
     * Test of copiarCarpeta method, of class UtilidadesArchivo.
     */
    @Test
    public void testCopiarCarpeta() {
        System.out.println("copiarCarpeta");
        String carpetaOrigen = "D:\\PruebasCarpetaOrigen";
        String carpetaDestino = "D:\\PruebasCopia";
        UtilidadesArchivo.copiarCarpeta(carpetaOrigen, carpetaDestino);
    }

    /**
     * Test of copyFile method, of class UtilidadesArchivo.
     */
    @Test
    public void testCopyFile() {
        System.out.println("copyFile");
        File s = new File("D:\\PruebasCarpetaOrigen\\Prueba.java");
        File t = new File("D:\\PruebasCarpetaOrigen\\Prueba2.java");
        UtilidadesArchivo.copyFile(s, t);
    }
}
