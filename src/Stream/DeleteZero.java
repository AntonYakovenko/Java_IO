package Stream;

import java.io.*;
import java.util.Arrays;

public class DeleteZero {
    public static void main(String[] args) throws IOException {
        final int BUFF_LEN = 4;
        //final byte[] INPUT_DATA = {0, 0, 1, 1,  1, 0, 0, 0,  0, 1, 0};
        //final byte[] INPUT_DATA = {1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        final byte[] INPUT_DATA = {1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(INPUT_DATA);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        filter(in, out, BUFF_LEN);
        System.out.println(Arrays.toString(out.toByteArray()));
        System.out.println(out.toByteArray().length);
    }

    private static void filter(InputStream in, OutputStream out, int buffSize) throws IOException {
        final int ZERO_STATE = 0;
        final int NUMBERS_STATE = 1;
        byte[] buff = new byte[buffSize];
        int count;
        int state = ZERO_STATE;
        while ((count = in.read(buff)) != -1) {
            int len = 0;
            int from = 0;
            for (int index = 0; index < count; index++) {
                switch (state) {
                    case ZERO_STATE:
                        if (buff[index] == 0) {
                            state = ZERO_STATE;
                        } else {
                            from = index;
                            state = NUMBERS_STATE;
                            len++;
                        }
                        break;
                    case NUMBERS_STATE:
                        if (buff[index] == 0) {
                            out.write(buff, from, len);
                            state = ZERO_STATE;
                        } else {
                            state = NUMBERS_STATE;
                            len++;
                        }
                        break;
                }
            }
            if (state == NUMBERS_STATE) {
                out.write(buff, from, len - 1);
                out.flush();
            }
        }
    }
}
