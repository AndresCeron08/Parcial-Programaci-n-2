import java.util.ArrayList;

public class Sede {
    private String codigo;
    private String nombre;
    private String direccion;
    private ArrayList<EquipoAudiovisual> listaEquipoAudiovisual;

    public ArrayList<EquipoAudiovisual> getListaEquipoAudiovisual() {
        return listaEquipoAudiovisual;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Sede(String codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaEquipoAudiovisual = new ArrayList<>();
    }
}