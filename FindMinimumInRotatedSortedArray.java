import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class FindMinimumInRotatedSortedArray {
	@Test
	public void test() {
		Assert.assertEquals(1, findMin(new int[] { 1, 3, 4 }));
		Assert.assertEquals(1, findMin(new int[] { 3, 4, 1 }));
		Assert.assertEquals(1, findMin(new int[] { 4, 1, 3 }));
		Assert.assertEquals(1, findMin(new int[] { 4, 1, 3, 3, 3, 3, 3, 3, 3 }));

		Random seedR = new Random();
		// long seed = seedR.nextLong();
		long seed = -1249770733507074495l;
		System.out.println("seed=" + seed);
		Random r = new Random(seed);
		for (int i = 0; i < 10000; i++) {
			int n = r.nextInt(100) + 5;
			int[] nums = new int[n];
			for (int j = 0; j < n; j++) {
				int x = r.nextInt(1000);
				for (int k = 0; k < r.nextInt(n) && j < n; k++) {
					nums[j++] = x;
				}
			}
			Arrays.sort(nums);
			int m = r.nextInt(n);
			int[] rotated = new int[n];
			System.arraycopy(nums, 0, rotated, n - m, m);
			System.arraycopy(nums, m, rotated, 0, n - m);
			//print(rotated);
			Assert.assertEquals(nums[0], findMin(rotated));

		}
	}

	private void print(int[] nums) {
		System.out.println("array: ");
		for (int n : nums) {
			System.out.print(n + ", ");
		}
		System.out.println();
	}

	private int findMin(int[] nums) {
		int l = 0;
		int h = nums.length - 1;
		while (l + 1 < h) {
			int m = (l + h) / 2;
			if (nums[l] < nums[h]) {
				return nums[l];
			}
			if (nums[l] == nums[h]) {
				l++;
			} else if (nums[m] <= nums[h]) {
				// go left
				h = m;
			} else {
				// go right
				l = m;
			}
		}
		if (l == h) {
			return nums[l];
		} else {
			return Math.min(nums[l], nums[h]);
		}
	}
}
