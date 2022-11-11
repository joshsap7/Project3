
public class Record {
	
	private int key;
	private int value;
	
	public Record(int k, int v) {
		key = k;
		value = v;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int k) {
		key = k;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public void setTo(Record r) {
		key = r.getKey();
		value = r.getValue();
	}
}
