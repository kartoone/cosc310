package chapter13;

public class ProperIdentation {

    public static void indenter_r(String block, int amount) {
        if (block.length()==0)
            System.out.println();
        else {
            // find the head (beginning of string to first {, inclusive)
            int lbrace = block.indexOf("{");
            int rbrace = block.lastIndexOf("}");
            if (lbrace<0 || rbrace<0) {
                System.out.println(block);
                return;
            }
            String head = block.substring(0,lbrace+1);
            System.out.println(head);
            for (int j=0; j<amount; j++)
                System.out.print(" ");
            indenter_r(block.substring(lbrace+1, rbrace), amount+2);
            for (int j=0; j<amount; j++)
                System.out.print(" ");
            System.out.println(block.substring(rbrace));
        }
    }

    public static void indenter(String input, int amount) {
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i)=='{') {
                System.out.print(input.charAt(i));
                System.out.println();
                amount += 2;
                for (int j=0; j<amount; j++)
                    System.out.print(" ");
            } else if (input.charAt(i)=='}') {
                System.out.println();
                amount -= 2;
                for (int j=0; j<amount; j++)
                    System.out.print(" ");
                System.out.print(input.charAt(i));
            } else {
                System.out.print(input.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        String input = "for (int i=0; i<10; i++) {if (i%2==0) {System.out.println(\"even\"); } else {System.out.println(\"odd\"); } }";
        indenter_r(input, 0);
    }
}
