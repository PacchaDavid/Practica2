package com.practica.controller.dao.services;

import com.practica.controller.dao.TransaccionDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.controller.tda.models.Transaccion;
public class TransaccionServices {
    TransaccionDao obj;

    public TransaccionServices() {
        this.obj = new TransaccionDao();
    }

    public Transaccion getTransaccion() {
        return this.obj.getTransaccion();
    }

    public void setTransaccion(Transaccion transaccion) {
        obj.setTransaccion(transaccion);
    } 

    public void fromJson(String json) throws Exception {
        obj.setTransaccion(obj.fromJson(json));
    }

    public String getAll() throws Exception {
        return obj.getAll();
    }

     public LinkedList<Transaccion> listAll() throws Exception {
        return obj.getListAll();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.updateTransaccion(obj.getTransaccion());
    }

    public Transaccion deleteTransaccion(Integer id) throws Exception {
        Transaccion transaccion = obj.deleteTransaccion(id);
        return transaccion;
    }

    public String toJson() throws Exception {
        return obj.toJson();
    }

    public Transaccion getTransaccionById(Integer id) throws Exception {
        return obj.getTransaccionById(id);
    }

    public String getTransaccionJsonById(Integer id) throws Exception {
        return obj.getTransaccionJsonById(id);
    }

    public Boolean isThereAllFields() {
        if(this.getTransaccion().getFamiliaId() == null) return false;
        if(this.getTransaccion().getUsoGenerador() == null) return false;
        if(this.getTransaccion().getGeneradorId() == null) return false;
        return true;
    }
}