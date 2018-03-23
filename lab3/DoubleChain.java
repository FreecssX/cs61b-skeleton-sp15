
public class DoubleChain {
	
	private DNode head = new DNode(null, 0, null);
	
	public DoubleChain(double val) {
		/* your code here. */
		head.next = new DNode(val); 
	}

	public DNode getFront() {
		return head.next;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		/* your code here */
		DNode p = head;
		while (p.next != null) {
			p = p.next;
		}
		return p;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {
		/* your code here */
		DNode p = new DNode(head, d, head.next);
		head.next = p;
	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		/* your code here */
		DNode p = head;
		while (p.next != null) {
			p = p.next;
		}
		p.next = new DNode(p, d, null);
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
		if (head.next == null) {
			return null;
		}
		DNode p = head;
		while (p.next != null) {
			p = p.next;
		}
		p.prev.next = null;
		return p;
	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */
		String s;
		DNode p = head.next;
		s = Double.toString(p.val);
		while (p.next != null) {
			p = p.next;
			s = s + ", " + Double.toString(p.val);
		}
		return "<[" + s + "]>";
	}
	public void deleteByIndex(int i) {
		DNode p = head;
		while (i > 0 && p.next != null) {
			p = p.next;
			i -= 1;
		}
		if (i == 0 && p.next != null) {
			p.next = p.next.next;
		}
	}
	public void deleteByValue(double val) {
		DNode p = head;
		while (p.next != null) {
			p = p.next;
			if (Math.abs(val - p.val) < 0.0000001) {
				p.prev.next = p.next;
				p = p.prev;
			}
		}
	}
	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}
	
}
