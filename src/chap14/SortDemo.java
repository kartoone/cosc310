package chap14;

import java.util.Random;

public class SortDemo {
    
    static int[] nums = new int[1000000]; // 1 million elements
    public static void main(String[] args) {
        initNums();
        long start = System.nanoTime();
        Sorter.selectionSort(nums);
        long end = System.nanoTime();
        initNums();
        System.out.println("Time taken for selectionSort on " + nums.length + " items: " + (end - start)/1000000 + " ms");
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(nums,100)));
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
