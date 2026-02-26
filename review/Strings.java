package review;

// this demonstrates danger of using "==" for string comparison

public class Strings {

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "hello";
        System.out.printf("\"%s\".equals(\"%s\") evaluates to %b as expected\n", str1, str2, str1.equals(str2));
        System.out.printf("\"%s\"==\"%s\" ALSO evaluates to %b\n", str1, str2, str1==str2);
        System.out.println("str1==str2 evaluates to true b/c string literals with same characters end up being the SAME string object");
        System.out.println();
        String str3 = "hellos";
        String str4 = "hello";
        System.out.println("In our second example we are setting str3 and str4 to different string literals but then taking substring on str3 so that it has the same characters as str4. Watch what happens to \"==\"");
        str3 = str3.substring(0,str3.length()-1); // hello
        System.out.printf("\"%s\".equals(\"%s\") evaluates to %b as expected\n", str3, str4, str3.equals(str4));
        System.out.printf("BUT \"%s\"==\"%s\" evaluates to %b\n", str3, str4, str3==str4);
        
    }
    
}
