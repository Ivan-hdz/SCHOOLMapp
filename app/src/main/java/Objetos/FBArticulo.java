package Objetos;

public class FBArticulo {
    String nombre;
    String descripcion;
    String autor;
    String horario;
    String ubicacion;
    String telefono;
    String contacto;
    String imagen;
    Integer cantidad;
    String idioma;
    String impresion;
    //Cambios
    String web;
    //Fin de cambios
    public FBArticulo(){}
    public FBArticulo(String nombre, String descripcion, String autor, String horario, String ubicacion, String telefono, String contacto, String imagen,String web,Integer cantidad,String idioma,String impresion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor=autor;
        this.horario=horario;
        this.ubicacion=ubicacion;
        this.telefono=telefono;
        this.contacto=contacto;
        this.imagen=imagen;
        this.cantidad=cantidad;
        this.idioma=idioma;
        this.impresion=impresion;

        this.web=web;
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

    public String getWeb() {return web;}
    public void setWeb(String web) {
        this.web = web;
    }

    public Integer getCantidad() {return cantidad;}
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdioma() {return idioma;}
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getImpresion() {return impresion;}
    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

}
