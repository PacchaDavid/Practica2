package com.practica.controller.dao;

import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.models.Generador;

public class GeneradorDao extends AdapterDao<Generador> {
    Generador generador;
    
    public GeneradorDao() {
        super(Generador.class);
    }

    public Generador getGenerador() {
        if(this.generador == null) {
            this.generador = new Generador();
        }
        return this.generador;
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public String getAll() throws Exception {
        return gson.toJson(getListAll().toArray());
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        this.getGenerador().setId(id);
        persist(this.generador);
        return true;
    }

    public String toJson() throws Exception {
        return toJson(this.getGenerador());
    }

    public Generador getGeneradorById(Integer id) throws Exception {
        return this.get(id);
    }

    public String getGeneradorJsonById(Integer id) throws Exception {
        return gson.toJson(this.getGeneradorById(id));
    }

    public Boolean updateGenerador(Generador Generador) throws Exception {
        Integer id = Generador.getId();
        merge(Generador, id);
        return true;
    }

    public Generador deleteGenerador(Integer id) throws Exception {
        Generador generador = getGeneradorById(id);
        delete(id);
        return generador;
    }
}
