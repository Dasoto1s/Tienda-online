<%@ page import="Backend.BackendHSStudio" %>
<%@ page import="logica.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idProductoStr = request.getParameter("id");
    if (idProductoStr != null) {
        try {
            int idProducto = Integer.parseInt(idProductoStr);
            BackendHSStudio backend = new BackendHSStudio("root", "", "jdbc:mysql://localhost:3306/abc");
            Producto producto = backend.obtenerProductoPorId(idProducto);
            if (producto != null) {
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="CSS/editarProductos.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Krona+One&display=swap" rel="stylesheet">
  <title>Editar Producto</title>
</head>
<body>
    <h1 class="tituloEditar">Editar Producto</h1>
  <div id="detalleProducto">
    <!-- Formulario para editar el producto -->
    <form id="formularioEditarProducto" method="post" action="ActualizarProductoServlet" onsubmit="mostrarMensajeExito()">
      <!-- Aquí incluye todos los campos del producto, como nombre, descripción, imagen, precio, género, etc. -->
      <input type="hidden" name="id" value="<%= producto.getId() %>">
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" value="<%= producto.getNombre() %>"><br>
      <label for="descripcion">Descripción:</label>
      <input type="text" id="descripcion" name="descripcion" value="<%= producto.getDescripcion() %>"><br>
      <label for="imagen">Imagen:</label>
      <input type="file" id="imagen" name="imagen" value="<%= producto.getImagen() %>"><br>
      <label for="precio">Precio:</label>
      <input type="text" id="precio" name="precio" value="<%= producto.getPrecio() %>"><br>
      <label for="genero">Género:</label>
      <select id="genero" name="genero">
          <option value="Hombre" <%= producto.getGenero() != null && producto.getGenero().equals("Hombre") ? "selected" : "" %>>Hombre</option>
          <option value="Mujer" <%= producto.getGenero() != null && producto.getGenero().equals("Mujer") ? "selected" : "" %>>Mujer</option>
          <option value="Unisex" <%= producto.getGenero() != null && producto.getGenero().equals("Unisex") ? "selected" : "" %>>Unisex</option>
      </select><br>
      <label for="cantidad">Cantidad:</label> <!-- Nuevo campo para la cantidad -->
      <input type="number" id="cantidad" name="cantidad" value="<%= producto.getCantidad() %>"><br> <!-- Mostrar la cantidad actual -->

      <!-- Otros campos del producto -->
      <button type="submit">Guardar Cambios</button>
    </form>

    <a href="index.jsp">Volver a inicio</a>
  </div>

  <script>
    // Función para mostrar mensaje de éxito al actualizar el producto
    function mostrarMensajeExito() {
      alert("Producto actualizado exitosamente");
    }
  </script>
</body>
</html>
<%
            } else {
                // Manejar el caso en que no se encuentre el producto con el ID especificado
                out.println("No se encontró ningún producto con el ID especificado");
            }
        } catch (NumberFormatException e) {
            // Manejar el caso en que el parámetro ID no sea un número válido
            out.println("ID de producto no válido");
        }
    } else {
        // Manejar el caso en que no se proporciona un ID válido
        out.println("No se proporcionó ningún ID de producto");
    }
%>
