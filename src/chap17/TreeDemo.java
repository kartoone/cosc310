package chap17;

public class TreeDemo {
    public static void main(String[] args) {
        // Tree<String> airports = new Tree<>("BHM");
        // airports.addChild(airports.root(),"ATL");
        // Tree<String>.TreeNode<String> msp = airports.addChild(airports.root(),"MSP");
        // airports.addChild(msp,"JFK");
        // airports.addChild(msp,"ORD");
        // System.out.println(airports);
        // System.out.println(airports.toString(msp, 0));
        // System.out.println(airports.search("BHM"));
        // System.out.println(airports.searchBFS("BHM"));
        // System.out.println(airports.search("ABC"));
        // System.out.println(airports.searchBFS("ABC"));
        // System.out.println(airports.search("ORD"));
        // System.out.println(airports.searchBFS("ORD"));

        Tree<String> airports = createTreeSimple("BHM,ATL,MSP,JFK,ORD");
        System.out.println(airports);

        System.out.println();

        String csvstr = "ATL,PEK,LAX,DXB,HND,ORD,LHR,HKG,PVG,CDG,DFW,CAN,AMS,FRA,IST,DEL,JFK,SIN,ICN,DEN,BKK,SFO,KUL,MAD,LAS,CTU,BCN,SEA,PHX,MIA,MUC,SYD,FCO,EWR,MCO,SHA,CLT,YYZ,LGW,MSP,BOM,SZX,MEL,MNL,IAH,KMG,ZRH,GRU,OSL,ARN,DME,DOH,VIE,BNE,CPH,HEL,BRU,SVO,MXP,MAN,LIM,YVR,TXL,NRT,AUH,STN,LGG,LIS,HOU,BOG,PRG,WAW,ATH,GIG,LGA,CAI,TPE,SGN,LED,BUD,CGK,TXL,OTP,BSB,GMP,CHC,CMN,SAN,OKA,AKL,THR,TUN,ALG,KWI,DUB,CRK,CPT,ABV,BNE,SGF,ICT,OMA,ELP,BUF,BHM,HSV,MDT,PSP,RNO,GEG,MYR,ORF,ANC,FLL,PIT,CLE,SNA,JAX,MSY,SMF,PDX";
        Tree<String> leftleaning = createLeftLeaningBinaryTree(csvstr);
        System.out.println(leftleaning.size());
        System.out.println(leftleaning.height());// UNCOMMENT ONCE YOU HAVE IMPLEMTED HEIGHT

        System.out.println();

        Tree<String> rightleaning = createRightLeaningBinaryTree(csvstr);
        System.out.println(rightleaning.size());
        System.out.println(rightleaning.height());// UNCOMMENT ONCE YOU HAVE IMPLEMTED HEIGHT
        
        System.out.println();

        Tree<String> balanced = createBalancedBinaryTreeCorrect(csvstr);
        System.out.println(balanced);
        System.out.println(balanced.size());
        System.out.println(balanced.height()); // UNCOMMENT ONCE YOU HAVE IMPLEMTED HEIGHT
    }

    public static Tree<String> createTreeSimple(String csvdata) {
        String items[] = csvdata.split(",");
        if (items.length == 0)
            return new Tree<>();

        Tree<String> simple = new Tree<>(items[0]);
        for (int i=1; i<items.length; i++) {
            simple.addChild(simple.root(), items[i]);
        }

        return simple;
    }

    public static Tree<String> createRightLeaningBinaryTree(String csvdata) {
        String items[] = csvdata.split(",");
        if (items.length == 0)
            return new Tree<>();

        Tree<String> rightleaning = new Tree<>(items[0]);
        Tree<String>.TreeNode<String> parent = rightleaning.root();
        int childcount = 0;
        for (int i=1; i<items.length; i++) {
            if (childcount==2) {
                parent = parent.children.get(1);
                childcount = 1;
            } else {
                childcount++;
            }
            rightleaning.addChild(parent, items[i]);
        }
        return rightleaning;
    }

