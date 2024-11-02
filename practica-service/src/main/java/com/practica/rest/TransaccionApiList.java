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

import com.practica.controller.dao.services.TransaccionServices;
import com.practica.controller.exception.ListEmptyException;

@Path("/transaccion")
public class TransaccionApiList {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllTransaccions() {
        TransaccionServices ts = new TransaccionServices();
        String responseJson = "";
        try {
            responseJson += "{\"msg\":\"OK\",\"data\":" + ts.getAll() + "}";
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
        TransaccionServices ts = new TransaccionServices();
        Integer id = Integer.valueOf(json.get("id"));
        String responseJson = "";
        try {
            responseJson = "{\"message\":\"ok\",\"data\":"+ ts.getTransaccionJsonById(id) + "}";
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
    public Response saveTransaccion(String TransaccionJson) {
        TransaccionServices ts = new TransaccionServices();
        String responseJson = "";
        try {
            ts.fromJson(TransaccionJson);
            if(!ts.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            ts.save();
            responseJson = "{\"message\":\"ok\",\"data\":"+ ts.toJson() + "}";
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
    public Response updateTransaccion(String TransaccionJson) {
        TransaccionServices ts = new TransaccionServices();
        String responseJson = "";
        try {
            ts.fromJson(TransaccionJson);
            if(!ts.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            ts.update();
            responseJson = "{\"message\":\"ok\",\"data\":"+ ts.toJson() + "}";
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
    public Response deleteTransaccion(HashMap<String,String> json) {
        TransaccionServices ts = new TransaccionServices();
        Integer id = Integer.valueOf(json.get("id"));
        String responseJson = "";
        try {
            responseJson = "{\"message\":\"ok\",\"data\":"+ ts.deleteTransaccion(id) + "}";
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
