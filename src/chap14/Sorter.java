package chap14;

import java.util.ArrayList;

public class Sorter {
    
    /**
     * Sorts the given array in ascending order "in place".
     * @param unsorted - the original array which will be modified by the sort algorithm
     */
    public static void selectionSort(int[] unsorted) {
        for (int i=0; i<unsorted.length-1; i++) {
            // int current = unsorted[i];
            int minrest = findMin(i+1, unsorted);
            swap(i, minrest, unsorted);
        }

    }
    // returns the index of the minimum (not the value)
    public static int findMin(int start, int array[]) {
        int themin = array[start];
        int thewinner = start;
        for (int i=start+1; i<array.length; i++) {
            if (array[i]<themin) {
                thewinner = i;
                themin = array[i];
            }
        }
        return thewinner;
    }

    public static void swap(int i, int j, int array[]) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void bubbleSort(int array[]) {
        // TO DO: implement this!
    }

    /**
     * merge sort recursive fast fun
     * 
     * @param list
     * @return the sorted list
     */
    public static ArrayList<Comparable> mergeSort(ArrayList<Comparable> list) {
        if (list.size()<=1)
            return list;
        
        // Recursive case. First, divide the list into equal-sized sublists
        // consisting of the first half and second half of the list.
        // This assumes lists start at index 0.
        ArrayList<Comparable> left = new ArrayList<>();
        ArrayList<Comparable> right = new ArrayList<>();
        for (int i=0; i<list.size(); i++) {
            if (i<list.size()/2) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }

        // Recursively sort both sublists.
        left = mergeSort(left);
        right = mergeSort(right);

        // Then merge the now-sorted sublists.
        return merge(left, right);
    }

    public static ArrayList<Comparable> merge(ArrayList<Comparable> left, ArrayList<Comparable> right) {
        // Implement this pseudocode to finish mergesort:
        /*
            var result := empty list

            while left is not empty and right is not empty do
                if first(left) ≤ first(right) then
                    append first(left) to result
                    left := rest(left)
                else
                    append first(right) to result
                    right := rest(right)

            // Either left or right may have elements left; consume them.
            // (Only one of the following loops will actually be entered.)
            while left is not empty do
                append first(left) to result
                left := rest(left)
            while right is not empty do
                append first(right) to result
                right := rest(right)
            return result
         */
    }

}
