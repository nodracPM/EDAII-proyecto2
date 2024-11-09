public class NodoEA {
    NodoEA left;
    NodoEA right;
    String valor;


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
    
}
