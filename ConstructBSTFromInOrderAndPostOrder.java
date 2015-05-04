import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

public class ConstructBSTFromInOrderAndPostOrder {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private String printTree(TreeNode root) {
		if (root == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Deque<TreeNode> q = new ArrayDeque<TreeNode>();
		q.addLast(root);
		sb.append(root.val);
		while (!q.isEmpty()) {
			TreeNode node = q.getFirst();
			q.removeFirst();
			// if (node.left == null && node.right == null) {
			// continue;
			// }
			if (node.left != null) {
				q.addLast(node.left);
				sb.append(node.left.val);
			} else {
				sb.append("#");
			}
			if (node.right != null) {
				q.addLast(node.right);
				sb.append(node.right.val);
			} else {
				sb.append("#");
			}
		}
		return sb.toString();
	}

	@Test
	public void test() {
		Assert.assertEquals(
				"215##3##4##",
				printTree(buildTree(new int[] {
						1, 2, 3, 4, 5 }, new int[] { 1, 4, 3, 5, 2 })));
		Assert.assertEquals("",
				printTree(buildTree(new int[] {}, new int[] {})));
		Assert.assertEquals(
				"314#2####",
				printTree(buildTree(new int[] { 1, 2, 3, 4 }, new int[] { 2, 1,
						4, 3 })));
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[postorder.length - 1]);
		buildTree(inorder, postorder, root, new IndexRef(inorder.length-1,
				postorder.length - 2), root.val);
		return root;
	}

	class IndexRef {
		int inorderIndex;
		int postorderIndex;

		public IndexRef(int inorderIndex, int postorderIndex) {
			this.inorderIndex = inorderIndex;
			this.postorderIndex = postorderIndex;
		}
	}

	public void buildTree(int[] inorder, int[] postorder, TreeNode node,
			IndexRef ref, Integer lowerBound) {
		if (node.val != inorder[ref.inorderIndex]) {
			// go right
			TreeNode right = new TreeNode(postorder[ref.postorderIndex]);
			ref.postorderIndex--;
			node.right = right;
			buildTree(inorder, postorder, right, ref, node.val);
		}

		ref.inorderIndex--;

		if (ref.postorderIndex >= 0
				&& (lowerBound == null || inorder[ref.inorderIndex] != lowerBound)) {
			// go left
			TreeNode left = new TreeNode(postorder[ref.postorderIndex]);
			ref.postorderIndex--;
			node.left = left;
			buildTree(inorder, postorder, left, ref, lowerBound);
		}
	}
}
