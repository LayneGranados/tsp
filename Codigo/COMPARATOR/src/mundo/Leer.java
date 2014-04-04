/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author laynegranadosmogollon
 */
public class Leer 
{
    
    private ArrayList<Clase> clases;
    private ArrayList<Clase> clasesVersionAntigua;
    private ArrayList<Clase> clasesVersionNueva;

    
//INICIO_METODO
    public Leer() 
    {
        this.clases= new ArrayList<Clase>();
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
             if(n.getEstado().equalsIgnoreCase("H")){
                 otroResutado+=n.getRutaRelativa()+"-->"+n.getNombre()+"\n";
                 otroResutado+="Cantidad de Lineas Eliminadas: "+n.getLineasEliminadas().size()+"\n";
                 for(Linea l : n.getLineasEliminadas()){
                     otroResutado+=l.getNumLinea()+"--"+l.getContenido()+"\n";
                 }
                 otroResutado+="Cantidad de Lineas Agregadas: "+n.getLineasAgregadas().size()+"\n";
                 for(Linea l : n.getLineasAgregadas()){
                     otroResutado+=l.getNumLinea()+"++"+l.getContenido()+"\n";
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
    return this.contarChildren(file, listado); //&& file.delete();  
}  
//FIN_METODO  

//INICIO_METODO
private boolean contarChildren(File dir, ArrayList<Clase> listado) 
{
    File[] children = dir.listFiles(); 
    boolean childrenContar = true;
    for (int i = 0;children != null && i < children.length; i++) 
    {  
        File child = children[i]; 
        if (child.isDirectory()) 
        {
            childrenContar = this.contarChildren(child, listado) && childrenContar;
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

private String compararClases(ArrayList<Clase> antigua, ArrayList<Clase> nueva){
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

private void compararDocumentos(ArrayList<Clase> antigua, ArrayList<Clase> nueva){
    for(int i=0;i<nueva.size();i++){
        Clase claseNew = nueva.get(i);
        if(claseNew.getEstado().equalsIgnoreCase("H")){
            for(int j=0;j<antigua.size();j++){
                Clase claseOld = antigua.get(j);
                if(claseNew.equals(claseOld)){
                    claseNew.guardarMisLineas();
                    claseOld.guardarMisLineas();
                    int sizeAComparar=0;
                    String menor="";
                    if(claseNew.getLineas().size()>claseOld.getLineas().size()){
                        sizeAComparar=claseOld.getLineas().size();
                        menor = "old";
                    }
                    else{
                        sizeAComparar=claseNew.getLineas().size();
                        menor="new";
                    }
                    System.out.println("sizeAComparar: "+sizeAComparar);
                    
                    for(int k=0;k<sizeAComparar;k++){
                        Linea deNuevaClase = claseNew.getLineas().get(k);
                        Linea deViejaClase = claseOld.getLineas().get(k);
                        if(deViejaClase.getContenido().equalsIgnoreCase("")&&!deNuevaClase.getContenido().equalsIgnoreCase(""))
                            claseNew.getLineasAgregadas().add(deNuevaClase);
                        else
                            if(!deViejaClase.getContenido().equalsIgnoreCase("")&&deNuevaClase.getContenido().equalsIgnoreCase("")){
                                claseNew.getLineasEliminadas().add(deViejaClase);
                            }
                        else
                            if(!deViejaClase.getContenido().equalsIgnoreCase("")&&!deNuevaClase.getContenido().equalsIgnoreCase("")){
                                if(!deViejaClase.getContenido().equals(deNuevaClase.getContenido())){
                                    claseNew.getLineasAgregadas().add(deNuevaClase);
                                    claseNew.getLineasEliminadas().add(deViejaClase);
                                }
                            }
                    }
                    if(menor.equalsIgnoreCase("new")){
                        System.out.println("new ");
                        for(int m=sizeAComparar;m<claseOld.getLineas().size();m++){
                            System.out.println("m: "+m);
                            claseNew.getLineasEliminadas().add(claseOld.getLineas().get(m));
                        }
                    }
                    else{
                        System.out.println("old ");
                        for(int m=sizeAComparar;m<claseNew.getLineas().size();m++){
                            System.out.println("m: "+m);
                            claseNew.getLineasAgregadas().add(claseNew.getLineas().get(m));
                        }
                    }
                }
            }
            
        }
        
    }
}



}
