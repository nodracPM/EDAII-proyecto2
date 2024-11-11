public class NodoEA {
    private NodoEA left;
    private NodoEA right;
    private String valor;


    // Constructor para un operador
    public NodoEA(String valor, NodoEA left, NodoEA right)
    {
        this.valor = valor;
        this.left = left;
        this.right = right;   
    }

    // Constructor para un operando
    public NodoEA(String valor)
    {
        this.valor = valor;
        this.left = null;
        this.right = null;
    }

    //Metodos para verificar que tipo de valor es
    public boolean esOperador() {
        return "+-*/".contains(valor);
    }
    
    public boolean esOperando() {
        return left == null && right == null;
    }

    //Metodos de acceso a los datos
    public NodoEA getLeft() {
        return left;
    }

    public NodoEA getRight() {
        return right;
    }

    public String getValor() {
        return valor;
    }
    
}
