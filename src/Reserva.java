import java.util.ArrayList;

public class Reserva {
    private String codigoUnico;
    private String fecha;
    private Turno turno;
    private EstadoReserva estado;
    private Cliente cliente;
    private Operador operador;
    private ArrayList<DetalleReserva> listaDetalleReserva;

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getFecha() {
        return fecha;
    }

    public Turno getTurno() {
        return turno;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetalleReserva> getListaDetalleReserva() {
        return listaDetalleReserva;
    }

    public Reserva(String codigoUnico, String fecha, Turno turno, Cliente cliente) {
        this.codigoUnico = codigoUnico;
        this.fecha = fecha;
        this.turno = turno;
        this.cliente = cliente;
        this.estado = EstadoReserva.SOLICITADA;
        this.listaDetalleReserva = new ArrayList<>();
    }



    public boolean agregarEquipo(EquipoAudiovisual equipo, int horas) {
        if (equipo.getCondicion() != CondicionEquipo.DISPONIBLE) {
            return false;
        }

        for (int i = 0; i < listaDetalleReserva.size(); i++) {
            if (listaDetalleReserva.get(i).getEquipo().getCodigoUnico().equals(equipo.getCodigoUnico())) {
                return false;
            }
        }

        listaDetalleReserva.add(new DetalleReserva(horas, equipo));
        return true;
    }

    public boolean asignarOperador(Operador operador) {
        this.operador = operador;
        return true;
    }

    public boolean confirmarReserva(ArrayList<Reserva> reservasGlobales) {
        if (estado == EstadoReserva.CANCELADA || estado == EstadoReserva.FINALIZADA) {
            return false;
        }

        if (listaDetalleReserva.size() == 0) {
            return false;
        }

        boolean tieneDron = false;

        for (int i = 0; i < listaDetalleReserva.size(); i++) {
            DetalleReserva dr = listaDetalleReserva.get(i);

            if (dr.getEquipo() instanceof Dron) {
                tieneDron = true;
            }

            for (int j = 0; j < reservasGlobales.size(); j++) {
                Reserva otraReserva = reservasGlobales.get(j);

                if (otraReserva.getEstado() == EstadoReserva.CONFIRMADA &&
                        otraReserva.getFecha().equals(this.fecha) &&
                        otraReserva.getTurno() == this.turno &&
                        !otraReserva.getCodigoUnico().equals(this.codigoUnico)) {

                    for (int k = 0; k < otraReserva.getListaDetalleReserva().size(); k++) {
                        DetalleReserva drOtro = otraReserva.getListaDetalleReserva().get(k);
                        if (drOtro.getEquipo().getCodigoUnico().equals(dr.getEquipo().getCodigoUnico())) {
                            return false;
                        }
                    }
                }
            }
        }

        if (tieneDron == true && this.operador == null) {
            return false;
        }

        this.estado = EstadoReserva.CONFIRMADA;
        return true;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < listaDetalleReserva.size(); i++) {
            total += listaDetalleReserva.get(i).calcularCostoSubtotal();
        }
        return total;
    }
}