import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static AnalizadorCadenas analizador = new AnalizadorCadenas();

    public static void main(String[] args) {
        menu();
        scanner.close();
    }

    public static void menu() {

        int opcion = 0;

        do {
            System.out.println("\n===== ANALIZADOR DE CADENAS =====");
            System.out.println("1. Registrar variable");
            System.out.println("2. Analizar expresion");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarVariable();
                        break;

                    case 2:
                        analizarExpresion();
                        break;

                    case 3:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un numero valido.");
            }

        } while (opcion != 3);
    }

    private static void registrarVariable() {

        System.out.print("Nombre de la variable: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Valor de la variable: ");
        String valor = scanner.nextLine();

        analizador.registrarVariable(nombre, valor);

        System.out.println("Variable registrada correctamente.");
    }

    private static void analizarExpresion() {

        System.out.print("Ingrese la expresion: ");
        String expresion = scanner.nextLine();

        try {
            ArrayList<Token> tokens = analizador.tokenizar(expresion);
            analizador.validarExpresion(tokens);

            System.out.println("\nCantidad de tokens: " + tokens.size());
            System.out.println("Tokens identificados:");

            for (Token token : tokens) {
                System.out.println(
                    token.getTipo() + " -> " + token.getLexema()
                );
            }

            String resultado = analizador.evaluar(expresion);

            System.out.println("\nResultado: " + resultado);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}