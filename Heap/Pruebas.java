package heap; 

public class Pruebas {

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);
        heap.insert(9);
        heap.insert(10);
        heap.print();

        System.out.println("Works!");

        System.out.println("Deleting 1");
        heap.delete(1);
        heap.print();

        System.out.println("Deleting 10");
        heap.delete(10);
        heap.print();

        System.out.println("Deleting 6");
        heap.delete(6);
        heap.print();

        System.out.println("Deleting 100");
        heap.delete(100);
        heap.print();
    }
}
