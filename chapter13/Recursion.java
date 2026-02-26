package chapter13;

public class Recursion {

    // returns -1 if not found
    // otherwise returns the index of the first occurrence
    public static int search(String needle, String haystack[]) {
        for (int i=0; i<haystack.length; i++) {
            if (needle.equals(haystack[i])) {
                return i;
            }
        }
        return -1;
    }

    public static int search_r(String needle, String haystack[]) {
        return searchHelper(needle, haystack, 0);
    }
    
    public static int search_r2(String needle, String haystack[]) {
        int result = searchHelper2(needle, haystack);
        if (result == haystack.length)
            return -1;
        else
            return result;
    }

    public static int searchHelper(String needle, String haystack[], int i) {
        if (haystack.length==0)
            return -1;
        else if(needle.equals(haystack[0]))
            return i;
        else
            return searchHelper(needle, java.util.Arrays.copyOfRange(haystack,1,haystack.length), i+1);
    }

    public static int searchHelper2(String needle, String haystack[]) {
        if (haystack.length==0)
            return 0;
        else if(needle.equals(haystack[0]))
            return 0;
        else
            return 1+searchHelper2(needle, java.util.Arrays.copyOfRange(haystack,1,haystack.length));
    }

    public static boolean contains(String needle, String haystack[]) {
        if (haystack.length==0)
            return false;
        else if (needle.equals(haystack[0]))
            return true;
        else
            return contains(needle, java.util.Arrays.copyOfRange(haystack,1,haystack.length));
    }

    public static long sum(int lis[]) {
        if (lis.length==1) {
            return lis[0];
        } else {
            return lis[0] + sum(java.util.Arrays.copyOfRange(lis, 1, lis.length));
        }
    }

    public static int count(int lis[]) {
        int c=0;
        for (int i = 0; i < lis.length; i++) {
            c++;
        }
        return c;
    }
    
    public static int count_r(int list[]) {
        if (list.length==0)
            return 0;
        else
            return 1+count_r(java.util.Arrays.copyOfRange(list,1,list.length));
    }

    public static int count_r2(int list[]) {
        return countHelper(list, 0);
    }

    public static int countHelper(int list[], int i) {
        if (list.length==0)
            return i;
        else
            return countHelper(java.util.Arrays.copyOfRange(list,1,list.length), i+1);
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1, 7, 8}));
        System.out.println(args.length);
    }
}
