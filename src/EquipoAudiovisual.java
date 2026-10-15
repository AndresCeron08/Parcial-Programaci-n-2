public abstract class EquipoAudiovisual {
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public CondicionEquipo getCondicion() {
        return condicion;
    }

    public String getModelo() {
        return modelo;
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
    public abstract double calcularCostoPorHora();
}