    public static Tree<String> createLeftLeaningBinaryTree(String csvdata) {
        String items[] = csvdata.split(",");
        if (items.length == 0)
            return new Tree<>();

        Tree<String> leftleaning = new Tree<>(items[0]);
        Tree<String>.TreeNode<String> parent = leftleaning.root();
        int childcount = 0;
        for (int i=1; i<items.length; i++) {
            if (childcount==2) {
                parent = parent.children.get(0);
                childcount = 1;
            } else {
                childcount++;
            }
            leftleaning.addChild(parent, items[i]);
        }
        return leftleaning;
    }

    // THIS DOESN'T WORK - IT STILL CREATES AN UNBALANCED TREE - MAKE SURE YOU UNDERSTAND WHY!
    public static Tree<String> createBalancedBinaryTree(String csvdata) {
        java.util.Random random = new java.util.Random();
        String items[] = csvdata.split(",");
        if (items.length == 0)
            return new Tree<>();

        Tree<String> balanced = new Tree<>(items[0]);
        Tree<String>.TreeNode<String> parent = balanced.root();
        int childcount = 0;
        for (int i=1; i<items.length; i++) {
            if (childcount==2) {
                parent = parent.children.get(random.nextInt(2));
                childcount = 1;
            } else {
                childcount++;
            }
            balanced.addChild(parent, items[i]);
        }
        return balanced;
    }

    // CORRECT balanced tree creation ... need a helper variable to keep track of the parent
    public static Tree<String> createBalancedBinaryTreeCorrect(String csvdata) {
        String items[] = csvdata.split(",");
        Tree<String> balanced = new Tree<>(items[0]);
        createBBTHelper(balanced.root(), java.util.Arrays.copyOfRange(items, 1, items.length));
        return balanced;
    }
        
    // recursive helper ... no return method b/c we are always manipulating nodes within the tree which indirectly affects the original tree!
    private static void createBBTHelper(Tree<String>.TreeNode<String> parent, String[] remainingitems) {
        if (remainingitems.length == 0)
            return; // base case - we have no more items to add to whatever branch of the tree we are on 

        // need to be initialized depending on one of several conditions below
        Tree<String>.TreeNode<String> leftchild;
        Tree<String>.TreeNode<String> rightchild;

        if (parent.children == null) {
            leftchild = new Tree<String>().new TreeNode<String>(parent, null, remainingitems[0]);
            parent.addChild(leftchild);
            remainingitems = java.util.Arrays.copyOfRange(remainingitems, 1, remainingitems.length);
            if (remainingitems.length > 0) {
                rightchild = new Tree<String>().new TreeNode<String>(parent, null, remainingitems[0]);
                parent.addChild(rightchild);
                if (remainingitems.length > 0)
                    remainingitems = java.util.Arrays.copyOfRange(remainingitems, 1, remainingitems.length);
                else
                    return;
            } else {
                return; // we have officially run out of items with the new leftchild we just added so no need to continue recursion
            }
        } else {
            leftchild = parent.children.get(0);
            if (parent.children.size() == 1) {
                rightchild = new Tree<String>().new TreeNode<String>(parent, null, remainingitems[0]);
                parent.addChild(rightchild);
                remainingitems = java.util.Arrays.copyOfRange(remainingitems, 1, remainingitems.length);
                if (remainingitems.length > 0)
                    remainingitems = java.util.Arrays.copyOfRange(remainingitems, 1, remainingitems.length);
                else
                    return;
            } else {
                rightchild = parent.children.get(1);
            }
        }
        if (remainingitems.length > 1) {
            createBBTHelper(leftchild, java.util.Arrays.copyOfRange(remainingitems, 0, remainingitems.length/2));
            createBBTHelper(rightchild, java.util.Arrays.copyOfRange(remainingitems, remainingitems.length/2, remainingitems.length));
        } else {
            createBBTHelper(leftchild, remainingitems); // only one item left so just add it to the leftchild
        }
    }


}
