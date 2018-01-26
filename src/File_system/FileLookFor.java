package File_system;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileLookFor {
    private static final List<File> allFiles = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("d:/tmp");
        print(file);
        System.out.println(allFiles);
    }

    private static void print(File root) {
        if (root.isFile()) {
            if (root.getPath().endsWith(".jpg") && root.length() > 1024 * 1024) {
                allFiles.add(root);
            }
        } else {
            File[] fileArray = root.listFiles();
            for (File file : fileArray) {
                print(file);
            }
        }
    }

}
