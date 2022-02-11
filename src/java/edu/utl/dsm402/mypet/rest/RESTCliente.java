/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm402.mypet.rest;
import com.google.gson.Gson;
import edu.utl.dsm402.mypet.controller.ControllerCliente;
import edu.utl.dsm402.mypet.model.Cliente;
import edu.utl.dsm402.mypet.model.Persona;
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

@Path("cliente")
public class RESTCliente  extends Application
{
    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response save(@FormParam("idPersona") @DefaultValue("0") int idPersona, 
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
                         @FormParam("idCliente") @DefaultValue("0") int idCliente,
                         @FormParam("correo") @DefaultValue("0") String correo,
                         @FormParam("contrasenia") @DefaultValue("0") String contrasenia)
            
    {
        Persona per = new Persona (idPersona, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, calle, numero, colonia, cp, ciudad, estado, tel1,  tel2 ,1);
        Cliente c = new Cliente (idCliente, per, correo,contrasenia);
        String out = "";
        ControllerCliente cc = new ControllerCliente();
        
        try
        {
            if(c.getPersona().getIdPersona() == 0)
            cc.insert(c);
            else
            cc.update(c);
            out= new Gson().toJson(c);
            }
            catch(Exception e)
            {
            e.printStackTrace();
            out = "{\"exception\":\" "+e.toString()+" \"}";
            }
            return Response.status(Response.Status.OK).entity(out).build();
     }
@Path("delete")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@FormParam("idPersona") @DefaultValue("0") int idPersona)
{
    ControllerCliente cc = new ControllerCliente();
    String out="";
    try{
    cc.delete(idPersona);
    out = "{\"result\":\"OK\"}";
    }catch(Exception e)
    {
    e.printStackTrace();
    out = "{\"exception\":\" "+e.toString()+" \"}";
    }
    return Response.status(Response.Status.OK).entity(out).build();
}



@Path("reactivate")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response reactivate(@FormParam("idCliente") @DefaultValue("0") int idCliente)
 {
    ControllerCliente cc = new ControllerCliente();
    String out="";
    try
    {
        cc.reactivate(idCliente);
        out = "{​​\"result\":\"OK\"}​​";
    }
    catch(Exception e)
    {
    e.printStackTrace();
    out = "{​​\"exception\":\" "+e.toString()+" \"}​​";
    }
    return Response.status(Response.Status.OK).entity(out).build();
 }



@Path("getAll")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getAll()
{
    ControllerCliente cc = new ControllerCliente();
    List<Cliente> clientes = new ArrayList<>();
    String out="";
    try{
    clientes=cc.getAll();
    if(!clientes.isEmpty())
    out = new Gson().toJson(clientes);
    else
    out="{\"exception\":\"No hay datos\"}";
    }catch(Exception e)
    {
    e.printStackTrace();
    out="{\"exception\":\""+e.toString()+"\"}";
    }
return Response.status(Response.Status.OK).entity(out).build();
}


@Path("getAllInactive")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getAllInactive()
{
    ControllerCliente cc = new ControllerCliente();
    List<Cliente> clientes = new ArrayList<>();
    String out="";
    try{
    clientes=cc.getAllInactive();
    if(!clientes.isEmpty())
    out = new Gson().toJson(clientes);
    else
    out="{\"exception\":\"No hay inactivos\"}";
    }catch(Exception e)
    {
    e.printStackTrace();
    out="{\"exception\":\""+e.toString()+"\"}";
}
return Response.status(Response.Status.OK).entity(out).build();
}


//QueryParam para metodo post
//FormParam para metodo get
@Path("search")
@POST
@Produces(MediaType.APPLICATION_JSON)

public Response search(@FormParam("palabra") @DefaultValue("") String p)
{
ControllerCliente cm = new ControllerCliente();
List<Cliente> clientes = new ArrayList<>();
String out="";
try{
clientes=cm.search(p);

if(!clientes.isEmpty())
out = new Gson().toJson(clientes);
else
out="{\"error\":\"No hay datos\"}";
}catch(Exception e)
{
e.printStackTrace();
out="{\"exception\":\""+e.toString()+"\"}";
}
return Response.status(Response.Status.OK).entity(out).build();
}   
}
