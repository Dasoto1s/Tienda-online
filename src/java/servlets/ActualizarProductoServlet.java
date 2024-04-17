package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ActualizarProductoServlet", urlPatterns = {"/ActualizarProductoServlet"})
public class ActualizarProductoServlet extends HttpServlet {

    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/abc";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String genero = request.getParameter("genero");
        int cantidad = Integer.parseInt(request.getParameter("cantidad")); // Nueva cantidad obtenida del formulario

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);

            pstmt = conexion.prepareStatement("UPDATE PRODUCTO SET Nombre = ?, Descripcion = ?, Imagen = ?, Precio = ?, Genero = ?, Cantidad = ? WHERE Id_Producto = ?");
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, imagen);
            pstmt.setDouble(4, precio);
            pstmt.setString(5, genero);
            pstmt.setInt(6, cantidad); // Establecer la nueva cantidad
            pstmt.setInt(7, idProducto);

            int filasActualizadas = pstmt.executeUpdate();

            if (filasActualizadas > 0) {
                response.sendRedirect("editarProducto.jsp?id=" + idProducto);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            try {
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
