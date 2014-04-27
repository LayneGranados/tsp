package vista;

/**
 * Javier Abell�n, 26 Oct 2003
 *
 * ModeloTabla.java
 *
 * Modelo de tabla para el ejmplo de uso del JTable
 */
import comparator.mundo.comun.HistoricoVersionDTO;
import java.util.LinkedList;
import javax.swing.event.*;
import javax.swing.table.*;

/** 
 * Modelo de personas. Cada fila es una persona y las columnas son los datos
 * de la persona.
 * Implementa TableModel y dos m�todos para a�adir y eliminar Personas del
 * modelo 
 */
public class ModeloTabla implements TableModel
{
    
    /** Lista con los datos. Cada elemento de la lista es una instancia de
     * Persona */
    private LinkedList datos = new LinkedList();
    
    /** Lista de suscriptores. El JTable ser� un suscriptor de este modelo de
     * datos */
    private LinkedList listeners = new LinkedList();
    
    
    /** Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     *
     */
    public int getColumnCount() {
        // Devuelve el n�mero de columnas del modelo, que coincide con el
        // n�mero de datos que tenemos de cada persona.
        return 8;
    }
    
    /** Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     *
     */
    public int getRowCount() {
        // Devuelve el n�mero de personas en el modelo, es decir, el n�mero
        // de filas en la tabla.
        return datos.size();
    }
    
    /** Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex 	the column whose value is to be queried
     * @return	the value Object at the specified cell
     *
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        HistoricoVersionDTO aux;
        
        // Se obtiene la persona de la fila indicada
        aux = (HistoricoVersionDTO)(datos.get(rowIndex));
        
        // Se obtiene el campo apropiado seg�n el valor de columnIndex
        switch (columnIndex)
        {
            case 0:
                return aux.getVersion();
            case 1:
                return aux.getFecha();
            case 2:
                return aux.getUsuario();
            case 3:
                return aux.getRazonComentario();
            case 4:
                return aux.getLocAdicionados();
            case 5:
                return aux.getLocEliminados();
            case 6:
                return aux.getLocModificados();
            case 7:
                return aux.getLocTotales();
            default:
                return null;
        }
    }
    
    /**
     * Borra del modelo la persona en la fila indicada 
     */
    public void borraVersion (int fila)
    {
        // Se borra la fila 
        datos.remove(fila);
        
        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, fila, fila, 
            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        
        // ... y pas�ndoselo a los suscriptores
        avisaSuscriptores (evento);
    }
    
    /**
     * A�ade una persona al final de la tabla
     */
    public void agregaVersion (HistoricoVersionDTO nuevaVersion)
    {
        // A�ade la persona al modelo 
        datos.add (nuevaVersion);
        
        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores (evento);
    }
    
    /** Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    public void addTableModelListener(TableModelListener l) {
        // A�ade el suscriptor a la lista de suscriptores
        listeners.add (l);
    }
    
    /** Returns the most specific superclass for all the cell values
     * in the column.  This is used by the <code>JTable</code> to set up a
     * default renderer and editor for the column.
     *
     * @param columnIndex  the index of the column
     * @return the common ancestor class of the object values in the model.
     *
     */
    public Class getColumnClass(int columnIndex) {
        // Devuelve la clase que hay en cada columna.
        switch (columnIndex)
        {
            case 0:
                // La columna cero contiene el nombre de la persona, que es
                // un String
                return String.class;
            case 1:
                // La columna uno contiene el apellido de la persona, que es
                // un String
                return String.class;
            case 2:
                // La columna dos contine la edad de la persona, que es un
                // Integer (no vale int, debe ser una clase)
                return Integer.class;
            default:
                // Devuelve una clase Object por defecto.
                return Object.class;
        }
    }
    
    /** Returns the name of the column at <code>columnIndex</code>.  This is used
     * to initialize the table's column header name.  Note: this name does
     * not need to be unique; two columns in a table can have the same name.
     *
     * @param	columnIndex	the index of the column
     * @return  the name of the column
     *
     */
    public String getColumnName(int columnIndex) 
    {
        // Devuelve el nombre de cada columna. Este texto aparecer� en la
        // cabecera de la tabla.
        switch (columnIndex)
        {
            case 0:
                return "Versión";
            case 1:
                return "Fecha";
            case 2:
                return "Usuario";
            case 3:
                return "Razón/Comentario";
            case 4:
                return "LOCs Adicionados";
            case 5:
                return "LOCs Eliminados";
            case 6:
                return "LOCs Modificados";
            case 7:
                return "LOCs Totales";
            default:
                return null;
        }
    }
    
    /** Returns true if the cell at <code>rowIndex</code> and
     * <code>columnIndex</code>
     * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
     * change the value of that cell.
     *
     * @param	rowIndex	the row whose value to be queried
     * @param	columnIndex	the column whose value to be queried
     * @return	true if the cell is editable
     * @see #setValueAt
     *
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permite que la celda sea editable.
        return true;
    }
    
    /** Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }
    
    /** Sets the value in the cell at <code>columnIndex</code> and
     * <code>rowIndex</code> to <code>aValue</code>.
     *
     * @param	aValue		 the new value
     * @param	rowIndex	 the row whose value is to be changed
     * @param	columnIndex 	 the column whose value is to be changed
     * @see #getValueAt
     * @see #isCellEditable
     *
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) 
    {
        // Obtiene la persona de la fila indicada
        HistoricoVersionDTO aux;
        aux = (HistoricoVersionDTO)(datos.get(rowIndex));
        
        // Cambia el campo de Persona que indica columnIndex, poniendole el 
        // aValue que se nos pasa.
        switch (columnIndex)
        {
            case 0:
                aux.setVersion(((Integer)aValue));
                break;
            case 1:
                aux.setFecha((String)aValue);
                break;
            case 2:
                aux.setUsuario(((String)aValue));
                break;
            case 3:
                aux.setRazonComentario(((String)aValue));
                break;
            case 4:
                aux.setLocAdicionados(((Integer)aValue));
            case 5:
                aux.setLocEliminados(((Integer)aValue));
                break;
            case 6:
                aux.setLocModificados(((Integer)aValue));
                break;
            case 7:
                aux.setLocTotales(((Integer)aValue));
                break;
            default:
                break;
        }
        
        // Avisa a los suscriptores del cambio, creando un TableModelEvent ...
        TableModelEvent evento = new TableModelEvent (this, rowIndex, rowIndex, 
            columnIndex);

        // ... y pas�ndoselo a los suscriptores.
        avisaSuscriptores (evento);
    }
    
    /**
     * Pasa a los suscriptores el evento.
     */
    private void avisaSuscriptores (TableModelEvent evento)
    {
        int i;
        
        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pas�ndole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }
    
    
}
