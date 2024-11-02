package com.practica.controller.dao;

import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.models.Transaccion;

public class TransaccionDao extends AdapterDao<Transaccion> {
    Transaccion transaccion;
    
    public TransaccionDao() {
        super(Transaccion.class);
    }

    public Transaccion getTransaccion() {
        if(this.transaccion == null) {
            this.transaccion = new Transaccion();
        }
        return this.transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public String getAll() throws Exception {
        return gson.toJson(getListAll().toArray());
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        this.getTransaccion().setId(id);
        persist(this.transaccion);
        return true;
    }

    public String toJson() throws Exception {
        return toJson(this.getTransaccion());
    }

    public Transaccion getTransaccionById(Integer id) throws Exception {
        return this.get(id);
    }

    public String getTransaccionJsonById(Integer id) throws Exception {
        return gson.toJson(this.getTransaccionById(id));
    }

    public Boolean updateTransaccion(Transaccion Transaccion) throws Exception {
        Integer id = Transaccion.getId();
        merge(Transaccion, id);
        return true;
    }

    public Transaccion deleteTransaccion(Integer id) throws Exception {
        Transaccion transaccion = getTransaccionById(id);
        delete(id);
        return transaccion;
    }
}
