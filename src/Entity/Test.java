package Entity;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Test {
    public static void main(String[] args) throws IOException{
        Point point1 = new Point(10, 11);
        Person person1 = new Person(57, "Mike");
        OutputStream dst = System.out;
        EntityOutputStream entityOutputStream = new EntityOutputStream(dst);
        entityOutputStream.writePoint(point1);
        System.out.println();
        entityOutputStream.writePerson(person1);
    }
}
