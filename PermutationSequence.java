import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PermutationSequence {
	@Test
	public void test() {
		Assert.assertEquals("321", getSequenceAt(3, 6));
		Assert.assertEquals("312", getSequenceAt(3, 5));

		Assert.assertEquals("321", getSequenceAtII(3, 6));
		Assert.assertEquals("312", getSequenceAtII(3, 5));
		
		Assert.assertArrayEquals(new int[]{1, 3, 2}, nextPermutation(new int[]{1, 2, 3}));
		Assert.assertArrayEquals(new int[]{1, 2, 3}, nextPermutation(new int[]{3, 2, 1}));

	}

	private String getSequenceAt(int n, int k) {
		int[] nums = new int[n];
		int perm = 1;
		for (int i = 1; i <= n; i++) {
			perm *= i;
			nums[i - 1] = i;
		}
		StringBuilder ret = new StringBuilder();
		k--;
		for (int i = n; i >= 1; i--) {
			perm /= i;
			int x = k / perm;
			k = k % perm;
			ret.append(String.valueOf(nums[x]));
			for (int j = x; j < i - 1; j++) {// what is this loop for?
				nums[j] = nums[j + 1];
			}
		}
		return ret.toString();
	}

	private String getSequenceAtII(int n, int k) {
		int[] nums = new int[n];
		for (int i = 1; i <= n; i++) {
			nums[i - 1] = i;
		}
		List<String> perms = getSequences(n, nums);
		return perms.get(k-1);
	}

	private List<String> getSequences(int n, int[] nums) {
		List<String> ret = new ArrayList<String>();
		if (n == 1) {
			ret.add(String.valueOf(nums[0]));
			return ret;
		}
		for (int i = 0; i < n; i++) {
			// select i and construct a new array of news with i-th element
			// missing
			int[] newNums = new int[n - 1];
			int index = 0;
			for (int j = 0; j < n; j++) {
				if (j != i) {
					newNums[index++] = nums[j];
				}
			}
			List<String> perms = getSequences(n - 1, newNums);
			for (String perm : perms) {
				ret.add(String.valueOf(nums[i]) + perm);
			}
		}
		return ret;
	}

	private int[] nextPermutation(int[] nums) {
		int left = -1;
		for(int i=nums.length-2; i>=0; i--) {
			if(nums[i] < nums[i+1]) {
				left = i;
				break;
			}
		}
		if(left == -1) {
			Arrays.sort(nums);
			return nums;
		}
		int right = -1;
		for(int i=nums.length-1; i>left; i--) {
			if(nums[i] > nums[left]) {
				right = i;
				break;
			}
		}
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
		Arrays.sort(nums, left+1, nums.length);
		return nums;
	}
}
