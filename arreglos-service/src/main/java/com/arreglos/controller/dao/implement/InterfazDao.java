package com.arreglos.controller.dao.implement;

public interface InterfazDao<T> {
    public T[] getArray() throws Exception;
    public void merge(T object, Integer index) throws Exception;
    public  T get(Integer index) throws Exception;
    public void persist(T object) throws Exception;    
} 