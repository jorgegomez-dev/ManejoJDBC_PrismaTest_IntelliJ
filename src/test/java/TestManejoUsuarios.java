import datos.UsuarioDAO;
import domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static datos.Conexion.getConnection;

public class TestManejoUsuarios {
    public static void main(String[] args) {

        // Declaramos un objeto del tipo Connection de la clase sql.Connection para pasarselo luego a la clase Usuario
        Connection conexion = null;

        try {
            // Llamamos al metodo getConnection de nuestra clase Conexion del paquete Datos
            conexion = getConnection();
            // Verificamos que AutoCommit este desactivado. Si al solicitarlo el if evalua que es "true", lo ponemos en "false"
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            //Inicio-------Dentro de este bloque colocamos las instrucciones para la base de datos--------//
            UsuarioDAO usuarioDao = new UsuarioDAO(conexion);  //Creamos un UsuarioDao que vamos a usar para accesar a Usuario y le pasamos la conexion

                //1. Insert (insertamos nuevo Usuario)
                //Usuario insertUsuario = new Usuario();
                //insertUsuario.setUsername("Pepe");
                //insertUsuario.setPassword("1234");
                //usuarioDao.insert(insertUsuario); // Instruccion para persistir al usuario en base de datos

                //2. Update (actualizar Usuario)
                //Usuario updateUsuario = new Usuario();
                //updateUsuario.setId_usuario(5);
                //updateUsuario.setUsername("Pepito");
                //updateUsuario.setPassword("4567");
                //usuarioDao.update(updateUsuario);

                //3. Delete (borrar Usuario)
                //Usuario deleteUsuario = new Usuario(5); //Le paso el id del usuario a borrar
                //usuarioDao.delete(deleteUsuario);

                //4. Select (lista de todos los rows persistidos en la base de datos)
                List<Usuario> listaUsuarios = usuarioDao.select();
                for (Usuario usuario : listaUsuarios){
                    System.out.println("Usuario: " + usuario);
                }

            //Fin-------Dentro de este bloque colocamos las instrucciones para la base de datos--------//

            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Entramos al rollback.");
            try{
                conexion.rollback();
            } catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
    }
}
