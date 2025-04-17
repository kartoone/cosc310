package chap14;

import java.util.Random;

public class ThreadDemo2 {
    static byte[] nums = new byte[10_000_000]; 
    static private int shared_hit_counter = 0;

    static synchronized void incrementHitCounter() {
        shared_hit_counter++;
    }

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

        // Since we are generated 21 different random numbers (-10 to 10), we can calculate the expected number of hits
        // by multiplying the number of elements by the probability of hitting a number
        // 1/21 = 0.047619047619047616
        System.out.println("Exiting main: grand total hit count was: " + grandtotal);
        System.out.println("Expected hit count: 1/21 = " + (1.0/21.0) + " * " + nums.length + " = " + Math.round(1.0/21.0 * nums.length));

        // Now let's use our "shared state" worker without synchronization

        SSWorker sworkers[] = new SSWorker[TCOUNT];
        for (int i = 0; i < threads.length; i++) {
            sworkers[i] = new SSWorker(i*slice, i*slice+slice, (byte) 3, nums);
            threads[i] = new Thread(sworkers[i]);
            threads[i].start();
        }

        // join is used to block until a thread finishes
        // this loop will wait until continue all threads finishes
        for (int i=0; i<TCOUNT; i++) {
            threads[i].join();
            System.out.println(" DONE waiting on thread: " + i);
        }

        // Since we are generated 21 different random numbers (-10 to 10), we can calculate the expected number of hits
        // by multiplying the number of elements by the probability of hitting a number
        // 1/21 = 0.047619047619047616
        System.out.println("Exiting main: shared state hit count was: " + shared_hit_counter);
        System.out.println("Independent workers hit count was: " + grandtotal);
        System.out.println("Expected hit count: 1/21 = " + (1.0/21.0) + " * " + nums.length + " = " + Math.round(1.0/21.0 * nums.length));

        // let's do this again with the shared hit counter but get rid of the unnecessary worker class
        shared_hit_counter = 0;
        for (int i = 0; i < threads.length; i++) {
            int start = i * slice;
            int end = i*slice + slice;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = start; i < end; i++) {
                        if (nums[i]==3)
                            incrementHitCounter();
                    }
                    System.out.println("Thread exiting: my start and end was: " + start + ", " + end);
                }                
            });
            threads[i].start();
        }

        // join is used to block until a thread finishes
        // this loop will wait until continue all threads finishes
        for (int i=0; i<TCOUNT; i++) {
            threads[i].join();
            System.out.println(" DONE waiting on thread: " + i);
        }

        // Since we are generated 21 different random numbers (-10 to 10), we can calculate the expected number of hits
        // by multiplying the number of elements by the probability of hitting a number
        // 1/21 = 0.047619047619047616
        System.out.println("Exiting main: shared state hit count was: " + shared_hit_counter);
        System.out.println("Independent workers hit count was: " + grandtotal);
        System.out.println("Expected hit count: 1/21 = " + (1.0/21.0) + " * " + nums.length + " = " + Math.round(1.0/21.0 * nums.length));

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

class SSWorker implements Runnable {
    protected int start; // inclusive
    protected int end;   // exclusive
    protected byte needle;
    protected byte nums[]; // shared data we are accessing

    SSWorker(int start, int end, byte needle, byte nums[]) {
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
                ThreadDemo2.incrementHitCounter();
        }        
    }

}


// NOTE: we already have a SearchWorker class inside our chap14 package, so we had to rename this to SearchWorker2
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

