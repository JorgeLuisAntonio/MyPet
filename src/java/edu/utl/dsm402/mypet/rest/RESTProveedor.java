/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm402.mypet.rest;

import com.google.gson.Gson;
import edu.utl.dsm402.mypet.controller.ControllerProveedor;
import edu.utl.dsm402.mypet.model.Persona;
import edu.utl.dsm402.mypet.model.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michelsk8
 */
@Path("proveedor")
public class RESTProveedor extends Application{
    
    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("idPersona") @DefaultValue("0") int idPersona,
                        @FormParam("nombre") @DefaultValue("0") String nombre,
                        @FormParam("apellidoPaterno") @DefaultValue("0") String apellidoPaterno,
                        @FormParam("apellidoMaterno") @DefaultValue("0") String apellidoMaterno,
                        @FormParam("calle") @DefaultValue("0") String calle,
                        @FormParam("numero") @DefaultValue("0") int numero,
                        @FormParam("colonia") @DefaultValue("0") String colonia,
                        @FormParam("cp") @DefaultValue("0") int cp,
                        @FormParam("ciudad") @DefaultValue("0") String ciudad,
                        @FormParam("estado") @DefaultValue("0") String estado,
                        @FormParam("tel1") @DefaultValue("0") int tel1,
                        @FormParam("tel2") @DefaultValue("0") int tel2,
                        @FormParam("idProveedor") @DefaultValue("0") int idProveedor,
                        @FormParam("rfc") @DefaultValue("0") String rfc,
                        @FormParam("razonSocial") @DefaultValue("0") String razonSocial)                        
    {   
        Persona per = new Persona(idPersona,nombre,apellidoPaterno,apellidoMaterno,calle,numero,colonia,cp,ciudad,estado,tel1,tel2,1);
        Proveedor prov = new Proveedor(idProveedor,rfc,razonSocial,per);
        
        String out= "";
        ControllerProveedor cprov = new ControllerProveedor();
        
        try 
        {
            if (prov.getIdRepresentante().getIdPersona() == 0)
            {   
                cprov.insert(prov);
            }else{
                cprov.update(prov);
            }
            out= new Gson().toJson(prov);            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            out = "{\"exception\":\""+e.toString()+"\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response delete(@FormParam("idPersona") @DefaultValue("0") int idPersona)
    {
        ControllerProveedor cprov = new ControllerProveedor();
        String out="";
        
        try
        {
            cprov.delete(idPersona);
            out="{\"result\":\"Ok\"}";
        }
        catch (Exception e)
        {
            e.printStackTrace();
              out="{\"exception\":\" "+e.toString()+" \"}";      
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("reactivate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response reactivate(@FormParam("idPersona") @DefaultValue("0") int idPersona)
    {
        ControllerProveedor cprov = new ControllerProveedor();
        String out="";
        
        try
        {
            cprov.reactivate(idPersona);
            out="{\"result\":\"Ok\"}";
        }
        catch (Exception e)
        {
            e.printStackTrace();
              out="{\"exception\":\" "+e.toString()+" \"}";      
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getAll()
    {        
        ControllerProveedor cprov = new ControllerProveedor();
        List<Proveedor> proveedores = new ArrayList<>();
        
        String out="";
                      
        try 
        {
            proveedores=cprov.getAll();
            if(!proveedores.isEmpty()){
               out = new Gson().toJson(proveedores);
            }else
               out="{\"exception\":\"No hay datos\"}";
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out="{\"exception\":\""+e.toString()+ "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllInactive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getAllInactive()
    {        
        ControllerProveedor cprov = new ControllerProveedor();
        List<Proveedor> proveedores = new ArrayList<>();
        
        String out="";
                      
        try 
        {
            proveedores=cprov.getAllInactive();
            if(!proveedores.isEmpty()){
               out = new Gson().toJson(proveedores);
            }else
               out="{\"exception\":\"No hay datos\"}";
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out="{\"exception\":\""+e.toString()+ "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
   
    @Path("search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response search(@FormParam("palabra") @DefaultValue("") String p)
    {        
        ControllerProveedor cprov = new ControllerProveedor();
        List<Proveedor> proveedores = new ArrayList<>();
        
        String out="";
                      
        try 
        {
            proveedores=cprov.search(p);
            if(!proveedores.isEmpty())
                out = new Gson().toJson(proveedores);
            else
                out="{\"error\":\"No hay datos\"}";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out="{\"exception\":\""+e.toString()+"\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
