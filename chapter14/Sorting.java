package chapter14;

import java.util.ArrayList;
import java.util.Iterator;

public class Sorting {

    private static void swap(ArrayList list, int i, int j) {
        Object tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);    
    }
    
    // Comparable
    // o1.compareTo(o2) ... returns -1 if o1 is "less than" o2
    // o1.compareTo(o2) ... returns 0 if o1 is "equal to" o2
    // o1.compareTo(o2) ... returns 1 if o1 is "great than" o2
    // destructive method (i.e., it modifies the original list)
    public static void selectionSort(ArrayList list) {
        // some optimizations
        if (list.size()<2) {
            return;
        }

        if (!(list.get(0) instanceof Comparable)) {
            // unsortable
            return;
        }
        
        for (int i=0; i<list.size()-1; i++) {
            Comparable smallest = (Comparable) list.get(i);
            int smallesti = i;
            for (int j=i+1; j<list.size(); j++) {
                Comparable c = (Comparable) list.get(j);
                if (c.compareTo(smallest)<0) {
                    smallest = c;
                    smallesti = j;
                }
            }
            swap(list, i, smallesti);
        }
    }

    public static void insertionSort(ArrayList list) {
        // some optimizations
        if (list.size()<2) {
            return;
        }

        if (!(list.get(0) instanceof Comparable)) {
            // unsortable
            return;
        }
        
        Iterator<Comparable> it = list.iterator();
        it.next();
        int i = 1;
        while (it.hasNext()) {
            Comparable next = it.remove();
            int j=i-1;
            for (; j>=0 && next.compareTo(list.get(j))<0; j--); 
            i++;
        }
    }

    public static void main(String[] args) {
        ArrayList test = new ArrayList<>();
        test.add(5);
        test.add(7);
        test.add(32);
        test.add(2);
        test.add(15);
        System.out.println(test);
        selectionSort(test);
        System.out.println(test);
    }

}
