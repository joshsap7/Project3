import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * BufferPool Class
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class BufferPool {
	/**
	 * Private fields for the BufferPool
	 */
	private RandomAccessFile file;
	private FiniteLinkedPriorityQueue<Buffer> lru;
	private int blockSize;
	private Buffer[] pool;
	private int maxBuffers;
	
	/**
	 * Creates a BufferPool to be used by the program
	 * @param fileName
	 * 				File that BufferPool uses
	 * @param numBuffers
	 * 				Number of buffers that the BufferPool uses
	 * @param blockSize
	 * 				The size of the blocks of data in the Buffers
	 * @throws IOException
	 */
	public BufferPool(File fileName, int numBuffers,
			int blockSize) throws IOException
	{
		this.blockSize = blockSize;
		
		if (fileName.exists()) { 
			file = new RandomAccessFile(fileName, "rw");
			maxBuffers = ((int) file.length() / blockSize);
			pool = new Buffer[maxBuffers];
			lru = new FiniteLinkedPriorityQueue<Buffer>(numBuffers);
		}
		else {
			throw new FileNotFoundException(); 
		}
	}
	
	/**
	 * Gets the size of the BufferPool
	 * @return int
	 */
	public int size()
	{
		return maxBuffers;
	}
	
	/**
	 * Flushes the entire buffer pool
	 */
	public void flush()
	{
		for (int i = 0; i < maxBuffers; i++) {
			
			pool[i].flush();
			
		}
	}
	
	/**
	 * Gets the Buffer at a specified block
	 * @param block
	 * 			the block number
	 * @return Buffer
	 */
	public Buffer get(int block) {
		
		if (pool[block] == null) {
			
			pool[block] = createBuffer(block);
			
		}
		
		return pool[block];
	}

	/**
	 * Lets the queue know that the buffer has been used
	 * @param buffer
	 * 				The buffer that was used
	 */
	public void used(Buffer buffer)
	{
		Buffer remove = lru.insertOrPromote(buffer);
		
		if (remove != null) {
			
			remove.flush();
			
		}
	}
	
	/**
	 * Lets the queue know that a buffer needs to be flushed
	 * @param buffer
	 * 				The buffer that needs to be flushed
	 */
	public void flushed(Buffer buffer)
	{
		lru.remove(buffer);
	}
	
	/**
	 * Helper method that creates a new Buffer to be added to the BufferPool
	 * @param block
	 * 				the block of bytes
	 * @return Buffer
	 */
	private Buffer createBuffer(int block)
	{
		assert (block < maxBuffers);
		return new Buffer(this, file, block * blockSize, blockSize);
	}
	
}
