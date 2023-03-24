package datos;

import static datos.Conexion.*;
import domain.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {

    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (username, password) VALUES (?, ?)";

    private static final String SQL_UPDATE = "UPDATE usuario SET username = ?, password = ? WHERE id_usuario = ?";

    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public List<Usuario> select() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            System.out.println("Ejecutando Query..." + SQL_SELECT);

            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                usuario = new Usuario (id_usuario, username, password);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(rs);
                close(ps);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    public static int insert(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());

            System.out.println("Ejecutando Query..." + SQL_INSERT);
            rows = ps.executeUpdate();
            System.out.println("Registros afectados: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }

    public static int update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getId_usuario());

            System.out.println("Ejecutando Query..." + SQL_UPDATE);
            rows = ps.executeUpdate();
            System.out.println("Registros actualizados: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }

    public static int delete(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = getConnection();
            System.out.println("Ejecutando query..." + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getId_usuario());
            rows = ps.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;

    }

}

