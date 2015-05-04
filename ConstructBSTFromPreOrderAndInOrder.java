import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class ConstructBSTFromPreOrderAndInOrder {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private String printTree(TreeNode root) {
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
//		Assert.assertEquals(
//				"215##3##4##",
//				printTree(buildTree(new int[] { 2, 1, 5, 3, 4 }, new int[] {
//						1, 2, 3, 4, 5 })));
//		Assert.assertEquals("0#1##",
//				printTree(buildTree(new int[] { 0, 1 }, new int[] { 0, 1 })));
//		Assert.assertEquals("10###",
//				printTree(buildTree(new int[] { 1, 0 }, new int[] { 0, 1 })));
		Assert.assertEquals("314#2####", printTree(buildTree2(new int[]{3,1,2,4}, new int[]{1, 2, 3, 4})));
		Assert.assertEquals(
				"215##3##4##",
				printTree(buildTree2(new int[] { 2, 1, 5, 3, 4 }, new int[] {
						1, 2, 3, 4, 5 })));
		Assert.assertEquals("0#1##",
				printTree(buildTree2(new int[] { 0, 1 }, new int[] { 0, 1 })));
		Assert.assertEquals("10###",
				printTree(buildTree2(new int[] { 1, 0 }, new int[] { 0, 1 })));

	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode root = new TreeNode(preorder[0]);
		stack.push(root);
		int i = 1;
		int j = 0;
		while (!stack.isEmpty()&&(i < preorder.length || j < inorder.length)) {
			// explore left
			while (!stack.isEmpty() && stack.peek().val != inorder[j]) {
				TreeNode left = new TreeNode(preorder[i]);
				stack.peek().left = left;
				stack.push(left);
				i++;
			}

			// got an inorder match
			stack.pop();
			j++;

			// check if there's right side
			if (i < preorder.length && !stack.isEmpty() && stack.peek().val != inorder[j]) {
				TreeNode right = new TreeNode(preorder[i]);
				stack.peek().right = right;
				stack.push(right);
			}
		}
		return root;
	}

	class IndexRef {
		int i;
		int j;

		public IndexRef(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		TreeNode root = new TreeNode(preorder[0]);
		IndexRef ref = new IndexRef(1, 0);
		buildTree2(preorder, inorder, root, ref, null);
		return root;
	}

	private void buildTree2(int[] preorder, int[] inorder, TreeNode node,
			IndexRef ref, Integer upperBound) {
		// if there's left tree hasn't been built, then go left
		if (node.val != inorder[ref.j]) {
			TreeNode left = new TreeNode(preorder[ref.i]);
			node.left = left;
			ref.i++;
			buildTree2(preorder, inorder, left, ref, node.val);
		}

		ref.j++; // got an inorder match

		// if there's right tree hasn't been built, then go right
		if (ref.i < preorder.length
				&& (upperBound == null || inorder[ref.j] != upperBound)) {
			TreeNode right = new TreeNode(preorder[ref.i]);
			node.right = right;
			ref.i++;
			buildTree2(preorder, inorder, right, ref, upperBound);
		}

	}
}
