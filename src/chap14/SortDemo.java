package chap14;

import java.util.ArrayList;
import java.util.Random;

public class SortDemo {
    
    static int[] nums = new int[10]; // 1 million elements
    public static void main(String[] args) {
        initNums();
        long start = System.nanoTime();
        Sorter.selectionSort(nums);
        long end = System.nanoTime();
        System.out.println("Time taken for selectionSort on " + nums.length + " items: " + (end - start)/1000000 + " ms");
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(nums,10)));

        // reinitialize the array with new random numbers
        initNums();
        start = System.nanoTime();
        Sorter.bubbleSort(nums);
        end = System.nanoTime();
        System.out.println("Time taken for bubbleSort on " + nums.length + " items: " + (end - start)/1000000 + " ms");
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(nums,10)));

        // reinitialize the array with new random numbers
        initNums();
        // convert the int array to an ArrayList
        ArrayList<Integer> numsList = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            numsList.add(nums[i]);
        }
        start = System.nanoTime();
        Sorter.mergeSort(numsList);
        end = System.nanoTime();
        System.out.println("Time taken for mergeSort on " + nums.length + " items: " + (end - start)/1000000 + " ms");
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(nums,10)));
    }

    public static void initNums() {
        long start = System.currentTimeMillis();
        Random rand = new Random();
        for (int i=0; i<nums.length; i++) {
            nums[i] = rand.nextInt(200_000) - 100_000; // random number between -100,000 and 100,000
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken to initialize nums: " + (end - start) + " ms");        
    }
}
