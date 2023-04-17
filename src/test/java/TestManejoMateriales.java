import datos.MaterialDAO;
import domain.Material;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static datos.Conexion.getConnection;

public class TestManejoMateriales {
    public static void main(String[] args) {

        // Declaramos un objeto del tipo Connection de la clase sql.Connection para pasarselo luego a la clase Usuario
        Connection conexion = null;

        try{
            // Llamamos al metodo getConnection de nuestra clase Conexion del paquete Datos
            conexion = getConnection();
            // Verificamos que AutoCommit este desactivado. Si al solicitarlo el if evalua que es "true", lo ponemos en "false"
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            //Inicio-------Dentro de este bloque colocamos las instrucciones para la base de datos--------//
            MaterialDAO materialDao = new MaterialDAO(conexion); //Creamos un objeto MaterialDao, para acceder a Material y le pasamos la conexion

            //1. Insert (insertamos nuevo Material)
            //Material insertMaterial = new Material();
            //insertMaterial.setNombre("MDF 5,5mm");
            //insertMaterial.setPrecioPlaca_900x600(1500);
            //insertMaterial.setPrecioPlaca_Comercial(8500);
            //materialDao.insert(insertMaterial);

            //2. Update (actualizar Material)
            Material updateMaterial = new Material();
            updateMaterial.setIdMaterial(2);
            updateMaterial.setNombre("MDF 2mm");
            updateMaterial.setPrecioPlaca_900x600(650);
            updateMaterial.setPrecioPlaca_Comercial(4800);
            materialDao.update(updateMaterial);

            //3. Delete (borrar Material)
            //Material deleteMaterial = new Material();
            //deleteMaterial.setIdMaterial(9);
            //materialDao.delete(deleteMaterial);

            //4. Select (lista de todos los rows persistidos en la base de datos)
            List<Material> listaMateriales = materialDao.select();
            for (Material material : listaMateriales){
                System.out.println("Material: " + material);
            }

            //Fin-------Dentro de este bloque colocamos las instrucciones para la base de datos--------//

            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion.");

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Entramos al rollback...");
            try{
                conexion.rollback();
            } catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }

    }
}
