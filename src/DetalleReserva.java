public class DetalleReserva {
    private int horasDeUsoPorEquipo;
    private EquipoAudiovisual equipo;



    public int getHorasDeUsoPorEquipo() {
        return horasDeUsoPorEquipo;
    }

    public EquipoAudiovisual getEquipo() {
        return equipo;
    }

    public DetalleReserva(int horasDeUsoPorEquipo, EquipoAudiovisual equipo) {
        this.horasDeUsoPorEquipo = horasDeUsoPorEquipo;
        this.equipo = equipo;
    }

    public double calcularCostoSubtotal() {
        return equipo.calcularCostoHora(horasDeUsoPorEquipo);
    }
}