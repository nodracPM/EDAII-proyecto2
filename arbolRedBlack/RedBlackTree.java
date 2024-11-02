import java.util.LinkedList;
import java.util.Queue;
public class RedBlackTree {
    private RedBlackNode root;


    public RedBlackTree(RedBlackNode root) {
        this.root = root;
    }

    public void setRoot(RedBlackNode root) {
        this.root = root;
    }
    public RedBlackNode getRoot() {
        return root;
    }

    public void LeftRotation(RedBlackNode node) {
        RedBlackNode x = node; 
        RedBlackNode y = x.getRight();

        x.setRight(y.getLeft());
        y.setLeft(x);
    }

    public void RightRotation(RedBlackNode node) {
        RedBlackNode x = node; 
        RedBlackNode y = x.getLeft();

        x.setLeft(y.getRight());
        y.setRight(x);
    }

    public void fixInsertion(RedBlackNode node) {
        //there are 4 cases to consider after inserting a node
        // case 1: z node is root node
        // case 2: z's uncle is red 
        // case 3: z's uncle is black (triangle)
        // case 4: z's uncle is black (line)

        System.out.println("Fixing insertion");

        
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
}
