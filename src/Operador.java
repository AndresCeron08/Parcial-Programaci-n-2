public class Operador {
    private String nombre;
    private String numIdentificacion;
    private String certificacion;
    private int aniosExperiencia;

    public String getNombre() {
        return nombre;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public Operador(String nombre, String numIdentificacion, String certificacion, int aniosExperiencia) {
        this.nombre = nombre;
        this.numIdentificacion = numIdentificacion;
        this.certificacion = certificacion;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }
}