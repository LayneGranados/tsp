/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author laynegranadosmogollon
 */
public class MyNode extends DefaultMutableTreeNode{
    
    private String ruta;

    public MyNode(String ruta, Object userObject) {
        super(userObject);
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Vector getChildren() {
        return children;
    }

    public void setChildren(Vector children) {
        this.children = children;
    }
    
    
            
            
    
}
