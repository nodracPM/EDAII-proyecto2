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
            if(node.key == key) 
                break; 
            
            if(node.left != null) 
                nodos.add(node.left);
            if(node.right != null) 
                nodos.add(node.right);
        } 

        //Se remplaza por el último nodo
        Node lastNode = findLastNode(); 
        swap(lastNode, node); 

        lastNode.left = null; 
        lastNode.right = null; 
        
        if(lastNode == root){ //Si el último nodo es la raíz (solo hay un elemento) se actualiza root
            root = null; 
        }
        else{ //Si no es la raíz se elimina la referencia de su padre 
            if(lastNode.parent.left == lastNode)
                lastNode.parent.left = null;
            else
                lastNode.parent.right = null;
            lastNode.parent = null;
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
            if(aux.key == key)
                return true;
            if(aux.left != null) 
                nodos.add(aux.left);
            if(aux.right != null) 
                nodos.add(aux.right);
        }
        return false;
    }

    public Node findLastNode(){
        Queue<Node> nodos = new LinkedList<>();
        nodos.add(root);
        Node aux = null; 
        while(!nodos.isEmpty()){
            aux = nodos.poll();
 
            if(aux.left != null) nodos.add(aux.left);
            if(aux.right != null) nodos.add(aux.right);
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
            if(aux.left == null){
                aux.left = node;
                node.parent = aux;; 
                return;
            }
            //si la posición derecha está disponible, el nodo se inserta ahí
            else if(aux.right == null) {
                aux.right = node;
                node.parent = aux;; 
                return;
            }
            //si ambas posiciones están ocupadas, se inserta el nodo a la cola
            //para continuar con la búsqueda por BFS
            nodos.add(aux.left);
            nodos.add(aux.right);
        }

    }


    public void fixHeapViolation(Node node) {
        Node aux = node;

        // Se intercambia el nuevo nodo por su padre mientras su clave sea mayor
        // y el padre no sea la raiz
        while(aux.parent != null && aux.key > aux.parent.key) {
            swap(aux, aux.parent);
            aux = aux.parent;
        }        
    }

    public void swap(Node a, Node b) {
         int aux = a.key; 
         a.key = b.key;
         b.key = aux;
    }
    protected void heapify(Node node){
        Node max = node;
        if(node.left != null && node.left.key > max.key)
            max = node.left;
        if(node.right != null && node.right.key > max.key)
            max = node.right;
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
        print(node.right, level + 1);
    
        // Imprime espacios para representar el nivel del nodo
        for (int i = 0; i < level; i++)
            System.out.print("    ");
    
        System.out.println(node.key);
        
        // Imprime después los nodos más a la izquierda para mantener la coherencia del árbol
        print(node.left, level + 1);
    }
    
}