public class Token {

    private String tipo;
    private String lexema;
    private String valor;

    public Token(String tipo, String lexema, String valor) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public String getValor() {
        return valor;
    }
}