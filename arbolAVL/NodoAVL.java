package arbolAVL;


public class NodoAVL{
    private int valor;
    private int altura = 1;
    private NodoAVL izq = null;
    private NodoAVL der = null;

    public NodoAVL(){}

    public NodoAVL(int valor){
        setValor(valor);
    }

    public NodoAVL(int valor, NodoAVL der, NodoAVL izq){
        setValor(valor);
        setDer(der);
        setIzq(izq);
    }


    public void setValor(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public int getAltura(){
        return this.altura;
    }

    public void setIzq(NodoAVL izq){
        this.izq = izq;
    }

    public NodoAVL getIzq(){
        return this.izq;
    }

    public void setDer(NodoAVL der){
        this.der = der;
    }

    public NodoAVL getDer(){
        return this.der;
    }

}