package com.practica.controller.dao.implement;

import com.practica.controller.tda.list.LinkedList;

public interface InterfazDao<T> {
    public void persist(T object) throws Exception;
    public LinkedList<T> getListAll() throws Exception;
    public T get(Integer id) throws Exception;  
    public void merge(T object, Integer id) throws Exception;
}
