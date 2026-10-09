import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class AnalizadorCadenas {
    private Map<String, String> variables = new HashMap<>();

    // Registra o actualiza una variable de cadena.
    public void registrarVariable(String nombre, String valor) {
        variables.put(nombre, valor);
    }

    public ArrayList<Token> tokenizar(String expresion) {

        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < expresion.length()) {

            char caracter = expresion.charAt(i);

            // Ignorar espacios fuera de las comillas.
            if (Character.isWhitespace(caracter)) {
                i++;
                continue;
            }

            // Reconocer cadenas literales.
            if (caracter == '"') {

                int inicio = i;
                i++;

                while (i < expresion.length()
                        && expresion.charAt(i) != '"') {
                    i++;
                }

                if (i >= expresion.length()) {
                    throw new IllegalArgumentException(
                        "Error: cadena sin cerrar."
                    );
                }

                String lexema = expresion.substring(inicio, i + 1);
                String valor = expresion.substring(inicio + 1, i);

                tokens.add(new Token(
                    "LITERAL_CADENA", lexema, valor
                ));

                i++;
                continue;
            }

            // Reconocer operadores de concatenación.
            if (caracter == '+') {

                tokens.add(new Token(
                    "CONCATENAR", "+", "+"
                ));

                i++;
                continue;
            }

            // Reconocer nombres de variables.
            if (Character.isLetter(caracter) || caracter == '_') {

                int inicio = i;

                while (i < expresion.length()
                        && (Character.isLetterOrDigit(expresion.charAt(i))
                        || expresion.charAt(i) == '_')) {
                    i++;
                }

                String nombre = expresion.substring(inicio, i);

                tokens.add(new Token(
                    "VARIABLE_CADENA", nombre, null
                ));

                continue;
            }

            // Detectar caracteres no permitidos.
            throw new IllegalArgumentException(
                "Carácter no permitido: " + caracter
            );
        }

        return tokens;
    }
        // Verifica que los tokens estén en el orden correcto.
    public void validarExpresion(ArrayList<Token> tokens) {

        if (tokens.isEmpty()) {
            throw new IllegalArgumentException(
                "Error: la expresion esta vacia."
            );
        }

        boolean esperaOperando = true;

        for (Token token : tokens) {

            String tipo = token.getTipo();

            if (esperaOperando) {

                if (!tipo.equals("LITERAL_CADENA")
                        && !tipo.equals("VARIABLE_CADENA")) {

                    throw new IllegalArgumentException(
                        "Error: se esperaba una cadena o variable."
                    );
                }

                esperaOperando = false;

            } else {

                if (!tipo.equals("CONCATENAR")) {

                    throw new IllegalArgumentException(
                        "Error: se esperaba el operador +."
                    );
                }

                esperaOperando = true;
            }
        }

        if (esperaOperando) {
            throw new IllegalArgumentException(
                "Error: la expresion termina con un operador."
            );
        }
    }
        // Evalúa una expresión de concatenación utilizando pilas.
        public String evaluar(String expresion) {

            ArrayList<Token> tokens = tokenizar(expresion);
            validarExpresion(tokens);

            ArrayList<Token> postfija = new ArrayList<>();
            Pila operadores = new Pila();

            // Convertir la expresión infija a postfija.
            for (Token token : tokens) {

                if (token.getTipo().equals("CONCATENAR")) {

                    while (!operadores.isEmpty()) {
                        postfija.add(operadores.pop());
                    }

                    operadores.push(token);

                } else {
                    postfija.add(token);
                }
            }

            while (!operadores.isEmpty()) {
                postfija.add(operadores.pop());
            }

            // Evaluar la expresión postfija.
            Pila operandos = new Pila();

            for (Token token : postfija) {

                if (token.getTipo().equals("LITERAL_CADENA")) {

                    operandos.push(token);

                } else if (token.getTipo().equals("VARIABLE_CADENA")) {

                    String nombre = token.getLexema();

                    if (!variables.containsKey(nombre)) {
                        throw new IllegalArgumentException(
                            "Variable no definida: " + nombre
                        );
                    }

                    String valor = variables.get(nombre);

                    operandos.push(new Token(
                        "LITERAL_CADENA", nombre, valor
                    ));

                } else if (token.getTipo().equals("CONCATENAR")) {

                    if (operandos.size() < 2) {
                        throw new IllegalArgumentException(
                            "Faltan operandos para concatenar."
                        );
                    }

                    String derecho = operandos.pop().getValor();
                    String izquierdo = operandos.pop().getValor();

                    String resultado = izquierdo + derecho;

                    operandos.push(new Token(
                        "LITERAL_CADENA", resultado, resultado
                    ));
                }
            }

            if (operandos.size() != 1) {
                throw new IllegalArgumentException(
                    "La expresion no pudo evaluarse."
                );
            }

            return operandos.pop().getValor();
        }
    

}