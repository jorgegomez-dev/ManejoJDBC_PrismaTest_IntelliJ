import datos.UsuarioDAO;
import domain.Usuario;

import java.util.List;

public class TestManejoUsuarios {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        //1. SELECT: para seleccionar los rows (registros existentes) de Materiales a mostrar
        List<Usuario> usuarios = usuarioDAO.select();
        for (Usuario u : usuarios){
            System.out.println("Usuario: " + u);
        }

        //2. INSERT: para agregar un nuevo row (registro) de Material a la base de datos
        //Usuario usuario = new Usuario( "Empleado.borrar", "1234");
        //UsuarioDAO.insert(usuario);

        //3. UPDATE: para actualizar un row (registro) de Material existente o alguna caracteristica del mismo
        //Usuario usuario = new Usuario(3,"roberto.empleado", "12345");
        //usuarioDAO.update(usuario);

        //4. DELETE: para eliminar (Borrar un registro existente)
        //usuarioDAO.delete(new Usuario(4));
    }
}
