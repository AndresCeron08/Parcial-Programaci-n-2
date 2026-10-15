public abstract class EquipoAudiovisual {
    public abstract double calcularCostoHora(int horas);
    private String codigoUnico;
    private String marca;
    private String modelo;
    private CondicionEquipo condicion;


    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public CondicionEquipo getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionEquipo condicion) {
        this.condicion = condicion;
    }

    public EquipoAudiovisual (String codigoUnico, String marca, String modelo, CondicionEquipo condicion){
        this.codigoUnico = codigoUnico;
        this.marca = marca;
        this.modelo = modelo;
        this.condicion = condicion;
    }
}