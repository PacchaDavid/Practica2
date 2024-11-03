package com.arreglos.controller.dao;

import java.lang.reflect.Array;

import com.arreglos.controller.dao.implement.AdapterDao;
import com.arreglos.controller.tda.models.Transaccion;
import com.google.gson.Gson;

public class TransaccionDao extends AdapterDao<Transaccion> {
    private Transaccion transaccion;

    public TransaccionDao() {
        super(Transaccion.class);
        gson = new Gson();
    }


    public TransaccionDao(Boolean conContador) throws Exception {
        super(true,Transaccion.class);
        gson = new Gson();
    }

    public Transaccion[] getAllTransaccion() throws Exception {
        return getArray();
    }

    public String getAllTransaccionJson() throws Exception {
        return this.gson.toJson(getArray());
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

    public void transaccionFromJson(String transaccionJson) {
        this.transaccion = gson.fromJson(transaccionJson, Transaccion.class);
    }

    public String transaccionToJson() {
        return gson.toJson(this.getTransaccion());
    }

    public Transaccion getTransaccionById(Integer id) throws Exception {
        Transaccion[] transacciones = getArray();
        for(int i = 0; i < transacciones.length; i++) {
            if(transacciones[i].getId() == id) {
                return transacciones[i];
            }
        }
        throw new Exception("idNotFound");
    }

    public String getTransaccionJsonById(Integer id) throws Exception {
        return gson.toJson(this.getTransaccionJsonById(id));
    }

    public void updateTransaccionWithId(Integer id) throws Exception {
        getTransaccionById(id);//Para comprobar que sí exista la Transaccion
        Transaccion[] transacciones = getArray();
        for(int i = 0; i < transacciones.length; i++) {
            if(transacciones[i].getId() == id) {
                transacciones[i] = this.getTransaccion();
            }
        }
        saveFile(transacciones);
    }

    public Transaccion deleteTransaccion(Integer id) throws Exception {
        Transaccion transaccion = getTransaccionById(id);
        Transaccion[] transacciones = getArray();
        Transaccion[] newtransacciones = (Transaccion[])Array.newInstance(Transaccion.class , transacciones.length-1);
        int j=0;
        for(int i = 0; i < transacciones.length;i++) {
            if(transacciones[i].getId() != id) {
                newtransacciones[j] = transacciones[i];
                j++; 
            }
        }
        saveFile(newtransacciones);
        return transaccion;
    }

    public Boolean save() throws Exception {
        contadorId++;
        writeMeta(contadorId);
        final Integer id = contadorId;
        this.getTransaccion().setId(id);
        persist(this.getTransaccion());
        return true;
    }
}
