package ByteArrayIOStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BAOSWithStrategy extends OutputStream {
    private static int DEFAULT_START_SIZE = 16;
    private static final AllocateStrategy DEFAULT_ALLOCATE_STRATEGY = new DoubleAllocateStrategy();

    private final AllocateStrategy strategy;
    private final List<byte[]> bufferList = new ArrayList<>(16);
    private int count = 0;

    public BAOSWithStrategy() {
        this(DEFAULT_START_SIZE, DEFAULT_ALLOCATE_STRATEGY);
    }

    BAOSWithStrategy(int startSize) {
        this(startSize, DEFAULT_ALLOCATE_STRATEGY);
    }

    BAOSWithStrategy(AllocateStrategy strategy) {
        this(DEFAULT_START_SIZE, strategy);
    }

    BAOSWithStrategy(int startSize, AllocateStrategy strategy) {
        this.bufferList.add(new byte[startSize]);
        this.strategy = strategy;
    }

    @Override
    public void write(int b) throws IOException {
        byte[] lastBuff = bufferList.get(bufferList.size() - 1);
        if (count == lastBuff.length) {
            int newSize = strategy.nextAfter(lastBuff.length);
            byte[] newLastBuff = new byte[newSize];
            bufferList.add(newLastBuff);
            count = 0;
            lastBuff = newLastBuff;
        }
        lastBuff[count++] = (byte) b;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        byte[] lastBuff = bufferList.get(bufferList.size() - 1);
        int free = lastBuff.length - count;
        if (free < bytes.length) {
            System.arraycopy(bytes, 0, lastBuff, count, free);
            int remainder = bytes.length - free;            // ?
            int newSize = lastBuff.length;
            while (newSize < remainder) {
                newSize = strategy.nextAfter(newSize);
            }
            byte[] newLastBuff = new byte[newSize];
            bufferList.add(newLastBuff);
            System.arraycopy(bytes, free, newLastBuff, 0, remainder);
            count = remainder;
        } else {
            System.arraycopy(bytes, 0, lastBuff, count, bytes.length);
            count += bytes.length;
        }
    }

    @Override
    public void write(byte[] bytes, int off, int len) throws IOException {
        for (int k = off; k < off + len; k++) {
            byte b = bytes[k];
            write(b & 0xFF);
        }
    }

    public void writeTo(OutputStream out) throws IOException {
        for (byte[] byteArray : bufferList) {
            out.write(byteArray);
        }
    }

    public void showBuffers() {
        for (byte[] byteArray : bufferList) {
            System.out.println(Arrays.toString(byteArray));
            System.out.println("length = " + byteArray.length);
        }
    }

    public byte[] toByteArray() {
        int totalSize = 0;
        for (int i = 0; i < bufferList.size() - 1; i++) {
            byte[] currentBuffer = bufferList.get(i);
            totalSize += currentBuffer.length;
        }
        totalSize += count;
        byte[] result = new byte[totalSize];
        int lengthCounter = 0;
        for (int i = 0; i < bufferList.size() - 1; i++) {
            byte[] currentBuffer = bufferList.get(i);
            System.arraycopy(currentBuffer, 0, result, lengthCounter, currentBuffer.length);
            lengthCounter += currentBuffer.length;
        }
        byte[] lastBuff = bufferList.get(bufferList.size() - 1);
        System.arraycopy(lastBuff, 0, result, lengthCounter, count);
        return result;
    }
}
