import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class FindMedianOfTwoSortedArray {
	@Test
	public void test() {
		Assert.assertEquals(
				findMedianOfTwoSortedArrayBase(new int[] {  },
						new int[] { 1 }),
				findMedianOfTwoSortedArray(new int[] {  }, new int[] {
						1 }), 0.000001);
		Assert.assertEquals(
				findMedianOfTwoSortedArrayBase(new int[] { 1, 5, 6 },
						new int[] { 2, 3, 4, 7, 8 }),
				findMedianOfTwoSortedArray(new int[] { 1, 5, 6 }, new int[] {
						2, 3, 4, 7, 8 }), 0.000001);
		Assert.assertEquals(
				findMedianOfTwoSortedArrayBase(new int[] { 1, 2, 3, 4, 5, 6 },
						new int[] { 2, 3, 4, 5 }),
				findMedianOfTwoSortedArray(new int[] { 1, 2, 3, 4, 5, 6 },
						new int[] { 2, 3, 4, 5 }), 0.000001);
		Assert.assertEquals(
				findMedianOfTwoSortedArrayBase(new int[] { 1, 3, 4, 7, 8, 11,
						44, 55, 62 }, new int[] { 2, 4, 5, 7, 33, 56, 77, 88 }),
				findMedianOfTwoSortedArray(new int[] { 1, 3, 4, 7, 8, 11, 44,
						55, 62 }, new int[] { 2, 4, 5, 7, 33, 56, 77, 88 }),
				0.000001);
		Random seedR = new Random();
		// long seed = seedR.nextLong();
		long seed = 2184050073844249789l;
		System.out.println("seed=" + seed);
		Random r = new Random(seed);
		for (int i = 0; i < 50000; i++) {
			int m = r.nextInt(100);
			int n = r.nextInt(100);
			if (m + n == 0) {
				continue;
			}
			int[] A = new int[m];
			int[] B = new int[n];
			for (int j = 0; j < m; j++) {
				A[j] = r.nextInt(10000);
			}
			for (int j = 0; j < n; j++) {
				B[j] = r.nextInt(10000);
			}
			Arrays.sort(A);
			Arrays.sort(B);
			print(A);
			print(B);
			Assert.assertEquals(findMedianOfTwoSortedArrayBase(A, B),
					findMedianOfTwoSortedArray(A, B), 0.00001);
			Assert.assertEquals(findMedianOfTwoSortedArrayBase(A, B),
					findMedianOfTwoSortedArrayII(A, B), 0.00001);
			System.out.println("done with " + i);
		}
	}

	private double findMedianOfTwoSortedArrayBase(int[] A, int[] B) {
		int[] C = new int[A.length + B.length];
		System.arraycopy(A, 0, C, 0, A.length);
		System.arraycopy(B, 0, C, A.length, B.length);
		Arrays.sort(C);
		print(A);
		print(B);
		// print(C);
		if (C.length % 2 == 0) {
			System.out.println("even");
			int mid = (A.length + B.length) / 2 - 1;
			return (C[mid] + C[mid + 1]) * 0.5;
		} else {
			int mid = (A.length + B.length) / 2;
			System.out.println("odd - mid: " + mid);
			return C[mid];
		}
	}

	private void print(int[] nums) {
		System.out.println("array: ");
		for (int n : nums) {
			System.out.print(n + ", ");
		}
		System.out.println();
	}

	/**
	 * merge sort
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	private double findMedianOfTwoSortedArrayII(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		if ((m + n) % 2 == 0) {
			return (findKthElement(A, B, (m + n) / 2) + findKthElement(A, B,
					(m + n) / 2 + 1)) / 2.0;
		} else {
			return findKthElement(A, B, (m + n) / 2 + 1);
		}
	}

	private double findKthElement(int[] A, int[] B, int k) {
		int i = 0;
		int j = 0;
		int m = 1;
		while (i < A.length && j < B.length && m < k) {
			if (A[i] < B[j]) {
				i++;
			} else {
				j++;
			}
			m++;
		}
		if (j == B.length) {
			i += k - m;
			return A[i];
		} else if (i == A.length) {
			j += k - m;
			return B[j];
		} else {
			// m == k
			return Math.min(A[i], B[j]);
		}
	}

	/**
	 * binary search
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	private double findMedianOfTwoSortedArray(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;

		if ((m + n) % 2 == 0) {
			double a = findKthElement3(A, B, (m + n) / 2);
			double b = findKthElement3(A, B, (m + n) / 2 + 1);
			System.out.println("a=" + a + ",b=" + b);
			return (a + b) / 2.0;
		} else {
			return findKthElement3(A, B, (m + n) / 2 + 1);
		}

	}

	private double findKthElement3(int[] A, int[] B, int k) {
		int startA = 0;
		int startB = 0;
		int endA = A.length - 1;
		int endB = B.length - 1;
		int lenA = A.length;
		int lenB = B.length;
		while (lenA > 0 && lenB > 0 && k > 0 && k < lenA + lenB) {
			int kthA = lenA * k / (lenA + lenB);
			int kthB = k - kthA - 1;

			kthA += startA;
			kthB += startB;
			if (A[kthA] > B[kthB]) {
				// chop off B's left and A's right
				// take A[startA..kthA-1] and B[kthB..endB]
				k = k - (kthB - startB);
				endA = kthA - 1; // exclude the bigger one
				startB = kthB; // keep the smaller one
			} else if (A[kthA] < B[kthB]) {
				// chop off A's left and B's right
				// take A[kthA..endA] and B[startB..kthB-1]
				k = k - (kthA - startA);
				endB = kthB - 1; // exclude the bigger one
				startA = kthA; // keep the smaller one
			} else {
				return A[kthA];
			}
			lenA = endA - startA + 1;
			lenB = endB - startB + 1;
		}
		if (k == 0) {
			return Math.min(A[startA], B[startB]);
		} else if (lenA == 0) {
			return B[startB + k - 1];
		} else if (lenB == 0) {
			return A[startA + k - 1];
		} else {
			// k == lenA + lenB
			return Math.max(A[endA], B[endB]);
		}
	}

}
