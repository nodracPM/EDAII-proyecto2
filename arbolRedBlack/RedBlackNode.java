public class RedBlackNode {
    private int key; 
    private RedBlackNode parent;
    private RedBlackNode left;
    private RedBlackNode right;
    private boolean color;

    public RedBlackNode(int key) {
        this.key = key;
        parent = null;
        left = null;
        right = null;
        color = true; // true = red, false = black
    }

    public int getKey() {
        return key;
    }

    public void setParent(RedBlackNode parent) {
        this.parent = parent;
    }

    public RedBlackNode getParent() {
        return parent;
    }
    public void setLeft(RedBlackNode left) {
        this.left = left;
    }
    public RedBlackNode getLeft() {
        return left;
    }   

    public void setRight(RedBlackNode right) {
        this.right = right;
    }

    public RedBlackNode getRight() {
        return right;
    }   

    public void setColor(boolean color) {
        this.color = color;
    }
    public boolean getColor() {
        return color;
    }   
}
