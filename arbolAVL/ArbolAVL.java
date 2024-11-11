package arbolAVL;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolAVL {
   
    public NodoAVL raiz;


    public void buscar(int valor){
        if(buscarNodo(valor)==null){
            System.out.println("El nodo con el valor "+valor+" no se encuentra en el árbol.");
        }else{
            System.out.println("Nodo encontrado dentro del árbol!");
        }
    }

    public boolean arbolVacio(){
        return raiz == null;
    }

    public void preFija(NodoAVL r){
        if(r!=null){
            System.out.print(r.getValor()+" ");
            if(r.getIzq()!=null){
                preFija(r.getIzq());
            }
            if(r.getDer()!=null){
                preFija(r.getDer());
            }
        }
    }

    public Integer encontrarMax(NodoAVL nodo){
        if(arbolVacio()){
            return null;
        } else {
            if(nodo.getDer() != null){
                return encontrarMax(nodo.getDer());
            }
            return nodo.getValor();
        }
    }


    private int altura(NodoAVL nodo){
        return nodo != null ? nodo.getAltura() : 0;
    }

    public void actualizarAltura(NodoAVL nodo){
        int alturaMax = Math.max(altura(nodo.getIzq()),altura(nodo.getDer()));
        nodo.setAltura(alturaMax+1);
    }

    private int balance(NodoAVL nodo){
        return nodo != null ? altura(nodo.getIzq()) - altura(nodo.getDer()) : 0;
    }

    public NodoAVL realizarRotacion(NodoAVL nodo){
        int balance = balance(nodo);
        if(balance > 1){
            if(balance(nodo.getIzq()) < 0){
                nodo.setIzq(rotarIzq(nodo.getIzq()));
            }

            return rotarDer(nodo);
        }else if(balance < -1){
            if(balance(nodo.getDer())> 0){
                nodo.setDer(rotarDer(nodo.getDer()));
            }
            return rotarIzq(nodo);
        }
        return nodo;
    }

    private NodoAVL rotarDer(NodoAVL nodo) {
        if (nodo == null || nodo.getIzq() == null) {
            return nodo; // Manejo de caso null
        }
        NodoAVL nodoIzq = nodo.getIzq();
        NodoAVL nodoCentral = nodoIzq.getDer();
        nodoIzq.setDer(nodo);
        nodo.setIzq(nodoCentral);
        actualizarAltura(nodo);
        actualizarAltura(nodoIzq);
        return nodoIzq;
    }

    private NodoAVL rotarIzq(NodoAVL nodo) {
        if (nodo == null || nodo.getDer() == null) {
            return nodo; // Manejo de caso null
        }
        NodoAVL nodoDer = nodo.getDer();
        NodoAVL nodoCentral = nodoDer.getIzq();
        nodoDer.setIzq(nodo);
        nodo.setDer(nodoCentral);
        actualizarAltura(nodo);
        actualizarAltura(nodoDer);
        return nodoDer;
    }

    public void insertar(int valor){
        raiz = insertar(valor, raiz);
    }

    private NodoAVL insertar(int valor,NodoAVL nodo){
        if(nodo == null){
            return new NodoAVL(valor);
        }
        if (valor < nodo.getValor()) {
            nodo.setIzq(insertar(valor, nodo.getIzq()));
        } else if (valor > nodo.getValor()) {
            nodo.setDer(insertar(valor, nodo.getDer()));
        } else {
            return nodo;
        }

        actualizarAltura(nodo);
        return realizarRotacion(nodo); 
    }
        

    
    public NodoAVL buscarNodo(int  valor) { 
        NodoAVL r = raiz;

	    Queue<NodoAVL> queue = new LinkedList<>();

	    if(r!=null){
            queue.add(r);
            while(!queue.isEmpty() ){
                r = (NodoAVL)queue.poll();
                if(r.getValor() == valor) return r; 
                if(r.getIzq()!=null) queue.add(r.getIzq());
                if(r.getDer()!=null) queue.add(r.getDer());
            }
	    }
        return null; 
    }   
    
    public void eliminar(int valor){
        raiz = eliminar(valor, raiz);
    }
    private NodoAVL eliminar(int valor, NodoAVL nodo){
        if(nodo == null){
            return null;
        }
        if(valor < nodo.getValor()){
            nodo.setIzq(eliminar(valor, nodo.getIzq()));
        }else if(valor > nodo.getValor()){
            nodo.setDer(eliminar(valor, nodo.getDer()));
        }else{
            if(nodo.getIzq() == null){
                return nodo.getDer();
            }else if(nodo.getDer() == null){
                return nodo.getIzq();
            }
            nodo.setValor(encontrarMax(nodo.getIzq()));
            nodo.setIzq(eliminar(nodo.getValor(),nodo.getIzq()));
        }
        
        actualizarAltura(nodo);
        return realizarRotacion(nodo);
        
    }

    public void imprimirArbol(NodoAVL n, String sangria) {
        if (n != null) {
            // Imprimir subárbol derecho primero (para que quede en la parte superior)
            imprimirArbol(n.getDer(), sangria + "   ");
            
            // Imprimir el valor del nodo con la indentación correspondiente
            System.out.println(sangria + n.getValor());
            
            // Imprimir subárbol izquierdo
            imprimirArbol(n.getIzq(), sangria + "   ");
        }
    }


}
