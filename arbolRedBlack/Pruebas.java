public class Pruebas {
    public static void main(String[] args) {
        RedBlackNode root = new RedBlackNode(5);
        RedBlackTree tree = new RedBlackTree(root);
    
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(9);
        
        System.out.println("BFS on the red black tree:");
        tree.showTree();
    }
}
