package Charset;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

public class Charsets {
    public static void main(String[] args) {
        Map<String, Charset> allCharsets = Charset.availableCharsets();
        Set<String> charsetNames = allCharsets.keySet();
        System.out.println(charsetNames);
        System.out.println(charsetNames.size());
    }
}
