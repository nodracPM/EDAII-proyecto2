import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree {
    private RedBlackNode root;


    public RedBlackTree(RedBlackNode root) {
        this.root = root;
        root.setColor(false);
    }

    public void setRoot(RedBlackNode root) {
        this.root = root;
    }
    public RedBlackNode getRoot() {
        return root;
    }

    public boolean find(int value) {
        Queue<RedBlackNode> AuxQueue = new LinkedList<>();
        AuxQueue.add(root);

        while(!AuxQueue.isEmpty()) {
            RedBlackNode current = AuxQueue.poll();
            if(current.getKey() == value) return true;

            if(current.getLeft() != null) AuxQueue.add(current.getLeft());
            else if(current.getRight() != null) AuxQueue.add(current.getRight());
        } 
        return false;
    }

    public void LeftRotation(RedBlackNode node) {
        RedBlackNode x = node; 
        RedBlackNode y = x.getRight();
        RedBlackNode parent = x.getParent();
        
        x.setRight(y.getLeft());
        y.setLeft(x);
        y.setParent(x.getParent());
        x.setParent(y);
        if(parent.getLeft() == x) parent.setLeft(y);
        else parent.setRight(y);
    }

    public void RightRotation(RedBlackNode node) {
        RedBlackNode x = node; 
        RedBlackNode y = x.getLeft();
        RedBlackNode parent = x.getParent();

        x.setLeft(y.getRight());
        y.setRight(x);
        y.setParent(x.getParent());
        x.setParent(y);
        if(parent.getLeft() == x) parent.setLeft(y);
        else parent.setRight(y);
    }

    public void fixInsertion(RedBlackNode node) {
        //there are 4 cases to consider after inserting a node
        // case 1: z node is root node
        // case 2: z's uncle is red 
        // case 3: z's uncle is black (triangle)
        // case 4: z's uncle is black (line)

        RedBlackNode current = node;

        while(current != root && current.getParent().getColor()) {
            //find z's uncle
            RedBlackNode parent = current.getParent();
            RedBlackNode grandparent = current.getParent().getParent();
            RedBlackNode uncle; 
            if(grandparent.getLeft() != parent) uncle = grandparent.getLeft(); 
            else uncle = grandparent.getRight();

            //if z's uncle is red
            if(uncle != null && uncle.getColor()) {
                if(grandparent == root) {
                    parent.setColor(false);
                    if(parent.getLeft() == current) { 
                        RightRotation(parent);
                    }
                    else LeftRotation(parent);
                }

                else {
                    uncle.setColor(false);
                    parent.setColor(false);
                    grandparent.setColor(true);
                }
                current = grandparent;
            }
    
    
            //if z's uncle is black
            else if(grandparent != null){
                System.out.println("ENTRA2");
                //case 3: z's uncle is black (triangle)
                if(grandparent.getLeft() == parent && parent.getRight() == node) {
                    LeftRotation(parent);
                    current = parent;
                }
                else if(grandparent.getRight() == parent && parent.getLeft() == node) {
                    RightRotation(parent);
                    current = parent;
                }
                
                //case 4: z's uncle is black (line)
                else {
                    if(grandparent.getLeft() == parent && parent.getLeft() == node) RightRotation(grandparent);
                    else if(grandparent.getRight() == parent && parent.getRight() == node) LeftRotation(grandparent);
                    
                    parent.setColor(false);
                    grandparent.setColor(true);
                    current = grandparent;
                }
                
            }    
            //System.out.println("Fixing insertion");
        }    
        root.setColor(false); 
    }

    public void insert(int value) {
        RedBlackNode NodeToInsert = new RedBlackNode(value);

        if(root == null) {
             setRoot(NodeToInsert);
             return;
        }

        Queue<RedBlackNode> AuxQueue = new LinkedList<>();
        AuxQueue.add(root); 

        while(!AuxQueue.isEmpty()) {
            RedBlackNode current = AuxQueue.poll();

            if(current.getKey() < NodeToInsert.getKey()) {
                if(current.getRight() == null) {
                    current.setRight(NodeToInsert);
                    NodeToInsert.setParent(current);
                    break;
                } else {
                    AuxQueue.add(current.getRight());
                }
            } else if(current.getKey() > NodeToInsert.getKey()) {
                if(current.getLeft() == null) {
                    current.setLeft(NodeToInsert);
                    NodeToInsert.setParent(current);
                    break;
                } else {
                    AuxQueue.add(current.getLeft());
                }
            }
        }

        fixInsertion(NodeToInsert);
    }


    public void fixDeletion(RedBlackNode node) {

    }

    public void delete(int value) {

    }

    public void showTree() {
        Queue<RedBlackNode> AuxQueue = new LinkedList<>();
        AuxQueue.add(root);

        while(!AuxQueue.isEmpty()) {
            RedBlackNode current = AuxQueue.poll();
            if(!current.getColor()) System.out.println("*" + current.getKey());
            else System.out.println(current.getKey());

            if(current.getLeft() != null) AuxQueue.add(current.getLeft());
            if(current.getRight() != null) AuxQueue.add(current.getRight());
        }
    }

    //Se utiliza recursión para imprimir el árbol horizontalmente 
    public void print() {
        print(root, 0);
    }

    private void print(RedBlackNode node, int level) {
        if (node == null)
            return;
        
        // Imprime primero los nodos más a la derecha para mantener la coherencia del árbol
        print(node.getRight(), level + 1);
    
        // Imprime espacios para representar el nivel del nodo
        for (int i = 0; i < level; i++)
            System.out.print("    ");
    
        if(node.getColor()) System.out.println(node.getKey());
        else System.out.println("*" + node.getKey());
        
        // Imprime después los nodos más a la izquierda para mantener la coherencia del árbol
        print(node.getLeft(), level + 1);
    }
}
