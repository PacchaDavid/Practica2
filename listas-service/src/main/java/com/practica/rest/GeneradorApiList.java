package com.practica.rest;

import java.lang.reflect.Array;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.practica.controller.dao.services.GeneradorServices;
import com.practica.controller.exception.ListEmptyException;

@Path("/generador")
public class GeneradorApiList {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllGeneradors() {
        GeneradorServices gs = new GeneradorServices();
        String responseJson = "";
        try {
            responseJson += "{\"msg\":\"OK\",\"data\":" + gs.getAll() + "}";
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getClass() == ListEmptyException.class) 
                return Response.ok(Array.newInstance(getClass(), 0)).build();

            responseJson += "{\"msg\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseJson).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getFamimlia(HashMap<String,String> json) {
        GeneradorServices gs = new GeneradorServices();
        Integer id = Integer.valueOf(json.get("id"));
        String responseJson = "";
        try {
            responseJson = "{\"message\":\"ok\",\"data\":"+ gs.getGeneradorJsonById(id) + "}";
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson += "{\"msg\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
            if(e.getClass() == IndexOutOfBoundsException.class ||e.getClass() == ListEmptyException.class)
                return Response.status(Status.NOT_FOUND).entity(responseJson).build();

            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseJson).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response saveGenerador(String GeneradorJson) {
        GeneradorServices gs = new GeneradorServices();
        String responseJson = "";
        try {
            gs.fromJson(GeneradorJson);
            if(!gs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            gs.save();
            responseJson = "{\"message\":\"ok\",\"data\":"+ gs.toJson() + "}";
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson += "{\"msg\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseJson).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateGenerador(String GeneradorJson) {
        GeneradorServices gs = new GeneradorServices();
        String responseJson = "";
        System.out.println(GeneradorJson);
        try {
            gs.fromJson(GeneradorJson);
            if(!gs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            gs.update();
            responseJson = "{\"message\":\"ok\",\"data\":"+ gs.toJson() + "}";
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson += "{\"msg\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
            if(e.getClass() == IndexOutOfBoundsException.class) 
                return Response.status(Status.BAD_REQUEST).entity(responseJson).build();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseJson).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response deleteGenerador(HashMap<String,String> json) {
        GeneradorServices gs = new GeneradorServices();
        Integer id = Integer.valueOf(json.get("id"));
        String responseJson = "";
        try {
            responseJson = "{\"message\":\"ok\",\"data\":"+ gs.deleteGenerador(id) + "}";
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson += "{\"msg\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
            if(e.getClass() == IndexOutOfBoundsException.class || e.getClass() == ListEmptyException.class)
                return Response.status(Status.NOT_FOUND).entity(responseJson).build();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
