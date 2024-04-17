<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestión Inventario</title>
  <link rel="stylesheet" href="CSS/index.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Krona+One&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="CSS/inicioAdmin.css">
  <link rel="stylesheet" href="CSS/gestionInventario.css">
  
</head>
<body>
  <header>
    <nav>
      <div class="buscador">
        <h2 class="Dasboard">Dasboard</h2>
        <div class="navBuscador">
          <input type="text" placeholder="Buscar">
          <button>
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-search" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#2c3e50" fill="none" stroke-linecap="round" stroke-linejoin="round">
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <path d="M10 10m-7 0a7 7 0 1 0 14 0a7 7 0 1 0 -14 0" />
              <path d="M21 21l-6 -6" />
            </svg>
          </button>
        </div>
      </div>
    </nav>
  </header>

  <div class="menu">
    <div class="perfil">
        <img src="IMAGENES/foto perfil.webp" alt="Perfil del administrador">
      <p>Nombre del Administrador</p>
    </div>
    <ul class="opciones">
      <li><a href="inicioAdmin.html" id="gestionarInventario">Gestionar Inventario</a></li>
      <li><a href="gestionPerfiles.html" id="gestionarPerfiles">Gestionar Perfiles</a></li>
      <li><a href="gestionarCambiosDevoluciones.html" id="devolucionesCambios">Devoluciones / Cambios</a></li>
      <li><a href="gestionarbannerDestacados.html" id="publicidadDestacados">Banner / Destacados</a></li>
      <li><a href="gestionarPedidos.html" id="pedidos">Pedidos</a></li>
      <li><a href="index.html" id="verTienda" target="_blank">Ver Tienda</a></li>
    </ul>
  </div>

  <h1>Inventario</h1> 

  <div class="container">
    <div class="buscadorDos">
        <input type="text" placeholder="Código">
        <input type="text" placeholder="Nombre">
        <button>Buscar</button>
    </div>
    <table id="productosTable">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Prescripción</th>
          <th>Precio</th>
          <th>Imagen</th>
          <th>Género</th>
          <th>Cantidad</th> <!-- Nueva columna para mostrar la cantidad -->
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <!-- Aquí se agregarán dinámicamente las filas de productos -->
      </tbody>
    </table>

    <div class="agregar-producto">
      <button id="btnAgregarProducto">Agregar Nuevo Producto</button>
      <form action="AgregarProductoServlet" method="POST" enctype="multipart/form-data" id="formularioAgregarProducto" class="hidden">
        <input type="text" placeholder="Nombre" name="nombre" required>
        <input type="text" placeholder="Descripción" name="descripcion" required>
        <input type="file" id="imagenProducto" accept="image/*" name="imagen" required>
        <input type="number" placeholder="Precio" name="precio" step="0.01" required>
        <input type="number" placeholder="Talla" name="talla" required>
        <input type="text" placeholder="Color" name="color" required>
        <input type="text" placeholder="Género" name="genero" required>
        <input type="number" placeholder="Cantidad" name="cantidad" required> <!-- Nueva entrada para la cantidad -->
        <button type="submit">Guardar</button>
      </form>
    </div>
  </div>

  <script>
    // Cuando el documento esté listo
    document.addEventListener('DOMContentLoaded', function() {
      // Realizar una solicitud AJAX para obtener los productos del servidor
      var xhr = new XMLHttpRequest();
      xhr.open('GET', 'MostrarProductosServlet', true);
      xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
          if (xhr.status === 200) {
            // Procesar los datos recibidos y actualizar la tabla HTML
            var productos = JSON.parse(xhr.responseText);
            var tableBody = document.querySelector('#productosTable tbody');
            tableBody.innerHTML = ''; // Limpiar el contenido existente
            productos.forEach(function(producto) {
              var row = '<tr>' +
                '<td>' + producto.id + '</td>' +
                '<td>' + producto.nombre + '</td>' +
                '<td>' + producto.descripcion + '</td>' +
                '<td>$' + producto.precio + '</td>' +
                '<td><img src="' + producto.imagen + '" alt="' + producto.nombre + '"></td>' +
                '<td>' + producto.genero + '</td>' +
                '<td>' + producto.cantidad + '</td>' + // Nueva celda para la cantidad
                '<td>' +
                '<button class="edit-btn" onclick="editarProducto(' + producto.id + ')">Editar</button>' +
                '<button class="delete-btn" onclick="eliminarProducto(' + producto.id + ')">Eliminar</button>' +
                '</td>' +
                '</tr>';
              tableBody.innerHTML += row;
            });
          } else {
            console.error('Error al obtener los productos:', xhr.status);
          }
        }
      };
      xhr.send();
    });
    

    // Función para eliminar un producto
    function eliminarProducto(idProducto) {
      var confirmacion = confirm("¿Estás seguro de que quieres eliminar este producto?");
      if (confirmacion) {
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', 'EliminarProductosServlet?id=' + idProducto, true);
        xhr.onreadystatechange = function() {
          if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
              // Recargar la página para mostrar los cambios
              location.reload();
            } else {
              console.error('Error al eliminar el producto:', xhr.status);
            }
          }
        };
        xhr.send();
      }
    }

    // Función para redireccionar a la página de edición del producto
    function editarProducto(idProducto) {
      // Redirigir a la página de edición del producto con el ID como parámetro
      window.location.href = 'editarProducto.jsp?id=' + idProducto;
    }

  </script>
  <script src="JS/gestionInventario.js"></script>
</body>
</html>
