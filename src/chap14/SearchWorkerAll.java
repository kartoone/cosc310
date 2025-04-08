package chap14;

import java.util.ArrayList;

public class SearchWorkerAll implements Runnable {
    private int i;
    private int j;
    private int needle;
    private int haystack[];
    private ArrayList<Integer> results = new ArrayList<>();

    public SearchWorkerAll(int i, int j, int needle, int haystack[]) {
        this.i = i;
        this.j = j;
        this.needle = needle;
        this.haystack = haystack;
    }

    public int searchHelper(int i, int j, int needle, int haystack[]) {
        for (int k=i; k<j; k++) {
            if (needle==haystack[k]) {
                results.add(k);
            }
        }
        return -1;
    }

    public void run() {
        searchHelper(i, j, needle, haystack);
    }

    public int[] getResults() {
        return java.util.Arrays.stream(results.toArray(new Integer[results.size()]))
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
