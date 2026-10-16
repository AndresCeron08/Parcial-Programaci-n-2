public class Cliente {
    private String nombre;
    private String numIdentificacion;
    private String telefono;
    private String correo;


    public String getNombre() {
        return nombre;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public Cliente (String nombre, String numIdentificacion, String telefono, String correo) {
        this.nombre = nombre;
        this.numIdentificacion = numIdentificacion;
        this.telefono = telefono;
        this.correo = correo;
    }

}