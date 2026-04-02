package chapter19;

import java.util.stream.IntStream;

public class FunctionalProgramming {

    public static void main(String[] args) {
        String words[] = new String[] { "hello", "there", "i", "am", "asldkfjalsdkfjalsdkfjaldsfkjadslkfjadsl;kfjadslkfjadslkfjadslkfjasdlkfjasdlfkjadsf", "making", "up", "some", "words" };
        double wordlengths[] = java.util.Arrays.stream(words).mapToDouble((w)->(double) w.length()).filter((wl)->wl<15).toArray();
        double avglength = java.util.Arrays.stream(wordlengths).reduce(0.0, Double::sum) / wordlengths.length;
        System.out.println(avglength);

    }
    
}
