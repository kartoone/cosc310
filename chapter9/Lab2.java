package chapter9;

import my.util.DLinkedList;

public class Lab2 {
    public static void main(String[] args) {
        Stack<String> airports = new ArrayStack<>();
        System.out.println(airports);
        airports.push("BHM");
        System.out.println(airports);
        airports.push("ATL");
        System.out.println(airports);
        airports.push("MSP");
        System.out.println(airports);
        if (!airports.isEmpty()) {
            String airport1 = airports.pop();
            System.out.println(airports);
            String airport2 = airports.pop();
            System.out.println(airports);
            System.out.println(airport1);
            System.out.println(airport2);
        }
        airports.pop();

        DLinkedList<String> list = new DLinkedList<>();
        System.out.println(list);
        list.add("BHM");
        System.out.println(list);
        list.add("ATL");
        System.out.println(list);
        list.add("MSP");
        System.out.println(list);
    }
}
