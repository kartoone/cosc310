package chap13;

public class RecursiveDemo {

    public static void main(String[] args) {
        // informal - non-automated testing
        int tbyte = 0b10110111;
        int bit = extractBit(tbyte, 0);
        System.out.println(bit);

        int reading = 0b00000000111111110000000000000000;
        int passdist = bitextractor(reading, 18, 3);
        int passdist_r = bitextractor_r(reading, 18, 3);
        System.out.println(passdist);
        System.out.println(passdist_r);
        for (int n = -1; n < 100; n++) {
            long result1 = factorial(n);
            // long result2 = factorial_it(n);
            // System.out.println(n + ": " + result1);
            // System.out.println(result2);
        }
    }

    public static int extractBit(int number, int position) {
        return (number >> position) & 1;
    }

    public static int bitextractor_r(int reading, int pos, int n) {
        if (n == 1) {
            return extractBit(reading, pos);
        } else {
            return (extractBit(reading, pos) << (n-1)) | bitextractor_r(reading, pos-1, n-1);
        }
    }
    public static int bitextractor(int reading, int pos, int n) {
        if (pos>n-1) {
            int needsmasking = reading >> (pos-(n-1));
            int mask = 0;
            int currentmaskbit = 1;
            for (int i=0;i<n;i++) {
                mask = mask | currentmaskbit;
                currentmaskbit = currentmaskbit << 1;
            }
            return needsmasking & mask;
        }
        // error case ... invalid pos, n
        return -1;
    }

    public static long factorial(int n) {
        if (n > 0) {
            return n * factorial(n - 1);
        } else {
            return 1L;
        }
    }

    public static long factorial_it(int n) {
        if (n > 0) {
            long result = 1;
            for (int i = n; i > 0; i--) {
                result = result * i;
            }
            return result;
        } else {
            return 1L;
        }
    }
}
