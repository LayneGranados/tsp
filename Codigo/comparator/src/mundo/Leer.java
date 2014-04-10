/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import difflib.Chunk;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public class Leer 
{
    
    private ArrayList<Clase> clasesVersionAntigua;
    private ArrayList<Clase> clasesVersionNueva;

    
//INICIO_METODO
    public Leer() 
    {
        this.clasesVersionAntigua= new ArrayList<Clase>();
        this.clasesVersionNueva= new ArrayList<Clase>();
    }
//FIN_METODO

//INICIO_METODO
    public String buscarClasesEnCarpeta(String carpetaVersionAntigua, String carpetaVersionNueva) throws IOException 
    {
         try 
         {
             this.buscarEnHijo(carpetaVersionAntigua,this.clasesVersionAntigua);
             this.buscarEnHijo(carpetaVersionNueva,this.clasesVersionNueva);
         } catch (Exception e) 
         {
             System.out.println(e);
         }
         
         String resultado=this.compararClases(this.clasesVersionAntigua, this.clasesVersionNueva);
         this.compararDocumentos(this.clasesVersionAntigua, this.clasesVersionNueva);
         String otroResutado="";
         for(int i=0;i<this.clasesVersionNueva.size();i++){
             Clase n = this.clasesVersionNueva.get(i);
             n.getContenidoHTML();
             if(n.getEstado().equalsIgnoreCase("H")){
                 otroResutado+=n.getRutaRelativa()+"-->"+n.getNombre()+"\n";
                 otroResutado+="Cantidad de Lineas Eliminadas: "+n.getLineasEliminadas().size()+"\n";
                 for(Linea l : n.getLineasEliminadas()){
                     otroResutado+=l.getNumeroLinea()+"--"+l.getContenido()+"\n";
                 }
                 otroResutado+="Cantidad de Lineas Agregadas: "+n.getLineasAgregadas().size()+"\n";
                 for(Linea l : n.getLineasAgregadas()){
                     otroResutado+=l.getNumeroLinea()+"++"+l.getContenido()+"\n";
                 }
             }
         }
         return resultado+otroResutado;
    }
//FIN_METODO
    
    
//INICIO_METODO
    public boolean buscarEnHijo(String path,ArrayList<Clase> listado) 
    {
        File file = new File(path);
        if (!file.exists()) 
        {  
            return true;  
        }  
        if (!file.isDirectory()) 
        {
            return true;
        }
    return this.countChildren(file, listado); //&& file.delete();  
}  
//FIN_METODO  

//INICIO_METODO
private boolean countChildren(File dir, ArrayList<Clase> listado) 
{
    File[] children = dir.listFiles(); 
    boolean childrenContar = true;
    for (int i = 0;children != null && i < children.length; i++) 
    {  
        File child = children[i]; 
        if (child.isDirectory()) 
        {
            childrenContar = this.countChildren(child, listado) && childrenContar;
        }  
        if (child.exists()) 
        {
            boolean esJava = child.getName().contains("java");
            if(esJava)
            {
                Clase c = new  Clase();
                c.setNombre(child.getName());
                c.setRuta(child.getAbsolutePath());
                //c.contarMisLineas();
                listado.add(c);
            }
        }  
    } 
    return childrenContar;  
}
//FIN_METODO

public String compararClases(ArrayList<Clase> antigua, ArrayList<Clase> nueva){
    String resultadoAdicion="CLASES ADICIONADAS: ";
    String cuerpoA ="";
    int contadorA=0;
    for(int i=0;i<nueva.size();i++){
        Clase claseNew = nueva.get(i);
        boolean existe=false;
        for(int j=0;j<antigua.size();j++){
            Clase claseOld = antigua.get(j);
            if(claseNew.equals(claseOld)){
                existe=true;
            }
        }
        if(!existe){
            contadorA++;
            cuerpoA+=claseNew.getNombre()+"\n";
            claseNew.setEstado("A");
            nueva.set(i, claseNew);
        }
    }
    resultadoAdicion+=contadorA+"\n";
    
    String resultadoEliminacion="CLASES   ELIMINADAS: ";
    String cuerpoE ="";
    int contadorE=0;
    for(int i=0;i<antigua.size();i++){
        Clase claseOld = nueva.get(i);
        boolean existe=false;
        for(int j=0;j<nueva.size();j++){
            Clase claseNew = nueva.get(j);
            if(claseNew.equals(claseOld)){
                existe=true;
            }
        }
        if(!existe){
            contadorE++;
            cuerpoE+=claseOld.getNombre()+"\n";
            claseOld.setEstado("E");
            antigua.set(i, claseOld);
        }
    }
    resultadoEliminacion+=contadorE+"\n";
    
    return resultadoAdicion+cuerpoA+"\n"+resultadoEliminacion+cuerpoE+"\n"+"\n"+"DIFERENCIAS";
}

public void compararDocumentos(ArrayList<Clase> antigua, ArrayList<Clase> nueva) throws IOException{
    
    for(int i=0;i<nueva.size();i++){
        Clase claseNew = nueva.get(i);
        
        if(claseNew.getEstado().equalsIgnoreCase("H")){
            for(int j=0;j<antigua.size();j++){
                Clase claseOld = antigua.get(j);
                
                if(claseNew.equals(claseOld)){
                    claseNew.guardarMisLineas();
                    claseOld.guardarMisLineas();
                    this.modificarEstadoLineas(claseOld, claseNew, true);
                    this.modificarEstadoLineas(claseNew, claseOld, false);
                }
            }
        }
        nueva.set(i, claseNew);
    }
}

private void modificarEstadoLineas(Clase claseOld, Clase claseNew, boolean orientacion) throws IOException{
    File versionOld=new File(claseOld.getRuta());
    File versionNew=new File(claseNew.getRuta());
    FileComparator f = new FileComparator(versionOld,versionNew);
                    
    List<Chunk> cambios = f.getChangesFromOriginal();
    List<Chunk> eliminados = f.getDeletesFromOriginal();
    List<Chunk> agregados = f.getInsertsFromOriginal();
    int posicion=-1;
    for(Chunk c: cambios){
        posicion=c.getPosition();
        for(int k=0;k<c.getLines().size();k++){
            if(k>0)
                posicion++;
            Linea l = claseNew.getLineas().get(posicion);
            l.setEstado("M");
            claseNew.getLineas().set(posicion, l);
        }
    }
    
    
        for(Chunk c: agregados){
            posicion=c.getPosition();
            for(int k=0;k<c.getLines().size();k++){
                if(k>0)
                    posicion++;
                Linea l = claseNew.getLineas().get(posicion);
                if(orientacion)
                    l.setEstado("A");
                else 
                    l.setEstado("E");
                claseNew.getLineas().set(posicion, l);
            }
        
    }
    
    if(orientacion){
        
                    
        for(Chunk c: eliminados){

            posicion=c.getPosition();
            if(c.getLines().size()==0){
                 Linea l = claseNew.getLineas().get(posicion);
                 if(orientacion)
                    l.setEstado("E");
                else
                    l.setEstado("A");
                 claseNew.getLineas().set(posicion, l);
            }
            for(int k=0;k<c.getLines().size();k++){
                if(k>0)
                    posicion++;
                Linea l = claseNew.getLineas().get(posicion);
                if(orientacion)
                    l.setEstado("E");
                else
                    l.setEstado("A");
                claseNew.getLineas().set(posicion, l);
            }
        }
    }
    
}


}
