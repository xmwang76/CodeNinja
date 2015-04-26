import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TaskSchedulerWithCoolDownTime {
	@Test
	public void test() {
		Assert.assertEquals("A__AB_ABCD",
				scheduleTasksWithCoolDownTime("AABABCD", 2));
		Assert.assertEquals("AB____BAC___ABC__AD",
				scheduleTasksWithCoolDownTime("ABBACABCAD", 4));
	}

	private String scheduleTasksWithCoolDownTime(String s, int k) {
		Map<Character, Integer> lastSeen = new HashMap<Character, Integer>();
		StringBuilder ret = new StringBuilder();
		int j = 0;
		for (int i = 0; i < s.length(); i++, j++) {
			char c = s.charAt(i);
			Integer lastIndex = lastSeen.get(c);
			if (lastIndex != null) {
				while (j - lastIndex <= k) {
					ret.append('_');
					j++;
				}
			}
			lastSeen.put(c, j);
			ret.append(c);
		}
		return ret.toString();
	}
}
