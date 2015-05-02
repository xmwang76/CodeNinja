import org.junit.Assert;
import org.junit.Test;

public class LongestIncreasingSubsequence {
	@Test
	public void test() {
		Assert.assertEquals(6, lis2( new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 80 }));
	}

	public int lis(int[] nums) {
		return lis(nums, nums.length - 1);
	}

	public int lis(int[] nums, int endingIndex) {
		int ret = 1;
		for (int i = 0; i <= endingIndex; i++) {
			int max = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					int candidate = lis(nums, j) + 1;
					if (candidate > max) {
						max = candidate;
					}
				}
			}
			if (max > ret) {
				ret = max;
			}
		}
		return ret;
	}
	
	public int lis2(int[] nums) {
		int [] dp = new int [nums.length];
		int ret = 1;
		for(int i=0; i< nums.length; i++) {
			int max = 1;
			for(int j=0; j<i; j++) {
				if(nums[j] < nums[i]) {
					if(dp[j] + 1>max) {
						max = dp[j]+1;
					}
				}
			}
			dp[i] = max;
			if(max>ret) {
				ret = max;
			}
		}
		return ret;
	}
}
