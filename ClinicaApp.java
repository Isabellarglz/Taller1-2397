import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Gestión de Turnos - Clínica San Rafael
 *
 * Cada turno se representa como un arreglo de String de 5 posiciones:
 * turno = [idTurno, nombrePaciente, especialidad, duracionMinutos, valorMinuto]
 *
 * Todos los turnos se almacenan en: ArrayList<String[]> turnos
 *
 * IMPORTANTE: como todo se guarda como texto, los datos numéricos deben
 * convertirse con Integer.parseInt(...) o Double.parseDouble(...) al usarlos.
 */
public class ClinicaApp {

    // ====== Índices de cada campo (usarlos SIEMPRE en lugar de 0,1,2...) ======
    static final int ID = 0;
    static final int PACIENTE = 1;
    static final int ESPECIALIDAD = 2;
    static final int DURACION = 3;
    static final int VALOR_MINUTO = 4;
    static final int CAMPOS = 5;

    static ArrayList<String[]> turnos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 : registrarTurno();
                case 2 : mostrarTurnos();
                case 3 : buscarTurnoPorId();
                case 4 : actualizarTurno();
                case 5 : cancelarTurno();
                case 6 : calcularTotalFacturado();
                case 7 : reportePorEspecialidad();
                case 8 : System.out.println("Cerrando el sistema. Hasta pronto.");
                default : System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 8);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("=== Clínica San Rafael: Gestión de Turnos ===");
        System.out.println("1. Registrar nuevo turno");
        System.out.println("2. Mostrar todos los turnos");
        System.out.println("3. Buscar turno por ID");
        System.out.println("4. Actualizar un turno");
        System.out.println("5. Cancelar un turno");
        System.out.println("6. Calcular total facturado");
        System.out.println("7. Reporte por especialidad");
        System.out.println("8. Salir");
    }

    // ================= ROL A: feature/menu-base =================
    // Responsable de: mostrarMenu (ya dado), registrarTurno, mostrarTurnos

    static void registrarTurno() {
      String id = leerTexto("ID del turno: ");

    if (buscarIndicePorId(id) != -1) {
        System.out.println("Ya existe un turno con ese ID.");
        return;
    }

    String paciente = leerTexto("Nombre del paciente: ");
    String especialidad = leerTexto("Especialidad: ");
    int duracion = leerEntero("Duración en minutos: ");
    double valorMinuto = leerDecimal("Valor por minuto: ");

    String[] turno = new String[CAMPOS];
    turno[ID] = id;
    turno[PACIENTE] = paciente;
    turno[ESPECIALIDAD] = especialidad;
    turno[DURACION] = String.valueOf(duracion);
    turno[VALOR_MINUTO] = String.valueOf(valorMinuto);

    turnos.add(turno);
    System.out.println("Turno registrado con éxito.");
    }

    static void mostrarTurnos() {
       if (turnos.isEmpty()) {
        System.out.println("No hay turnos registrados.");
        return;
    }
       System.out.printf("%-6s %-20s %-15s %8s %12s%n",
            "ID", "Paciente", "Especialidad", "Duración", "Valor/min");

    for (String[] turno : turnos) {
        System.out.printf("%-6s %-20s %-15s %8s %12s%n",
                turno[ID], turno[PACIENTE], turno[ESPECIALIDAD],
                turno[DURACION], turno[VALOR_MINUTO]);
    }

    }

    // ================= ROL B: feature/crud-turnos =================
    // Responsable de: buscarTurnoPorId, actualizarTurno, cancelarTurno, buscarIndicePorId

    static int buscarIndicePorId(String id) {
        // TODO (Rol B)
        // Recorrer la lista y devolver la POSICIÓN del turno cuyo ID coincida.
        // Si no existe, devolver -1. Este método lo reutilizan los demás roles.
        return -1;
    }

    static void buscarTurnoPorId() {
        // TODO (Rol B)
        // Pedir el ID, usar buscarIndicePorId y mostrar los datos o un mensaje de "no existe".
    }

    static void actualizarTurno() {
        // TODO (Rol B)
        // Pedir el ID, verificar que exista y mostrar un submenú para elegir
        // qué campo modificar: paciente, especialidad, duración o valor por minuto.
    }

    static void cancelarTurno() {
        // TODO (Rol B)
        // Pedir el ID, verificar que exista, pedir confirmación (S/N) y eliminar
        // con turnos.remove(indice);
    }

    // ============ ROL C: feature/calculos-validaciones ============
    // Responsable de: calcularTotalFacturado, reportePorEspecialidad, validaciones

    static void calcularTotalFacturado() {

        Double Total = 0;

        for (String[] turno : turnos) {

            Double Subtotal = Double.parseDouble(turno[DURACION]) * Double.parseDouble(turno[VALOR_MINUTO]);
            System.out.print("El valor total del turno facturado es: $"+Subtotal);

            Total += Subtotal;
        }
        
        System.out.print("El valor total de todos los turno acumulados facturados es:$"+Total);
    }

    static void reportePorEspecialidad() {

        if (turnos.isEmpty()) {

            System.out.println("No hay turnos registrados.");
            return;
        }

        String especialidadBuscada = leerTexto("Ingrese la especialidad: ");

        int cantidad = 0;
        int sumaDuracion = 0;

        System.out.println("\nTurnos de la especialidad " + especialidadBuscada + ":");

        for (String[] turno : turnos) {

            if (turno[ESPECIALIDAD].equalsIgnoreCase(especialidadBuscada)) {

                System.out.println("ID: " + turno[ID]);

                cantidad++;
                sumaDuracion += Integer.parseInt(turno[DURACION]);
            }
        }  

        if (cantidad == 0) {

            System.out.println("No existen turnos para esa especialidad.");

        } else {
            
            double promedio = (double) sumaDuracion / cantidad;

            System.out.println("Cantidad de turnos: " + cantidad);
            System.out.println("Promedio de duración: " + promedio + " minutos");
        }
    }

    // ====== Utilidades (ya implementadas, no es necesario modificarlas) ======

    static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    static double leerDecimal(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (Exception e) {
                System.out.println("Ingrese un número válido (ej: 1500.50).");
            }
        }
    }

    static String leerTexto(String msg) {
        String valor;
        do {
            System.out.print(msg);
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) System.out.println("Este campo no puede quedar vacío.");
        } while (valor.isEmpty());
        return valor;
    }
}
