import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Buffer Class
 * 
 * @author jsapirstein, nahomk
 * @version 10/25/22
 */
public class Buffer {
	
	private RandomAccessFile file;
	private int block;
	private byte[] bytes;
	private int pos;
	private boolean dirty;
	private final static int BLOCK_SIZE = 4096;
	
	/**
	 * 
	 * @param file
	 * 				the file
	 * @param block
	 * 				the block it is current positioned at
	 */
	public Buffer(RandomAccessFile file, int block) {
		
		this.file = file;
		this.block = block;
		bytes = new byte[BLOCK_SIZE];
		pos = block * BLOCK_SIZE;
		dirty = false;
		
		
	}
	
	/**
	 * Gets the current block
	 * @return int
	 */
	public int getBlock() {
		return block;
	}
	
	/**
	 * Gets the bytes
	 * @return byte[]
	 */
	public byte[] read() {
		//Stats.cacheHits++;
		return bytes;
	}
	
	/**
	 * Gets the bytes from the file
	 * @throws IOException
	 */
	public void readFile() throws IOException {
		bytes = new byte[BLOCK_SIZE];
		file.seek(pos);
		file.write(bytes);
		dirty = false;
		//Stats.diskWrites++;
	}
	
	/**
	 * Writes on the bytes
	 * @param bytes
	 * 				bytes to be written
	 */
	public void write(byte[] bytes) {
		this.bytes = bytes;
		dirty = true;
		
	}
	
	/**
	 * Writes the bytes to the file
	 * @throws IOException
	 */
	public void writeFile() throws IOException {
		
		if(dirty) {
			file.seek(pos);
			file.write(bytes);
			dirty = false;
			//Stats.diskWrites++;
		}
		
	}
	

}
