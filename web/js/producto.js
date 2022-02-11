//Aquí vamos a guardar los datos de los productos:
var productos = [
                    {
                        id : 1,
                        codigoBarras : "75894546",
                        nombre : "Shampoo anti pulgas",
                        marca : "Jerklin",
                        modelo : "N/A",
                        precioCompra : 45.75,
                        precioVenta : 75.00,
                        existencias : 15,
                        descripcion : "Jabón anti-pulgas para perros.",
                        status : 1
                    },
                    {

                        id : 2,
                        codigoBarras : "7576542",
                        nombre : "Cadena de castigo metálico",
                        marca : "Genérico",
                        modelo : "N/A",
                        precioCompra : 99.99,
                        precioVenta : 120.00,
                        existencias : 200,
                        descripcion : "Cadena metalica para entrenamiento de perros.",
                        status : 1
                    },
                    {
                        id : 3,
                        codigoBarras : "769874",
                        nombre : "Croquetas PM",
                        marca : "Purina",
                        modelo : "Gold",
                        precioCompra : 320.99,
                        precioVenta : 474.99,
                        existencias :56,
                        descripcion : "Croqueta de alta proteina para perro mediano.",
                        status : 1
                    }
                ];

//Aquí vamos a controlar el código interno del producto:
var codigo_interno_auto = 3;

function guardarProducto()
{
    //Creamos un nuevo objeto y le asignamos propiedades y valores 
    //obtenidos desde las cajas de texto:
    var producto =  {
                        id: 0, //Codigo Interno
                        codigoBarras : $("#txtCodigoBarras").val(),
                        nombre : $("#txtNombre").val(),
                        marca : $("#txtMarca").val(),
                        modelo : $("#txtModelo").val(),
                        precioCompra : parseFloat($("#txtPrecioCompra").val()),
                        precioVenta : parseFloat($("#txtPrecioVenta").val()),
                        existencias : $("#txtExistencias").val(),
                        descripcion : $("#txtDescripcion").val(),
                        status: 1
                    };
       
    //Revisamos si la caja de texto del código interno tiene un valor:
    if ($("#txtCodigoInterno").val().length > 0)
    {
        //Asignamos el ID = 0
        producto.id = parseInt($("#txtCodigoInterno").val());        
    }
    
    // Comparamos si el ID del producto tiene un valor de 0 o superior para
    // decidir si insertamos o actualizamos:
    if (producto.id == 0)
    {
        //Incrementamos el codigo interno:
        codigo_interno_auto ++;
        
        //Asignamos el codigo interno actual:
        producto.id = codigo_interno_auto;
        
        insertarProducto(producto);
        refrescarTablaProductos();
    }
    else
    {
        modificarProducto(producto);
        refrescarTablaProductos();
    }
}

/**
 * Inserta un producto en el arreglo JSON productos.
 * @param {type} p
 * @returns {undefined}
 */
function insertarProducto(p)
{
    //Insertamos el objeto dentro del arreglo JSON utilizando el método push:
    productos.push(p);
    
    //Actualizamos la caja de texto del ID:
    $("#txtCodigoInterno").val(p.id);
    
    //Avisamos al usuario:
    alert('Producto agregado correctamente.');
}

/**
 * Modifica los datos de un producto existente.
 * @param {type} p
 * @returns {undefined}
 */
function modificarProducto(p)
{
    //Primero buscamos la posicion del producto con base en su id:
    var pos = buscarPosicionProducto(p.id);
    
    //Revisamos que sea una posición válida:
    if (pos >= 0)
    {
        //Guardamos el nuevo objeto en dicha posición:
        productos[pos] = p;
        
        //Avisamos al usuario:
        alert('Datos de producto actualizados correctamente.');
    }
    else
    {
        alert('No se encontro un producto con ese ID: Error de integridad.');        
    }
}

function eliminarProducto()
{
    //Recuperamos el ID del producto:
    var id = $("#txtCodigoInterno").val();
    var pos = buscarPosicionProducto(id);
    
    if (pos >= 0)
    {
        productos[pos].status = 0;
        alert('Producto eliminado correctamente.');        
        refrescarTablaProductos();
        limpiarCamposDetalleProducto();
    }
    
}

/**
 * Busca un producto por su ID dentro del arreglo
 * JSON de productos.
 * @param {type} id
 * @returns {undefined}
 */
function buscarPosicionProducto(id)
{
    //Recorremos el arreglo para buscar el ID del producto
    //correspondiente y devolver la posición:
    for (var i = 0; i < productos.length; i++)
    {
        //Si el id buscado es igual al ID del producto en la posición i,
        //devolvemos el valor de i:
        if (id == productos[i].id)
        {
            return i;
        }
    }    
    //Si llegamos a este punto, significa que el ID no existe y entonces,
    //retornamos el valor de -1.
    return -1;
}

/**
 * Actualiza el contenido de la tabla de productos
 * con los objetos contenidos en el arreglo JSON productos.
 * @returns {undefined}
 */
function refrescarTablaProductos()
{
    var contenido = '';
    
    //Recorremos el arreglo JSON para ir generando el
    //contenido de la tabla:
    for(var i = 0; i < productos.length; i++)
    {
        contenido = contenido + '<tr>' +
                                    '<td>' + productos[i].id + '</td>' +
                                    '<td>' + productos[i].codigoBarras + '</td>' +
                                    '<td>' + productos[i].nombre + '</td>' +
                                    '<td>' + productos[i].marca + '</td>' +
                                    '<td>' + productos[i].modelo + '</td>' +
                                    '<td>' + productos[i].precioCompra + '</td>' +
                                    '<td>' + productos[i].precioVenta + '</td>' +
                                    '<td>' + productos[i].existencias + '</td>' +
                                    '<td>' + productos[i].descripcion + '</td>' +
                                    '<td>' + productos[i].status + '</td>' +
                                    '<td><button class="btn btn-sm btn-primary" onclick="mostrarDetalleProducto(' + i + ')">Ver Detalle</button></td>' +
                                '</tr>';
    }
    $("#tbodyProductos").html(contenido);
}

function limpiarCamposDetalleProducto()
{
    $("#txtCodigoInterno").val("");
    $("#txtCodigoBarras").val("");
    $("#txtNombre").val("");
    $("#txtMarca").val("");
    $("#txtModelo").val("");
    $("#txtPrecioCompra").val("");
    $("#txtPrecioVenta").val("");
    $("#txtExistencias").val("");
    $("#txtDescripcion").val("");
    $("#divStatusP").prop("checked", false);
}


function mostrarDetalleProducto(pos)
{
    var p = productos[pos];
    
    $("#txtCodigoInterno").val(p.id);
    $("#txtCodigoBarras").val(p.codigoBarras);
    $("#txtNombre").val(p.nombre);
    $("#txtMarca").val(p.marca);
    $("#txtModelo").val(p.modelo);
    $("#txtPrecioCompra").val(p.precioCompra);
    $("#txtPrecioVenta").val(p.precioVenta);
    $("#txtExistencias").val(p.existencias);
    $("#txtDescripcion").val(p.descripcion);
    
    if (p.status == 1)
    {
        $("#divStatusP").prop("checked", true);
    }
    else
    {
        $("#divStatusP").prop("checked", false);
    }
    
}