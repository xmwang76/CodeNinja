import org.junit.Assert;
import org.junit.Test;

public class SearchInRotatedSortedArray {
	@Test
	public void test() {
		Assert.assertEquals(0, search(new int[] { 3, 1 }, 3));
		Assert.assertEquals(2, search(new int[] { 3, 5, 1 }, 1));
		Assert.assertEquals(2, search(new int[] { 5, 1, 3 }, 3));
		Assert.assertEquals(0, search(new int[] { 1, 3, 5 }, 1));
		Assert.assertEquals(1, search(new int[] { 1, 3, 5 }, 3));

	}

	public int search(int[] nums, int target) {
		int l = 0;
		int h = nums.length - 1;
		while (l < h) {
			int m = (l + h) / 2;
			if(target == nums[m]) {
				return m;
			}
			if (nums[m] <= nums[h]) {
				// on the low side
				if (target > nums[m] && target <= nums[h]) {
					l = m + 1;
				} else {
					h = m - 1;
				}
			} else {
				// on the high side
				if (target < nums[m] && target >= nums[l]) {
					h = m - 1;
				} else {
					l = m + 1;
				}
			}
		}
		if (nums[l] == target) {
			return l;
		} else {
			return -1;
		}
	}

}
