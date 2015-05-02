import java.util.ArrayList;
import java.util.List;

public class FixBinarySearchTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class NodeRef {
        TreeNode ref;
    }
    public void recoverTree(TreeNode root) {
        NodeRef left = new NodeRef();
        NodeRef right = new NodeRef();
        recoverTree(root, new NodeRef(), left, right);
        int tmp = left.ref.val;
        left.ref.val = right.ref.val;
        right.ref.val = tmp;
    }
    private void recoverTree(TreeNode node, NodeRef prev, NodeRef left, NodeRef right) {
        if(node == null) {
            return;
        }
        
        recoverTree(node.left, prev, left, right);
        
        if(prev.ref != null && node.val <= prev.ref.val) {
            if(left.ref == null) {
                left.ref = prev.ref;
            }
            right.ref = node;
        }
        
        prev.ref = node;        
        recoverTree(node.right, prev, left, right);
       
    }
    
}
