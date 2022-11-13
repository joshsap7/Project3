import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Records Class
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class Records {
    private BufferPool pool;
    private final static int BLOCK_SIZE = 4096;
    private final static int RECORD_SIZE = 4;

    /**
     * Creates new Records object
     * 
     * @param filename   String
     * @param numBuffers number of buffers
     * @throws IOException
     */
    public Records(String filename, int numBuffers) throws IOException {
        File file = new File(filename);
        pool = new BufferPool(file, numBuffers, BLOCK_SIZE);
    }

    /**
     * Gets the key at a specified index
     * 
     * @param index specified index
     * @return key
     */
    public short getKey(int index) {
        int block = (index * RECORD_SIZE) / BLOCK_SIZE;
        int offset = (index * RECORD_SIZE) % BLOCK_SIZE;
        byte[] buff = pool.get(block).readBuffer();
        return ByteBuffer.wrap(buff).getShort(offset);
    }

    /**
     * Gets the value at a specified index
     * 
     * @param index specified index
     * @return key
     */
    public short getValue(int index) {
        int block = (index * RECORD_SIZE) / BLOCK_SIZE;
        int offset = (index * RECORD_SIZE) % BLOCK_SIZE;
        byte[] buff = pool.get(block).readBuffer();
        return ByteBuffer.wrap(buff).getShort(offset + 2);
    }

    /**
     * Swaps two records a specified indices
     * 
     * @param pos1 first record
     * @param pos2 second record
     */
    public void swap(int pos1, int pos2) {
        byte[] record1 = new byte[RECORD_SIZE];
        byte[] record2 = new byte[RECORD_SIZE];
        int block1 = (pos1 * RECORD_SIZE) / BLOCK_SIZE;
        int block2 = (pos2 * RECORD_SIZE) / BLOCK_SIZE;
        int offset1 = (pos1 * RECORD_SIZE) % BLOCK_SIZE;
        int offset2 = (pos2 * RECORD_SIZE) % BLOCK_SIZE;

        Buffer buffer1 = pool.get(block1);
        Buffer buffer2 = pool.get(block2);

        byte[] buffer;

        buffer = buffer1.readBuffer();
        System.arraycopy(buffer, offset1, record1, 0, RECORD_SIZE);
        buffer = buffer2.readBuffer();
        System.arraycopy(buffer, offset2, record2, 0, RECORD_SIZE);
        System.arraycopy(record1, 0, buffer, offset2, RECORD_SIZE);
        buffer2.writeBuffer(buffer);
        buffer = buffer1.readBuffer();
        System.arraycopy(record2, 0, buffer, offset1, RECORD_SIZE);
        buffer1.writeBuffer(buffer);
    }

    /**
     * Gets the size of the heap
     * 
     * @return int
     */
    public int size() {
        return (pool.size() * (BLOCK_SIZE / RECORD_SIZE));
    }

    /**
     * Flushes to the disk
     */
    public void flush() {
        pool.flush();
    }
}
