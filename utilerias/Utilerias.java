
package utilerias;

import java.util.Scanner;
import arbolExpresionAritmetica.ArbolEA;
import arbolExpresionAritmetica.NodoEA;
import heap.Heap;
import heap.Node;

public class Utilerias {
    static Scanner input = new Scanner(System.in);
    public static void menuPrincipal()
    {
        int choice = 0;
        do{
        System.out.println("_____________Menu Principal_____________");
        System.out.println("1. Arbol de Expresiones Aritmeticas.");
        System.out.println("2. Salir.");
        System.out.print("Ingrese la opcion que desee:");
        choice = input.nextInt();
        
        switch (choice) {
            case 1->{menuAEA();}
            case 2->{System.out.println("Saliendo... ¡que tenga un excelente dia!");}
            default->{System.out.println("Opcion invalida, vuelva a intentarlo.");}
            }
        }while(choice!=2);
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
        System.out.println("_______________Menu Arbol de Expresion Aritmetica_______________");
        System.out.println("1. Ingresar Expresion.");
        System.out.println("2. Mostrar Arbol.");
        System.out.println("3. Evaluar.");
        System.out.println("4. Regresear al Menu Principal.");
        System.out.print("Ingresar opcion:");
        choice = input.nextInt();
        input.nextLine();
        
            switch (choice) {
                case 1:
                    System.out.print("Ingrese la expresion que desee convertir (Por ejemplo: (3 + 2) * (4 - 1)):");
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
                    System.out.println("Regresando al Menu Principal...");
                    return;
                default:
                    break;
            }
            
        } while (choice!=4);
    }
}
