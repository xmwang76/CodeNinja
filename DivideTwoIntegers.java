import org.junit.Assert;
import org.junit.Test;

public class DivideTwoIntegers {
	@Test
	public void test() {
		Assert.assertEquals(715827882, divide(2147483647, 3));
		Assert.assertEquals(2, divide(5, 2));
		Assert.assertEquals(2147483647, divide(2147483647, 1));
		Assert.assertEquals(2147483647, divide(-2147483648, -1));
		Assert.assertEquals(1, divide(1, 1));
		Assert.assertEquals(3, divide(15, 5));
		Assert.assertEquals(3, divide(17, 5));
	}

	public int divide(int dividend, int divisor) {
		long sign = 1;
		if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
			sign = -1;
		}
		long ldividend = Math.abs((long)dividend);
		long ldivisor = Math.abs((long)divisor);
		
		long ret = 0;
		long c = ldivisor;
		long x = 1;
		while (ldividend > 0 && ldividend >= ldivisor) {
			while (c < ldividend) {
				c = c << 1;
				x = x << 1;
			}
			while(c> ldividend) {
				c = c>>1;
				x = x>>1;
			}
			ldividend -= c;
			ret += x;
		}
		ret *= sign;
		if(ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		return (int) ret;
	}
}
