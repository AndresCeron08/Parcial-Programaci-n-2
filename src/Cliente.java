public class Cliente {
    private String nombre;
    private String numIdentifiacion;
    private String telefono;
    private String correo;


    public String getNombre() {
        return nombre;
    }

    public String getNumIdentifiacion() {
        return numIdentifiacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public Cliente (String nombre, String numIdentifiacion, String telefono, String correo) {
        this.nombre = nombre;
        this.numIdentifiacion = numIdentifiacion;
        this.telefono = telefono;
        this.correo = correo;
    }

}