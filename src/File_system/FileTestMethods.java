package File_system;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class FileTestMethods {
    public static void main(String[] args) {
        String[] names = new File("d:/tmp/").list();
        System.out.println(Arrays.toString(names));

        File file = new File("d:/tmp/Beauty.jpg");
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.canExecute());
        System.out.println(file.getTotalSpace());
        System.out.println(file.getUsableSpace());
        System.out.println(file.lastModified()); // returns time in milliseconds since the epoch (00:00:00 GMT, January 1, 1970)
        System.out.println(new Date(file.lastModified()));
        System.out.println(new Date());

    }
}
