package com.arreglos.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.arreglos.controller.dao.services.GeneradorServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/generador")
public class GeneradorApi {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllGeneradors() {
        HashMap<String,Object> responseMap = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        ObjectMapper om = new ObjectMapper();
        try {                
            responseMap.put("msg", "OK");
            responseMap.put("data", gs.getAllGenerador());
            String jsonResponse = om.writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("msg", "ERROR");
            responseMap.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getGeneradorById(HashMap<String,String> map) {
        HashMap<String,Object> responseMap = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        ObjectMapper om = new ObjectMapper();
        Integer id = Integer.valueOf(map.get("id"));

        try {                
            responseMap.put("msg", "OK");
            responseMap.put("data", gs.getGeneradorById(id));
            String jsonResponse = om.writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("msg", "ERROR");
            responseMap.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response saveGenerador(String json) {
        HashMap<String,Object> responseMap = new HashMap<>();
        GeneradorServices gs = new GeneradorServices(true);
        ObjectMapper om = new ObjectMapper();
        try {
            gs.generadorFromJson(json);
            if(!gs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            gs.save();
            responseMap.put("msg","OK");
            responseMap.put("data", gs.getGenerador());
            String jsonResponse = om.writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("msg","ERROR");
            responseMap.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response deleteGenerador(HashMap<String, String> json) {
        GeneradorServices gs = new GeneradorServices();
        HashMap<String,Object> responseMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        
        try {
            Integer id = Integer.valueOf(json.get("id"));   
            responseMap.put("msg","OK");
            responseMap.put("data", gs.deleteGenerador(id)); //En esta línea se borra la Generador
            String jsonResponse = om.writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("msg","ERROR");
            responseMap.put("data", e.getMessage());
            if(e.getClass() == ArrayIndexOutOfBoundsException.class) 
                return Response.status(Status.NOT_FOUND).entity(responseMap).build();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateGenerador(HashMap<String, String> json) {
        GeneradorServices gs = new GeneradorServices();
        HashMap<String,Object> responseMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        
        try {
            Integer id = Integer.valueOf(json.get("id"));
            String jsonRequest = om.writeValueAsString(json);
            gs.generadorFromJson(jsonRequest);
            if(!gs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            gs.updateGeneradorWithId(id);   
            responseMap.put("msg","OK");
            responseMap.put("data", gs.getGenerador()); 
            String jsonResponse = om.writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("msg","ERROR");
            responseMap.put("data", e.getMessage());
            if(e.getClass() == ArrayIndexOutOfBoundsException.class) 
                return Response.status(Status.NOT_FOUND).entity(responseMap).build();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }


}
