public class Camara extends EquipoAudiovisual {
    private String resolucion;
    private boolean poseeEstabilizador;

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public boolean isPoseeEstabilizador() {
        return poseeEstabilizador;
    }

    public void setPoseeEstabilizador(boolean poseeEstabilizador) {
        this.poseeEstabilizador = poseeEstabilizador;
    }

    public Camara (String resolucion, boolean poseeEstabilizador, String codigoUnico, String marca, String modelo, CondicionEquipo condicion){
        super (codigoUnico, marca, modelo, condicion);
        this.resolucion = resolucion;
        this.poseeEstabilizador = poseeEstabilizador;
    }
}
