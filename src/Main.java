import java.util.Scanner;

public class Main {

    // 1. Definición de estados del autómata
    enum Estado {
        S0, S1, S2, ERROR
    }

    // 2. Transiciones del DFA
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
                return Estado.ERROR; // Rechazo permanente
            default:
                return Estado.ERROR;
        }
    }

    // 3. Evaluación de cadena
    static boolean esComandoValido(String cadena) {
        Estado estado = Estado.S0;

        for (char simbolo : cadena.toCharArray()) {
            if (simbolo != '0' && simbolo != '1') {
                return false; // símbolo inválido
            }
            estado = transicion(estado, simbolo);
        }

        // Solo aceptamos si NO terminamos en ERROR
        return estado != Estado.ERROR;
    }

    // 4. Main para probar
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Ingrese: ");
            String entrada = scanner.nextLine();

            if (esComandoValido(entrada)) {
                System.out.println("Aceptado.");
            } else {
                System.out.println("Error(contiene '111').");
            }
        }
    }
}
