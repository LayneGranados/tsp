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
import comparator.mundo.comun.ClaseDTO;
import comparator.mundo.utilidades.LecturaArchivo;
import java.awt.Dimension;

/**
 *
 * @author laynegranadosmogollon
 */
public class Interfaz extends javax.swing.JFrame {

    private final Fachada f;
    private String res;
    private String rutaProyecto;
    
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.eliminarTodos();
        this.f=new Fachada();
        res="";
        final JTree tree = this.treeDirectorios;
        tree.addTreeSelectionListener(new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent e) {
        MyNode nseleccionado = (MyNode) tree.getLastSelectedPathComponent();
        res = nseleccionado.getRuta();
        
        
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
        lblArchivoSeleccionado = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        btnComparar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtResultados = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 668));
        setPreferredSize(new java.awt.Dimension(1040, 668));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setText("COMPARATOR");

        jLabel3.setText("Ruta Proyecto:");

        btnSeleccionarVersionNueva.setText("Seleccionar");
        btnSeleccionarVersionNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarVersionNuevaActionPerformed(evt);
            }
        });

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(32767, 386));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(974, 386));

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 358));
        jPanel1.setPreferredSize(new java.awt.Dimension(953, 358));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 100));

        editorAntigua.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de la Version Anterior"));
        editorAntigua.setEditable(false);
        editorAntigua.setAutoscrolls(false);
        editorAntigua.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        editorAntigua.setMinimumSize(new java.awt.Dimension(118, 40));
        editorAntigua.setPreferredSize(new java.awt.Dimension(118, 39));
        jScrollPane2.setViewportView(editorAntigua);

        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setFocusable(false);
        jScrollPane3.setMaximumSize(new java.awt.Dimension(32767, 100));
        jScrollPane3.setRequestFocusEnabled(false);
        jScrollPane3.setVerifyInputWhenFocusTarget(false);

        editorNueva.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de la Nueva Versión"));
        editorNueva.setEditable(false);
        editorNueva.setAutoscrolls(false);
        editorNueva.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        editorNueva.setMinimumSize(new java.awt.Dimension(118, 40));
        editorNueva.setPreferredSize(new java.awt.Dimension(118, 40));
        jScrollPane3.setViewportView(editorNueva);

        treeDirectorios.setBorder(javax.swing.BorderFactory.createTitledBorder("Carpetas y Archivos del Proyecto"));
        jScrollPane4.setViewportView(treeDirectorios);

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

        txtResultados.setColumns(20);
        txtResultados.setRows(5);
        txtResultados.setBorder(null);
        jScrollPane5.setViewportView(txtResultados);

        jTabbedPane2.addTab("Cambios del proyecto", jScrollPane5);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane2.addTab("Cambios del archivo", jScrollPane1);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 457, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 464, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 449, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(8, 8, 8))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 467, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(17, 17, 17)
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(lblArchivoSeleccionado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btnComparar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnCalcular, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(14, 14, 14))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblArchivoSeleccionado)
                        .add(btnComparar))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btnCalcular))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 300, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(191, 191, 191))
        );

        jTabbedPane1.addTab("Comparar Archivos", jPanel1);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 953, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 494, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ver historico de Cambios", jPanel3);

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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 540, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
           String res ="";
            try {
                f.inicializarComparator(rutaProyecto);
                res = f.calcularComparacion(this.txtRutaVersionNueva.getText());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            this.txtResultados.setText(res);
        }
        else
        {
            this.txtRutaVersionNueva.setText("Ninguna carpeta fue seleccionada");
        }
        //FIN_CODIGO
    }//GEN-LAST:event_btnSeleccionarVersionNuevaActionPerformed

    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed
        //INICIO_CODIGO
        String[] res = new String [4];

        try {
            // TODO add your handling code here:
            if(this.res.equalsIgnoreCase("")){
                this.lblArchivoSeleccionado.setText("No se ha seleccionado un archivo a comparar");
            }else{
                ClaseDTO c = this.f.buscarClasdeRuta(this.res);
                if(c==null){
                    this.lblArchivoSeleccionado.setText("No se ha encontrado una clase con ese nombre");
                }else{
                    if(c.getEstado().equalsIgnoreCase("A")){
                        this.lblArchivoSeleccionado.setText("Archivo Seleccionado: Archivo nuevo, no puede ser comparado");
                    }
                    else
                    if(!c.isEsTextoPlano()){
                        this.lblArchivoSeleccionado.setText("Archivo Seleccionado: Formato inválido, no puede ser comparado");
                    }
                    else{
                        this.lblArchivoSeleccionado.setText("Archivo Seleccionado:" +c.getRutaRelativa());
                        res = f.compararDosClases(c);
                        this.editorAntigua.setContentType("text/html");
                        this.editorAntigua.setText("");
                        this.editorAntigua.setText(res[3]);
                        this.editorNueva.setContentType("text/html");
                        this.editorNueva.setText("");
                        this.editorNueva.setText(res[1]);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        //FIN_CODIGO
    }//GEN-LAST:event_btnCompararActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCalcularActionPerformed

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblArchivoSeleccionado;
    private javax.swing.JTree treeDirectorios;
    private javax.swing.JTextArea txtResultados;
    private javax.swing.JTextField txtRutaVersionNueva;
    // End of variables declaration//GEN-END:variables
}
