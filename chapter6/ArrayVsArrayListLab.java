package chapter6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ArrayVsArrayListLab {
    public static void main(String[] args) throws IOException {
        int[] array = DataLoader.loadArray("numbers.txt");
        ArrayList<Integer> arrayList = DataLoader.loadArrayList("numbers.txt");
        
        System.out.println("Loaded  " + array.length + "numbers.");
        System.nanoTime();
    
        int K = 200_000;
        int A = 200_000;
        int F = 20_000;
        int R = 20_000;
        int trials = 5;

        // Operation 1: Random Access Sum
        long arrayAccessTime = 0;
        long arrayListAccessTime = 0;

        for (int t = 0; t < trials; t++) {
            long startTime = System.nanoTime();
            int sum = 0;
            for (int i = 0; i < K; i++) {
            int randomIndex = (int) (Math.random() * array.length);
            sum += array[randomIndex];
            }
            arrayAccessTime += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sum = 0;
            for (int i = 0; i < K; i++) {
            int randomIndex = (int) (Math.random() * arrayList.size());
            sum += arrayList.get(randomIndex);
            }
            arrayListAccessTime += (System.nanoTime() - startTime);
        }

        System.out.println("Average time for array random access: " + (arrayAccessTime / trials) + " ns");
        System.out.println("Average time for ArrayList random access: " + (arrayListAccessTime / trials) + " ns");

        // Operation 2: Append Elements
        long arrayAppendTime = 0;
        long arrayListAppendTime = 0;

        for (int t = 0; t < trials; t++) {
            long startTime = System.nanoTime();
            int[] newArray = new int[array.length + A];
            System.arraycopy(array, 0, newArray, 0, array.length);
            arrayAppendTime += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            for (int i = 0; i < A; i++) {
            arrayList.add(i);
            }
            arrayListAppendTime += (System.nanoTime() - startTime);
        }

        System.out.println("Average time for array append: " + (arrayAppendTime / trials) + " ns");
        System.out.println("Average time for ArrayList append: " + (arrayListAppendTime / trials) + " ns");

        // Operation 3: Insert at Front
        long arrayInsertTime = 0;
        long arrayListInsertTime = 0;

        for (int t = 0; t < trials; t++) {
            long startTime = System.nanoTime();
            int[] newArray = new int[array.length + F];
            System.arraycopy(array, 0, newArray, F, array.length);
            arrayInsertTime += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            for (int i = 0; i < F; i++) {
            arrayList.add(0, i);
            }
            arrayListInsertTime += (System.nanoTime() - startTime);
        }

        System.out.println("Average time for array insert at front: " + (arrayInsertTime / trials) + " ns");
        System.out.println("Average time for ArrayList insert at front: " + (arrayListInsertTime / trials) + " ns");

        // Operation 4: Remove from Front
        long arrayRemoveTime = 0;
        long arrayListRemoveTime = 0;

        for (int t = 0; t < trials; t++) {
            long startTime = System.nanoTime();
            int[] newArray = new int[array.length - R];
            System.arraycopy(array, R, newArray, 0, array.length - R);
            arrayRemoveTime += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            for (int i = 0; i < R; i++) {
            arrayList.remove(0);
            }
            arrayListRemoveTime += (System.nanoTime() - startTime);
        }

        System.out.println("Average time for array remove from front: " + (arrayRemoveTime / trials) + " ns");
        System.out.println("Average time for ArrayList remove from front: " + (arrayListRemoveTime / trials) + " ns");

        System.out.println("\n=== SUMMARY ===");
        System.out.printf("Operation: random_access array avg: %.2f ms arraylist avg: %.2f ms winner: %s%n",
            arrayAccessTime / trials / 1_000_000.0,
            arrayListAccessTime / trials / 1_000_000.0,
            arrayAccessTime <= arrayListAccessTime ? "array" : "arraylist");

        System.out.printf("Operation: append array avg: %.2f ms arraylist avg: %.2f ms winner: %s%n",
            arrayAppendTime / trials / 1_000_000.0,
            arrayListAppendTime / trials / 1_000_000.0,
            arrayAppendTime <= arrayListAppendTime ? "array" : "arraylist");

        System.out.printf("Operation: insert_front array avg: %.2f ms arraylist avg: %.2f ms winner: %s%n",
            arrayInsertTime / trials / 1_000_000.0,
            arrayListInsertTime / trials / 1_000_000.0,
            arrayInsertTime <= arrayListInsertTime ? "array" : "arraylist");

        System.out.printf("Operation: remove_front array avg: %.2f ms arraylist avg: %.2f ms winner: %s%n",
            arrayRemoveTime / trials / 1_000_000.0,
            arrayListRemoveTime / trials / 1_000_000.0,
            arrayRemoveTime <= arrayListRemoveTime ? "array" : "arraylist");
    }
}
