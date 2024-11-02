package com.practica.controller.dao.services;

import com.practica.controller.dao.GeneradorDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.controller.tda.models.Generador;

public class GeneradorServices {
    GeneradorDao obj;

    public GeneradorServices() {
        this.obj = new GeneradorDao();
    }

    public Generador getGenerador() {
        return this.obj.getGenerador();
    }

    public void setGenerador(Generador generador) {
        obj.setGenerador(generador);
    } 

    public String getAll() throws Exception {
        return obj.getAll();
    }

     public LinkedList<Generador> listAll() throws Exception {
        return obj.getListAll();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.updateGenerador(obj.getGenerador());
    }

    public Generador deleteGenerador(Integer id) throws Exception {
        Generador generador = obj.deleteGenerador(id);
        return generador;
    }

    public String toJson() throws Exception {
        return obj.toJson();
    }

    public void fromJson(String json) throws Exception {
        obj.setGenerador(obj.fromJson(json));
    }

    public Generador getGeneradorById(Integer id) throws Exception {
        return obj.getGeneradorById(id);
    }

    public String getGeneradorJsonById(Integer id) throws Exception {
        return obj.getGeneradorJsonById(id);
    }

    public Boolean isThereAllFields() {
        if(this.getGenerador().getConsumo() == null) return false;
        if(this.getGenerador().getPotencia() == null) return false;
        if(this.getGenerador().getPrecio() == null) return false;
        if(this.getGenerador().getModelo() == null) return false;
        return true;
    }
}