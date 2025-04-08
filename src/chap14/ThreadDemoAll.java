package chap14;

import java.util.Random;

public class ThreadDemoAll {
    
    static int[] nums = new int[100_000_000]; // 10 million elements
    public static void main(String[] args) {
        initNums();

        // Search for a number in the array
        long start = System.nanoTime();
        int needle = -348; // number to search for 
        int index[] = Searcher.findAll(needle, nums);
        long end = System.nanoTime();
        if (index.length>0) {
            System.out.println("Found " + needle + " at index " + java.util.Arrays.toString(index));
        } else {
            System.out.println(needle + " not found");
        }
        System.out.println("Time taken to search for " + needle + ": " + (end - start)/1000 + " µs");

        // Now let's try to search for the same number using multiple threads
        start = System.nanoTime();
        int nthreads = 6;

        // split the search space into 10 different threads
        int chunk = nums.length / nthreads;
        SearchWorkerAll workers[] = new SearchWorkerAll[nthreads];
        for (int i=0; i<nthreads; i++) {
            int startIndex = i * chunk;
            int endIndex = (i+1) * chunk;
            if (i == nthreads - 1) {
                endIndex = nums.length; // last thread takes the rest
            }
            workers[i] = new SearchWorkerAll(startIndex, endIndex, needle, nums);
        }

        // start the threads
        Thread threads[] = new Thread[nthreads];
        for (int i=0; i<nthreads; i++) {
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }
        // wait for the threads to finish
        for (int i=0; i<nthreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // merge the results coming back from the workers
        int results[] = new int[0];
        for (int i=0; i<nthreads; i++) {
            int r[] = workers[i].getResults();
            results = java.util.Arrays.copyOf(results, results.length + r.length);
            System.arraycopy(r, 0, results, results.length - r.length, r.length);
        }
        end = System.nanoTime();
        
        if (results.length > 0) {
            System.out.println("Found " + needle + " at index " + java.util.Arrays.toString(results));
        } else {
            System.out.println(needle + " not found");
        }
        System.out.println("Time taken to search for " + needle + " using threads: " + (end - start)/1000 + " µs");
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
