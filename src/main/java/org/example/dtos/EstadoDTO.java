package org.example.dtos;

import java.util.List;

public class EstadoDTO {

    private int estatus;
    private Object objeto;
    private List<?> listaObjetos;

    public EstadoDTO() {
    }

    public EstadoDTO(int estatus, Object objeto, List<?> listaObjetos) {
        this.estatus = estatus;
        this.objeto = objeto;
        this.listaObjetos = listaObjetos;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public List<?> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<?> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

}
