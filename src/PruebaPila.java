public class PruebaPila {

    public static void main(String[] args) {

        Pila pila = new Pila();

        Token token1 = new Token("LITERAL_CADENA", "\"Hola\"", "Hola");
        Token token2 = new Token("CONCATENAR", "+", "+");
        Token token3 = new Token("VARIABLE_CADENA", "nombre", "Ana");

        pila.push(token1);
        pila.push(token2);
        pila.push(token3);

        System.out.println("Cantidad de tokens: " + pila.size());

        System.out.println("Token en la cima: " + pila.peek().getLexema());

        System.out.println("\nExtrayendo tokens:");

        while (!pila.isEmpty()) {
            System.out.println(pila.pop().getLexema());
        }

        System.out.println("\nPila vacía: " + pila.isEmpty());
    }
}