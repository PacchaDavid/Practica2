package com.practica.controller.dao;

import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.models.Canton;
import com.practica.controller.tda.models.Familia;
import com.practica.controller.tda.models.NivelSocioeconomico;

public class FamiliaDao extends AdapterDao<Familia> {
    Familia familia;
    
    public FamiliaDao() {
        super(Familia.class);
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

    public String getAll() throws Exception {
        return gson.toJson(getListAll().toArray());
    }
    
    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        this.getFamilia().setId(id);
        persist(this.familia);
        return true;
    }

    public String toJson() throws Exception {
        return toJson(this.getFamilia());
    }

    public Familia getFamiliaById(Integer id) throws Exception {
        return this.get(id);
    }

    public String getFamiliaJsonById(Integer id) throws Exception {
        return gson.toJson(this.getFamiliaById(id));
    }

    public Boolean updateFamilia(Familia familia) throws Exception {
        Integer id = this.getFamilia().getId();
        merge(familia, id);
        return true;
    }

    public String[] nivelesSocioeconomicos() {
        NivelSocioeconomico[] niveles = NivelSocioeconomico.values();
        String[] nivelesSocioeconomicos = new String[niveles.length];
        for(int i = 0; i < niveles.length; i++) {
            nivelesSocioeconomicos[i] = niveles[i].name();
        }
        return nivelesSocioeconomicos;
    }

    public String nivelesSocioeconomicosJson() {
        return gson.toJson(nivelesSocioeconomicos());
    }

    public String[] cantones() {
        Canton[] cantones = Canton.values();
        String[] cantonesString = new String[cantones.length];
        for(int i = 0; i < cantones.length; i++) {
            cantonesString[i] = cantones[i].name();
        }
        return cantonesString;
    }

    public String cantontesJson() {
        return gson.toJson(cantones());
    }

    public String deleteFamilia(Integer id) throws Exception {
        Familia familia = getFamiliaById(id);
        delete(id);
        return gson.toJson(familia);
    }

}
