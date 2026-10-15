public class Dron extends EquipoAudiovisual {
    private int autonomia;
    private boolean bateriaAdicional;

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    public boolean isBateriaAdicional() {
        return bateriaAdicional;
    }

    public void setBateriaAdicional(boolean bateriaAdicional) {
        this.bateriaAdicional = bateriaAdicional;
    }

    public Dron(String codigoUnico, String marca, String modelo, CondicionEquipo condicion, int autonomia, boolean bateriaAdicional) {
        super(codigoUnico, marca, modelo, condicion);
        this.autonomia = autonomia;
        this.bateriaAdicional = bateriaAdicional;
    }

    @Override
    public double calcularCostoHora(int horas) {
        double costo = 18000 * horas;
        if (bateriaAdicional) {
            costo += 20000;
        }
        return costo;
    }
}