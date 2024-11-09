
package utilerias;

import java.util.Scanner;
import arbolExpresionAritmetica.ArbolEA;
import arbolExpresionAritmetica.NodoEA;
import heap.Heap;

public class Utilerias {
    static Scanner input = new Scanner(System.in);
    public static void menuPrincipal()
    {
        int choice = 0;
        do{
        System.out.println("\n________________________Menu Principal__________________________");
        System.out.println("\n1. Arbol de Expresiones Aritmeticas.");
        System.out.println("2. Heap.");
        System.out.println("3. Árbol AVL.");
        System.out.println("4. Salir.");
        System.out.print("\nIngresar opción: ");
        choice = input.nextInt();
        
        switch (choice) {
            case 1->{menuAEA();}
            case 2->{menuHeap();}
            case 3->{System.out.println("Aquí va el menú del AVL :)");}
            case 4->{System.out.println("Finalizando ejecución. ¡Que tenga un excelente dia!");}
            default->{System.out.println("Opcion invalida, vuelva a intentarlo.");}
            }
        }while(choice!=4);
        input.close();
    }

    public static void menuAEA()
    {
        boolean flag = false;
        int choice = 0;
        ArbolEA arbolEA = null;
        NodoEA root = null;
        String expresion = null;
        do{
        System.out.println("\n_______________Menu Arbol de Expresion Aritmetica_______________");
        System.out.println("\n1. Ingresar Expresion.");
        System.out.println("2. Mostrar Arbol.");
        System.out.println("3. Evaluar.");
        System.out.println("4. Regresear al Menu Principal.");
        System.out.print("\nIngresar opción: ");
        choice = input.nextInt();
        input.nextLine();
        
            switch (choice) {
                case 1:
                    System.out.print("Ingrese la expresion que desee convertir (Por ejemplo: (3 + 2) * (4 - 1)): ");
                    expresion = input.nextLine();
                    arbolEA = new ArbolEA();
                    root = arbolEA.crear(expresion);
                    flag = true;
                    System.out.println("Se creo adecuadamente el arbol con la expresion: "+expresion);
                    break;
                case 2:
                    if(!flag)
                    {
                        System.out.println("Debe ingresar una expresion antes.");
                        break;
                    } else
                    {
                        System.out.println("Arbol de Expresiones Aritmeticas: ");
                        System.out.println("\n");
                        arbolEA.imprimirArbol(root, "");
                        break;
                    }
                case 3:
                    if(!flag)
                    {
                    System.out.println("Debe ingresar una expresion antes.");
                    break;
                    } else
                    {
                        System.out.println("La evaluacion del arbol con respecto a la expresion: "+ expresion);
                        System.out.println("\n");
                        arbolEA.imprimirArbol(root, "");
                        System.out.println("Es igual a: " + arbolEA.evaluar(root));
                        break;
                    }
                case 4:
                    return;
                default:
                    System.out.println("Por favor ingrese un número de opción válido.");
                    break;
            }
            
        } while (choice!=4);
    }

    public static void menuHeap()
    {
        Heap heap = new Heap();
        int choice = 0;
        int key; 

        do{
            System.out.println("\n__________________________Menu Heap_____________________________");

            System.out.println("\n1. Agregar clave.");
            System.out.println("2. Eliminar clave.");
            System.out.println("3. Mostrar Arbol.");
            System.out.println("4. Regresear al Menu Principal.");
            System.out.print("\nIngresar opción: ");
            choice = input.nextInt();
            input.nextLine();
        
            switch (choice) {
                case 1:
                    System.out.print("Ingrese la clave a agregar: ");
                    key = input.nextInt();
                    heap.insert(key);
                    System.out.println("La clave fue agregada con éxito.");
                    break;
                case 2:
                    System.out.print("Ingresa la clave a eliminar: ");
                    key = input.nextInt();
                    heap.delete(key);
                    break; 
                case 3:
                    heap.print();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Por favor ingrese un número de opción válido.");
                    break;
            }
            
        } while (choice!=4);
    }
}
