package Stream;

import java.io.*;

public class Adapter {
    public static void main(String[] args) throws IOException {
        String str = "Hello!";
        char[] chars = str.toCharArray();
        OutputStream dst = new FileOutputStream(new File("d:/tmp.txt"));

        try {
            Writer writer = new OutputStreamWriter(dst, "UTF-8");
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
