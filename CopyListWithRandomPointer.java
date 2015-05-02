
import org.junit.Assert;
import org.junit.Test;

public class CopyListWithRandomPointer {

	@Test
	public void test() {
		Assert.assertEquals(null, copyRandomList(null));
	}

	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};

	public RandomListNode copyRandomList(RandomListNode head) {
		RandomListNode p = head;
		while (p != null) {
			RandomListNode next = p.next;
			RandomListNode copy = new RandomListNode(p.label);
			copy.next = next;
			copy.random = null;
			p.next = copy;
			p = next;
		}

		p = head;
		while (p != null) {
			if (p.random != null) {
				p.next.random = p.random.next;
			}
			p = p.next.next;
		}

		p = head;
		RandomListNode fake = new RandomListNode(0);
		RandomListNode prev = fake;
		while (p != null) {
			prev.next = p.next;
			p.next = p.next.next;
			prev = prev.next;
			p = p.next;
		}
		prev.next = null;
		return fake.next;
	}
}
