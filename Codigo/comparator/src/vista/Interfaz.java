//VISTA
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import comparator.mundo.Fachada;
import comparator.mundo.utilidades.LecturaArchivo;

/**
 *
 * @author laynegranadosmogollon
 */
public class Interfaz extends javax.swing.JFrame {

    private Fachada f;
    private JLabel selectedLabel;
    private String rutaProyecto;
    
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        f= new Fachada();
        this.eliminarTodos();
        final JTree tree = this.treeDirectorios;
        tree.addTreeSelectionListener(new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent e) {
        MyNode nseleccionado = (MyNode) tree.getLastSelectedPathComponent();
        System.out.println("ruta del nodo: "+nseleccionado.getRuta());
    }
});
        this.treeDirectorios = tree;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRutaVersionNueva = new javax.swing.JTextField();
        btnSeleccionarVersionNueva = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        editorAntigua = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        editorNueva = new javax.swing.JEditorPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        treeDirectorios = new javax.swing.JTree();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtResultados = new javax.swing.JTextArea();
        lblArchivoSeleccionado = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        btnComparar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setText("COMPARATOR");

        jLabel3.setText("Ruta Proyecto:");

        btnSeleccionarVersionNueva.setText("Seleccionar");
        btnSeleccionarVersionNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarVersionNuevaActionPerformed(evt);
            }
        });

        editorAntigua.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de la Version Anterior"));
        jScrollPane2.setViewportView(editorAntigua);

        editorNueva.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de la Nueva Versión"));
        jScrollPane3.setViewportView(editorNueva);

        treeDirectorios.setBorder(javax.swing.BorderFactory.createTitledBorder("Carpetas y Archivos del Proyecto"));
        jScrollPane4.setViewportView(treeDirectorios);

        txtResultados.setColumns(20);
        txtResultados.setRows(5);
        txtResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de Cambios"));
        jScrollPane5.setViewportView(txtResultados);

        lblArchivoSeleccionado.setText("Archivo Seleccionado:");
        lblArchivoSeleccionado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCalcular.setText("Agregar Versión");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnComparar.setText("Comparar");
        btnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompararActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 464, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 463, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 467, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(17, 17, 17)
                                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(lblArchivoSeleccionado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(btnComparar)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnCalcular, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(0, 9, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblArchivoSeleccionado)
                        .add(btnComparar))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btnCalcular))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .add(jScrollPane3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 953, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 640, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel2)
                .add(422, 422, 422))
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 974, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtRutaVersionNueva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 746, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnSeleccionarVersionNueva)))
                .add(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(jLabel2)
                .add(13, 13, 13)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(txtRutaVersionNueva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnSeleccionarVersionNueva))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 686, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargaEstructuraDirectorios(DefaultTreeModel arbol, MyNode padre, String ruta) {
    MyNode aux = null;
 
    File archivo = new File(ruta);
    File[] archivos = archivo.listFiles();
 
    if (archivos != null) {
        for (int i = 0; i < archivos.length; i++) {
            File archi = archivos[i];
            aux = new MyNode(archi.getAbsolutePath(),archi.getName());
            arbol.insertNodeInto(aux, padre, i); 
 
            if (archivos[i].isDirectory()) {
                try {
                    CargaEstructuraDirectorios(arbol, aux, archivos[i].getAbsolutePath() + "/");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
 
        }
 
    }
}
    private void eliminarTodos(){
        DefaultTreeModel arbolE;
        arbolE = (DefaultTreeModel) this.treeDirectorios.getModel();
        DefaultMutableTreeNode padreE = (DefaultMutableTreeNode) arbolE.getRoot();
        while(arbolE.getChildCount(padreE)>0){
            this.EliminarNodosArbol();
        }
    }
    
    private void EliminarNodosArbol() {
    DefaultTreeModel arbolE;
    arbolE = (DefaultTreeModel) this.treeDirectorios.getModel();
    DefaultMutableTreeNode padreE = (DefaultMutableTreeNode) arbolE.getRoot();
    int numeroDeHijos = arbolE.getChildCount(padreE);
    // borra el último hijo del padre
    arbolE.removeNodeFromParent((MutableTreeNode) arbolE.getChild(padreE, numeroDeHijos - 1));
}
    
    
    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        //INICIO_CODIGO
            String[] res = new String [5];
        try {
            res = f.calcularComparacion(this.txtRutaVersionNueva.getText(), this.txtRutaVersionNueva.getText());
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
  
       
            this.txtResultados.setText(res[0]);
            this.editorAntigua.setContentType("text/html");
            this.editorAntigua.setText(res[4]);
            this.editorNueva.setContentType("text/html");
            this.editorNueva.setText(res[2]);
            
        //FIN_CODIGO
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnSeleccionarVersionNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarVersionNuevaActionPerformed
        // TODO add your handling code here:
        //INICIO_CODIGO
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
           this.rutaProyecto = chooser.getSelectedFile().getAbsolutePath();
           this.txtRutaVersionNueva.setText(this.rutaProyecto);
           DefaultTreeModel arbol = (DefaultTreeModel) this.treeDirectorios.getModel();
           MyNode nroot = new MyNode(this.rutaProyecto,"Árbol de directorios");
           arbol.setRoot(nroot);
           CargaEstructuraDirectorios(arbol, nroot, this.txtRutaVersionNueva.getText());
           String[] res = new String [5];
            try {
                File fo = new File(this.rutaProyecto);
                File[] childs = fo.listFiles();
                res = f.calcularComparacion(this.rutaProyecto, this.txtRutaVersionNueva.getText());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            this.txtResultados.setText(res[0]);
        }
        else
        {
            this.txtRutaVersionNueva.setText("Ninguna carpeta fue seleccionada");
        }
        //FIN_CODIGO
    }//GEN-LAST:event_btnSeleccionarVersionNuevaActionPerformed

    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompararActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton btnSeleccionarVersionNueva;
    private javax.swing.JEditorPane editorAntigua;
    private javax.swing.JEditorPane editorNueva;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblArchivoSeleccionado;
    private javax.swing.JTree treeDirectorios;
    private javax.swing.JTextArea txtResultados;
    private javax.swing.JTextField txtRutaVersionNueva;
    // End of variables declaration//GEN-END:variables
}
