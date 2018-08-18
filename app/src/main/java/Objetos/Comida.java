package Objetos;

public class Comida {
    String nombre;
    String descripcion;
    String autor;
    String horario;
    String ubicacion;
    String telefono;
    String contacto;
    String imagen;
    public Comida(){}
    public Comida(String nombre, String descripcion,String autor,String horario, String ubicacion, String telefono, String contacto, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor=autor;
        this.horario=horario;
        this.ubicacion=ubicacion;
        this.telefono=telefono;
        this.contacto=contacto;
        this.imagen=imagen;
    }

    public String getNombre() {
        return nombre;
    }
    public String getAutor(){return autor;}
    public void setAutor(){this.autor=autor;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {return horario;}
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getUbicacion() {return ubicacion;}
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContacto() {return contacto;}
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
