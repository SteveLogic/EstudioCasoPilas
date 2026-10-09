public class Nodo {

    private Token token;
    private Nodo siguiente;

    public Nodo(Token token) {
        this.token = token;
        this.siguiente = null;
    }

    public Token getToken() {
        return token;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}