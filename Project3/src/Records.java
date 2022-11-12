import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Records Class
 * 
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class Records {
	
	private BufferPool pool;
	
	//Private constants for the Records class
	private final static int BLOCK_SIZE = 4096;
	private final static int RECORD_SIZE = 4;
	
	/**
	 * Creates a Records object
	 * @param filename
	 * 				The name of the RandomAccessFile
	 * @param numBuffers
	 * 				Number of buffers
	 * @throws IOException
	 */
	public Records(String filename, int numBuffers) throws IOException
	{
		File file = new File(filename);
		pool = new BufferPool(file, numBuffers, BLOCK_SIZE);
	}
	
	/**
	 * Gets the number of records
	 * @return int
	 */
	public int size()
	{
		return (pool.size() * (BLOCK_SIZE / RECORD_SIZE));
	}
	
	/**
	 * Flushes the BufferPool
	 */
	public void flush()
	{
		pool.flush();
	}
	
	/**
	 * Gets the key of a record at a specified index
	 * @param index
	 * 			The index to get the key
	 * @return short
	 */
	public short getKey(int index)
	{
		int block = (index * RECORD_SIZE) / BLOCK_SIZE;
		int offset = (index * RECORD_SIZE) % BLOCK_SIZE;
		byte[] buff = pool.get(block).readBuffer();
		return ByteBuffer.wrap(buff).getShort(offset + 2);
	}
	
	/**
	 * Gets the value of a record at a specified index
	 * @param index
	 * 			The index to get the key
	 * @return short
	 */
	public short getValue(int index)
	{
		int block = (index * RECORD_SIZE) / BLOCK_SIZE;
		int offset = (index * RECORD_SIZE) % BLOCK_SIZE;
		byte[] buff = pool.get(block).readBuffer();
		return ByteBuffer.wrap(buff).getShort(offset + 2);
	}
	
	/**
	 * Swap operation to swap two records
	 * @param index1
	 * 			index of first record
	 * @param index2
	 * 			index of second record
	 */
	public void swap(int index1, int index2)
	{
		int block1 = (index1 * RECORD_SIZE) / BLOCK_SIZE;
		int block2 = (index2 * RECORD_SIZE) / BLOCK_SIZE;
		int offset1 = (index1 * RECORD_SIZE) % BLOCK_SIZE;
		int offset2 = (index2 * RECORD_SIZE) % BLOCK_SIZE;
		
		byte[] record1 = new byte[RECORD_SIZE];
		byte[] record2 = new byte[RECORD_SIZE];
		
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
}
