import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<EquipoAudiovisual> listaEquipoAudiovisual;
    private ArrayList<Sede> listaSede;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Operador> listaOperador;
    private ArrayList<Reserva> listaReserva;

    public ArrayList<Sede> getListaSede() {
        return listaSede;
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.listaEquipoAudiovisual = new ArrayList<>();
        this.listaSede = new ArrayList<>();
        this.listaCliente = new ArrayList<>();
        this.listaOperador = new ArrayList<>();
        this.listaReserva = new ArrayList<>();
    }

    public boolean registrarEquipo(EquipoAudiovisual equipo) {
        for (int i = 0; i < listaEquipoAudiovisual.size(); i++) {
            if (listaEquipoAudiovisual.get(i).getCodigoUnico().equals(equipo.getCodigoUnico())) {
                return false;
            }
        }
        listaEquipoAudiovisual.add(equipo);
        return true;
    }

    public boolean registrarCliente(Cliente cliente) {
        listaCliente.add(cliente);
        return true;
    }

    public boolean registrarOperador(Operador operador) {
        listaOperador.add(operador);
        return true;
    }

    public boolean registrarReserva(Reserva reserva) {
        for (int i = 0; i < listaReserva.size(); i++) {
            if (listaReserva.get(i).getCodigoUnico().equals(reserva.getCodigoUnico())) {
                return false;
            }
        }
        listaReserva.add(reserva);
        return true;
    }

    public String consultarEquiposDisponibles(String fecha, Turno turno) {
        String disponibles = "Equipos disponibles:\n";

        for (int i = 0; i < listaEquipoAudiovisual.size(); i++) {
            EquipoAudiovisual eq = listaEquipoAudiovisual.get(i);

            if (eq.getCondicion() == CondicionEquipo.DISPONIBLE) {
                boolean estaOcupado = false;

                for (int j = 0; j < listaReserva.size(); j++) {
                    Reserva res = listaReserva.get(j);

                    if (res.getEstado() == EstadoReserva.CONFIRMADA &&
                            res.getFecha().equals(fecha) &&
                            res.getTurno() == turno) {

                        for (int k = 0; k < res.getListaDetalleReserva().size(); k++) {
                            DetalleReserva dr = res.getListaDetalleReserva().get(k);
                            if (dr.getEquipo().getCodigoUnico().equals(eq.getCodigoUnico())) {
                                estaOcupado = true;
                            }
                        }
                    }
                }

                if (estaOcupado == false) {
                    disponibles += "- " + eq.getCodigoUnico() + " (" + eq.getMarca() + ")\n";
                }
            }
        }
        return disponibles;
    }

    public String consultarReservasCliente(String numIdentificacion) {
        String resultados = "Reservas de " + numIdentificacion + ":\n";

        for (int i = 0; i < listaReserva.size(); i++) {
            Reserva r = listaReserva.get(i);

            if (r.getCliente().getNumIdentificacion().equals(numIdentificacion) &&
                    r.getEstado() == EstadoReserva.CONFIRMADA) {

                resultados += "- Reserva: " + r.getCodigoUnico() + " | Fecha: " + r.getFecha() + "\n";
            }
        }
        return resultados;
    }

    public double consultarValorReserva(String codigoReserva) {
        for (int i = 0; i < listaReserva.size(); i++) {
            if (listaReserva.get(i).getCodigoUnico().equals(codigoReserva)) {
                return listaReserva.get(i).calcularValorTotal();
            }
        }
        return 0.0;
    }

    public ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public ArrayList<Operador> getListaOperador() {
        return listaOperador;
    }

    public ArrayList<EquipoAudiovisual> getListaEquipoAudiovisual() {
        return listaEquipoAudiovisual;
    }

    public ArrayList<Reserva> getListaReserva() {
        return listaReserva;
    }
}

