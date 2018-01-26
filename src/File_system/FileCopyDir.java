package File_system;

import java.io.*;

public class FileCopyDir {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        copy(new File("d:/tmp"), new File("d:/tmp2"));
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println((double) elapsedTime / 1000 + " seconds");
    }

    private static void copy(File src, File dst) throws IOException {
        if (src.isDirectory()) {
            if (!dst.exists()) {
                dst.mkdir();
            }
            for (File srcSubDir : src.listFiles()) {
                String subDirName = srcSubDir.getName();
                copy(srcSubDir, new File(dst, subDirName));
            }
        } else if (src.isFile()) {
            if (!dst.exists()) {
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dst);
                copyFile(fis, fos);
            }
        } else throw new IOException();
    }

    private static void copyFile(FileInputStream src, FileOutputStream dst) throws IOException {
        byte[] buff = new byte[1_922_071];    //Average file size = 1_922_071 bytes (1.922 Mb)
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
