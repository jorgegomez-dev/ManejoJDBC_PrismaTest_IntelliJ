package domain;

public class Material {

    private int idMaterial;
    private String nombre;
    private double precioPlaca_900x600;
    private double precioPlaca_Comercial;

    public Material() {
    }

    public Material(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material(String nombre, double precioPlaca_900x600, double precioPlaca_Comercial) {
        this.nombre = nombre;
        this.precioPlaca_900x600 = precioPlaca_900x600;
        this.precioPlaca_Comercial = precioPlaca_Comercial;
    }

    public Material(int idMaterial, String nombre, double precioPlaca_900x600, double precioPlaca_Comercial) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.precioPlaca_900x600 = precioPlaca_900x600;
        this.precioPlaca_Comercial = precioPlaca_Comercial;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public Material setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Material setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public double getPrecioPlaca_900x600() {
        return precioPlaca_900x600;
    }

    public Material setPrecioPlaca_900x600(double precioPlaca_900x600) {
        this.precioPlaca_900x600 = precioPlaca_900x600;
        return this;
    }

    public double getPrecioPlaca_Comercial() {
        return precioPlaca_Comercial;
    }

    public Material setPrecioPlaca_Comercial(double precioPlaca_Comercial) {
        this.precioPlaca_Comercial = precioPlaca_Comercial;
        return this;
    }

    @Override
    public String toString() {
        return "Material{" +
                "idMaterial=" + idMaterial +
                ", nombre='" + nombre + '\'' +
                ", precioPlaca_900x600=" + precioPlaca_900x600 +
                ", precioPlaca_Comercial=" + precioPlaca_Comercial +
                '}';
    }
}
