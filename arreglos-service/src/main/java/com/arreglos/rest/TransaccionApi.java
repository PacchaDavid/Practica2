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

import com.arreglos.controller.dao.services.TransaccionServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/transaccion")
public class TransaccionApi {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllTransaccions() {
        HashMap<String,Object> responseMap = new HashMap<>();
        TransaccionServices ts = new TransaccionServices();
        ObjectMapper om = new ObjectMapper();
        try {                
            responseMap.put("msg", "OK");
            responseMap.put("data", ts.getAllTransaccion());
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
    @Path("/get/")
    public Response getTransaccionById(HashMap<String,String> map) {
        HashMap<String,Object> responseMap = new HashMap<>();
        TransaccionServices ts = new TransaccionServices();
        ObjectMapper om = new ObjectMapper();
        Integer id = Integer.valueOf(map.get("id"));

        try {                
            responseMap.put("msg", "OK");
            responseMap.put("data", ts.getTransaccionById(id));
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
    public Response saveTransaccion(String json) {
        HashMap<String,Object> responseMap = new HashMap<>();
        TransaccionServices ts = new TransaccionServices(true);
        ObjectMapper om = new ObjectMapper();
        try {
            ts.transaccionFromJson(json);
            if(!ts.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            ts.save();
            responseMap.put("msg","OK");
            responseMap.put("data", ts.getTransaccion());
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
    public Response deleteTransaccion(HashMap<String, String> json) {
        TransaccionServices ts = new TransaccionServices();
        HashMap<String,Object> responseMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        
        try {
            Integer id = Integer.valueOf(json.get("id"));   
            responseMap.put("msg","OK");
            responseMap.put("data", ts.deleteTransaccion(id)); //En esta línea se borra la Transaccion
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
    public Response updateTransaccion(HashMap<String, String> json) {
        TransaccionServices ts = new TransaccionServices();
        HashMap<String,Object> responseMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        
        try {
            Integer id = Integer.valueOf(json.get("id"));
            String jsonRequest = om.writeValueAsString(json);
            ts.transaccionFromJson(jsonRequest);
            if(!ts.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            ts.updateTransaccionWithId(id);   
            responseMap.put("msg","OK");
            responseMap.put("data", ts.getTransaccion()); 
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
