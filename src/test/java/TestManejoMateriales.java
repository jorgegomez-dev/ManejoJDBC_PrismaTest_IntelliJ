import datos.MaterialDAO;
import domain.Material;

import java.util.List;

public class TestManejoMateriales {
    public static void main(String[] args) {
        MaterialDAO materialDao = new MaterialDAO();

        // 1. SELECT: para seleccionar los rows (registros existentes) de Materiales a mostrar
        List<Material> materiales = materialDao.select();
        for (Material material : materiales) {
            System.out.println("Material: " + material);
        }

        //2. INSERT: para agregar un nuevo row (registro) de Material a la base de datos
        Material material = new Material("MDF 3mm", 1200, 1200);
        MaterialDAO.insert(material);

        //3. UPDATE: para actualizar un row (registro) de Material existente o alguna caracteristica del mismo
//        Material material = new Material(3,"MDF 2mm", 1400, 1400);
//        materialDao.update(material);


        //4. DELETE: para eliminar (Borrar un registro existente)
       //MaterialDAO.delete(new Material(4));

    }
}
