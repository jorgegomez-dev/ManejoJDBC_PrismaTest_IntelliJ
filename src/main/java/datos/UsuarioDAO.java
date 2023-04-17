package datos;

import static datos.Conexion.*;
import domain.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {

    /* Variable para poder recibir una conexion desde afuera de esta clase
    Se usa para que la conexion no se cierre cada vez que se termina una Query. En vez de ello,
    queda abierta y se maneja desde afuera de la clase. Esa clase externa va a decidir cuando se hace commit o
    rollback de toda la transaccion. */
    private final Connection conexionTransaction;

    // Querys para accesar a la base de datos (En este caso MySQL)
    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (username, password) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    // Constructor para poder recibir un objeto del tipo Connection desde afuera de la clase


    public UsuarioDAO(Connection conexionTransaction) {
        this.conexionTransaction = conexionTransaction;
    }

    public List<Usuario> select() throws SQLException{

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
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
        } finally {
                close(rs);
                close(ps);
                if(this.conexionTransaction == null){
                    close(conn);
                }
        }
        return usuarios;
    }

    public int insert(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());

            System.out.println("Ejecutando Query..." + SQL_INSERT);
            rows = ps.executeUpdate();
            System.out.println("Registros afectados: " + rows);

        } finally {

                close(ps);
                if(this.conexionTransaction == null){
                    close(conn);
                }
        }
        return rows;
    }

    public int update(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getId_usuario());

            System.out.println("Ejecutando Query..." + SQL_UPDATE);
            rows = ps.executeUpdate();
            System.out.println("Registros actualizados: " + rows);

        } finally {
                close(ps);
                if(this.conexionTransaction == null){
                    close(conn);
                }
        }
        return rows;
    }

    public int delete(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            System.out.println("Ejecutando query..." + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getId_usuario());
            rows = ps.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } finally {

                close(ps);
                if(this.conexionTransaction == null){
                    close(conn);
                }
        }
        return rows;
    }
}

