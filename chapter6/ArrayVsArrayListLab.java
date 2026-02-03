package chapter6;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ArrayVsArrayListLab {

    public static void main(String[] args) {
        try {
            int arr[] = DataLoader.loadArray("numbers.txt");
            ArrayList<Integer> list = DataLoader.loadArrayList("numbers.txt");
            Random r = new Random();
            int indices[] = new int[100_000];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = r.nextInt(arr.length);
            }

            PrintWriter fileOut = new PrintWriter(new File("results.csv"));
            Target tests[] = new Target[8];
            double testAverages[] = new double[8];

            tests[0] = new ArrayRandom(arr, list, "array,random_access");
            tests[1] = new ListRandom(arr, list, "arraylist, random_access");
            tests[2] = new ArrayAppend(arr, list, "array,append");
            
            for (int i=0; i<tests.length; i++) {
                Target target = tests[i];
                if (target != null) {
                    testAverages[i] = target.runTests(indices);
                    target.writeResults(fileOut);
                }
            }
            System.out.println(java.util.Arrays.toString(testAverages));
            


            fileOut.close();
        } catch (Exception ex) {
            System.out.println("oh no, something went wrong");
        }
    }
}
