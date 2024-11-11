package arbolAVL;

public class Pruebas {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        arbol.insertar(1);
        arbol.insertar(2);
        arbol.insertar(3);
        arbol.insertar(4);
        arbol.insertar(5);
        arbol.imprimirArbol(arbol.raiz, "");

        System.out.println("\nPrueba1");

        arbol.insertar(10);
        arbol.insertar(9);
        arbol.insertar(8);
        arbol.insertar(7);
        arbol.insertar(6);
        arbol.imprimirArbol(arbol.raiz, "");

        System.out.println("\nPrueba2");

        arbol.insertar(15);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(25);
        arbol.insertar(10);
        arbol.imprimirArbol(arbol.raiz, "");

        System.out.println("\nPrueba3");

        arbol.insertar(30);
        arbol.insertar(40);
        arbol.insertar(35);
        arbol.imprimirArbol(arbol.raiz, "");

        System.out.println("\nPrueba4");

        arbol.eliminar(1);  // Eliminar una hoja
        arbol.eliminar(40); // Eliminar nodo con un solo hijo
        arbol.eliminar(20); // Eliminar nodo con dos hijos
        arbol.imprimirArbol(arbol.raiz, "");
        
        System.out.println("\nPrueba5");

        arbol.eliminar(arbol.raiz.getValor()); // Eliminar raíz actual
        arbol.eliminar(arbol.raiz.getValor()); // Repetir varias veces

        arbol.imprimirArbol(arbol.raiz, "");
        
        System.out.println("\nPrueba6");

        for (int i = 50; i <= 100; i += 10) {
            arbol.insertar(i);
        }
        arbol.eliminar(70);
        arbol.eliminar(80);

        arbol.imprimirArbol(arbol.raiz, "");
        
        System.out.println("\nPrueba7");

    }
}
