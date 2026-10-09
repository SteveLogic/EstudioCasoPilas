import java.util.NoSuchElementException;

public class Pila {

    private Nodo cima;
    private int cantidad;

    public Pila() {
        this.cima = null;
        this.cantidad = 0;
    }

    // Agrega un token a la cima de la pila.
    public void push(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("El token no puede ser null.");
        }

        Nodo nuevo = new Nodo(token);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        cantidad++;
    }

    // Extrae y devuelve el token de la cima.
    public Token pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila está vacía.");
        }

        Token token = cima.getToken();
        cima = cima.getSiguiente();
        cantidad--;

        return token;
    }

    // Consulta el token superior sin retirarlo.
    public Token peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila está vacía.");
        }

        return cima.getToken();
    }

    // Indica si la pila no contiene elementos.
    public boolean isEmpty() {
        return cima == null;
    }

    // Devuelve la cantidad de tokens almacenados.
    public int size() {
        return cantidad;
    }
}