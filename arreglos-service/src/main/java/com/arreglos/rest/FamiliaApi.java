package com.arreglos.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.arreglos.controller.dao.services.FamiliaServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/familia")
public class FamiliaApi {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllFamilias() {
        HashMap<String,Object> responseMap = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        ObjectMapper om = new ObjectMapper();
        try {                
            responseMap.put("msg", "OK");
            responseMap.put("data", fs.getAllFamilia());
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getFamiliaById(HashMap<String,String> map) {
        HashMap<String,Object> responseMap = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        ObjectMapper om = new ObjectMapper();
        Integer id = Integer.valueOf(map.get("id"));
        try {                
            responseMap.put("msg", "OK");
            responseMap.put("data", fs.getFamiliaById(id));
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
    public Response saveFamilia(String json) {
        HashMap<String,Object> responseMap = new HashMap<>();
        FamiliaServices fs = new FamiliaServices(true);
        ObjectMapper om = new ObjectMapper();
        try {
            fs.familiaFromJson(json);
            if(!fs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            fs.save();
            responseMap.put("msg","OK");
            responseMap.put("data", fs.getFamilia());
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
    public Response deleteFamilia(HashMap<String, String> json) {
        FamiliaServices fs = new FamiliaServices();
        HashMap<String,Object> responseMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        
        try {
            Integer id = Integer.valueOf(json.get("id"));   
            responseMap.put("msg","OK");
            responseMap.put("data", fs.deleteFamilia(id)); //En esta línea se borra la familia
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
    public Response updateFamilia(HashMap<String, String> json) {
        FamiliaServices fs = new FamiliaServices();
        HashMap<String,Object> responseMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        
        try {
            Integer id = Integer.valueOf(json.get("id"));
            String jsonRequest = om.writeValueAsString(json);
            fs.familiaFromJson(jsonRequest);
            System.out.println(fs.familiaToJson());
            if(!fs.isThereAllFields()) return Response.status(Status.BAD_REQUEST).build();
            fs.updateFamiliaWithId(id);   
            responseMap.put("msg","OK");
            responseMap.put("data", fs.getFamilia()); 
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
