package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ObtenerProductoServlet", urlPatterns = {"/ObtenerProductoServlet"})
public class ObtenerProductoServlet extends HttpServlet {

    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/abc";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProductoStr = request.getParameter("id");
        if (idProductoStr != null) {
            int idProducto = Integer.parseInt(idProductoStr);

            Connection conexion = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);

                pstmt = conexion.prepareStatement("SELECT * FROM PRODUCTO WHERE Id_Producto = ?");
                pstmt.setInt(1, idProducto);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String nombre = rs.getString("Nombre");
                    String descripcion = rs.getString("Descripcion");
                    String imagen = rs.getString("Imagen");
                    double precio = rs.getDouble("Precio");
                    String genero = rs.getString("Genero");
                    int cantidad = rs.getInt("Cantidad"); // Nueva columna para la cantidad

                    // Construir una cadena con los detalles del producto
                    String detallesProducto = nombre + "," + descripcion + "," + imagen + "," + precio + "," + genero + "," + cantidad;

                    // Escribir los detalles del producto como respuesta
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(detallesProducto);
                    out.flush();
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } finally {
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
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
