public class kitIluminacion extends EquipoAudiovisual {
    private int cantidadLuces;
    private boolean controlInalam;

    public int getCantidadLuces() {
        return cantidadLuces;
    }

    public void setCantidadLuces(int cantidadLuces) {
        this.cantidadLuces = cantidadLuces;
    }

    public boolean isControlInalam() {
        return controlInalam;
    }

    public void setControlInalam(boolean controlInalam) {
        this.controlInalam = controlInalam;
    }

    public kitIluminacion(String codigoUnico, String marca, String modelo, CondicionEquipo condicion, int cantidadLuces, boolean controlInalam) {
        super(codigoUnico, marca, modelo, condicion);
        this.cantidadLuces = cantidadLuces;
        this.controlInalam = controlInalam;
    }

    @Override
    public double calcularCostoHora(int horas) {
        double costo = 10000 * horas;
        if (controlInalam) {
            costo += 12000;
        }
        return costo;
    }
}