package Entity;

import java.io.*;

public class EntityInputStream implements EntityInput {
    private final DataInput in;

    public EntityInputStream(InputStream in) {
        this.in = new DataInputStream(in);
    }

    @Override
    public Person readPerson() throws IOException {
        int age = in.readInt();
        String name;
        if (!in.readBoolean()) {
            name = null;
        } else {
            name = in.readUTF();
        }
        return new Person(age, name);
    }

    @Override
    public Point readPoint() throws IOException {
        int xy = in.readInt();
        int x = xy >> 4;
        int y = xy & 0b1111;
        return new Point(x, y);
    }

}
