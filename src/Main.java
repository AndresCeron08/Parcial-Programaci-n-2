import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args) {
    // Inicializamos el sistema gestor
    Empresa pixelRent = new Empresa("PixelRent");
    int opcion = 0;

    // Bucle clásico de menú
    while (opcion != 10) {
      String menu = "PIXELRENT\n\n"
              + "1. Registrar Equipo\n"
              + "2. Registrar Cliente\n"
              + "3. Registrar Operador\n"
              + "4. Registrar Reserva (Inicia Solicitada)\n"
              + "5. Agregar Equipo a Reserva\n"
              + "6. Asignar Operador a Reserva\n"
              + "7. Confirmar Reserva\n"
              + "8. Consultar Equipos Disponibles\n"
              + "9. Consultar Valor Total de Reserva\n"
              + "10. Salir\n\n"
              + "Elija una opción (1-10):";

      String entrada = JOptionPane.showInputDialog(null, menu);

      // Si el usuario presiona cancelar o cierra la ventana, salimos del ciclo
      if (entrada == null) {
        opcion = 10;
      } else {
        opcion = Integer.parseInt(entrada);
      }

      switch (opcion) {
        case 1:
          String txtTipo = "Tipo de equipo a registrar:\n1. Cámara\n2. Dron\n3. Kit de Iluminación";
          int tipo = Integer.parseInt(JOptionPane.showInputDialog(txtTipo));

          String cod = JOptionPane.showInputDialog("Código único del equipo:");
          String marca = JOptionPane.showInputDialog("Marca:");
          String modelo = JOptionPane.showInputDialog("Modelo:");

          EquipoAudiovisual nuevoEquipo = null;

          if (tipo == 1) {
            String res = JOptionPane.showInputDialog("Resolución máxima:");
            int est = Integer.parseInt(JOptionPane.showInputDialog("¿Posee estabilizador? (1 para Sí, 2 para No):"));
            boolean tieneEst = false;
            if (est == 1) {
              tieneEst = true;
            }
            nuevoEquipo = new Camara(cod, marca, modelo, CondicionEquipo.DISPONIBLE, res, tieneEst);

          } else if (tipo == 2) {
            int aut = Integer.parseInt(JOptionPane.showInputDialog("Autonomía de vuelo (minutos):"));
            int bat = Integer.parseInt(JOptionPane.showInputDialog("¿Tiene batería adicional? (1 para Sí, 2 para No):"));
            boolean tieneBat = false;
            if (bat == 1) {
              tieneBat = true;
            }
            nuevoEquipo = new Dron(cod, marca, modelo, CondicionEquipo.DISPONIBLE, aut, tieneBat);

          } else if (tipo == 3) {
            int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de luces:"));
            int inal = Integer.parseInt(JOptionPane.showInputDialog("¿Tiene control inalámbrico? (1 para Sí, 2 para No):"));
            boolean tieneInal = false;
            if (inal == 1) {
              tieneInal = true;
            }
            // Usamos la clase con k minúscula tal como la tienes en tu diagrama
            nuevoEquipo = new kitIluminacion(cod, marca, modelo, CondicionEquipo.DISPONIBLE, cant, tieneInal);
          }

          if (nuevoEquipo != null) {
            boolean guardado = pixelRent.registrarEquipo(nuevoEquipo);
            if (guardado == true) {
              JOptionPane.showMessageDialog(null, "Equipo registrado con éxito.");
            } else {
              JOptionPane.showMessageDialog(null, "Error: Ya existe un equipo con ese código.");
            }
          }
          break;

        case 2:
          String nomCli = JOptionPane.showInputDialog("Nombre completo:");
          String idCli = JOptionPane.showInputDialog("Número de identificación:");
          String telCli = JOptionPane.showInputDialog("Teléfono:");
          String correoCli = JOptionPane.showInputDialog("Correo electrónico:");

          Cliente c = new Cliente(nomCli, idCli, telCli, correoCli);
          pixelRent.registrarCliente(c);
          JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
          break;

        case 3:
          String nomOp = JOptionPane.showInputDialog("Nombre completo del operador:");
          String idOp = JOptionPane.showInputDialog("Número de identificación:");
          String cert = JOptionPane.showInputDialog("Certificación:");
          int exp = Integer.parseInt(JOptionPane.showInputDialog("Años de experiencia:"));

          Operador op = new Operador(nomOp, idOp, cert, exp);
          pixelRent.registrarOperador(op);
          JOptionPane.showMessageDialog(null, "Operador registrado exitosamente.");
          break;

        case 4:
          String idBuscar = JOptionPane.showInputDialog("Ingrese la identificación del Cliente:");
          Cliente clienteEncontrado = null;
          for (int i = 0; i < pixelRent.getListaCliente().size(); i++) {
            if (pixelRent.getListaCliente().get(i).getNumIdentificacion().equals(idBuscar)) {
              clienteEncontrado = pixelRent.getListaCliente().get(i);
            }
          }

          if (clienteEncontrado != null) {
            String codRes = JOptionPane.showInputDialog("Código único de la nueva reserva:");
            String fechaRes = JOptionPane.showInputDialog("Fecha de la reserva (ej. 2026-10-20):");
            int turnoSel = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el turno:\n1. Mañana\n2. Tarde\n3. Noche"));

            Turno t = Turno.MANANA;
            if (turnoSel == 2) {
              t = Turno.TARDE;
            } else if (turnoSel == 3) {
              t = Turno.NOCHE;
            }

            Reserva nuevaReserva = new Reserva(codRes, fechaRes, t, clienteEncontrado);
            boolean resGuardada = pixelRent.registrarReserva(nuevaReserva);

            if (resGuardada == true) {
              JOptionPane.showMessageDialog(null, "Reserva creada. Estado actual: SOLICITADA.");
            } else {
              JOptionPane.showMessageDialog(null, "Error: Ya existe una reserva con ese código.");
            }
          } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado. Debe registrarlo primero.");
          }
          break;

        case 5:
          String codR = JOptionPane.showInputDialog("Ingrese el código de la reserva:");
          Reserva resEncontrada = null;

          for (int i = 0; i < pixelRent.getListaReserva().size(); i++) {
            if (pixelRent.getListaReserva().get(i).getCodigoUnico().equals(codR)) {
              resEncontrada = pixelRent.getListaReserva().get(i);
            }
          }

          if (resEncontrada != null) {
            String codEq = JOptionPane.showInputDialog("Ingrese el código del equipo a agregar:");
            EquipoAudiovisual eqEncontrado = null;

            for (int i = 0; i < pixelRent.getListaEquipoAudiovisual().size(); i++) {
              if (pixelRent.getListaEquipoAudiovisual().get(i).getCodigoUnico().equals(codEq)) {
                eqEncontrado = pixelRent.getListaEquipoAudiovisual().get(i);
              }
            }

            if (eqEncontrado != null) {
              int horas = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de horas de uso:"));
              boolean agregado = resEncontrada.agregarEquipo(eqEncontrado, horas);

              if (agregado == true) {
                JOptionPane.showMessageDialog(null, "Equipo agregado a la reserva correctamente.");
              } else {
                JOptionPane.showMessageDialog(null, "Error: El equipo no está DISPONIBLE o ya está agregado en esta reserva.");
              }
            } else {
              JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
            }
          } else {
            JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
          }
          break;

        case 6:
          String codResOp = JOptionPane.showInputDialog("Ingrese el código de la reserva:");
          Reserva reservaParaOp = null;

          for (int i = 0; i < pixelRent.getListaReserva().size(); i++) {
            if (pixelRent.getListaReserva().get(i).getCodigoUnico().equals(codResOp)) {
              reservaParaOp = pixelRent.getListaReserva().get(i);
            }
          }

          if (reservaParaOp != null) {
            String idOpBuscar = JOptionPane.showInputDialog("Ingrese la identificación del Operador:");
            Operador operadorEncontrado = null;

            for (int i = 0; i < pixelRent.getListaOperador().size(); i++) {
              if (pixelRent.getListaOperador().get(i).getNumIdentificacion().equals(idOpBuscar)) {
                operadorEncontrado = pixelRent.getListaOperador().get(i);
              }
            }

            if (operadorEncontrado != null) {
              reservaParaOp.asignarOperador(operadorEncontrado);
              JOptionPane.showMessageDialog(null, "Operador asignado correctamente a la reserva.");
            } else {
              JOptionPane.showMessageDialog(null, "Operador no encontrado.");
            }
          } else {
            JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
          }
          break;

        case 7:
          String resConfirmar = JOptionPane.showInputDialog("Ingrese el código de la reserva a confirmar:");
          boolean encontrada = false;

          for (int i = 0; i < pixelRent.getListaReserva().size(); i++) {
            if (pixelRent.getListaReserva().get(i).getCodigoUnico().equals(resConfirmar)) {
              encontrada = true;
              // Llamamos al método de confirmar pasándole la lista de todas las reservas para que valide
              boolean seConfirmo = pixelRent.getListaReserva().get(i).confirmarReserva(pixelRent.getListaReserva());

              if (seConfirmo == true) {
                JOptionPane.showMessageDialog(null, "¡Reserva CONFIRMADA exitosamente!");
              } else {
                JOptionPane.showMessageDialog(null, "No se puede confirmar. Posibles razones:\n- No tiene equipos.\n- Tiene un Dron pero no tiene operador.\n- Un equipo está ocupado en otra reserva ese mismo día y turno.");
              }
            }
          }
          if (encontrada == false) {
            JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
          }
          break;

        case 8:
          String fBuscar = JOptionPane.showInputDialog("Ingrese la fecha a consultar (ej. 2026-10-20):");
          int tBuscarSel = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el turno a consultar:\n1. Mañana\n2. Tarde\n3. Noche"));

          Turno turnoB = Turno.MANANA;
          if (tBuscarSel == 2) turnoB = Turno.TARDE;
          if (tBuscarSel == 3) turnoB = Turno.NOCHE;

          String disponibles = pixelRent.consultarEquiposDisponibles(fBuscar, turnoB);
          JOptionPane.showMessageDialog(null, disponibles);
          break;

        case 9:
          String resCosto = JOptionPane.showInputDialog("Ingrese el código de la reserva para calcular su valor:");
          double valor = pixelRent.consultarValorReserva(resCosto);
          JOptionPane.showMessageDialog(null, "El valor total de la reserva es: $" + valor);
          break;

        case 10:
          JOptionPane.showMessageDialog(null, "Saliendo del sistema PixelRent... ¡Hasta pronto!");
          break;

        default:
          JOptionPane.showMessageDialog(null, "Opción incorrecta. Por favor ingrese un número del 1 al 10.");
          break;
      }
    }
  }
}