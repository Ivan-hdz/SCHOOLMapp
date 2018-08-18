package Objetos;

public class ObjectRecyclerView {
    private String nombre;
    private String descripcion;


    public ObjectRecyclerView(){ }
    public ObjectRecyclerView(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
