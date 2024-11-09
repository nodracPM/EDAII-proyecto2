public class Node {
    private int key;
    private Node parent;
    private Node left; 
    private Node right;

    public Node(int key) {
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }
    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }
}