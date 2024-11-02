package com.arreglos.controller.tda.models;

public class Transaccion {
    private Integer id;
    private Integer familiaId;
    private String usoGenerador;
    private Integer generadorId;

    public Transaccion() {}

    public Integer getFamiliaId() {
        return familiaId;
    }  
    
    public void setFamiliaId(Integer familiaId) {
        this.familiaId = familiaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsoGenerador() {
        return usoGenerador;
    }

    public void setUsoGenerador(String usoGenerador) {
        this.usoGenerador = usoGenerador;
    }

    public Integer getGeneradorId() {
        return generadorId;
    }

    public void setGeneradorId(Integer generadorId) {
        this.generadorId = generadorId;
    }
}
