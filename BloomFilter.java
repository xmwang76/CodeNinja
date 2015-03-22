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
    for(int i=0; i<K; i++) {
      int hash = md5(item) % N;
      int index = hash / 8;
      int position = hash % 8;
      int x = 1 >> position;
      A[index] = A[index] | x;
    }
  }
  
  public boolean exist(String item) {
    for(int i=0; i<K; i++) {
      int hash = md5(item) % N;
      int index = hash / 8;
      int position = hash % 8;
      int x = 1 >> position;
      if(A[index] & x == 0) {
        return false;
      }
    }
    return true;
  }
}
