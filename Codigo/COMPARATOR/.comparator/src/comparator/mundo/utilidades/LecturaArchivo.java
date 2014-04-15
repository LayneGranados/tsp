/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator.mundo.utilidades;

import comparator.mundo.comun.LineaDTO;
import comparator.mundo.comun.ClaseDTO;
import difflib.Chunk;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public class LecturaArchivo 
{
    
    private ArrayList<ClaseDTO> clasesVersionAntigua;
    private ArrayList<ClaseDTO> clasesVersionNueva;

    
//INICIO_METODO
    public LecturaArchivo() 
    {
        this.clasesVersionAntigua= new ArrayList<ClaseDTO>();
        this.clasesVersionNueva= new ArrayList<ClaseDTO>();
    }
//FIN_METODO
  
    
//INICIO_METODO
    public boolean buscarEnHijo(String path,ArrayList<ClaseDTO> listado) 
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
private boolean countChildren(File dir, ArrayList<ClaseDTO> listado) 
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
                ClaseDTO c = new  ClaseDTO();
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

public String compararClases(ArrayList<ClaseDTO> antigua, ArrayList<ClaseDTO> nueva){
    String resultadoAdicion="CLASES ADICIONADAS: ";
    String cuerpoA ="";
    int contadorA=0;
    for(int i=0;i<nueva.size();i++){
        ClaseDTO claseNew = nueva.get(i);
        boolean existe=false;
        for(int j=0;j<antigua.size();j++){
            ClaseDTO claseOld = antigua.get(j);
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
        ClaseDTO claseOld = nueva.get(i);
        boolean existe=false;
        for(int j=0;j<nueva.size();j++){
            ClaseDTO claseNew = nueva.get(j);
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

public void compararDocumentos(ArrayList<ClaseDTO> antigua, ArrayList<ClaseDTO> nueva) throws IOException{
    
    for(int i=0;i<nueva.size();i++){
        ClaseDTO claseNew = nueva.get(i);
        
        if(claseNew.getEstado().equalsIgnoreCase("H")){
            for(int j=0;j<antigua.size();j++){
                ClaseDTO claseOld = antigua.get(j);
                
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

private void modificarEstadoLineas(ClaseDTO claseOld, ClaseDTO claseNew, boolean orientacion) throws IOException{
    File versionOld=new File(claseOld.getRuta());
    File versionNew=new File(claseNew.getRuta());
    ComparadorArchivo f = new ComparadorArchivo(versionOld,versionNew);
                    
    List<Chunk> cambios = f.getChangesFromOriginal();
    List<Chunk> eliminados = f.getDeletesFromOriginal();
    List<Chunk> agregados = f.getInsertsFromOriginal();
    int posicion=-1;
    for(Chunk c: cambios){
        posicion=c.getPosition();
        for(int k=0;k<c.getLines().size();k++){
            if(k>0)
                posicion++;
            LineaDTO l = claseNew.getLineas().get(posicion);
            l.setEstado("M");
            claseNew.getLineas().set(posicion, l);
        }
    }
    
    
        for(Chunk c: agregados){
            posicion=c.getPosition();
            for(int k=0;k<c.getLines().size();k++){
                if(k>0)
                    posicion++;
                LineaDTO l = claseNew.getLineas().get(posicion);
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
                 LineaDTO l = claseNew.getLineas().get(posicion);
                 if(orientacion)
                    l.setEstado("E");
                else
                    l.setEstado("A");
                 claseNew.getLineas().set(posicion, l);
            }
            for(int k=0;k<c.getLines().size();k++){
                if(k>0)
                    posicion++;
                LineaDTO l = claseNew.getLineas().get(posicion);
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
