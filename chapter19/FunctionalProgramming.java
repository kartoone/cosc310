package chapter19;

import java.util.Arrays;

public class FunctionalProgramming {

    public static void main(String[] args) {
        String words[] = new String[] { "hello", "there", "i", "am", "asldkfjalsdkfjalsdkfjaldsfkjadslkfjadsl;kfjadslkfjadslkfjadslkfjasdlkfjasdlfkjadsf", "making", "up", "some", "words" };
        int wordlengths[] = Arrays.stream(words).mapToInt((w)-> w.length()).toArray();
        // note that map is producing a new array of doubles that is the same length as the original array of strings, but each element is the length of the corresponding string in the original array 
        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(wordlengths));
        double avglength1 = Arrays.stream(wordlengths).reduce(0, Integer::sum) / (double) wordlengths.length;
        System.out.printf("%.2f\n", avglength1);

        // what if we want to filter out the long words and only look at the short words? we can use filter to do that ... note that filter produces a new stream that is a subset of the original stream, but it does not change the original stream
        System.out.println("After filtering...");
        String shortwords[] = Arrays.stream(words).filter((w)->w.length()<15).toArray(String[]::new);
        System.out.println(Arrays.toString(shortwords));
        int shortwordlengths[] = Arrays.stream(words).filter((w)->w.length()<15).mapToInt((w)-> w.length()).toArray();
        System.out.println(Arrays.toString(shortwordlengths));

        // calculate average word length after filtering
        double avglength2 = Arrays.stream(shortwordlengths).reduce(0, Integer::sum) / (double) shortwordlengths.length;
        System.out.println(avglength2);
    }
    
}
