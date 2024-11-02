package com.practica.controller.dao.services;

import com.practica.controller.dao.FamiliaDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.controller.tda.models.Familia;

public class FamiliaServices {
    FamiliaDao obj;

    public FamiliaServices() {
        this.obj = new FamiliaDao();
    }

    public String getAll() throws Exception {
        return obj.getAll();
    }

    public LinkedList<Familia> listAll() throws Exception {
        return obj.getListAll();
    }

    public Familia getFamilia() {
        return this.obj.getFamilia();
    }

    public void setFamilia(Familia familia) {
        obj.setFamilia(familia);
    } 

    public void fromJson(String json) throws Exception {
        obj.setFamilia(obj.fromJson(json));
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.updateFamilia(obj.getFamilia());
    }

    public String deleteFamilia(Integer id) throws Exception {
        return obj.deleteFamilia(id);
    }

    public String toJson() throws Exception {
        return obj.toJson();
    }

    public Familia getFamiliaById(Integer id) throws Exception {
        return obj.getFamiliaById(id);
    }

    public String getFamiliaJsonById(Integer id) throws Exception {
        return obj.getFamiliaJsonById(id);
    }

    public String[] nivelesSocioeconomicos() {
        return obj.nivelesSocioeconomicos();
    }

    public String nivelesSocioeconomicosJson() {
        return obj.nivelesSocioeconomicosJson();
    }

    public String[] cantones() {
        return obj.cantones();
    }

    public String cantonesJson() {
        return obj.cantontesJson();
    }

    public Boolean isThereAllFields() {
        if(this.getFamilia().getApellidosRepresentantes() == null) return false;
        if(this.getFamilia().getCanton() == null) return false;
        if(this.getFamilia().getIngresosMensuales() == null) return false;
        if(this.getFamilia().getNivelSocioeconomico() == null) return false;
        if(this.getFamilia().getNroIntegrantes() == null) return false;
        return true;
    }
}