package Objetos;

public class Librox {
    String coleccion;
    String nombre;
    String autor;
    String codigo;
    String clasificacion;
    String isbn;
    String editorial;
    String material;
    String imagen;
    String ioyam;
    public Librox(){}
    public Librox(String coleccion,String nombre, String autor, String codigo, String clasificacion,String isbn,String editorial, String material, String imagen, String ioyam){
        this.coleccion=coleccion;
        this.nombre=nombre;
        this.autor=autor;
        this.codigo=codigo;
        this.clasificacion=clasificacion;
        this.isbn=isbn;
        this.editorial=editorial;
        this.material=material;
        this.imagen=imagen;
        this.ioyam=ioyam;
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIoyam() {
        return ioyam;
    }

    public void setIoyam(String ioyam) {
        this.ioyam = ioyam;
    }
}
