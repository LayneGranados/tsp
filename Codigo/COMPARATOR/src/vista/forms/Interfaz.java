//VISTA
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.forms;

import vista.forms.VentanaAgregarVersion;
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
import comparator.mundo.utilidades.UtilidadesArchivo;
import java.awt.Dimension;
import javax.swing.JTable;
import vista.controladores.ControlTabla;
import vista.controladores.ModeloTabla;
import vista.controladores.MyNode;

/**
 *
 * @author laynegranadosmogollon
 */
public class Interfaz extends javax.swing.JFrame {

    private Fachada f;
    private String res;
    private String rutaProyecto;
    private boolean versionado;
    
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.eliminarTodos();
        this.f=new Fachada();
        versionado=false;
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
        txtCambiosProyecto = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCambiosArchivo = new javax.swing.JTextArea();
        panelHistorico = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnVerDetalles = new javax.swing.JButton();
        btnActualizarHistorico = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 20)); // NOI18N
        jLabel2.setText("COMPARATOR");

        txtRutaVersionNueva.setEditable(false);
        txtRutaVersionNueva.setBackground(new java.awt.Color(236, 232, 232));
        txtRutaVersionNueva.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtRutaVersionNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaVersionNuevaActionPerformed(evt);
            }
        });

        btnSeleccionarVersionNueva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/media/saved-search.png"))); // NOI18N
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

        editorAntigua.setEditable(false);
        editorAntigua.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de la Version Anterior"));
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

        editorNueva.setEditable(false);
        editorNueva.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de la Nueva Versión"));
        editorNueva.setAutoscrolls(false);
        editorNueva.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        editorNueva.setMinimumSize(new java.awt.Dimension(118, 40));
        jScrollPane3.setViewportView(editorNueva);

        treeDirectorios.setBorder(javax.swing.BorderFactory.createTitledBorder("Carpetas y Archivos del Proyecto"));
        jScrollPane4.setViewportView(treeDirectorios);

        lblArchivoSeleccionado.setText("Archivo Seleccionado:");
        lblArchivoSeleccionado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/media/code-add.png"))); // NOI18N
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnComparar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/media/compare.png"))); // NOI18N
        btnComparar.setEnabled(false);
        btnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompararActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 152, Short.MAX_VALUE)
        );

        txtCambiosProyecto.setColumns(20);
        txtCambiosProyecto.setRows(5);
        txtCambiosProyecto.setBorder(null);
        jScrollPane5.setViewportView(txtCambiosProyecto);

        jTabbedPane2.addTab("Cambios del proyecto", jScrollPane5);

        txtCambiosArchivo.setColumns(20);
        txtCambiosArchivo.setRows(5);
        jScrollPane1.setViewportView(txtCambiosArchivo);

        jTabbedPane2.addTab("Cambios del archivo", jScrollPane1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 498, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 488, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(518, 518, 518)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(lblArchivoSeleccionado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 864, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnComparar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnCalcular, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 495, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(389, 389, 389))
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(33, 33, 33)
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, btnComparar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, btnCalcular, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(lblArchivoSeleccionado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(16, 16, 16)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Comparar Archivos", jPanel1);

        tabla.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setRowHeight(25);
        jScrollPane6.setViewportView(tabla);

        jScrollPane7.setViewportView(jScrollPane6);

        btnVerDetalles.setText("Ver Detalle de los cambios de la Versión");
        btnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallesActionPerformed(evt);
            }
        });

        btnActualizarHistorico.setText("Actualizar Histórico");
        btnActualizarHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarHistoricoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panelHistoricoLayout = new org.jdesktop.layout.GroupLayout(panelHistorico);
        panelHistorico.setLayout(panelHistoricoLayout);
        panelHistoricoLayout.setHorizontalGroup(
            panelHistoricoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelHistoricoLayout.createSequentialGroup()
                .add(104, 104, 104)
                .add(btnVerDetalles)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 260, Short.MAX_VALUE)
                .add(btnActualizarHistorico)
                .add(180, 180, 180))
            .add(panelHistoricoLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 974, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHistoricoLayout.setVerticalGroup(
            panelHistoricoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelHistoricoLayout.createSequentialGroup()
                .add(panelHistoricoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnVerDetalles)
                    .add(btnActualizarHistorico))
                .add(8, 8, 8)
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 481, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14))
        );

        jTabbedPane1.addTab("Ver historico de Cambios", panelHistorico);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/media/sync.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/media/logo.PNG"))); // NOI18N

        jLabel4.setText("Aplicación que permite comparar los archivos (.txt, .sql, .java, .html, .xml, .properties) de un proyecto con su versión anterior");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1030, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(txtRutaVersionNueva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 864, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnSeleccionarVersionNueva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(333, 333, 333)
                                        .add(jLabel2))
                                    .add(jLabel4))))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(btnSeleccionarVersionNueva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(txtRutaVersionNueva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargaEstructuraDirectorios(DefaultTreeModel arbol, MyNode padre, String ruta) {
    MyNode aux = null;
 
    File archivo = new File(ruta);
    File[] archivos = archivo.listFiles();
    int contadorNodo=0;
    if (archivos != null) {
        for (int i = 0; i < archivos.length;i++) {
            File archi = archivos[i];
            if(!archi.getName().equalsIgnoreCase(".comparator")){
                aux = new MyNode(archi.getAbsolutePath(),archi.getName());
                arbol.insertNodeInto(aux, padre, contadorNodo); 

                if (archivos[i].isDirectory()) {
                    try {
                        CargaEstructuraDirectorios(arbol, aux, archivos[i].getAbsolutePath() + "/");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                contadorNodo++;
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
           Object[] res;
           String cadena = "";
            try {
                f.inicializarComparator(rutaProyecto);
                res = f.calcularComparacion(this.txtRutaVersionNueva.getText());
                cadena = (String)res[1];
                this.versionado=(Boolean)res[0];
                if(versionado){
                    ModeloTabla modelo = new ModeloTabla(); 
                    ControlTabla control = new ControlTabla (modelo,this.rutaProyecto);
                    this.jScrollPane7.setViewportView(tabla);
                    this.jScrollPane7.setColumnHeaderView (tabla.getTableHeader());
                    this.tabla.setModel(modelo);
                }
                this.btnComparar.setEnabled(versionado);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            this.txtCambiosProyecto.setText(cadena);
        }
        else
        {
            this.txtRutaVersionNueva.setText("Ninguna carpeta fue seleccionada");
        }
        //FIN_CODIGO
    }//GEN-LAST:event_btnSeleccionarVersionNuevaActionPerformed

    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed
        String[] res = new String [8];

        try {
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
                        String cambiosArchivo = "";
                        cambiosArchivo = "Archivo:  "+c.getRutaRelativa()+" \n"+"- Cantidad Lineas Agregadas: "+res[4]+" \n"+"- Cantidad Lineas Eliminadas: "+res[5]+" \n"+"- Cantidad Lineas Modificadas: "+res[6]+" \n"+"- Cantidad Total Lineas Lógicas: "+res[7];
                        this.txtCambiosArchivo.setText(cambiosArchivo);
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
        VentanaAgregarVersion ventana=new VentanaAgregarVersion(this, f.getComparator());
        ventana.setVisible(true);
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallesActionPerformed
        // TODO add your handling code here:
        
        int i = this.tabla.getSelectedRow();
        if(i>-1){
            String detallado = this.f.getDetalleVersion(i);
            DetalleCambio detalle = new DetalleCambio(detallado);
            detalle.setVisible(true);
        }
    }//GEN-LAST:event_btnVerDetallesActionPerformed

    private void btnActualizarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarHistoricoActionPerformed
        // TODO add your handling code here:
        try{
            ModeloTabla modelo = new ModeloTabla(); 
            ControlTabla control = new ControlTabla (modelo,this.rutaProyecto);
            this.jScrollPane7.setViewportView(tabla);
            this.jScrollPane7.setColumnHeaderView (tabla.getTableHeader());
            this.tabla.setModel(modelo);
        }catch(Exception e){
            System.out.println();
        }
    }//GEN-LAST:event_btnActualizarHistoricoActionPerformed

    private void txtRutaVersionNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaVersionNuevaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaVersionNuevaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //initComponents();
        //this.eliminarTodos();
        this.f=new Fachada();
        versionado=false;
        res="";
        final JTree tree = this.treeDirectorios;
        tree.addTreeSelectionListener(new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent e) {
        MyNode nseleccionado = (MyNode) tree.getLastSelectedPathComponent();
        res = nseleccionado.getRuta();
    }
    });
        this.treeDirectorios = tree;
        
           this.txtRutaVersionNueva.setText(this.rutaProyecto);
           DefaultTreeModel arbol = (DefaultTreeModel) this.treeDirectorios.getModel();
           MyNode nroot = new MyNode(this.rutaProyecto,"Árbol de directorios");
           arbol.setRoot(nroot);
           CargaEstructuraDirectorios(arbol, nroot, this.txtRutaVersionNueva.getText());
           Object[] res;
           String cadena = "";
            try {
                f.inicializarComparator(rutaProyecto);
                res = f.calcularComparacion(this.txtRutaVersionNueva.getText());
                cadena = (String)res[1];
                System.out.println(cadena);
                System.out.println((Boolean)res[0]);
                this.versionado=(Boolean)res[0];
                System.out.println(this.versionado);
                if(versionado){
                    ModeloTabla modelo = new ModeloTabla(); 
                    ControlTabla control = new ControlTabla (modelo,this.rutaProyecto);
                    this.jScrollPane7.setViewportView(tabla);
                    this.jScrollPane7.setColumnHeaderView (tabla.getTableHeader());
                    this.tabla.setModel(modelo);
                }
                this.btnComparar.setEnabled(versionado);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            this.txtCambiosProyecto.setText(cadena);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton btnActualizarHistorico;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton btnSeleccionarVersionNueva;
    private javax.swing.JButton btnVerDetalles;
    private javax.swing.JEditorPane editorAntigua;
    private javax.swing.JEditorPane editorNueva;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblArchivoSeleccionado;
    private javax.swing.JPanel panelHistorico;
    private javax.swing.JTable tabla;
    private javax.swing.JTree treeDirectorios;
    private javax.swing.JTextArea txtCambiosArchivo;
    private javax.swing.JTextArea txtCambiosProyecto;
    private javax.swing.JTextField txtRutaVersionNueva;
    // End of variables declaration//GEN-END:variables
}