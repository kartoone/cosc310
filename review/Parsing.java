package review;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import chapter13.Palindrome;

public class Parsing {

    public static void main(String[] args) {
        File f = new File("palindromes.csv");
        try (Scanner in = new Scanner(f)) {
            in.nextLine(); // skip the first line, which is the header
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String parts[] = line.split(",");
                String sentence = parts[0];
                int start = Integer.parseInt(parts[1]);
                int end = Integer.parseInt(parts[2]);
                String phrase = sentence.substring(start,end+1);
                System.out.println(phrase + " ==P?== " + Palindrome.palin(phrase));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
}
