package com.arreglos.controller.dao.services;

import com.arreglos.controller.dao.TransaccionDao;
import com.arreglos.controller.tda.models.Transaccion;

public class TransaccionServices {
    private TransaccionDao obj;

    public TransaccionServices(Boolean conContador) {
        try {
            this.obj = new TransaccionDao(conContador);
        } catch(Exception e) {
            this.obj = new TransaccionDao();
        }    
    }

    public TransaccionServices() {
        this.obj = new TransaccionDao();
    }

    public Transaccion[] getAllTransaccion() throws Exception {
        return this.obj.getAllTransaccion();
    }

    public String getAllTransaccionJson() throws Exception {
        return this.obj.getAllTransaccionJson();
    }

    public Transaccion getTransaccion() {
        return this.obj.getTransaccion();
    }

    public void setTransaccion(Transaccion Transaccion) {
        this.obj.setTransaccion(Transaccion);
    }

    public void transaccionFromJson(String TransaccionJson) {
        this.obj.transaccionFromJson(TransaccionJson);
    }

    public String transaccionToJson() {
        return this.obj.transaccionToJson();
    }

    public Boolean save() throws Exception {
        return this.obj.save();
    }

    public Transaccion getTransaccionById(Integer id) throws Exception { 
        return this.obj.getTransaccionById(id);
    }

    public String getTransaccionJsonById(Integer id) throws Exception {
        return this.obj.getTransaccionJsonById(id);
    }

    public void updateTransaccionWithId(Integer id) throws Exception {
        obj.updateTransaccionWithId(id);
    }

    public Transaccion deleteTransaccion(Integer id) throws Exception {
        return this.obj.deleteTransaccion(id);
    }

    public Boolean isThereAllFields() {
        if(this.getTransaccion().getFamiliaId() == null) return false;
        if(this.getTransaccion().getGeneradorId() == null) return false;
        if(this.getTransaccion().getUsoGenerador() == null) return false;
        return true;
    }

}
