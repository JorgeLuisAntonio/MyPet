/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm402.mypet.rest;

import com.google.gson.Gson;
import edu.utl.dsm402.mypet.controller.ControllerMascotas;
import edu.utl.dsm402.mypet.controller.ControllerMercancia;

import edu.utl.dsm402.mypet.model.Animal; 
import edu.utl.dsm402.mypet.model.Mercancia;
import edu.utl.dsm402.mypet.model.Producto;
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

@Path("mascota")
public class RESTMascota {
    
@Path("save")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response save(@FormParam("idProducto") @DefaultValue("0") int idProducto, @FormParam("nombre") @DefaultValue("0") String nombre, @FormParam("existencias") @DefaultValue("0") int existencias,
@FormParam("precioCompra") @DefaultValue("0") float precioCompra, @FormParam("foto") @DefaultValue("0") String foto, @FormParam("idAnimal") @DefaultValue("0") int idAnimal, @FormParam("raza") @DefaultValue("0") String raza, @FormParam("especie") @DefaultValue("0") String especie, @FormParam("fechaNacimiento") @DefaultValue("0") String fechaNacimiento, @FormParam("fechaLlegada") @DefaultValue("0") String fechaLlegada)
{

Producto p = new Producto(idProducto, nombre, existencias, precioCompra, foto, 1); Animal a = new Animal(idAnimal, p, raza, especie, fechaNacimiento, fechaLlegada);
String out = "";
ControllerMascotas cm = new ControllerMascotas();

try{
if(a.getProducto().getIdProducto() == 0)
cm.insert(a);
else
cm.update(a);
out= new Gson().toJson(a);
}catch(Exception e)
{
e.printStackTrace();
out = "{\"exception\":\" "+e.toString()+" \"}";
}
return Response.status(Response.Status.OK).entity(out).build();
}
    
@Path("getAll")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getAll(){
ControllerMascotas cm = new ControllerMascotas();
List<Animal> animales = new ArrayList<>();
String out="";
try{
animales=cm.getAll();
if(!animales.isEmpty())
out = new Gson().toJson(animales);
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
public Response getAllInactive(){
ControllerMascotas cm = new ControllerMascotas();
List<Animal> animales = new ArrayList<>();
String out="";
try{
animales=cm.getAllInactive();
if(!animales.isEmpty())
out = new Gson().toJson(animales);
else
out="{\"exception\":\"No hay datos\"}";
}catch(Exception e)
{
e.printStackTrace();
out="{\"exception\":\""+e.toString()+"\"}";
}
return Response.status(Response.Status.OK).entity(out).build();
}

    
@Path("delete")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@FormParam("idProducto") @DefaultValue("0") int idProducto){
ControllerMascotas cm = new ControllerMascotas();
String out="";
try{
cm.delete(idProducto);
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
 public Response reactivate(@FormParam("idProducto") @DefaultValue("0") int idProducto)
 {
 ControllerMascotas cm = new ControllerMascotas();
 String out="";
 try{
 cm.reactivate(idProducto);
 out = "{​​\"result\":\"OK\"}​​";
 }
 catch(Exception e)
 {
 e.printStackTrace();
 out = "{​​\"exception\":\" "+e.toString()+" \"}​​";
 }
 return Response.status(Response.Status.OK).entity(out).build();
 }


@Path("search")
@POST
@Produces(MediaType.APPLICATION_JSON)

public Response search(@FormParam("palabra") @DefaultValue("") String p)
{
ControllerMascotas cm = new ControllerMascotas();
List<Animal> animales=new ArrayList<>();
String out="";
try{
animales=cm.search(p);

if(!animales.isEmpty())
out = new Gson().toJson(animales);
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
