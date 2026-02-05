package chapter9;

import my.util.DLinkedList;

public class Lab2 {

    public static void runStackTests(Stack<String> stack, String whichStack) {
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

    public static void main(String[] args) {
        Stack<String> stack1 = new ArrayStack<>();
        Stack<String> stack2 = new ListStack<>();
        runStackTests(stack1, "ArrayStack");
        runStackTests(stack2, "ListStack");

    }
}
