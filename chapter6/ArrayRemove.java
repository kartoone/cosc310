package chapter6;

import java.util.ArrayList;

public class ArrayRemove extends Target {

    public ArrayRemove(int arr[], ArrayList<Integer> list, String name) {
        super(arr, list, name);
    }    

    // indicesOrnums is ignored!
    // we are removing the first 20,000 items in the array
    // returns the number of items removed
    @Override
    public int method(int[] indicesOrnums) {
        int result = 0;
        int smallerarray[] = new int[arr.length - 20_000];
        for (int i = 20_000; i < arr.length; i++) {
            smallerarray[i-20_000] = arr[i];
            result++;
        }
        return result;
    }


    
}
