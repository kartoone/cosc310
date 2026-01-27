package chapter6;

import java.io.PrintWriter;
import java.util.ArrayList;

abstract public class Target {
    public static final int TRIALS = 5;

    protected int arr[];
    protected ArrayList<Integer> list;
    private String name; // must be in this format: array,random_access ... etc...
    private long results[] = new long[TRIALS];

    public Target(int arr[], ArrayList<Integer> list, String name) {
        this.arr = arr;
        this.list = list;
        this.name = name;
    }

    // method under test
    // indicesOrnums is being used for two different purposes
    abstract public int method(int indicesOrnums[]);

    // calls the method under test TRIALS number of times
    // 
    public double runTests(int indicesOrnums[]) {
        long total = 0;
        for (int i = 0; i < TRIALS; i++) {
            long start = System.nanoTime();
            int result = method(indicesOrnums);
            long end = System.nanoTime();
            long elapsed = end-start;
            total += elapsed;
            results[i] = elapsed;
        }
        double avg = total / (double) TRIALS;
        return avg;
    }

    // outputs the individual results to the output stream
    public void writeResults(PrintWriter out) {
        for (int i = 0; i < TRIALS; i++) {
            out.printf("%s,%d,%.2f\n", name, i+1, results[i]/1000.0);
        }
    }
    
}
