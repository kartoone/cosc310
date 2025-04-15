package chap14;

import java.util.Random;

class SearchWorker2 implements Runnable {
    protected int start; // inclusive
    protected int end;   // exclusive
    protected byte needle;
    protected byte nums[]; // shared data we are accessing
    protected int hitcount = 0;

    SearchWorker2(int start, int end, byte needle, byte nums[]) {
        this.start = start;
        this.end = end;
        this.needle = needle;
        this.nums = nums;
        System.out.println("Starting at: " + start + " and ending at: " + end);
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (nums[i]==needle)
                hitcount++;
        }        
    }

    public int getHits() {
        return hitcount;
    }

}

class SumWorker implements Runnable {
    protected int start; // inclusive
    protected int end;   // exclusive
    protected byte nums[]; // shared data we are accessing
    protected long total = 0;

    SumWorker(int start, int end, byte nums[]) {
        this.start = start;
        this.end = end;
        this.nums = nums;
        System.out.println("Starting at: " + start + " and ending at: " + end);
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            total += nums[i];
        }        
    }

    public long getTotal() {
        return total;
    }

}

class HelloWorker implements Runnable {
    int tnum;
    int remaining = 100;

    HelloWorker(int tnum) {
        this.tnum = tnum;
    }

    @Override
    public void run() {
        while (remaining-- > 0) {
            System.out.println(remaining + "- Hello from thread #" + tnum);
        }
        System.out.println("Exiting from thread #" + tnum);
    }

}

public class ThreadDemo2 {
    static byte[] nums = new byte[100_000_000]; // 10 million elements
    public static void main(String[] args) throws InterruptedException {
        System.out.println("2");
        initNums();

        int TCOUNT=10;
        Thread threads[] = new Thread[TCOUNT];
        SearchWorker2 workers[] = new SearchWorker2[TCOUNT];
        int slice = nums.length / threads.length;
        // for (int i=0; i<TCOUNT; i++) {
        //     threads[i] = new Thread(new HelloWorker(i));
        //     threads[i].start();
        // }
        // for (int i = 0; i < threads.length; i++) {
        //     workers[i] = new SumWorker(i*slice, i*slice+slice, nums);
        //     threads[i] = new Thread(workers[i]);
        //     threads[i].start();
        // }
        for (int i = 0; i < threads.length; i++) {
            workers[i] = new SearchWorker2(i*slice, i*slice+slice, (byte) 3, nums);
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }

        // join is used to block until a thread finishes
        // this loop will wait until continue all threads finishes
        long grandtotal = 0L;
        for (int i=0; i<TCOUNT; i++) {
            // System.out.println("Waiting on thread: " + i);
            threads[i].join();
            grandtotal += workers[i].getHits();
            System.out.println(workers[i].getHits() + " DONE waiting on thread: " + i);
        }

        System.out.println("Exiting main: grand total was: " + grandtotal);

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
