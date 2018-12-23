//	1.3.21 Write a method find() that takes a linked list and a string key as arguments
//	       and returns true if some node in the list has key as its item ﬁeld, false otherwise.

public boolean find(Node node, String key) {
	while (true) {
		if (node.item.equals(key))
			return true;
		
		if (node.hasNext())
			node = node.next;
		else
			return false;
	}
}
