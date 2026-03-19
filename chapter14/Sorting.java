package chapter14;

import java.util.ArrayList;
import java.util.List;

public class Sorting {

    private static <T extends Comparable<T>> void swap(ArrayList<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);    
    }
    
    // Comparable
    // o1.compareTo(o2) ... returns -1 if o1 is "less than" o2
    // o1.compareTo(o2) ... returns 0 if o1 is "equal to" o2
    // o1.compareTo(o2) ... returns 1 if o1 is "great than" o2
    // destructive method (i.e., it modifies the original list)
    public static <T extends Comparable<T>> void selectionSort(ArrayList<T> list) {
        // some optimizations
        if (list.size()<2) {
            return;
        }

        for (int i=0; i<list.size()-1; i++) {
            T smallest = list.get(i);
            int smallesti = i;
            for (int j=i+1; j<list.size(); j++) {
                T c = list.get(j);
                if (c.compareTo(smallest)<0) {
                    smallest = c;
                    smallesti = j;
                }
            }
            swap(list, i, smallesti);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(ArrayList<T> list) {
        // some optimizations
        if (list.size()<2) {
            return;
        }

        // This is the "true-to-name" version of insertion sort
        for (int i = 1; i<list.size(); i++) {
            T next = list.remove(i); // remove the item we are trying to insert
            int j=i-1;
            for (; j>=0 && list.get(j).compareTo(next)>0; j--); // this empty for loop finds the right spot for next
            list.add(j+1, next); // insert it at the right spot
        }
 
    }

    public static <T extends Comparable<T>> void bubbleSort(ArrayList<T> list) {
        for (int i=0; i<list.size(); i++)
            for (int j=0; j<list.size()-i-1; j++)
                if (list.get(j).compareTo(list.get(j+1))>0) 
                    swap(list, j, j+1);
    }

    // because this is recursive, it cannot modify the list, it must return a new sorted list
    public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        if (list.size()<=1)
            return list; // it's already sorted ... guaranteed!

        int mid = list.size()/2;
        List<T> leftSorted = mergeSort(list.subList(0,mid));
        List<T> rightSorted = mergeSort(list.subList(mid,list.size()));
        return merge(leftSorted, rightSorted);
    }
    
    private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
        ArrayList<T> sortedList = new ArrayList<>();
        int i=0;
        int j=0;
        while (i<left.size() && j<right.size()) {
            T leftval = left.get(i);
            T rightval = right.get(j);
            if (leftval.compareTo(rightval)<0) {
                sortedList.add(leftval);
                i++;
            } else {
                sortedList.add(rightval);
                j++;
            }
        }
        for (;i<left.size();i++)
            sortedList.add(left.get(i));
        for (;j<right.size();j++)
            sortedList.add(right.get(j));
        return sortedList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(5);
        test.add(7);
        test.add(32);
        test.add(2);
        test.add(15);
        System.out.println(test);
        List<Integer> sorted = mergeSort(test);
        System.out.println(sorted);
        bubbleSort(test);
        System.out.println(test);
        insertionSort(test);
        System.out.println(test);
        selectionSort(test);
        System.out.println(test);
    }


}
