package chapter6;

import java.util.ArrayList;

public class ArrayAppend extends Target {

    public ArrayAppend(int arr[], ArrayList<Integer> list, String name) {
        super(arr, list, name);
    }    

    @Override
    public int method(int[] indicesOrnums) {
        int result = 0;
        int largerarr[] = java.util.Arrays.copyOf(arr, arr.length+indicesOrnums.length);
        for (int i = 0; i < indicesOrnums.length; i++) {
            result += arr[indicesOrnums[i]];
            largerarr[arr.length+i] = indicesOrnums[i];
        }
        return result;
    }


    
}
