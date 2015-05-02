import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class RegularExpressionMatching {
	@Test
	public void test() {
		Assert.assertEquals(true, isMatch2("bbbba", ".*a*a"));
		Assert.assertEquals(true, isMatch2("aa", "a*"));
		Assert.assertEquals(false,
				isMatch2("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c"));
		Assert.assertEquals(true, isMatch2("a", "ab*"));
		Assert.assertEquals(true, isMatch2("abc", "a.*c"));
		Assert.assertEquals(true, isMatch2("abbc", "ab.*c"));
		Assert.assertEquals(true, isMatch2("ac", "ab*c"));
		Assert.assertEquals(false, isMatch2("abc", "ac"));
		Assert.assertEquals(false, isMatch2("abc", "abcc"));
		Assert.assertEquals(false, isMatch2("abc", ""));
		Assert.assertEquals(false, isMatch2("", "a"));

	}

	public boolean isMatch2(String s, String p) {
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		// initialize dp
		dp[s.length()][p.length()] = true;
		for (int k = 0; k < s.length(); k++) {
			dp[k][p.length()] = false;
		}
		for (int k = p.length() - 1; k >= 0; k--) {
			if (k == p.length() - 1 || p.charAt(k + 1) != '*') {
				dp[s.length()][k] = false;
			} else {
				dp[s.length()][k] = dp[s.length()][k + 2];
				k--; // double jump to skip *
			}
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = p.length() - 1; j >= 0; j--) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
					if (j == p.length() - 1) {
						dp[i][j] = i == s.length() - 1;
					} else if (p.charAt(j + 1) != '*') {
						dp[i][j] = dp[i + 1][j + 1];
					} else {
						dp[i][j] = dp[i + 1][j] || dp[i + 1][j + 2]
								|| dp[i][j + 2];
					}
				} else {
					if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
						dp[i][j] = false;
					} else {
						dp[i][j] = dp[i][j + 2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public boolean isMatch(String s, String p) {
		if (p.length() == 0) {
			return s.length() == 0;
		}
		if (s.length() == 0 || s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
			if (p.length() == 1 || p.charAt(1) != '*') {
				return false;
			} else {
				return isMatch(s, p.substring(2));
			}
		} else {
			if (p.length() == 1 || p.charAt(1) != '*') {
				return isMatch(s.substring(1), p.substring(1));
			} else {
				return isMatch(s.substring(1), p)
						|| isMatch(s.substring(1), p.substring(2))
						|| isMatch(s, p.substring(2));
			}
		}
	}

}
