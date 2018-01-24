package Entity;

import java.io.IOException;

public interface EntityInput {
    public Person readPerson() throws IOException;

    public Point readPoint() throws IOException;
}
