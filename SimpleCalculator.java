import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class SimpleCalculator {
	@Test
	public void test() {
		Assert.assertEquals(0, calculate(null));
		Assert.assertEquals(0, calculate(""));
		Assert.assertEquals(5, calculate("3+2"));
		Assert.assertEquals(13, calculate("3+2*5"));
		Assert.assertEquals(20, calculate("3+2*5+7"));
		Assert.assertEquals(6, calculate("3+2*5-7"));
	}

	public int calculate(String expression) {
		if (expression == null || expression.length() == 0) {
			return 0;
		}
		Stack<Integer> vstack = new Stack<Integer>();
		Stack<Character> ostack = new Stack<Character>();
		int currentV = 0;
		boolean numberRead = false;
		for (char c : expression.toCharArray()) {
			if (isNumber(c)) {
				currentV *= 10;
				currentV += c - '0';
				numberRead = true;
			} else if (isOperand(c)) {
				if (!numberRead) {
					throw new IllegalStateException(
							"expect number before operand: " + c);
				}
				vstack.push(currentV);
				currentV = 0;
				numberRead = false;
				while (!ostack.isEmpty()
						&& rankOperand(c) <= rankOperand(ostack.peek())) {
					vstack.push(calculate(vstack.pop(), vstack.pop(),
							ostack.pop()));
				}
				ostack.push(c);
			} else {
				throw new IllegalStateException("unknown expression: " + c);
			}
		}
		if (!numberRead) {
			throw new IllegalStateException("expect number in the end");
		}
		vstack.push(currentV);
		while (!ostack.isEmpty()) {
			vstack.push(calculate(vstack.pop(), vstack.pop(), ostack.pop()));
		}
		return vstack.pop();
	}

	private int calculate(int right, int left, char operand) {
		switch (operand) {
		case '+':
			return left + right;
		case '-':
			return left - right;
		case '*':
			return left * right;
		default:
			throw new IllegalStateException();
		}
	}

	private boolean isOperand(char c) {
		return c == '+' || c == '-' || c == '*';
	}

	private boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	private int rankOperand(char o) {
		switch (o) {
		case '+':
			return 0;
		case '-':
			return 0;
		case '*':
			return 1;
		default:
			throw new IllegalStateException();
		}
	}
}
