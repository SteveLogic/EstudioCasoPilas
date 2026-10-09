import java.util.ArrayList;

public class PruebaAnalizador {

    public static void main(String[] args) {

        AnalizadorCadenas analizador = new AnalizadorCadenas();

        String expresion = "\"Hola, \" + nombre + \"!\"";

        ArrayList<Token> tokens = analizador.tokenizar(expresion);

        System.out.println("Expresion: " + expresion);
        System.out.println("Cantidad de tokens: " + tokens.size());

        System.out.println("\nTokens identificados:");

        for (Token token : tokens) {
            System.out.println(
                token.getTipo() + " -> " + token.getLexema()
            );
        }
        System.out.println("\nValidando expresiones:");

        String[] expresiones = {
            "\"Hola\" + nombre",
            "+ nombre",
            "nombre +",
            "\"Hola\" + + nombre",
            "\"Hola\" nombre"
        };

        for (String prueba : expresiones) {

            try {
                ArrayList<Token> resultado = analizador.tokenizar(prueba);
                analizador.validarExpresion(resultado);

                System.out.println("VALIDA: " + prueba);

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "INVALIDA: " + prueba + " -> " + e.getMessage()
                );
            }
        }
        System.out.println("\nPrueba de concatenacion:");

        analizador.registrarVariable("nombre", "Ana");

        String expresionEvaluar = "\"Hola, \" + nombre + \"!\"";

        try {
            String resultado = analizador.evaluar(expresionEvaluar);

            System.out.println("Expresion: " + expresionEvaluar);
            System.out.println("Resultado: " + resultado);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
                
                        

    }
}