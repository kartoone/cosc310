package chapter9;

public class Lab2 {

    public static void runStackTests(Stack<String> stack, String whichStack) throws Exception {
        System.out.println(stack);
        stack.push("BHM");
        System.out.println(stack);
        stack.push("ATL");
        System.out.println(stack);
        stack.push("MSP");
        System.out.println(stack);
        if (!stack.isEmpty()) {
            String airport1 = stack.pop();
            System.out.println(stack);
            String airport2 = stack.pop();
            System.out.println(stack);
            System.out.println(airport1);
            System.out.println(airport2);
        }
        stack.pop();
    }

    public static void runQueueTests(Queue<String> queue, String whichQueue) throws Exception {
        System.out.println("Start of test on: " + whichQueue);
        queue.enqueue("BHM");
        queue.enqueue("ATL");
        queue.enqueue("MSP");
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }

    public static void main(String[] args) throws Exception {
        Stack<String> stack1 = new ArrayStack<>();
        Stack<String> stack2 = new ListStack<>();
        runStackTests(stack1, "ArrayStack");
        runStackTests(stack2, "ListStack");

        Queue<String> queue1 = new ArrayQueue<>();
        runQueueTests(queue1, "Array Queue");

        Queue<Integer> queue2 = new DLinkedListQueue<>();
        for (int i=0; i<16; i++) {
            queue2.enqueue(i);
        }
        queue2.dequeue();
        for (int i=16; i<33; i++) {
            queue2.enqueue(i);
        }
        for(int i=0; i<32; i++) {
            System.out.print(queue2.dequeue() + " ");
        }
    }
}
