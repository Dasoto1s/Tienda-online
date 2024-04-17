/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.87
 * Generated at: 2024-04-16 18:54:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Backend.ConexionBaseDatos;
import logica.Producto;

public final class editarProducto_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(2);
    _jspx_imports_classes.add("Backend.ConexionBaseDatos");
    _jspx_imports_classes.add("logica.Producto");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');

    String idProductoStr = request.getParameter("id");
    if (idProductoStr != null) {
        try {
            int idProducto = Integer.parseInt(idProductoStr);
            ConexionBaseDatos backend = new ConexionBaseDatos("root", "", "jdbc:mysql://localhost:3306/abc");
            Producto producto = backend.obtenerProductoPorId(idProducto);
            if (producto != null) {

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"CSS/editarProductos.css\"/>\n");
      out.write("  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n");
      out.write("  <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n");
      out.write("  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n");
      out.write("  <link href=\"https://fonts.googleapis.com/css2?family=Krona+One&display=swap\" rel=\"stylesheet\">\n");
      out.write("  <title>Editar Producto</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1 class=\"tituloEditar\">Editar Producto</h1>\n");
      out.write("  <div id=\"detalleProducto\">\n");
      out.write("    <!-- Formulario para editar el producto -->\n");
      out.write("    <form id=\"formularioEditarProducto\" method=\"post\" action=\"ActualizarProductoServlet\" onsubmit=\"mostrarMensajeExito()\">\n");
      out.write("      <!-- Aquí incluye todos los campos del producto, como nombre, descripción, imagen, precio, género, etc. -->\n");
      out.write("      <input type=\"hidden\" name=\"id\" value=\"");
      out.print( producto.getId() );
      out.write("\">\n");
      out.write("      <label for=\"nombre\">Nombre:</label>\n");
      out.write("      <input type=\"text\" id=\"nombre\" name=\"nombre\" value=\"");
      out.print( producto.getNombre() );
      out.write("\"><br>\n");
      out.write("      <label for=\"descripcion\">Descripción:</label>\n");
      out.write("      <input type=\"text\" id=\"descripcion\" name=\"descripcion\" value=\"");
      out.print( producto.getDescripcion() );
      out.write("\"><br>\n");
      out.write("      <label for=\"imagen\">Imagen:</label>\n");
      out.write("      <input type=\"file\" id=\"imagen\" name=\"imagen\" value=\"");
      out.print( producto.getImagen() );
      out.write("\"><br>\n");
      out.write("      <label for=\"precio\">Precio:</label>\n");
      out.write("      <input type=\"text\" id=\"precio\" name=\"precio\" value=\"");
      out.print( producto.getPrecio() );
      out.write("\"><br>\n");
      out.write("      <label for=\"genero\">Género:</label>\n");
      out.write("      <select id=\"genero\" name=\"genero\">\n");
      out.write("          <option value=\"Hombre\" ");
      out.print( producto.getGenero() != null && producto.getGenero().equals("Hombre") ? "selected" : "" );
      out.write(">Hombre</option>\n");
      out.write("          <option value=\"Mujer\" ");
      out.print( producto.getGenero() != null && producto.getGenero().equals("Mujer") ? "selected" : "" );
      out.write(">Mujer</option>\n");
      out.write("          <option value=\"Unisex\" ");
      out.print( producto.getGenero() != null && producto.getGenero().equals("Unisex") ? "selected" : "" );
      out.write(">Unisex</option>\n");
      out.write("      </select><br>\n");
      out.write("      <label for=\"cantidad\">Cantidad:</label> <!-- Nuevo campo para la cantidad -->\n");
      out.write("      <input type=\"number\" id=\"cantidad\" name=\"cantidad\" value=\"");
      out.print( producto.getCantidad() );
      out.write("\"><br> <!-- Mostrar la cantidad actual -->\n");
      out.write("\n");
      out.write("      <!-- Otros campos del producto -->\n");
      out.write("      <button type=\"submit\">Guardar Cambios</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <a href=\"index.jsp\">Volver a inicio</a>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  <script>\n");
      out.write("    // Función para mostrar mensaje de éxito al actualizar el producto\n");
      out.write("    function mostrarMensajeExito() {\n");
      out.write("      alert(\"Producto actualizado exitosamente\");\n");
      out.write("    }\n");
      out.write("  </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");

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

      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
