package review;

import java.util.Map;
import java.util.HashMap;

class Solution {
    Map<String, Integer> m = new HashMap<>();

    Solution() {
        m.put("I", 1);
        m.put("V", 5);
        m.put("X", 10);
        m.put("L", 50);
        m.put("C", 100);
        m.put("D", 500);
        m.put("M", 1000);
    }

    public int romanToInt(String s) {
        int result = 0;
        int sign = 1;
        for (int i=s.length()-1; i>=0 ;i--) {
            String numeral = ""+s.charAt(i);
            int value = m.get(numeral);
            result += (sign)*value;
            if (i>0) {
                int prevValue = m.get(""+s.charAt(i-1));
                if (prevValue < value) {
                    sign = -1;
                } else {
                    sign = 1;
                }
            }
        }
        return result;
    }
}