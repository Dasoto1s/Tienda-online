package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Producto;

@WebServlet
        (name = "MostrarProductosServlet", urlPatterns = {"/MostrarProductosServlet"})
public class MostrarProductosServlet extends HttpServlet {

    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/abc";

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    Connection conexion = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(url, usuario, password);

        // Consulta para obtener todos los productos
        pstmt = conexion.prepareStatement("SELECT *, cantidad FROM PRODUCTO");
        rs = pstmt.executeQuery();

        // Crear lista para almacenar productos
        List<Producto> productos = new ArrayList<>();

        // Iterar sobre los resultados y crear objetos Producto
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getString("Id_Producto"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("Descripcion"));
            producto.setPrecio(rs.getFloat("precio"));
            producto.setTalla(rs.getInt("talla"));
            producto.setColor(rs.getString("color"));
            producto.setGenero(rs.getString("genero"));
            producto.setCantidad(rs.getInt("cantidad")); // Agregar cantidad

            productos.add(producto);
        }

        // Convertir la lista de productos a formato JSON manualmente
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (Producto producto : productos) {
            jsonBuilder.append("{");
            jsonBuilder.append("\"id\":").append(producto.getId()).append(",");
            jsonBuilder.append("\"nombre\":\"").append(producto.getNombre()).append("\",");
            jsonBuilder.append("\"descripcion\":\"").append(producto.getDescripcion()).append("\",");
            jsonBuilder.append("\"precio\":").append(producto.getPrecio()).append(",");
            jsonBuilder.append("\"talla\":").append(producto.getTalla()).append(",");
            jsonBuilder.append("\"color\":\"").append(producto.getColor()).append("\",");
            jsonBuilder.append("\"genero\":\"").append(producto.getGenero()).append("\",");
            jsonBuilder.append("\"cantidad\":").append(producto.getCantidad()); // Agregar cantidad
            jsonBuilder.append("},");
        }
        // Eliminar la última coma
        if (!productos.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        }
        jsonBuilder.append("]");

        // Enviar la respuesta con el JSON generado
        PrintWriter out = response.getWriter();
        out.print(jsonBuilder.toString());

    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        // Mostrar mensaje de error
        response.getWriter().println("<h1>Error al obtener los productos</h1>");
        response.getWriter().println("<p>" + ex.getMessage() + "</p>");
    } finally {
        // Cerrar recursos
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
}

