package chapter9;

public class Midterm1 {
    
    public static void main(String[] args) throws Exception {

        Queue<String> q = new ArrayListQueue<>();

        q.enqueue("BHM");
        q.enqueue("MTG");
        q.dequeue();
        q.enqueue("ATL");
        System.out.println(q.dequeue());
        System.out.println(q.size());
        
    }
}
