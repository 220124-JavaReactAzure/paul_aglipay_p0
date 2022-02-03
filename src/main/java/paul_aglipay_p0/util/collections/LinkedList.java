package paul_aglipay_p0.util.collections;

//import org.w3c.dom.Node;

public class LinkedList<T> implements List<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;

	public boolean add(T element) {

		if (element == null) {
			return false;
		}

		Node<T> newNode = new Node<>(element);
		if (head == null) {
			tail = head = newNode;
		} else {
			tail.nextNode = newNode;
			tail = newNode;
		}
		size++;

		return false;
	}

	public boolean contains(T element) {

		Node<T> runner = head;

		while (runner != null) {
			if (runner.data.equals(element)) {
				return true;
			}

			runner = runner.nextNode;
		}

		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(T element) {
		Node<T> prevNode = null;
		Node<T> currentNode = head;
		if (size == 0) {
			return false;
		}

		for (int i = 0; i < size; i++) {

			if ((currentNode.data == null && element == null)
					|| (currentNode.data != null && currentNode.data.equals(element))) {

				if (currentNode == head) {
					head = currentNode.nextNode;
				} else {
					prevNode.nextNode = currentNode.nextNode;
				}

				size--;
				return true;
			}

			prevNode = currentNode;
			currentNode = currentNode.nextNode;
		}

		return false;
	}

	public int size() {
		return size;
	}

	public T get(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> currentNode = head;
		for (int i = 0; i <= index; i++) {
			if (i == index) {
				return currentNode.data;
			}

			currentNode = currentNode.nextNode;
		}

		return null;
	}

	private static class Node<T> {
		T data;
		Node<T> nextNode;

		public Node(T data) {
			this.data = data;
		}
	}

}
