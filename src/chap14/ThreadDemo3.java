package chap14;

import java.util.Random;

public class ThreadDemo3 {
    static byte[] nums = new byte[1_000_000_000]; 
    static long shared_grand_total = 0;

    static synchronized void incrementTotal(byte b) {
        shared_grand_total += b;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("2");
        initNums();
        System.out.println("Starting single threaded calculation:");
        long t_start = System.nanoTime();
        long grandtotal = 0;
        for (int i=0; i<nums.length; i++) {
            grandtotal += nums[i];
        }
        long t_end = System.nanoTime();
        System.out.println("Grand total calculated in " + (t_end-t_start) + "ns");

        System.out.println("Starting multithread");
        t_start = System.nanoTime();
        int TCOUNT=10;
        Thread threads[] = new Thread[TCOUNT];
        int slice = nums.length / threads.length;
        for (int i = 0; i < threads.length; i++) {
            int start = i * slice;
            int end = i*slice + slice;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {               
                    int subtotal = 0;
                    for (int i = start; i < end; i++) {
                        subtotal += nums[i];
                    }
                    synchronized(nums) {
                        shared_grand_total += subtotal;
                    }
                    // System.out.println("Thread exiting: my start and end was: " + start + ", " + end);
                }                
            });
            threads[i].start();
        }

        // join is used to block until a thread finishes
        // this loop will wait until continue all threads finishes
        for (int i=0; i<TCOUNT; i++) {
            threads[i].join();
            // System.out.println(" DONE waiting on thread: " + i);
        }
        t_end = System.nanoTime();
        System.out.println("Multi-threaded total calculated in " + (t_end-t_start) + "ns");

        // Since we are generated 21 different random numbers (-10 to 10), we can calculate the expected number of hits
        // by multiplying the number of elements by the probability of hitting a number
        // 1/21 = 0.047619047619047616
        System.out.println("Exiting main: shared state total was: " + shared_grand_total);
        System.out.println("Independent total was: " + grandtotal);
        System.out.println("Expected total: 0");

    }

     public static void initNums() {
        long start = System.currentTimeMillis();
        Random rand = new Random();
        for (int i=0; i<nums.length; i++) {
            nums[i] = (byte) (rand.nextInt(21) - 10); // random number between -100,000 and 100,000
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken to initialize nums: " + (end - start) + " ms");        
    }
}

