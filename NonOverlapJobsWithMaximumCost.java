import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/*
 Given: a set of n jobs with (start time, end time, cost).
 Output: a subset so that no 2 jobs overlap and the cost is maximum
 */

public class NonOverlapJobsWithMaximumCost {

	@Test
	public void test() {
		List<Job> jobs = Arrays.asList(new Job(1, 3, 5), new Job(2, 4, 1),
				new Job(4, 7, 3));
		System.out.println(findNonOverlapJobs(jobs));
	}

	public class Job {
		int start;
		int end;
		int cost;

		public Job(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		public String toString() {
			return "(" + start + "," + end + "," + cost + ")";
		}
	}

	public static List<Job> findNonOverlapJobs(List<Job> jobs) {
		Job[] sortedJobs = jobs.toArray(new Job[0]);
		Arrays.sort(sortedJobs, new Comparator<Job>() {

			@Override
			public int compare(Job o1, Job o2) {
				if (o1.start < o2.start) {
					return -1;
				} else if (o1.start == o2.start) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		List<List<Job>> dp = new ArrayList<List<Job>>();
		for (int i = 0; i < jobs.size(); i++) {
			dp.add(new ArrayList<Job>());
		}
		dp.get(jobs.size() - 1).add(jobs.get(jobs.size() - 1));
		int[] dpp = new int[jobs.size()];
		dpp[jobs.size() - 1] = jobs.get(jobs.size() - 1).cost;
		for (int i = jobs.size() - 2; i >= 0; i--) {
			int j = i + 1;
			while (j < jobs.size() && jobs.get(i).end >= jobs.get(j).start) {
				j++;
			}
			int costA = dpp[i + 1];
			int costB = jobs.get(i).cost;
			if (j < jobs.size()) {
				costB += dpp[j];
			}
			if (costA > costB) {
				dpp[i] = dpp[i + 1];
				dp.get(i).addAll(dp.get(i + 1));
			} else {
				dpp[i] = jobs.get(i).cost;
				dp.get(i).add(jobs.get(i));
				if (j < jobs.size()) {
					dpp[i] += dpp[j];
					dp.get(i).addAll(dp.get(j));
				}
			}

		}
		return dp.get(0);
	}
}
