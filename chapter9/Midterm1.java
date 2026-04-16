package chapter9;

public class Midterm1 {
    
    public static void main(String[] args) throws Exception {

        Queue<Integer> q = new ArrayListQueue<>();
        q.enqueue(5);
        q.enqueue(10);
        q.dequeue(); // HINT: don't forget that this removes a piece of data!
        q.enqueue(11);
        q.dequeue(); // HINT: don't forget that this removes a piece of data!
        q.enqueue(15);
        System.out.println(q.dequeue());

        Stack<Integer> s = new ArrayListStack<>();
        s.push(5);
        s.push(10);
        s.pop();
        s.push(11);
        s.pop();
        s.push(15);
        System.out.println(s.pop());
        
    }
}
