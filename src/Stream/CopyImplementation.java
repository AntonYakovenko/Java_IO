package Stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class CopyImplementation {
    public static void main(String[] args) throws IOException {
        InputStream src = new URL("http://google.com").openStream();
        OutputStream dst = new FileOutputStream("d:/tmp/GOOGLE.txt");
        copy(src, System.out);
    }

    private static void copy(InputStream src, OutputStream dst) throws IOException {
        while (true) {
            int data = src.read();
            if (data != -1) {
                dst.write(data);
            } else {
                return;
            }
        }
    }

    public static void copyImproved(InputStream src, OutputStream dst) throws IOException {
        byte[] buff = new byte[64 * 1024];
        while (true) {
            int count = src.read(buff);
            if (count != -1) {
                dst.write(buff, 0, count);
            } else {
                return;
            }
        }
    }
}
