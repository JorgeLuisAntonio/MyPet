package edu.utl.dsm402.mypet.rest;



import com.google.gson.Gson;

import edu.utl.dsm402.mypet.controller.ControllerMercancia; 
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

*	@author  */


@Path("mercancia")

public class RESTMercancia extends Application
{
@Path("save")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response save(@FormParam("idProducto") @DefaultValue("0") int idProducto, @FormParam("nombre") @DefaultValue("0") String nombre, @FormParam("existencias") @DefaultValue("0") int existencias,
@FormParam("precioCompra") @DefaultValue("0") float procioCompra, @FormParam("foto") @DefaultValue("0") String foto, @FormParam("idMercancia") @DefaultValue("0") int idMercancia, @FormParam("codigoBarras") @DefaultValue("0") String codigoBarras, @FormParam("descripcion") @DefaultValue("0") String descripcion, @FormParam("marca") @DefaultValue("0") String marca, @FormParam("modelo") @DefaultValue("0") String modelo)
{

Producto p = new Producto(idProducto, nombre, existencias, procioCompra, foto, 1); Mercancia m = new Mercancia(idMercancia, p, codigoBarras, descripcion, modelo, marca);
String out = "";
ControllerMercancia cm = new ControllerMercancia();

try{
if(m.getProducto().getIdProducto() == 0)
cm.insert(m);
else
cm.update(m);
out= new Gson().toJson(m);
}catch(Exception e)
{
e.printStackTrace();
out = "{\"exception\":\" "+e.toString()+" \"}";
}
return Response.status(Response.Status.OK).entity(out).build();
}
 



@Path("delete")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@FormParam("idProducto") @DefaultValue("0") int idProducto){
ControllerMercancia cm = new ControllerMercancia();
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
 ControllerMercancia cm = new ControllerMercancia();
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



@Path("getAll")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getAll(){
ControllerMercancia cm = new ControllerMercancia();
List<Mercancia> mercancias = new ArrayList<>();
String out="";
try{
mercancias=cm.getAll();
if(!mercancias.isEmpty())
out = new Gson().toJson(mercancias);
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
ControllerMercancia cm = new ControllerMercancia();
List<Mercancia> mercancias = new ArrayList<>();
String out="";
try{
mercancias=cm.getAllInactive();
if(!mercancias.isEmpty())
out = new Gson().toJson(mercancias);
else
out="{\"exception\":\"No hay datos\"}";
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
ControllerMercancia cm = new ControllerMercancia();
List<Mercancia> mercancias = new ArrayList<>();
String out="";
try{
mercancias=cm.search(p);

if(!mercancias.isEmpty())
out = new Gson().toJson(mercancias);
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
