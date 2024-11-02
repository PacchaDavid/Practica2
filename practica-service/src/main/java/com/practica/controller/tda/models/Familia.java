package com.practica.controller.tda.models;

public class Familia {
    private Integer id;
    private String apellidosRepresentantes;
    private Integer nroIntegrantes;
    private Canton canton;
    private Float ingresosMensuales;
    NivelSocioeconomico nivelSocioeconomico;


    public Familia() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroIntegrantes() {
        return nroIntegrantes;
    }

    public void setNroIntegrantes(Integer nroIntegrantes) {
        this.nroIntegrantes = nroIntegrantes;
    }

    public NivelSocioeconomico getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }
    
    public void setNivelSocioeconomico(NivelSocioeconomico nivelSocioeconomico) {
        this.nivelSocioeconomico = nivelSocioeconomico;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public Canton getCanton() {
        return canton;
    }

    public Float getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(Float ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public String getApellidosRepresentantes() {
        return apellidosRepresentantes;
    }
    
    public void setApellidosRepresentantes(String apellidosRepresentantes) {
        this.apellidosRepresentantes = apellidosRepresentantes;
    }
    
    @Override
    public String toString() {
        return "numeroIntegrantes: " + String.valueOf(nroIntegrantes) + "\n" +
        "canton: " + this.canton.name() + "\n" +
        "ingresosMensuales: " + String.valueOf(ingresosMensuales) + "\n" +
        "nivel SocioEconomico: " + this.nivelSocioeconomico.name();
    }
}
