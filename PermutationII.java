import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


public class PermutationII {
	@Test
	public void test() {
		Assert.assertEquals(null, permuteUnique(new int[]{-1,-1,3,-1}));
	}
	public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ret.add(new ArrayList<Integer>());
        for(int i=0; i<nums.length; i++) {
            List<List<Integer>> current = new ArrayList<List<Integer>>();
            Set<List<Integer>> seen = new HashSet<List<Integer>>();
            for(List<Integer> p : ret) {
                for(int j=0; j<=p.size(); j++) {
                    List<Integer> candidate = new ArrayList<Integer>(p);
                    candidate.add(j, nums[i]);
                    if(!seen.contains(candidate)) {
                        seen.add(candidate);
                        current.add(candidate);
                    }
                }
            }
            ret = current;
        }
        return ret;
    }
}
