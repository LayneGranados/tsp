/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comparator.mundo.comun;

/**
 *
 * @author layne
 */
public class HistoricoVersionDTO {
    
    private int version;
    private String fecha;
    private String usuario;
    private String razonComentario;
    private int locAdicionados;
    private int locEliminados;
    private int locModificados;
    private int locTotales;

    public HistoricoVersionDTO() {
    }

    public HistoricoVersionDTO(int version, String fecha, String usuario, String razonComentario, int locAdicionados, int locEliminados, int locModificados, int locTotales) {
        this.version = version;
        this.fecha = fecha;
        this.usuario = usuario;
        this.razonComentario = razonComentario;
        this.locAdicionados = locAdicionados;
        this.locEliminados = locEliminados;
        this.locModificados = locModificados;
        this.locTotales = locTotales;
    }
    
    public HistoricoVersionDTO(String linea) {
        String [] a = linea.split("%;&");
        this.version = Integer.parseInt(a[0]);
        this.fecha = a[1];
        this.usuario = a[2];
        this.razonComentario = a[3];
        this.locAdicionados = Integer.parseInt(a[4]);
        this.locEliminados = Integer.parseInt(a[5]);
        this.locModificados = Integer.parseInt(a[6]);
        this.locTotales = Integer.parseInt(a[7]);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRazonComentario() {
        return razonComentario;
    }

    public void setRazonComentario(String razonComentario) {
        this.razonComentario = razonComentario;
    }

    public int getLocAdicionados() {
        return locAdicionados;
    }

    public void setLocAdicionados(int locAdicionados) {
        this.locAdicionados = locAdicionados;
    }

    public int getLocEliminados() {
        return locEliminados;
    }

    public void setLocEliminados(int locEliminados) {
        this.locEliminados = locEliminados;
    }

    public int getLocModificados() {
        return locModificados;
    }

    public void setLocModificados(int locModificados) {
        this.locModificados = locModificados;
    }

    public int getLocTotales() {
        return locTotales;
    }

    public void setLocTotales(int locTotales) {
        this.locTotales = locTotales;
    }
    
    public String linea(){
        String linea ="";
        linea = this.getVersion()+"%;&"+this.getFecha()+"%;&"+this.getUsuario()+"%;&"+this.getRazonComentario()+"%;&"+this.locAdicionados+"%;&"+this.locEliminados+"%;&"+this.locModificados+"%;&"+this.locTotales;
        return linea;
    }
    
    
}
