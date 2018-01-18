package Charset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class IOTestCharset {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str0 = new String(new byte[]{0, 1, 2}, "UTF-8");
        System.out.println("str0 = " + str0);

        byte[] bytes = {70, 71, 72};
        String str1 = new String(bytes, "UTF-8");
        System.out.println("str1 = " + str1);
        char[] chars = str1.toCharArray();
        System.out.println(chars[0]);
        System.out.println(chars[1] + 0);
        System.out.println((int) chars[2]);

        byte[] bytes1 = {70, 71, 72, 32, -35, -34, -33};
        System.out.println(new String(bytes1, "cp1251"));
        System.out.println(new String(bytes1, "UTF-8"));
        System.out.println(Arrays.toString(new String(bytes1, "UTF-8").toCharArray()));
        System.out.println(Arrays.toString(new String(bytes1, "cp1251").toCharArray()));
    }
}
