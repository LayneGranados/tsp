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

    
    public LecturaArchivo() {
    }
  

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
    return this.countChildren(file, listado, path); //&& file.delete();  
}  

private boolean countChildren(File dir, ArrayList<ClaseDTO> listado, String rutaProyecto) 
{
    File[] children = dir.listFiles(); 
    boolean childrenContar = true;
    for (int i = 0;children != null && i < children.length; i++) 
    {
        File child = children[i]; 
        if(!child.getName().equalsIgnoreCase(".comparator")){
            if (child.isDirectory()) 
            {
                childrenContar = this.countChildren(child, listado, rutaProyecto) && childrenContar;
            }  
            if (child.exists()&&!child.isDirectory()) 
            {
                ClaseDTO c = new  ClaseDTO();
                c.setNombre(child.getName());
                c.setRuta(child.getAbsolutePath());
                c.setRutaProyecto(rutaProyecto);
                c.setRutaRelativa(c.getRuta().substring(c.getRutaProyecto().length()));
                c.isFormatValid();
                listado.add(c);
            }
        }
    } 
    return childrenContar;  
}

public void modificarEstadoLineas(ClaseDTO claseOld, ClaseDTO claseNew, boolean orientacion) throws IOException{
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
