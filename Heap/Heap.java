import java.util.LinkedList;
import java.util.Queue;

public class Heap{
    Node root; 

    public Heap(){
        root = null;
    }

    public Heap(Node root){
        this.root = root;
    }

    public void insert(int key){
        Node node = new Node(key);
        if(root == null){
            root = node;
            return;
        }
        //Se recorre el heap hasta encontrar el siguientes espacio disponible y se inserta el nodo ahí
        doInsertion(node);
        //Se corrgie la posible violación de heap
        fixHeapViolation(node);
    }

    public void delete(int key){
        if(root == null) {
            System.out.println("No hay elementos en el heap");
            return; 
        }
        else if(!find(key)) {
            System.out.println("El nodo indicado no existe");
            return; 
        }

        Queue<Node> nodos = new LinkedList<>();
        nodos.add(root);
        Node node = null; 
        
        // Se busca elnodo con la clave indicada
        while(!nodos.isEmpty()){
            node = nodos.poll();
            if(node.getKey() == key) 
                break; 
            
            if(node.getLeft() != null) 
                nodos.add(node.getLeft());
            if(node.getRight() != null) 
                nodos.add(node.getRight());
        } 

        //Se remplaza por el último nodo
        Node lastNode = findLastNode(); 
        swap(lastNode, node); 

        lastNode.setLeft(null); 
        lastNode.setRight(null); 
        
        if(lastNode == root){ //Si el último nodo es la raíz (solo hay un elemento) se actualiza root
            root = null; 
        }
        else{ //Si no es la raíz se elimina la referencia de su padre 
            if(lastNode.getParent().getLeft() == lastNode)
                lastNode.getParent().setLeft(null);
            else
                lastNode.getParent().setRight(null);
            lastNode.setParent(null);
        }
        //Se corrige la violación del heap 
        heapify(node);
    }

    public Boolean find(int key) {
        Queue<Node> nodos = new LinkedList<>();
        nodos.add(root);
        Node aux; 
        while(!nodos.isEmpty()){
            aux = nodos.poll();
            if(aux.getKey() == key)
                return true;
            if(aux.getLeft() != null) 
                nodos.add(aux.getLeft());
            if(aux.getRight() != null) 
                nodos.add(aux.getRight());
        }
        return false;
    }

    public Node findLastNode(){
        Queue<Node> nodos = new LinkedList<>();
        nodos.add(root);
        Node aux = null; 
        while(!nodos.isEmpty()){
            aux = nodos.poll();
 
            if(aux.getLeft() != null) nodos.add(aux.getLeft());
            if(aux.getRight() != null) nodos.add(aux.getRight());
        }
        return aux; 
    }

    public void doInsertion(Node node){

        Queue<Node> nodos = new LinkedList<>();
        nodos.add(root);
        Node aux; 


        while( !nodos.isEmpty() ){
            
            aux = nodos.poll();
            //si la posición izquierda está disponible, el nodo se inserta ahí
            if(aux.getLeft() == null){
                aux.setLeft(node);
                node.setParent(aux); 
                return;
            }
            //si la posición derecha está disponible, el nodo se inserta ahí
            else if(aux.getRight() == null) {
                aux.setRight(node);
                node.setParent(aux); 
                return;
            }
            //si ambas posiciones están ocupadas, se inserta el nodo a la cola
            //para continuar con la búsqueda por BFS
            nodos.add(aux.getLeft());
            nodos.add(aux.getRight());
        }

    }


    public void fixHeapViolation(Node node) {
        Node aux = node;

        // Se intercambia el nuevo nodo por su padre mientras su clave sea mayor
        // y el padre no sea la raiz
        while(aux.getParent() != null && aux.getKey() > aux.getParent().getKey()) {
            swap(aux, aux.getParent());
            aux = aux.getParent();
        }        
    }

    public void swap(Node a, Node b) {
         int aux = a.getKey(); 
         a.setKey(b.getKey());
         b.setKey(aux);
    }
    protected void heapify(Node node){
        Node max = node;
        if(node.getLeft() != null && node.getLeft().getKey() > max.getKey())
            max = node.getLeft();
        if(node.getRight() != null && node.getRight().getKey() > max.getKey())
            max = node.getRight();
        if(max != node){
            swap(node, max);
            heapify(max);
        }
    }

    //Se utiliza recursión para imprimir el árbol horizontalmente 
    public void print() {
        print(root, 0);
    }

    private void print(Node node, int level) {
        if (node == null)
            return;
        
        // Imprime primero los nodos más a la derecha para mantener la coherencia del árbol
        print(node.getRight(), level + 1);
    
        // Imprime espacios para representar el nivel del nodo
        for (int i = 0; i < level; i++)
            System.out.print("    ");
    
        System.out.println(node.getKey());
        
        // Imprime después los nodos más a la izquierda para mantener la coherencia del árbol
        print(node.getLeft(), level + 1);
    }
    
}