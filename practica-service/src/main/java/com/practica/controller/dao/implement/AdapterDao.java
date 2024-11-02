package com.practica.controller.dao.implement;

import com.practica.controller.tda.list.LinkedList;
import com.practica.controller.tda.queue.Queue;
import com.practica.controller.tda.stack.Stack;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

import com.google.gson.Gson;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<?> clazz;
    protected Gson gson = new Gson(); 
    public static String listURL = "./media/list/";
    public static String queueURL = "./media/queue/";
    public static String stackURL = "./media/stack/";

    public AdapterDao() {}

    public AdapterDao(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void persist(T object) throws Exception {
        LinkedList<T> list = getListAll();
        list.add(object);
        try {
            saveFile(list.toArray(),listURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queuePersist(T object) {
        Queue<T> queue = queueAll();
        queue.queued(object);
        try {
            saveFile(queue.toArray(), queueURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stackPersist(T object) {
        Stack<T> stack = pushAll();
        stack.push(object);
        try {
            saveFile(stack.toArray(), stackURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public LinkedList<T> getListAll() throws Exception {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile(listURL); 
            Type arrayType = Array.newInstance(clazz, 0).getClass();
            T[] arrayObjects = gson.fromJson(data, arrayType);
            list.fromArrayToLinkedList(arrayObjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list; 
    }

    public Queue<T> queueAll() {
        Queue<T> queue = new Queue<>();
        try {
            String data = readFile(queueURL);
            Type arrayType = Array.newInstance(clazz, 0).getClass();
            queue.fromArrayToQueue(gson.fromJson(data, arrayType));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queue;
    }

    public Stack<T> pushAll() {
        Stack<T> stack = new Stack<>();
        try {
            String data = readFile(queueURL);
            Type arrayType = Array.newInstance(clazz, 0).getClass();
            stack.fromArrayToStack(gson.fromJson(data, arrayType));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stack;
    }

    public T dequeue() throws Exception {
        Queue<T> queue = queueAll();
        try {
            T info = queue.dequeued();
            saveFile(queue.toArray(), queueURL);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("Queue is empty");
    }

    public T get(Integer id) throws Exception {
        LinkedList<T> list = getListAll();
        return list.get(id-1);
    }


    public void saveFile(Object data,String url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data); 
        try (FileWriter fw = new FileWriter(url + clazz.getSimpleName() + ".json")) {
            fw.write(json);
        }
    }

    public String readFile(String url) throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(url + clazz.getSimpleName() + ".json"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                data.append(line).append("\n"); 
            }
        }
        return data.toString().trim(); 
    }

    public String toJson(T object) throws Exception {
        return gson.toJson(object);
    }

    public String getJson(Integer id) throws Exception {
        return gson.toJson(get(id));
    }

    public void merge(T object, Integer id) throws Exception {
        LinkedList<T> list = getListAll();
        list.update(object, id-1);
        saveFile(list.toArray(),listURL);
    }

    public void delete(Integer id) throws Exception {
        LinkedList<T> lista = getListAll();
        lista.delete(id-1);
        saveFile(lista.toArray(),listURL);
    }

    public T fromJson(String json) throws Exception {
        @SuppressWarnings("unchecked")
        T object = (T)gson.fromJson(json, clazz);
        return object;
    }
}
