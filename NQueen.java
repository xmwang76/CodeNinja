import java.util.ArrayList;
import java.util.List;

public class NQueen {
	public List<String[]> solveNQueens(int n) {
		boolean[] col = new boolean[n];
		boolean[] forward = new boolean[2 * n - 1];
		boolean[] backward = new boolean[2 * n - 1];
		String[] partialSolution = new String[n];
		return solve(n, col, forward, backward, 0, partialSolution);
	}

	private List<String[]> solve(int n, boolean[] col, boolean[] forward,
			boolean[] backward, int i, String[] partialSolution) {
		List<String[]> ret = new ArrayList<String[]>();
		if (i == n) {
			String[] solution = new String[n];
			System.arraycopy(partialSolution, 0, solution, 0, n);
			ret.add(solution);
			return ret;
		}
		for (int j = 0; j < n; j++) {
			if (!col[j] && !forward[i + j] && !backward[i - j + n - 1]) {
				col[j] = true;
				forward[i + j] = true;
				backward[i - j + n - 1] = true;
				StringBuilder sb = new StringBuilder();
				for (int k = 0; k < n; k++) {
					if (k == j) {
						sb.append("Q");
					} else {
						sb.append(".");
					}
				}
				partialSolution[i] = sb.toString();
				List<String[]> solutions = solve(n, col, forward, backward,
						i + 1, partialSolution);
				ret.addAll(solutions);
				col[j] = false;
				forward[i + j] = false;
				backward[i - j + n - 1] = false;
			}
		}
		return ret;
	}
}
