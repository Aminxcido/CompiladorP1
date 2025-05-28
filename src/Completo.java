import java.util.Scanner;

public class Completo {

    // estados del autómata
    enum Estado {
        S0, S1, S2, ERROR
    }

    // Transiciones del DFA
    static Estado transicion(Estado estadoActual, char simbolo) {
        switch (estadoActual) {
            case S0:
                return simbolo == '0' ? Estado.S0 :
                        simbolo == '1' ? Estado.S1 : Estado.ERROR;
            case S1:
                return simbolo == '0' ? Estado.S0 :
                        simbolo == '1' ? Estado.S2 : Estado.ERROR;
            case S2:
                return simbolo == '0' ? Estado.S0 :
                        simbolo == '1' ? Estado.ERROR : Estado.ERROR;
            case ERROR:
                return Estado.ERROR;
            default:
                return Estado.ERROR;
        }
    }

    // impresión de estados
    static boolean esComandoValido(String cadena) {
        Estado estado = Estado.S0;
        System.out.print("Ruta de estados: " + estado);

        for (char simbolo : cadena.toCharArray()) {
            if (simbolo != '0' && simbolo != '1') {
                System.out.println(" → símbolo inválido '" + simbolo + "'");
                return false;
            }
            estado = transicion(estado, simbolo);
            System.out.print(" → " + estado);
        }
        System.out.println();

        return estado != Estado.ERROR;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nIngrese cadena: ");
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("salir")) break;

            if (esComandoValido(entrada)) {
                System.out.println("Aceptado.");
            } else {
                System.out.println("Rechazado (contiene '111').");
            }
        }
    }
}
