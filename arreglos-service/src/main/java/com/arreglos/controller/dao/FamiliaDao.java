package com.arreglos.controller.dao;

import java.lang.reflect.Array;

import com.arreglos.controller.dao.implement.AdapterDao;
import com.arreglos.controller.tda.models.Familia;
import com.google.gson.Gson;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;

    public FamiliaDao() {
        super(Familia.class);
        gson = new Gson();
    }


    public FamiliaDao(Boolean conContador) throws Exception {
        super(true,Familia.class);
        gson = new Gson();
    }

    public Familia[] getAllFamilia() throws Exception {
        return getArray();
    }

    public String getAllFamiliaJson() throws Exception {
        return this.gson.toJson(getArray());
    }

    public Familia getFamilia() {
        if(this.familia == null) {
            this.familia = new Familia();
        }
        return this.familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    } 

    public void familiaFromJson(String familiaJson) {
        this.familia = gson.fromJson(familiaJson, Familia.class);
    }

    public String familiaToJson() {
        return gson.toJson(this.getFamilia());
    }

    /* public Boolean save() throws Exception {
        Familia[] familias = getArray();
        if(familias.length == 0) 
            this.getFamilia().setId(1);
        else {
            Integer id = familias[familias.length-1].getId();
            this.getFamilia().setId(id+1); 
        }
        persist(familia);
        return true;
    } */

    public Familia getFamiliaById(Integer id) throws Exception {
        Familia[] familias = getArray();
        for(int i = 0; i < familias.length; i++) {
            if(familias[i].getId() == id) {
                return familias[i];
            }
        }
        throw new Exception("idNotFound");
    }

    public String getFamiliaJsonById(Integer id) throws Exception {
        return gson.toJson(this.getFamiliaJsonById(id));
    }

    public void updateFamiliaWithId(Integer id) throws Exception {
        getFamiliaById(id);//Para comprobar que sí exista la familia
        Familia[] familias = getArray();
        for(int i = 0; i < familias.length; i++) {
            if(familias[i].getId() == id) {
                familias[i] = this.getFamilia();
            }
        }
    }

    public Familia deleteFamilia(Integer id) throws Exception {
        Familia familia = getFamiliaById(id);
        Familia[] familias = getArray();
        Familia[] newFamilias = (Familia[])Array.newInstance(Familia.class , familias.length-1);
        int j=0;
        for(int i = 0; i < familias.length;i++) {
            if(familias[i].getId() != id) {
                newFamilias[j] = familias[i];
                j++; 
            }
        }
        saveFile(newFamilias);
        return familia;
    }

    public Boolean save() throws Exception {
        contadorId++;
        writeMeta(contadorId);
        final Integer id = contadorId;
        this.getFamilia().setId(id);
        persist(familia);
        return true;
    }
}
