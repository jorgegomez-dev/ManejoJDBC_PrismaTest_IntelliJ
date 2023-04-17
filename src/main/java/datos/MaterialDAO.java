package datos;

import static datos.Conexion.*;
import domain.Material;
import java.sql.*;
import java.util.*;

public class MaterialDAO {

    /* Variable para poder recibir una conexion desde afuera de esta clase
    Se usa para que la conexion no se cierre cada vez que se termina una Query. En vez de ello,
    queda abierta y se maneja desde afuera de la clase. Esa clase externa va a decidir cuando se hace commit o
    rollback de toda la transaccion. */
    private final Connection conexionTransaction;

    private static final String SQL_SELECT = "SELECT id_material, nombre, precio_placa_900x600, precio_placa_comercial FROM material";
    private static final String SQL_INSERT = "INSERT INTO material(nombre, precio_placa_900x600, precio_placa_comercial) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE material SET nombre = ?, precio_placa_900x600 = ?, precio_placa_comercial = ? WHERE id_material = ?";
    private static final String SQL_DELETE = "DELETE FROM material WHERE id_material = ?";

    public MaterialDAO(Connection conexionTransaction) {
        this.conexionTransaction = conexionTransaction;
    }
    public List<Material> select() throws SQLException{

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Material material = null;
        List<Material> materiales = new ArrayList<Material>();

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            System.out.println("Ejecutando Query..." + SQL_SELECT);

            while (rs.next()) {
                int idMaterial = rs.getInt("id_material");
                String nombre = rs.getString("nombre");
                double precioPlaca_900x600 = rs.getDouble("precio_placa_900x600");
                double precioPlaca_Comercial = rs.getDouble("precio_placa_comercial");

                material = new Material(idMaterial, nombre, precioPlaca_900x600, precioPlaca_Comercial);
                materiales.add(material);
            }
        } finally {
                close(rs);
                close(ps);
                if(this.conexionTransaction==null){
                    close(conn);
                }
        }
        return materiales;
    }

    public int insert(Material material) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, material.getNombre());
            ps.setDouble(2, material.getPrecioPlaca_900x600());
            ps.setDouble(3, material.getPrecioPlaca_Comercial());

            System.out.println("Ejecutando Query..." + SQL_INSERT);
            rows = ps.executeUpdate();
            System.out.println("Registros afectados: " + rows);

        } finally {
                close(ps);
                if(this.conexionTransaction==null){
                    close(conn);
                }
        }
        return rows;
    }

    public int update(Material material) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, material.getNombre());
            ps.setDouble(2, material.getPrecioPlaca_900x600());
            ps.setDouble(3, material.getPrecioPlaca_Comercial());
            ps.setInt(4, material.getIdMaterial());

            System.out.println("Ejecutando Query..." + SQL_UPDATE);
            rows = ps.executeUpdate();
            System.out.println("Registros actualizados: " + rows);

        } finally {
                close(ps);
                if(this.conexionTransaction==null){
                    close(conn);
                }
        }
        return rows;
    }

    public int delete(Material material) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = this.conexionTransaction != null ? this.conexionTransaction : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, material.getIdMaterial());
            rows = ps.executeUpdate();
        } finally {
                close(ps);
                if(this.conexionTransaction == null){
                    close(conn);
                }
        }
        return rows;
    }
}
