/**
 * Bloom Filter implementation:
 * 
 * - add value: set all bits pointed by K hash values computed from (value, i)
 * 
 * - exist value: return true if all bits are set pointed by K hash values
 * computed from (value, i)
 * 
 * @author xmwang
 *
 */
class BloomFilter {
	private final int N;
	private final int K;
	private final byte A[];

	public BloomFilter(int filterSize, int filterDimension) {
		this.N = filterSize;
		this.K = filterDimension;
		this.A = new byte[N];
	}

	public void add(String item) {
		for (int i = 0; i < K; i++) {
			int hash = md5(item, i) % N;
			int index = hash / 8;
			int position = hash % 8;
			byte x = (byte) (1 >> position);
			A[index] = (byte) (A[index] | x);
		}
	}

	public boolean exist(String item) {
		for (int i = 0; i < K; i++) {
			int hash = md5(item, i) % N;
			int index = hash / 8;
			int position = hash % 8;
			byte x = (byte) (1 >> position);
			if ((A[index] & x) == 0) {
				return false;
			}
		}
		return true;
	}

	private int md5(String item, int i) {
		return (item + i).hashCode();
	}
}
