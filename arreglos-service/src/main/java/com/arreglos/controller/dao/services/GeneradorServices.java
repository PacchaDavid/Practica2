package com.arreglos.controller.dao.services;

import com.arreglos.controller.dao.GeneradorDao;
import com.arreglos.controller.tda.models.Generador;

public class GeneradorServices {
    private GeneradorDao obj;

    public GeneradorServices(Boolean conContador) {
        try {
            this.obj = new GeneradorDao(conContador);
        } catch(Exception e) {
            this.obj = new GeneradorDao();
        }    
    }

    public GeneradorServices() {
        this.obj = new GeneradorDao();
    }

    public Generador[] getAllGenerador() throws Exception {
        return this.obj.getAllGenerador();
    }

    public String getAllGeneradorJson() throws Exception {
        return this.obj.getAllGeneradorJson();
    }

    public Generador getGenerador() {
        return this.obj.getGenerador();
    }

    public void setGenerador(Generador Generador) {
        this.obj.setGenerador(Generador);
    }

    public void generadorFromJson(String GeneradorJson) {
        this.obj.generadorFromJson(GeneradorJson);
    }

    public String generadorToJson() {
        return this.obj.generadorToJson();
    }

    public Boolean save() throws Exception {
        return this.obj.save();
    }

    public Generador getGeneradorById(Integer id) throws Exception { 
        return this.obj.getGeneradorById(id);
    }

    public String getGeneradorJsonById(Integer id) throws Exception {
        return this.obj.getGeneradorJsonById(id);
    }

    public void updateGeneradorWithId(Integer id) throws Exception {
        obj.updateGeneradorWithId(id);
    }

    public Generador deleteGenerador(Integer id) throws Exception {
        return this.obj.deleteGenerador(id);
    }

    public Boolean isThereAllFields() {
        if(this.getGenerador().getConsumo() == null) return false;
        if(this.getGenerador().getModelo() == null) return false;
        if(this.getGenerador().getPotencia() == null) return false;
        if(this.getGenerador().getPrecio() == null) return false;
        return true;
    }

}
