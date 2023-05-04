package puerto;

import java.io.Serializable;

public class Contenedor implements Serializable {
    //atributos
    private final int id;
    private int peso, prioridad;
    private String pais, contenido, emisor, receptor;
    private boolean inspeccionar;

    //constructores

    public Contenedor(int id, int peso, int prioridad,String pais, String contenido, String emisor, String receptor){
        if(id<0)id=-id;
        this.id=id;
        this.prioridad=3;
        this.setPeso(peso);
        this.setPrioridad(prioridad);
        this.pais=pais;
        this.contenido=contenido;
        this.emisor=emisor;
        this.receptor=receptor;

    }

    public int getPeso(){
        return peso;
    }
    public void setPeso(int peso){
        if(peso>0)this.peso=peso;
    }
    public int getId(){
        return id;
    }
    public void setPrioridad(int prioridad){
        if(prioridad>=1 && prioridad<=3) this.prioridad=prioridad;
    }
    public int getPrioridad(){
        return prioridad;
    }
    public boolean isInspeccionar(){
        return this.inspeccionar;
    }
    public void setInspeccionado(){
        inspeccionar=true;
    }
    public String getContenido(){
        return contenido;
    }
    public void setContenido(String contenido){
        if(contenido!=null)this.contenido=contenido;
    }
    public String getPais(){
        return pais;
    }
    public void setPais(String pais){
        this.pais=pais;
    }
    public String getEmisor(){
        return emisor;
    }
    public void setEmisor(String emisor){
        this.emisor=emisor;
    }
    public String getReceptor(){
        return receptor;
    }
    public void setReceptor(String receptor){
        this.receptor=receptor;
    }

    public String toString(){
        String s="ID: " + id + "sender: " + emisor + " weight: " + peso;
        if(inspeccionar) s+= " checked";
        else s+= " not checked";
        return s;
    }
}
