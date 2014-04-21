/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.utilidades;

import difflib.Chunk;
import java.io.File;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author FabiánEduardo
 */
public class ComparadorArchivoTest {
    
    public ComparadorArchivoTest() {
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
     * Test of getDeletesFromOriginal method, of class ComparadorArchivo.
     */
    @Test
    public void testGetDeletesFromOriginal() throws Exception {
        System.out.println("getDeletesFromOriginal");
        ComparadorArchivo instance = new ComparadorArchivo(new File("D:\\PruebasComparator\\ClasePruebaE1Old.java"), new File("D:\\PruebasComparator\\ClasePruebaE1.java"));
        int expPosition = 3;
        List<Chunk> eliminados = instance.getDeletesFromOriginal();
        for(Chunk c: eliminados){
            System.out.println("Posicion Eliminada: "+c.getPosition());
            assertEquals(expPosition, c.getPosition());
        }
    }
    
    /**
     * Test of getChangesFromOriginal method, of class ComparadorArchivo.
     */
    @Test
    public void testGetChangesFromOriginal() throws Exception {
        System.out.println("getChangesFromOriginal");
        ComparadorArchivo instance = new ComparadorArchivo(new File("D:\\PruebasComparator\\ClasePruebaM2Old.java"), new File("D:\\PruebasComparator\\ClasePruebaM2.java"));
        int expPosition = 3;
        List<Chunk> eliminados = instance.getChangesFromOriginal();
        for(Chunk c: eliminados){
            System.out.println("Posicion Modificada: "+c.getPosition());
            assertEquals(expPosition, c.getPosition());
        }
    }

    /**
     * Test of getInsertsFromOriginal method, of class ComparadorArchivo.
     */
    @Test
    public void testGetInsertsFromOriginal() throws Exception {
        System.out.println("getInsertsFromOriginal");
        ComparadorArchivo instance = new ComparadorArchivo(new File("D:\\PruebasComparator\\ClasePruebaA3Old.java"), new File("D:\\PruebasComparator\\ClasePruebaA3.java"));
        int expPosition = 3;
        List<Chunk> eliminados = instance.getInsertsFromOriginal();
        for(Chunk c: eliminados){
            System.out.println("Posicion Agrega Linea: "+c.getPosition());
            assertEquals(expPosition, c.getPosition());
        }
    }

}
