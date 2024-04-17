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

@WebServlet(name = "EliminarProductosServlet", urlPatterns = {"/EliminarProductosServlet"})
public class EliminarProductosServlet extends HttpServlet {

    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/abc";

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del producto a eliminar desde la solicitud
        String idProductoStr = request.getParameter("id");
        if (idProductoStr != null) {
            int idProducto = Integer.parseInt(idProductoStr);

            Connection conexion = null;
            PreparedStatement pstmt = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);

                // Consulta para eliminar el producto
                pstmt = conexion.prepareStatement("DELETE FROM PRODUCTO WHERE Id_Producto = ?");
                pstmt.setInt(1, idProducto);
                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    // Producto eliminado exitosamente
                    response.getWriter().println("Producto eliminado exitosamente");
                } else {
                    // No se encontró el producto con el ID especificado
                    response.getWriter().println("No se encontró ningún producto con el ID especificado");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                // Mostrar mensaje de error
                response.getWriter().println("Error al eliminar el producto");
            } finally {
                // Cerrar recursos
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
        } else {
            // No se proporcionó un ID válido
            response.getWriter().println("ID de producto no válido");
        }
    }
}
