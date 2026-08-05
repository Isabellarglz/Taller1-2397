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
                case 1 -> registrarTurno();
                case 2 -> mostrarTurnos();
                case 3 -> buscarTurnoPorId();
                case 4 -> actualizarTurno();
                case 5 -> cancelarTurno();
                case 6 -> calcularTotalFacturado();
                case 7 -> reportePorEspecialidad();
                case 8 -> System.out.println("Cerrando el sistema. Hasta pronto.");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
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
        // TODO (Rol A)
        // 1. Pedir id, paciente, especialidad, duración y valor por minuto.
        // 2. Validar que el ID no exista ya (usar buscarIndicePorId).
        // 3. Crear el arreglo: String[] turno = new String[CAMPOS];
        // 4. Agregarlo a la lista con turnos.add(turno);
    }

    static void mostrarTurnos() {
        // TODO (Rol A)
        // Si la lista está vacía, avisar al usuario.
        // Recorrer la lista e imprimir cada turno en formato tabular y legible.
        // Sugerencia: System.out.printf("%-6s %-20s %-15s %8s %12s%n", ...);
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
        // TODO (Rol C)
        // Para cada turno: duracionMinutos * valorMinuto.
        // Mostrar el subtotal de cada turno y el gran total al final.
        // Recuerde convertir el texto a número antes de operar.
    }

    static void reportePorEspecialidad() {
        // TODO (Rol C)
        // Pedir una especialidad y mostrar solo los turnos de esa especialidad,
        // junto con la cantidad de turnos y el promedio de duración en minutos.
        // Comparar con equalsIgnoreCase para no depender de mayúsculas.
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
