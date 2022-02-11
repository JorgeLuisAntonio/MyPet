/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm402.mypet.rest;

import com.google.gson.Gson;
import edu.utl.dsm402.mypet.controller.ControllerEmpleado;
import edu.utl.dsm402.mypet.model.Empleado;
import edu.utl.dsm402.mypet.model.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jorge
 */

@Path("empleado")
public class RESTEmpleado extends Application{
    
    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public Response save(
            @FormParam("nombre") @DefaultValue("0") String nombre,
            @FormParam("apellidoPaterno") @DefaultValue("0") String apellidoPaterno,
            @FormParam("apellidoMaterno") @DefaultValue("0") String apellidoMaterno,
            @FormParam("fechaNacimiento") @DefaultValue("0") String fechaNacimiento,
            @FormParam("calle") @DefaultValue("0") String calle,
            @FormParam("numero") @DefaultValue("0") int numero,
            @FormParam("colonia") @DefaultValue("0") String colonia,
            @FormParam("cp") @DefaultValue("0") int cp,
            @FormParam("ciudad") @DefaultValue("0") String ciudad,
            @FormParam("estado") @DefaultValue("0") String estado,
            @FormParam("tel1") @DefaultValue("0") int tel1,
            @FormParam("tel2") @DefaultValue("0") int tel2,
            @FormParam("correo") @DefaultValue("0") String correo,
            @FormParam("contrasenia") @DefaultValue("0") String contrasenia,
            @FormParam("idPersona") @DefaultValue("0") int idPersona,
            @FormParam("idEmpleado") @DefaultValue("0") int idEmpleado) {
        Persona per = new Persona(idPersona, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, calle, numero, colonia, cp, ciudad, estado, tel1, tel2, 1);
        Empleado e = new Empleado(idEmpleado, per, correo, contrasenia);
        String out = "";
        ControllerEmpleado ce = new ControllerEmpleado();

        try {
            if (e.getPersona().getIdPersona() == 0) {
                ce.insert(e);
            } else {
                ce.update(e);
            }
            out = new Gson().toJson(e);
        } catch (Exception s) {
            s.printStackTrace();
            out = "{\"exception\":\" " + s.toString() + " \"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        ControllerEmpleado ce = new ControllerEmpleado();
        List<Empleado> empleados = new ArrayList<>();
        String out = "";
        try {
            empleados = ce.getAll();
            if (!empleados.isEmpty()) {
                out = new Gson().toJson(empleados);
            } else {
                out = "{\"exception\":\"No hay datos\"}";
            }
        } catch (Exception s) {
            s.printStackTrace();
            out = "{\"exception\":\"" + s.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllInactive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInactive() {
        ControllerEmpleado ce = new ControllerEmpleado();
        List<Empleado> empleados = new ArrayList<>();
        String out = "";
        try {
            empleados = ce.getAllInactive();
            if (!empleados.isEmpty()) {
                out = new Gson().toJson(empleados);
            } else {
                out = "{\"exception\":\"No hay datos\"}";
            }
        } catch (Exception s) {
            s.printStackTrace();
            out = "{\"exception\":\"" + s.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
    @Path("delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idPersona") @DefaultValue("0") int idPersona) {
        ControllerEmpleado ce = new ControllerEmpleado();
        String out = "";
        try {
            ce.delete(idPersona);
            out = "{\"result\":\"OK\"}";
        } catch (Exception s) {
            s.printStackTrace();
            out = "{\"exception\":\" " + s.toString() + " \"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
    @Path("reactivate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response reactivate(@FormParam("idPersona") @DefaultValue("0") int idPersona) {
        ControllerEmpleado ce = new ControllerEmpleado();
        String out = "";
        try {
            ce.reactivate(idPersona);
            out = "{​​\"result\":\"OK\"}​​";
        } catch (Exception s) {
            s.printStackTrace();
            out = "{​​\"exception\":\" " + s.toString() + " \"}​​";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
    
    
    @Path("search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public Response search(@FormParam("palabra") @DefaultValue("") String p) {
        ControllerEmpleado ce = new ControllerEmpleado();
        List<Empleado> empleados = new ArrayList<>();
        String out = "";
        try {
            empleados = ce.search(p);

            if (!empleados.isEmpty()) {
                out = new Gson().toJson(empleados);
            } else {
                out = "{\"error\":\"No hay datos\"}";
            }
        } catch (Exception s) {
            s.printStackTrace();
            out = "{\"exception\":\"" + s.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
