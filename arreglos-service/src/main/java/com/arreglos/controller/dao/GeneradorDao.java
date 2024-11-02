package com.arreglos.controller.dao;

import java.lang.reflect.Array;

import com.arreglos.controller.dao.implement.AdapterDao;
import com.arreglos.controller.tda.models.Generador;
import com.google.gson.Gson;

public class GeneradorDao extends AdapterDao<Generador> {
    private Generador generador;

    public GeneradorDao() {
        super(Generador.class);
        gson = new Gson();
    }


    public GeneradorDao(Boolean conContador) throws Exception {
        super(true,Generador.class);
        gson = new Gson();
    }

    public Generador[] getAllGenerador() throws Exception {
        return getArray();
    }

    public String getAllGeneradorJson() throws Exception {
        return this.gson.toJson(getArray());
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

    public void generadorFromJson(String generadorJson) {
        this.generador = gson.fromJson(generadorJson, Generador.class);
    }

    public String generadorToJson() {
        return gson.toJson(this.getGenerador());
    }

    /* public Boolean save() throws Exception {
        Generador[] generadores = getArray();
        if(generadores.length == 0) 
            this.getGenerador().setId(1);
        else {
            Integer id = generadores[generadores.length-1].getId();
            this.getGenerador().setId(id+1); 
        }
        persist(Generador);
        return true;
    } */

    public Generador getGeneradorById(Integer id) throws Exception {
        Generador[] generadores = getArray();
        for(int i = 0; i < generadores.length; i++) {
            if(generadores[i].getId() == id) {
                return generadores[i];
            }
        }
        throw new Exception("idNotFound");
    }

    public String getGeneradorJsonById(Integer id) throws Exception {
        return gson.toJson(this.getGeneradorJsonById(id));
    }

    public void updateGeneradorWithId(Integer id) throws Exception {
        getGeneradorById(id);//Para comprobar que sí exista la Generador
        Generador[] generadores = getArray();
        for(int i = 0; i < generadores.length; i++) {
            if(generadores[i].getId() == id) {
                generadores[i] = this.getGenerador();
            }
        }
    }

    public Generador deleteGenerador(Integer id) throws Exception {
        Generador Generador = getGeneradorById(id);
        Generador[] generadores = getArray();
        Generador[] newgeneradores = (Generador[])Array.newInstance(Generador.class , generadores.length-1);
        int j=0;
        for(int i = 0; i < generadores.length;i++) {
            if(generadores[i].getId() != id) {
                newgeneradores[j] = generadores[i];
                j++; 
            }
        }
        saveFile(newgeneradores);
        return Generador;
    }

    public Boolean save() throws Exception {
        contadorId++;
        writeMeta(contadorId);
        final Integer id = contadorId;
        this.getGenerador().setId(id);
        persist(this.getGenerador());
        return true;
    }
}
