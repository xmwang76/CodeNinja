import org.junit.Assert;
import org.junit.Test;

/*
 Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 */

public class WildcardMatching {
	@Test
	public void test() {
		Assert.assertEquals(false, isMatch3("babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab", "***bba**a*bbba**aab**b"));
		Assert.assertEquals(true, isMatch3("bb", "?*?"));
		Assert.assertEquals(true, isMatch3("aa", "aa"));
		Assert.assertEquals(false, isMatch3("aaa", "aa"));
		Assert.assertEquals(true, isMatch3("aa", "a*"));
		Assert.assertEquals(true, isMatch3("aa", "*"));
		Assert.assertEquals(true, isMatch3("aa", "a*"));
		Assert.assertEquals(true, isMatch3("aa", "?*"));
		Assert.assertEquals(false , isMatch3("aab", "c*a*b"));
		
	}

	public boolean isMatch(String s, String p) {

		if (p.length() == 0) {
			return s.length() == 0;
		}

		if (s.length() == 0 || s.charAt(0) != p.charAt(0) && p.charAt(0) != '?') {
			if (p.charAt(0) == '*') {
				return isMatch(s, p.substring(1))
						|| s.length() != 0
						&& (isMatch(s.substring(1), p) || isMatch(
								s.substring(1), p.substring(1)));
			} else {
				return false;
			}
		} else {
			return isMatch(s.substring(1), p.substring(1));
		}
	}
	
	public boolean isMatch2(String s, String p) {
//		if (p.replace("*", "").length() > s.length()) {
//	        return false;
//		}
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		// initialize
		dp[s.length()][p.length()] = true;
		
		for(int i=0; i<s.length(); i++) {
			dp[i][p.length()] = false;
		}
		for(int i=p.length()-1; i>=0; i--) {
			dp[s.length()][i] = p.charAt(i) == '*' && dp[s.length()][i+1];
		}
		
		for(int i=s.length()-1; i>=0; i--) {
			for(int j=p.length()-1; j>=0; j--) {
				if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					dp[i][j] = dp[i+1][j+1];
				} else {
					if(p.charAt(j) == '*') {
						dp[i][j] = dp[i][j+1] || dp[i+1][j] || dp[i+1][j+1];
					}
					else {
						dp[i][j] = false;
					}
				}
			}
		}
		return dp[0][0];
	}
	public boolean isMatch3(String s, String p) {
		if (p.replace("*", "").length() > s.length()) {
	        return false;
		}
		boolean[] next = new boolean[p.length()+1];
		// initialize
		next[p.length()] = true;
		
		for(int i=p.length()-1; i>=0; i--) {
			next[i] = p.charAt(i) == '*' && next[i+1];
		}
		
		for(int i=s.length()-1; i>=0; i--) {
			// current points to i
			// prev points to i+1
			boolean[] current = new boolean[p.length()+1];
			for(int j=p.length()-1; j>=0; j--) {
				if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					current[j] = next[j+1];
				} else {
					if(p.charAt(j) == '*') {
						current[j] = current[j+1] || next[j] || next[j+1];
					}
					else {
						current[j] = false;
					}
				}
			}
			next = current;
		}
		return next[0];
	}
}