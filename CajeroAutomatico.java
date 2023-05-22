import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CajeroAutomatico {
    private static double saldo = 10000; // Saldo inicial
    private static int intentos = 3; // Número de intentos permitidos
    private static String ultimoMovimiento = ""; // Último movimiento registrado
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        realizarTransacciones();
    }


    // Muestra el menú de opciones
    private static void mostrarMenu() {
        System.out.println("1.Retirar dinero");
        System.out.println("2.Hacer depósitos");
        System.out.println("3.Estado de cuenta");
        System.out.println("4.Quejas");
        System.out.println("5.Último movimiento");
        System.out.println("6.Hablar con un asesor");
        System.out.println("7.Salir del cajero");
    }

    // Ejecuta las opciones del usuario
    private static void realizarTransacciones() {
        while (intentos > 0) {
            System.out.println("-----------------------------------------");
            mostrarMenu();
            System.out.println("Ingresa una opción");
            int opcion = scanner.nextInt();

            if (!(opcion >= 1 && opcion <= 7)) {
                System.out.println("No ingresaste una opción válida");
                intentos--;
            } else {
                intentos = 3;
                procesarOpcion(opcion);
            }
        }
    }

    // Procesa la opción seleccionada por el usuario
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> retirarDinero();
            case 2 -> hacerDepositos();
            case 3 -> mostrarEstadoCuenta();
            case 4, 7 -> {
                mostrarUltimoMovimiento();
                intentos = 0;
            }
            case 5 -> mostrarUltimoMovimiento();
            case 6 -> hablarConAsesor();
        }
    }

    // Retira dinero de la cuenta
    private static void retirarDinero() {
        System.out.println("Su saldo actual es: " + saldo);
        System.out.println("Ingrese monto a retirar");
        int retiro = scanner.nextInt();

        if (retiro > saldo || retiro > 6000) {
            System.out.println("No se pudo realizar la transacción. El monto excede el límite de 6000 o excede su saldo disponible");
        } else if (retiro % 50 == 0 || retiro % 100 == 0) {
            saldo -= retiro;
            ultimoMovimiento = obtenerFechaHoraActual() + " Retiro de " + retiro;
            System.out.println("Transacción correcta, su nuevo saldo es: " + saldo);
        } else {
            System.out.println("Monto no válido");
        }
    }

    // Realiza depósitos en la cuenta
    private static void hacerDepositos() {
        System.out.println("1.Cuenta de cheques");
        System.out.println("2.Tarjeta de crédito");
        System.out.println("3.Cuenta de terceros");
        int subopcion = scanner.nextInt();

        switch (subopcion) {
            case 1 -> depositarEnCuentaCheques();
            case 2 -> realizarPagoTarjetaCredito();
            case 3 -> realizarPagoCuentaTerceros();
        }
    }

    // Realiza un depósito en la cuenta de cheques
    private static void depositarEnCuentaCheques() {
        System.out.println("Ingrese el monto a depositar de su cheque");
        double deposito = scanner.nextInt();

        if (deposito % 50 == 0 || deposito % 100 == 0) {
            saldo += deposito;
            ultimoMovimiento = obtenerFechaHoraActual() + " Depósito de " + deposito;
            System.out.println("Su depósito ha sido exitoso, su nuevo saldo es: " + saldo);
        } else {
            System.out.println("Monto no válido");
        }
    }

    // Realiza el pago con tarjeta de crédito
    private static void realizarPagoTarjetaCredito() {
        if (saldo >= 200.10) {
            saldo -= 200.10;
            System.out.println("Se hizo un pago de 200.10, su nuevo saldo es: " + saldo);
        } else {
            System.out.println("Su saldo no es suficiente para realizar la transacción");
        }
    }

    // Realiza el pago a cuenta de terceros
    private static void realizarPagoCuentaTerceros() {
        if (saldo >= 666.22) {
            saldo -= 666.22;
            System.out.println("Se hizo un pago de 666.22, su nuevo saldo es: " + saldo);
        } else {
            System.out.println("Su saldo no es suficiente para realizar la transacción");
        }
    }

    // Muestra el estado de cuenta
    private static void mostrarEstadoCuenta() {
        System.out.println("Su saldo disponible es de: " + saldo);
    }

    // Muestra el último movimiento registrado
    private static void mostrarUltimoMovimiento() {
        if (!ultimoMovimiento.isEmpty()) {
            System.out.println(ultimoMovimiento);
        } else {
            System.out.println("No tienes retiros realizados aún");
        }
    }

    // Habla con un asesor
    private static void hablarConAsesor() {
        System.out.println("No es horario de atención");
    }

    // Obtiene la fecha y hora actual
    private static String obtenerFechaHoraActual() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return fechaHoraActual.format(formateador);
    }
}
