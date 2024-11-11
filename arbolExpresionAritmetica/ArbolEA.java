import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;

public class ArbolEA {
    private Stack<String> pOperadores;  //Pila para los operadores
    private Stack<NodoEA> pOperandos;   //Pila para los operandos
    private final String operadores;    //Este string esta hecho para almacenar los diferentes operadores como + * / -
    private final String blanco;

    public ArbolEA() {
        pOperadores = new Stack<>();
        pOperandos = new Stack<>();
        operadores = "()+-*/";  //Aqui se incluyen los parentesis para el manejo adecuado de las expresiones
        blanco = "\t ";
    }

    public NodoEA crear(String expresion) {
        StringTokenizer valores = new StringTokenizer(expresion, operadores, true);
        String valor;
        NodoEA root = null;

        while (valores.hasMoreTokens()) {
            valor = valores.nextToken();
            if (blanco.indexOf(valor) >= 0) 
                continue;  // ignorar espacios en blanco
            
            if (operadores.indexOf(valor) < 0) {  // Es un operando
                pOperandos.push(new NodoEA(valor));
            } else if (valor.equals("(")) {  // Se maneja el primer parentesis de apertura
                pOperadores.push(valor);
            } else if (valor.equals(")")) {  // Se maneja el segundo parentesis de cierre
                while (!pOperadores.isEmpty() && !pOperadores.peek().equals("(")) {
                    guardarSubArbol();
                }
                pOperadores.pop();  // Eliminar parentesis
            } else {  // El caso en que sea un operador
                while (!pOperadores.isEmpty() && precedence(pOperadores.peek()) >= precedence(valor)) {
                    guardarSubArbol();
                }
                pOperadores.push(valor);
            }
        }

        //Despues de haber trabajado con todos los valores se vacia la pila de operadores y se guarda un subArbol
        while (!pOperadores.isEmpty()) {
            guardarSubArbol();
        }

        //La raiz del arbol sera el ultimo operador de la pila de operadores
        if (!pOperandos.isEmpty()) {
            root = pOperandos.pop();
        }
        return root;
    }

    //Metodo para jerarquerizar las operaciones
    private int precedence(String op) {
        switch (op) {
            case "+": case "-":
                return 1;
            case "*": case "/":
                return 2;
            default:
                return 0;
        }
    }

    private void guardarSubArbol() {
        if (!pOperandos.isEmpty() && !pOperadores.isEmpty()) {
            String operador = pOperadores.pop();  // Operador actual
            NodoEA nodo2 = pOperandos.pop();  // Operando 1
            NodoEA nodo1 = pOperandos.pop();  // Operando 2

            NodoEA nuevoSubArbol = new NodoEA(operador, nodo1, nodo2);  // Se crea un subArbol como un nodo
            pOperandos.push(nuevoSubArbol);  // Y se almacena en la pila de Operandos
        }
    }

    /**Se modifico el recorrido postOrden para que se almacenen sus valores de los nodos en una lista y 
     poder evaluar la expresion con la Notacion Polaca Inversa**/
    private ArrayList<NodoEA> postOrden(NodoEA n) {
        ArrayList<NodoEA> nodoEAs = new ArrayList<>();
        if (n != null) {
            nodoEAs.addAll(postOrden(n.getLeft()));  
            nodoEAs.addAll(postOrden(n.getRight())); 
            nodoEAs.add(n);  
        }
        return nodoEAs;
    }

    public double evaluar(NodoEA root)
    {
        Stack<Double> datos = new Stack<>(); // Pila que almacena los valores numericos
        ArrayList<NodoEA> nodos = postOrden(root); //Lista hecha con el recorrido postOrden
        for(NodoEA nodo : nodos)
        {
            if(nodo.esOperando())
            {
                String valor = nodo.getValor();
                datos.push(Double.parseDouble(valor)); //Si son operadondos se convierten de String a double
            }else if(nodo.esOperador())
            {
                double operando2 = datos.pop();
                double operando1 = datos.pop();
                double resultado = 0;

                //En otro caso se sacan 2 valores de la pila y se realiza su respectiva operacion
                switch(nodo.getValor())
                {
                    case "+"->{resultado = operando1 + operando2;}
                    case "-"->{resultado = operando1 - operando2;}
                    case "*"->{resultado = operando1 * operando2;}
                    case "/"->
                    {
                        if(operando2 != 0)
                        {
                            resultado= operando1/operando2;
                        }
                        else
                        {
                            throw new ArithmeticException("Division por cero");
                        }
                    }
                }
                datos.push(resultado); // Se guarda el resultado en la pila para poder realizar todas las demas operaciones
            }
        }
        return datos.pop(); //Obtenemos el resultado final
    }
    // Este metodo modifica el recorrido InOrden aplicando una sangria para mostrar adecuadamente la estructura del arbol
    public void imprimirArbol(NodoEA n, String sangria) {
        if (n != null) {
            // Imprimir subárbol derecho primero (para que quede en la parte superior)
            imprimirArbol(n.getRight(), sangria + "   ");
            
            // Imprimir el valor del nodo con la indentación correspondiente
            System.out.println(sangria + n.getValor());
            
            // Imprimir subárbol izquierdo
            imprimirArbol(n.getLeft(), sangria + "   ");
        }
    }
}
