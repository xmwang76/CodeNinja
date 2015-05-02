import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class SpiralMatrix {
	@Test
	public void test() {
		Assert.assertEquals(Lists.newArrayList(1, 2, 3, 4, 5, 6), spiralOrder(new int[][] {
				{ 1, 2, 3}, { 6, 5, 4 } }));
		Assert.assertEquals(Lists.newArrayList(1, 2, 3, 4), spiralOrder(new int[][] {
				{ 1, 2 }, { 4, 3 } }));
		Assert.assertEquals(Lists.newArrayList(1, 2), spiralOrder(new int[][] {
				{ 1 }, { 2 } }));
		Assert.assertEquals(Lists.newArrayList(1),
				spiralOrder(new int[][] { { 1 } }));
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ret = new ArrayList<Integer>();
		if (matrix.length == 0) {
			return ret;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int i = 0;
		int j = 0;
		int lc = 0;
		int hc = n - 1;
		int lr = 0;
		int hr = m - 1;
		char direction = 'e';
		if (n == 1) {
			direction = 's';
		}
		int count = 0;
		while (true) {
			ret.add(matrix[i][j]);
			count++;
			if (count == n * m) {
				return ret;
			}
			switch (direction) {
			case 'e':
				if (j == hc) {
					direction = nextDirection(direction);
					lr++;
					i++;
				} else {
					j++;
				}
				break;
			case 's':
				if (i == hr) {
					direction = nextDirection(direction);
					hc--;
					j--;
				} else {
					i++;
				}
				break;
			case 'w':
				if (j == lc) {
					direction = nextDirection(direction);
					hr--;
					i--;
				} else {
					j--;
				}
				break;
			case 'n':
				if (i == lr) {
					direction = nextDirection(direction);
					lc++;
					j++;
				} else {
					i--;
				}
			}
		}
	}

	private char nextDirection(char direction) {
		switch (direction) {
		case 'e':
			return 's';
		case 's':
			return 'w';
		case 'w':
			return 'n';
		case 'n':
			return 'e';
		}
		throw new IllegalStateException();
	}
}
