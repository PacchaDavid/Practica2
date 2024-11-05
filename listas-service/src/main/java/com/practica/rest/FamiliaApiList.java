package com.practica.rest;

import java.lang.reflect.Array;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.practica.controller.dao.services.FamiliaServices;
import com.practica.controller.exception.ListEmptyException;

@Path("/familia")
public class FamiliaApiList {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllFamilias() {
        FamiliaServices fs = new FamiliaServices();
        String responseJson = "";
        try {
            responseJson += "{\"msg\":\"OK\",\"data\":" + fs.getAll() + "}";
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
        FamiliaServices fs = new FamiliaServices();
        Integer id = Integer.valueOf(json.get("id"));
        String responseJson = "";
        try {
            responseJson = "{\"message\":\"ok\",\"data\":"+ fs.getFamiliaJsonById(id) + "}";
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
    public Response saveFamilia(String familiaJson) {
        FamiliaServices fs = new FamiliaServices();
        String responseJson = "";
        try {
            fs.fromJson(familiaJson);
            if(!fs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            fs.save();
            responseJson = "{\"message\":\"ok\",\"data\":"+ fs.toJson() + "}";
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
    public Response updateFamilia(String familiaJson) {
        FamiliaServices fs = new FamiliaServices();
        String responseJson = "";
        try {
            fs.fromJson(familiaJson);
            if(!fs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            fs.update();
            responseJson = "{\"message\":\"ok\",\"data\":"+ fs.toJson() + "}";
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
    public Response deleteFamilia(HashMap<String,String> json) {
        FamiliaServices fs = new FamiliaServices();
        Integer id = Integer.valueOf(json.get("id"));
        String responseJson = "";
        try {
            responseJson = "{\"message\":\"ok\",\"data\":"+ fs.deleteFamilia(id) + "}";
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson += "{\"msg\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
            if(e.getClass() == IndexOutOfBoundsException.class || e.getClass() == ListEmptyException.class)
                return Response.status(Status.NOT_FOUND).entity(responseJson).build();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cantones_and_nivelesSE")
    public Response enumerations() {
        FamiliaServices fs = new FamiliaServices();
        String jsonResponse = "{\"mgs\":\"ok\",";
        jsonResponse += "\"niveles\":" + fs.nivelesSocioeconomicosJson() + ",";
        jsonResponse += "\"cantones\":" + fs.cantonesJson() + "}";        
        return Response.ok(jsonResponse).build();
    }
    
}
