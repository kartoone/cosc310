package review;

public class Types {

    public static double tax = 1.08;

    public static void func1(int qty) {
        double orange = 3.1;
        double cost = orange*qty;
        System.out.println(cost*tax);
    }

    public static void main(String[] args) {
        func1(5);
        float apple = (float) 3.1;
        double cost = apple*4;
        System.out.println(cost*tax);
        for (int i=0; i<10; i++) {

        }
    }
}
