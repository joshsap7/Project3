import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Buffer Class
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class Buffer {

    /**
     * Private fields for a Buffer
     */
    private RandomAccessFile file;
    private BufferPool pool;
    private int block;
    private int blockSize;
    private byte[] bytes;
    private boolean dirty;
    private boolean loaded;

    /**
     * Creates a new Buffer
     * 
     * @param pool      the BufferPool
     * @param file      the file
     * @param block     the block
     * @param blockSize the size of the block
     */
    public Buffer(BufferPool pool, RandomAccessFile file, int block, int blockSize) {

        this.file = file;
        this.pool = pool;
        this.blockSize = blockSize;
        this.block = block;
        this.dirty = false;
        this.loaded = false;
    }

    /**
     * Gets the block size
     * 
     * @return int
     */
    public int getBlockSize() {

        return blockSize;

    }

    /**
     * Flushes the buffer
     */
    public void flush() {

        // If the buffer is dirty, write the bytes to the file
        if (dirty) {
            writeFile();
        }

        bytes = null;
        loaded = false;
    }

    /**
     * Reads in bytes from the buffer
     * 
     * @return byte[] The string of bytes meant to be read
     */
    public byte[] readBuffer() {

        // Lets the BufferPool know that the buffer has now been used
        pool.used(this);

        // Checks if the buffer has been loaded in to the buffer pool yet
        if (!loaded) {
            // If it has not been loaded, it is not in cache and gets read in
            Stats.incrementCM();
            readFile();
        } 
        else {

            // The buffer is in cache
            Stats.incrementCH();
        }
        return bytes.clone();
    }

    /**
     * Private helper method to read directly from the file
     */
    private void readFile() {
        bytes = new byte[blockSize];
        try {
            file.seek(block);
            file.read(bytes);
            Stats.incrementDR();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        loaded = true;
        dirty = false;
    }

    /**
     * Writes the data given to the current buffer
     * 
     * @param data bytes[]
     */
    public void writeBuffer(byte[] data) {

        // Lets the BufferPool know that the buffer has now been used
        pool.used(this);

        // Creates a copy of the bytes to be stored locally inside the buffer
        this.bytes = data.clone();

        // Buffer is now dirty and loaded
        this.dirty = true;
        this.loaded = true;
    }

    /**
     * Private helper method to write directly to the file
     */
    private void writeFile() {
        try {
            file.seek(block);
            file.write(bytes);
            Stats.incrementDW();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        dirty = false;
    }

}
