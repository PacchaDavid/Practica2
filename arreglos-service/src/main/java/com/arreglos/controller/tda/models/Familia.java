package com.arreglos.controller.tda.models;

public class Familia {
    private Integer id;
    private String apellidosRepresentantes;
    private Integer nroIntegrantes;
    private Float ingresosMensuales;
    private Canton canton;
    private NivelSocioeconomico nivelSocioeconomico;

    public Familia() {}

    public String getApellidosRepresentantes() {
        return apellidosRepresentantes;
    }

    public void setApellidosRepresentantes(String apellidosRepresentantes) {
        this.apellidosRepresentantes = apellidosRepresentantes;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(Float ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public NivelSocioeconomico getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }

    public void setNivelSocioeconomico(NivelSocioeconomico nivelSocioeconomico) {
        this.nivelSocioeconomico = nivelSocioeconomico;
    }

    public Integer getNroIntegrantes() {
        return nroIntegrantes;
    }

    public void setNroIntegrantes(Integer nroIntegrantes) {
        this.nroIntegrantes = nroIntegrantes;
    }

}
