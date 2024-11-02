

import java.util.ArrayList;

public class BNode {
    static int m;
    ArrayList<Integer> key; //saves child's keys 
    ArrayList<BNode> child; //saves child's references 
    BNode parent; //saves parent reference 
    boolean leaf;
    
    public BNode() {
        key = new ArrayList();
        child = new ArrayList();
        leaf = true;
        parent = null;
    }
    
    public int getKey( int i ){
        return this.key.get(i);
    }
    
    /**
     * 
     * @param i number of child (0 -> left child, 1 -> rigth child)
     * @return child ith reference 
     */
    public BNode getChild( int i ){
        try{
            this.child.get(i);
        }catch(Exception e){
            return null;
        }
        return this.child.get(i);
    }
    
    public void setM( int m ){
        this.m = m;
    }
    
    public void setKeys( ArrayList<Integer> list ){
        this.key = list;
    }
    
    public void setChildren( ArrayList<BNode> list  ){
        this.child = list;
    }
    
    /**
     * 
     * @return index of this node in its parent
     */
    public int getChildIndex(){
        if( parent == null ){
            return -1;
        }
        else{
            BNode padre = parent;
            for( int i = 0 ; i < padre.child.size() ; i++ ){
                if( padre.child.get(i) == this )
                    return i;
            }     
            return -1;
        }
    }
    
    public void mostrarLlaves(){
        for( int i = 0 ; i < key.size() ; i++ )
            System.out.print( key.get(i) + " " );
    }
   
    
    
    
}
