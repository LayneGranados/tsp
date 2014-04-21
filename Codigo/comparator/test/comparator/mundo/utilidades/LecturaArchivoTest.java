/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.utilidades;

import comparator.mundo.comun.ClaseDTO;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author FabiánEduardo
 */
public class LecturaArchivoTest {
    
    public LecturaArchivoTest() {
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
     * Test of modificarEstadoLineas method, of class LecturaArchivo.
     */
    @Test
    public void testObtenerLineasArchivo() throws Exception {
        System.out.println("guardarMisLineas");
        int tamano = 6;
        ClaseDTO claseOld = new ClaseDTO();
        claseOld.setRuta("D:\\PruebasComparator\\ClasePruebaE1Old.java");
        claseOld.guardarMisLineas();
        System.out.println("Número de lineas leidas: "+claseOld.getLineas().size());
        assertEquals(tamano, claseOld.getLineas().size());
    }
    
    /**
     * Test of modificarEstadoLineas method, of class LecturaArchivo.
     */
    @Test
    public void testModificarEstadoLineasE() throws Exception {
        System.out.println("modificarEstadoLineas");
        String estado = "E";
        ClaseDTO claseOld = new ClaseDTO();
        claseOld.setRuta("D:\\PruebasComparator\\ClasePruebaE1Old.java");
        claseOld.guardarMisLineas();
        ClaseDTO claseNew = new ClaseDTO();
        claseNew.setRuta("D:\\PruebasComparator\\ClasePruebaE1.java");
        claseNew.guardarMisLineas();
        boolean orientacion = true;
        LecturaArchivo instance = new LecturaArchivo();
        instance.modificarEstadoLineas(claseOld, claseNew, orientacion);
        System.out.println("Estado eliminada: "+claseNew.getLineas().get(3).getEstado());
        assertEquals(estado, claseNew.getLineas().get(3).getEstado());
    }
    
    /**
     * Test of modificarEstadoLineas method, of class LecturaArchivo.
     */
    @Test
    public void testModificarEstadoLineasM() throws Exception {
        System.out.println("modificarEstadoLineas");
        String estado = "M";
        ClaseDTO claseOld = new ClaseDTO();
        claseOld.setRuta("D:\\PruebasComparator\\ClasePruebaM2Old.java");
        claseOld.guardarMisLineas();
        ClaseDTO claseNew = new ClaseDTO();
        claseNew.setRuta("D:\\PruebasComparator\\ClasePruebaM2.java");
        claseNew.guardarMisLineas();
        boolean orientacion = true;
        LecturaArchivo instance = new LecturaArchivo();
        instance.modificarEstadoLineas(claseOld, claseNew, orientacion);
        System.out.println("Estado eliminada: "+claseNew.getLineas().get(3).getEstado());
        assertEquals(estado, claseNew.getLineas().get(3).getEstado());
    }
    
    /**
     * Test of modificarEstadoLineas method, of class LecturaArchivo.
     */
    @Test
    public void testModificarEstadoLineasA() throws Exception {
        System.out.println("modificarEstadoLineas");
        String estado = "A";
        ClaseDTO claseOld = new ClaseDTO();
        claseOld.setRuta("D:\\PruebasComparator\\ClasePruebaA3Old.java");
        claseOld.guardarMisLineas();
        ClaseDTO claseNew = new ClaseDTO();
        claseNew.setRuta("D:\\PruebasComparator\\ClasePruebaA3.java");
        claseNew.guardarMisLineas();
        boolean orientacion = true;
        LecturaArchivo instance = new LecturaArchivo();
        instance.modificarEstadoLineas(claseOld, claseNew, orientacion);
        System.out.println("Estado eliminada: "+claseNew.getLineas().get(3).getEstado());
        assertEquals(estado, claseNew.getLineas().get(3).getEstado());
    }
}
