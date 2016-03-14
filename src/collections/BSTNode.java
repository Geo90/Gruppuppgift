package collections;

/**
 * The BinarySearchTree uses this class to represent its nodes
 * @author Benjamin Sejdic
 *
 * @param <K> key
 * @param <V> value
 */
class BSTNode<K, V> {
	K key;
	V value;
	BSTNode<K, V> left;
	BSTNode<K, V> right;

	/**
	 * Constructs a node with a key and its corresponding value and also refers to its 
	 * left and right children if it has any.
	 * @param key the key 
	 * @param value the value 
	 * @param left reference to its left children
	 * @param right reference to its right children
	 */
	public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/**
	 * The tree's height gets returned when this method is called 
	 * @return the tree's veritcal height
	 */
	public int height() {
		int leftH = -1, rightH = -1;
		if (left != null)
			leftH = left.height();
		if (right != null)
			rightH = right.height();
		return 1 + Math.max(leftH, rightH);
	}

	/**
	 * Returns the total amount of nodes 
	 * @return amount of nodes in a BinarySearchTree
	 */
	public int size() {
		int leftS = 0, rightS = 0;
		if (left != null)
			leftS = left.size();
		if (right != null)
			rightS = right.size();
		return 1 + leftS + rightS;
	}

	/**
	 * Prints all the tree's node's key's and value's 
	 */
	public void print() {
		if (left != null)
			left.print();
		System.out.println(key + ": " + value);
		if (right != null)
			right.print();
	}

	public void showTree() {
		javax.swing.JOptionPane.showMessageDialog(null, new ShowBST<K, V>(this, 800, 600), "Show tree",
				javax.swing.JOptionPane.PLAIN_MESSAGE);
	}
}
