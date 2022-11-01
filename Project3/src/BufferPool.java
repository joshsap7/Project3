import java.io.File;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * BufferPool Class
 * 
 * @author jsapirstein, nahomk
 * @version 10/25/22
 */
public class BufferPool {
	
	private RandomAccessFile file;
	private Buffer[] pool;
	private int numBlocks;
	private Buffer lru;
	private final static int BLOCK_SIZE  = 4096;
    private final static int RECORD_SIZE = 4;
	
	/**
	 * Constructor for BufferPool
	 * @param fileName
	 * 					file
	 * @param numBuffer
	 * 					number of Buffers
	 * @throws IOException 
	 */
	public BufferPool(File fileName, int numBuffer) throws IOException {
		
		file = new RandomAccessFile(fileName, "rw");
		numBlocks = (int)file.length() / BLOCK_SIZE;
		setPool(new Buffer[numBuffer]);
	}
	
	/**
	 * Gets the number of blocks
	 * @return int
	 */
	public int getNumBlocks() {
		return numBlocks;
	}
	
	/**
	 * Gets the pool
	 * @return Buffer[]
	 */
	public Buffer[] getPool() {
		return pool;
	}

	/**
	 * Sets the pool of Buffers
	 * @param pool
	 * 			 The pool of buffers
	 */
	public void setPool(Buffer[] pool) {
		this.pool = pool;
	}

	/**
	 * Gets the buffer
	 * @return Buffer
	 */
	public Buffer getBuffer(int block) throws IOException {
		
		Buffer buffer;
		boolean exists = this.search(block);
		
		if (!exists) {
			buffer = new Buffer(file, block);
			buffer.readFile();
			this.insert(buffer);
		}
		
		return lru;
		
	}
	
	/**
	 * Inserts the buffer into the pool
	 * @param buffer
	 * @throws IOException
	 */
	public void insert(Buffer buffer) throws IOException {
		
		Buffer remove = new Buffer(file, 0); //queue enqueue buffer //TODO
		
		if (remove != null) {
			remove.writeFile();
		}
		
	}
	
	/**
	 * Gets the key of a specified index
	 * @param index
	 * 				index
	 * @return short
	 * @throws IOException
	 */
	public short getKey(int index) throws IOException {
		int block = index * RECORD_SIZE / BLOCK_SIZE;
		int pos = (index * RECORD_SIZE) % BLOCK_SIZE;
		return ByteBuffer.wrap(getBuffer(block).read()).getShort(pos);
	}
	
	/**
	 * Flushes the buffer pool
	 * @throws IOException
	 */
	public void flush() throws IOException {
		
		Buffer remove = new Buffer(file, 0); //pool dequeue //TODO
		
		while (remove != null) {
			remove.writeFile();
			remove = new Buffer(file, 0); //pool dequeue //TODO
		}
	}
	
	/**
	 * Private helper method to find if a buffer exists
	 * @param block
	 * 				block
	 * @return boolean
	 */
	private boolean search(int block) {
		
		//TODO
		
		return true;
	}

}
