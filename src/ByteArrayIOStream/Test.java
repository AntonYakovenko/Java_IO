package ByteArrayIOStream;

import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        BAOSWithStrategy out = new BAOSWithStrategy(2, new DoubleAllocateStrategy());
        final int LIMIT = 20;
        final int SIZE = 70;

        byte[] insertionArray = new byte[SIZE];
        for(int i = 0; i < SIZE; i++) {
            insertionArray[i] = (byte) i;
        }

//        int[] testArray = new int[LIMIT];

        for (int k = 0; k < LIMIT; k++){
            byte insertionValue = (byte) (0xFF * Math.random());
            out.write(insertionValue);
//            testArray[k] = insertionValue;
        }
        out.showBuffers();
        System.out.println("Result ArrayGot:  " + Arrays.toString(out.toByteArray()));
//        System.out.println("Result ArrayReal: " + Arrays.toString(testArray));

        out.write(insertionArray);
        System.out.println(Arrays.toString(out.toByteArray()));
    }
}
