import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class FractionToDecimal {
	@Test
	public void test() {
		Assert.assertEquals("-2147483648", fractionToDecimal(-2147483648, 1));
		Assert.assertEquals("0.1(6)", fractionToDecimal(1, 6));
		Assert.assertEquals("0.(3)", fractionToDecimal(1, 3));
		Assert.assertEquals("2", fractionToDecimal(4, 2));
		Assert.assertEquals("0.0(1)", fractionToDecimal(1, 90));
		Assert.assertEquals("0.(01)", fractionToDecimal(1, 99));
		Assert.assertEquals("0.(01)", fractionToDecimal(1, 99));
		Assert.assertEquals("-6.25", fractionToDecimal(-50, 8));
		Assert.assertEquals("0.0000000004656612873077392578125", fractionToDecimal(-1, -2147483648));
		Assert.assertEquals("2147483648", fractionToDecimal(-2147483648, -1));
	}

	public String fractionToDecimal(int numerator, int denominator) {
		String ret = "";
		if (numerator < 0 && denominator > 0 || numerator > 0
				&& denominator < 0) {
			ret = "-";
		}
		long lnumerator = Math.abs((long)numerator);
		long ldenominator = Math.abs((long)denominator);
		if (lnumerator % ldenominator == 0) {
			return ret+String.valueOf(lnumerator / ldenominator);
		}
		long current = lnumerator % ldenominator;
		ret += String.valueOf(lnumerator / ldenominator) + ".";

		Map<Long, Integer> seen = new HashMap<Long, Integer>();
		while (current != 0) {
			current *= 10;
			long dividant = current / ldenominator;
			Integer prevPos = seen.get(current);
			if (prevPos == null) {
				seen.put(current, ret.length());
				ret += String.valueOf(dividant);
			} else {
				ret = ret.substring(0, prevPos) + "(" + ret.substring(prevPos)
						+ ")";
				break;
			}
			current %= ldenominator;
		}
		return ret;
	}
}
