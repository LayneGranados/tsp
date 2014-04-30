/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo;

import comparator.mundo.comun.ClaseDTO;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author FabiánEduardo
 */
public class ComparatorTest {
    
    public ComparatorTest() {
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
     * Test of crearClases method, of class Comparator.
     */
    @Test
    public void testCrearClases() {
        System.out.println("crearClases");
        Comparator instance = new Comparator("D:\\PruebasComparator\\PruebasProyecto");
        instance.crearClases();
        int tamanoNueva = 2;
        int tamanoAntigua = 1;
        System.out.println("Número de archivos antigua versión: "+instance.getClasesVersionAntigua().size());
        System.out.println("Número de archivos nueva versión: "+instance.getClasesVersionNueva().size());
        assertEquals(tamanoNueva, instance.getClasesVersionNueva().size());
        assertEquals(tamanoAntigua, instance.getClasesVersionAntigua().size());
    }
    
    /**
     * Test of crearClases method, of class Comparator.
     */
    @Test
    public void testCrearClases2() {
        System.out.println("crearClases");
        Comparator instance = new Comparator("D:\\PruebasComparator\\PruebasProyecto2");
        instance.crearClases();
        int tamanoNueva = 2;
        int tamanoAntigua = 1;
        System.out.println("Número de archivos antigua versión: "+instance.getClasesVersionAntigua().size());
        System.out.println("Número de archivos nueva versión: "+instance.getClasesVersionNueva().size());
        assertEquals(tamanoNueva, instance.getClasesVersionNueva().size());
        assertEquals(tamanoAntigua, instance.getClasesVersionAntigua().size());
    }

    /**
     * Test of compararProyectos method, of class Comparator.
     */
    @Test
    public void testCompararProyectos() throws Exception {
        System.out.println("compararProyectos");
        Comparator instance = new Comparator("D:\\PruebasComparator\\PruebasProyecto3");
        instance.crearClases();
        Object[] result = instance.compararProyectos();
        System.out.println(result);
    }

}
