/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

/**
 *
 * @author laynegranadosmogollon
 */
public class Metodo 
{
    
    private String nombre;
    private int cantidadLineas;

//INICIO_METODO
    public Metodo() 
    {
    }
//FIN_METODO

//INICIO_METODO
    public Metodo(String nombre, int cantidadLineas) 
    {
        this.nombre = nombre;
        this.cantidadLineas = cantidadLineas;
    }
//FIN_METODO
    
//INICIO_METODO
    public String getNombre() {
        return nombre;
    }
//FIN_METODO
    
//INICIO_METODO
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
//FIN_METODO
    
//INICIO_METODO
    public int getCantidadLineas() 
    {
        return cantidadLineas;
    }
//FIN_METODO

//INICIO_METODO
    public void setCantidadLineas(int cantidadLineas) 
    {
        this.cantidadLineas = cantidadLineas;
    }
//FIN_METODO           
    
}
