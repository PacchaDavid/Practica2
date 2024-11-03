package com.arreglos.controller.dao.services;

import com.arreglos.controller.dao.FamiliaDao;
import com.arreglos.controller.tda.models.Familia;

public class FamiliaServices {
    private FamiliaDao obj;

    public FamiliaServices(Boolean conContador) {
        try {
            this.obj = new FamiliaDao(conContador);
        } catch(Exception e) {
            this.obj = new FamiliaDao();
        }    
    }

    public FamiliaServices() {
        this.obj = new FamiliaDao();
    }

    public Familia[] getAllFamilia() throws Exception {
        return this.obj.getAllFamilia();
    }

    public String getAllFamiliaJson() throws Exception {
        return this.obj.getAllFamiliaJson();
    }

    public Familia getFamilia() {
        return this.obj.getFamilia();
    }

    public void setFamilia(Familia familia) {
        this.obj.setFamilia(familia);
    }

    public void familiaFromJson(String familiaJson) {
        this.obj.familiaFromJson(familiaJson);
    }

    public String familiaToJson() {
        return this.obj.familiaToJson();
    }

    public Boolean save() throws Exception {
        return this.obj.save();
    }

    public Familia getFamiliaById(Integer id) throws Exception { 
        return this.obj.getFamiliaById(id);
    }

    public String getFamiliaJsonById(Integer id) throws Exception {
        return this.obj.getFamiliaJsonById(id);
    }

    public void updateFamiliaWithId(Integer id) throws Exception {
        obj.updateFamiliaWithId(id);
    }

    public Familia deleteFamilia(Integer id) throws Exception {
        return this.obj.deleteFamilia(id);
    }

    public Boolean isThereAllFields() {
        if(this.getFamilia().getApellidosRepresentantes() == null) return false;
        if(this.getFamilia().getCanton() == null) return false;
        if(this.getFamilia().getIngresosMensuales() == null) return false;
        if(this.getFamilia().getNivelSocioeconomico() == null) return false;
        if(this.getFamilia().getNroIntegrantes() == null) return false;
        return true;
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

}
