package benchmark;

import chapter9.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkDriver {

    // -----------------------------
    // CONFIG
    // -----------------------------
    private static final int WARMUP_OPS = 150_000;
    private static final int MEASURE_OPS = 600_000;
    private static final int TRIALS = 7;
    private static final long SEED = 315_351_107L;

    public static void main(String[] args) throws Exception {
        System.out.println("Java ADT Benchmark (nanoTime).");
        System.out.println("Warmup ops: " + WARMUP_OPS + ", Measure ops: " + MEASURE_OPS + ", Trials: " + TRIALS);
        System.out.println();

        sanity();

        runStackBench("ArrayListStack", new chapter9.ArrayListStack<Integer>());
        runStackBench("DLinkedListStack", new chapter9.DLinkedListStack<Integer>());

        runQueueBench("ArrayListQueue", new chapter9.ArrayListQueue<Integer>());
        runQueueBench("DLinkedListQueue", new chapter9.DLinkedListQueue<Integer>());

        runPQBench("SortedArrayListPQ", new chapter9.SortedArrayListPriorityQueue<Integer>());
        runPQBench("SortedDLinkedListPQ", new chapter9.SortedDLinkedListPriorityQueue<Integer>());
        runPQBench("BinaryHeapPQ", new chapter9.BinaryHeapPriorityQueue<Integer>());
    }

    // -----------------------------
    // SANITY
    // -----------------------------
    private static void sanity() throws Exception {
        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        stacks.add(new chapter9.ArrayListStack<Integer>());
        stacks.add(new chapter9.DLinkedListStack<Integer>());

        for (Stack<Integer> s : stacks) {
            for (int i = 0; i < 10; i++) s.push(i);
            if (s.top() != 9) throw new RuntimeException("Stack top failed");
            for (int i = 9; i >= 0; i--) {
                int v = s.pop();
                if (v != i) throw new RuntimeException("Stack pop order failed");
            }
            if (!s.isEmpty()) throw new RuntimeException("Stack empty failed");
        }

        ArrayList<Queue<Integer>> queues = new ArrayList<>();
        queues.add(new chapter9.ArrayListQueue<>());
        queues.add(new chapter9.DLinkedListQueue<>());

        for (Queue<Integer> q : queues) {
            for (int i = 0; i < 10; i++) q.enqueue(i);
            if (q.front() != 0) throw new RuntimeException("Queue front failed");
            for (int i = 0; i < 10; i++) {
                int v = q.dequeue();
                if (v != i) throw new RuntimeException("Queue dequeue order failed");
            }
            if (!q.isEmpty()) throw new RuntimeException("Queue empty failed");
        }

        PriorityQueue<Integer>[] pqs = new PriorityQueue[] {
            new chapter9.SortedArrayListPriorityQueue<>(),
            new chapter9.SortedDLinkedListPriorityQueue<>(),
            new chapter9.BinaryHeapPriorityQueue<>()
        };

        for (PriorityQueue<Integer> pq : pqs) {
            pq.enqueue(5, 50);
            pq.enqueue(1, 10);
            pq.enqueue(3, 30);
            if (pq.front() != 10) throw new RuntimeException("PQ front failed");
            if (pq.dequeue() != 10) throw new RuntimeException("PQ dequeue 1 failed");
            if (pq.dequeue() != 30) throw new RuntimeException("PQ dequeue 2 failed");
            if (pq.dequeue() != 50) throw new RuntimeException("PQ dequeue 3 failed");
            if (!pq.isEmpty()) throw new RuntimeException("PQ empty failed");
        }

        System.out.println("Sanity checks: OK");
        System.out.println();
    }

    // -----------------------------
    // BENCHMARKS
    // -----------------------------
    private static void runStackBench(String name, Stack<Integer> stack) throws Exception {
        System.out.println("== Stack: " + name + " ==");

        bench("Workload1 bulk push+pop",
                (warm) -> workloadStackBulk(stack, warm ? WARMUP_OPS : MEASURE_OPS));

        bench("Workload2 mixed steady-state",
                (warm) -> workloadStackMixed(stack, warm ? WARMUP_OPS : MEASURE_OPS));

        System.out.println();
    }

    private static void runQueueBench(String name, Queue<Integer> queue) throws Exception {
        System.out.println("== Queue: " + name + " ==");

        bench("Workload1 bulk enq+deq",
                (warm) -> workloadQueueBulk(queue, warm ? WARMUP_OPS : MEASURE_OPS));

        bench("Workload2 mixed steady-state",
                (warm) -> workloadQueueMixed(queue, warm ? WARMUP_OPS : MEASURE_OPS));

        System.out.println();
    }

    private static void runPQBench(String name, PriorityQueue<Integer> pq) throws Exception {
        System.out.println("== PriorityQueue: " + name + " ==");

        bench("Workload1 bulk enq+deq (uniform priorities)",
                (warm) -> workloadPQBulk(pq, warm ? WARMUP_OPS : MEASURE_OPS, false));

        bench("Workload2 mixed steady-state (uniform priorities)",
                (warm) -> workloadPQMixed(pq, warm ? WARMUP_OPS : MEASURE_OPS, false));

        bench("Workload3 skewed priorities (bulk)",
                (warm) -> workloadPQBulk(pq, warm ? WARMUP_OPS : MEASURE_OPS, true));

        System.out.println();
    }

    private static void bench(String label, BenchRun run) throws Exception {
        // warmup
        for (int i = 0; i < 2; i++) run.run(true);

        long[] times = new long[TRIALS];
        long[] sums = new long[TRIALS];

        for (int t = 0; t < TRIALS; t++) {
            Result r = run.run(false);
            times[t] = r.nanos;
            sums[t] = r.checksum;
        }

        Arrays.sort(times);
        long median = times[times.length / 2];

        for (int i = 1; i < sums.length; i++) {
            if (sums[i] != sums[0]) {
                throw new RuntimeException("Checksum mismatch across trials: " + sums[0] + " vs " + sums[i]);
            }
        }

        double nsPerOp = (double) median / (double) MEASURE_OPS;
        System.out.printf("  %-38s  median: %10.2f ns/op   checksum: %d%n", label, nsPerOp, sums[0]);
    }

    // -----------------------------
    // WORKLOADS
    // -----------------------------
    private static Result workloadStackBulk(Stack<Integer> s, int ops) throws Exception {
        resetStack(s);
        int n = ops / 2;

        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) s.push(i);
        for (int i = 0; i < n; i++) sum += s.pop();
        long end = System.nanoTime();

        return new Result(end - start, sum);
    }

    private static Result workloadStackMixed(Stack<Integer> s, int ops) throws Exception {
        resetStack(s);
        Random rng = new Random(SEED);

        for (int i = 0; i < 10_000; i++) s.push(rng.nextInt());

        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < ops; i++) {
            int r = rng.nextInt(100);
            if (r < 60) s.push(rng.nextInt());
            else if (r < 95) { if (!s.isEmpty()) sum += s.pop(); }
            else { if (!s.isEmpty()) sum += s.top(); }
        }
        long end = System.nanoTime();

        return new Result(end - start, sum);
    }

    private static Result workloadQueueBulk(Queue<Integer> q, int ops) throws Exception {
        resetQueue(q);
        int n = ops / 2;

        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) q.enqueue(i);
        for (int i = 0; i < n; i++) sum += q.dequeue();
        long end = System.nanoTime();

        return new Result(end - start, sum);
    }

    private static Result workloadQueueMixed(Queue<Integer> q, int ops) throws Exception {
        resetQueue(q);
        Random rng = new Random(SEED);

        for (int i = 0; i < 10_000; i++) q.enqueue(rng.nextInt());

        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < ops; i++) {
            int r = rng.nextInt(100);
            if (r < 60) q.enqueue(rng.nextInt());
            else if (r < 95) { if (!q.isEmpty()) sum += q.dequeue(); }
            else { if (!q.isEmpty()) sum += q.front(); }
        }
        long end = System.nanoTime();

        return new Result(end - start, sum);
    }

    private static Result workloadPQBulk(PriorityQueue<Integer> pq, int ops, boolean skewed) throws Exception {
        resetPQ(pq);
        Random rng = new Random(SEED);

        int n = ops / 2;
        long sum = 0;

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int pr = skewed ? skewedPriority(rng) : rng.nextInt(10_000);
            pq.enqueue(pr, i);
        }
        for (int i = 0; i < n; i++) sum += pq.dequeue();
        long end = System.nanoTime();

        return new Result(end - start, sum);
    }

    private static Result workloadPQMixed(PriorityQueue<Integer> pq, int ops, boolean skewed) throws Exception {
        resetPQ(pq);
        Random rng = new Random(SEED);

        for (int i = 0; i < 10_000; i++) {
            int pr = skewed ? skewedPriority(rng) : rng.nextInt(10_000);
            pq.enqueue(pr, rng.nextInt());
        }

        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < ops; i++) {
            int r = rng.nextInt(100);
            if (r < 60) {
                int pr = skewed ? skewedPriority(rng) : rng.nextInt(10_000);
                pq.enqueue(pr, rng.nextInt());
            } else if (r < 95) {
                if (!pq.isEmpty()) sum += pq.dequeue();
            } else {
                if (!pq.isEmpty()) sum += pq.front();
            }
        }
        long end = System.nanoTime();

        return new Result(end - start, sum);
    }

    private static int skewedPriority(Random rng) {
        int r = rng.nextInt(100);
        if (r < 90) return rng.nextInt(11); // 0..10
        return 11 + rng.nextInt(100_000 - 11 + 1);
    }

    // -----------------------------
    // RESET HELPERS
    // -----------------------------
    private static void resetStack(Stack<Integer> s) throws Exception {
        while (!s.isEmpty()) s.pop();
    }

    private static void resetQueue(Queue<Integer> q) throws Exception {
        while (!q.isEmpty()) q.dequeue();
    }

    private static void resetPQ(PriorityQueue<Integer> pq) throws Exception {
        while (!pq.isEmpty()) pq.dequeue();
    }

    // -----------------------------
    // SMALL TYPES
    // -----------------------------
    private interface BenchRun {
        Result run(boolean warmup) throws Exception;
    }

    private static class Result {
        final long nanos;
        final long checksum;
        Result(long nanos, long checksum) {
            this.nanos = nanos;
            this.checksum = checksum;
        }
    }
}
