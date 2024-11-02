package com.arreglos.controller.dao.implement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.HashMap;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<?> clazz;
    protected Gson gson;
    public static String URL = "./tilin/";
    
    public AdapterDao(Class<?> clazz) {
        this.clazz = clazz;
    }

    public T[] getArray() throws Exception {
        gson = new Gson();        
        try {
            String data = readFile();
            Type arrayType = Array.newInstance(clazz,0).getClass();
            @SuppressWarnings("unchecked")
            T[] objects = (gson.fromJson(data, arrayType) != null) ? gson.fromJson(data, arrayType) : (T[])Array.newInstance(clazz, 0);
            return objects;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void merge(T object, Integer index) throws Exception {
        T[] objects = getArray();
        objects[index-1] = object;
        saveFile(objects);
    }

    public  T get(Integer index) throws Exception {
        T[] objects = getArray();
        return objects[index-1];
    }

    public void persist(T object) throws Exception {
        T[] objects = getArray();
    
        @SuppressWarnings("unchecked")
        T[] newObjects = (T[]) Array.newInstance(clazz, objects.length + 1);

        for(int i = 0; i < objects.length; i++) {
            newObjects[i] = objects[i];
        }
    
        newObjects[objects.length] = object;
    
        saveFile(newObjects);
    }

    public void saveFile(T[] object) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        String data = gson.toJson(object);
        
        try (FileWriter tilin = new FileWriter(URL+clazz.getSimpleName()+".json");) {
            tilin.write(data);
            tilin.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile() {
        try(BufferedReader bf = new BufferedReader(new FileReader(URL+clazz.getSimpleName()+".json"))) {
            StringBuilder sb = new StringBuilder();
            String line = "";
            while((line = bf.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(Integer index) throws Exception {
        T[] objects = getArray();
        final Integer ID = index-1;

        @SuppressWarnings("unchecked")
        T[] newObjects = (T[])Array.newInstance(clazz, objects.length-1);

        for(int i = 0; i < objects.length; i++) {
            if(i < ID) {
                newObjects[i] = objects[i];
            } else if(i > ID) {
                newObjects[i - 1] = objects[i];
            }
        }
        
        saveFile(newObjects);
    }


    //Le agregué para mantener la consistencia de los datos
    public static String metaURL = URL+"meta";
    public Integer contadorId;

    public AdapterDao(Boolean conContador, Class<?> _class) throws Exception {
        String meta;
        this.clazz = _class;
        try {
            meta = readMeta();
            if(meta == null) {
                writeMeta(0);
                meta = readMeta();
            }
        } catch (Exception e) {
            writeMeta(0);
            meta = readMeta();
        }

        HashMap<String,String> mapa = new HashMap<>();
        Gson gson2 = new Gson();
        Type type = new TypeToken<HashMap<String,String>>(){}.getType();
        mapa = gson2.fromJson(meta, type);
        Integer contadorIdFromJson = Integer.valueOf(mapa.get("contadorId"));
        this.contadorId = contadorIdFromJson;
    }

    public String readMeta() {
        try(BufferedReader bf = new BufferedReader(new FileReader(metaURL+clazz.getSimpleName()+".json"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bf.readLine()) != null) 
                sb.append(line).append("\n");
            if(sb.toString().equals("")) return null;
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeMeta(Integer contId) {
        HashMap<String,Object> metaData = new HashMap<>();
        metaData.put("contadorId", contId);
        metaData.put("new",false);
        gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter fw = new FileWriter(metaURL+clazz.getSimpleName()+".json")) {
            fw.write(gson.toJson(metaData));
            fw.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
