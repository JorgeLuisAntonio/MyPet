/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm402.mypet.rest;

import com.google.gson.Gson;
import edu.utl.dsm402.mypet.controller.ControllerLogin;
import edu.utl.dsm402.mypet.model.Cliente;
import edu.utl.dsm402.mypet.model.Empleado;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Usuario
 */
@Path("log")
public class RESTLog extends Application{
    
    
    
    @POST
    @Path("in")
    @Produces(MediaType.APPLICATION_JSON)
    public Response in(@FormParam("nombre") @DefaultValue("0")String u,
            @FormParam("contrasenia") @DefaultValue("0")String p){
        String out="";
        
        Cliente cliente=new Cliente();
       Empleado empleado= new Empleado();
       ControllerLogin cLI=new ControllerLogin();
        try{
            cliente=cLI.logCliente(u,p);
            empleado=cLI.logEmpleado(u, p);
            
            if(!(cliente.getIdCliente()==0)){
                out=new Gson().toJson(cliente);
            }else if(empleado.getIdEmpleado()!=0){
                
                out=new Gson().toJson(empleado);
            }else{
                out="{\"exception\":\" Usuario y/o contraseña incorrectos\" }";
            }
            
            
        }catch(Exception e){
            out="{\"exception\": \""+e.toString()+"\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
        
    }
    // el servicio rest 
    /*
     @Path("update")
     @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("idEmpleado") @DefaultValue("0") int idEmpleado,
            @FormParam("correo") @DefaultValue("0") String correo,
            @FormParam("contrasenia") @DefaultValue("0") String contrasenia ) {
        ControllerLogin cl = new ControllerLogin();
        String out = "";
        try {
            cl.saveToken(idEmpleado,correo,contrasenia);
            out = "{\"result\":\"OK\"}";
        } catch (Exception s) {
            s.printStackTrace();
            out = "{\"exception\":\" " + s.toString() + " \"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
*/
}
