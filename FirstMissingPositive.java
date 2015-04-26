import org.junit.Assert;
import org.junit.Test;

public class FirstMissingPositive {
	@Test
	public void test() {
		Assert.assertEquals(3, firstMissingPositive(new int[] { 2, 1 }));
		Assert.assertEquals(2, firstMissingPositive(new int[] { 3, 4, -1, 1 }));
		Assert.assertEquals(2, firstMissingPositive(new int[] { 1, 1 }));
		Assert.assertEquals(3, firstMissingPositiveII(new int[] { 2, 1 }));
		Assert.assertEquals(2,
				firstMissingPositiveII(new int[] { 3, 4, -1, 1 }));
		Assert.assertEquals(2, firstMissingPositiveII(new int[] { 1, 1 }));
	}

	/**
	 * scan through the array and for each element: - if its value is positive
	 * and within range and is not at the proper location and the right location
	 * for it is not valid - then swap it with the value at its proper location
	 * - since this location now contains a new value, keep doing that until the
	 * value is proper - else move on
	 * 
	 * @param A
	 * @return
	 */
	public int firstMissingPositive(int[] A) {
		for (int i = 0; i < A.length; i++) {
			while (true) {
				int j = A[i] - 1;
				if (j != i && A[i] <= A.length && A[i] > 0 && A[i] != A[j]) {
					// swap(i, A[i]-1);
					int tmp = A[i];
					A[i] = A[j];
					A[j] = tmp;
				} else {
					break;
				}
			}
		}

		for (int i = 0; i < A.length; i++) {
			if (A[i] != i + 1) {
				return i + 1;
			}
		}
		return A.length + 1;
	}

	/**
	 * Mark the right location of a value by setting the value of the right
	 * location with negative
	 * 
	 * @param A
	 * @return
	 */
	public int firstMissingPositiveII(int[] A) {
		int j = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= 0) {
				int tmp = A[j];
				A[j] = A[i];
				A[i] = tmp;
				j++;
			}
		}

		for (int i = 0; i < A.length - j; i++) {
			int x = Math.abs(A[i + j]) - 1;
			if (x + j < A.length) {
				A[x + j] = -Math.abs(A[x + j]);
			}
		}
		for (int i = 0; i < A.length - j; i++) {
			if (A[i + j] > 0) {
				return i + 1;
			}
		}
		return A.length - j + 1;
	}
}
