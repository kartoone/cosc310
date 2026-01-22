package chapter6;

import java.util.ArrayList;

public class ListRandom extends Target {

    public ListRandom(int arr[], ArrayList<Integer> list, String name) {
        super(arr, list, name);
    }    

    @Override
    public int method(int[] indicesOrnums) {
        int result = 0;
        for (int i = 0; i < indicesOrnums.length; i++) {
            result += list.get(indicesOrnums[i]);
        }
        return result;
    }


    
}
