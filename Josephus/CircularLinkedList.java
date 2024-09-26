package circularlinkedlist;

import java.util.Iterator;
import java.util.Scanner;

public class CircularLinkedList<E> implements Iterable<E> {
	Node<E> head;
	Node<E> tail;
	int size;

	// Implement a constructor
	public CircularLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	// Return Node<E> found at the specified index
	// Be sure to check for out of bounds cases
	private Node<E> getNode(int index) {
		if (index >= size)
			return null;

		Node<E> curr = this.head;
		while (index != 0) {
			curr = curr.next;
			--index;
		}

		return curr;
	}

	// Add a node to the end of the list
	public boolean add(E item) {
		add(this.size, item);
		return true;
	}

	// Add a node based on the index
	public void add(int index, E item) {
		Node<E> node = new Node<E>(item);

		// Adding an element to an empty list
		if (size == 0) {
			this.head = node;
			this.tail = node;
			this.tail.next = this.head;
			this.head.next = this.tail;
			this.size++;
			return;
		}

		// Adding an element to the front
		if (index == 0) {
			node.next = this.head;
			this.head = node;
			this.tail.next = this.head;
			this.size++;
			return;
		}

		// Adding an element to the end
		if (index == size) {
			node.next = this.head;
			this.tail.next = node;
			this.tail = node;
			this.size++;
			return;
		}

		// Adding an element in the middle
		Node<E> curr = this.head;
		while (index != 0) {
			curr = curr.next;
			--index;
		}
		node.next = curr.next;
		curr.next = node;
		this.size++;
	}

	// Remove a node based on the index
	public E remove(int index) {
		// Out of bounds
		if (index > size || size == 0)
			return null;

		E temp;

		// Removing the first element in the list
		if (index == 0) {
			temp = this.head.item;
			this.head = this.head.next;
			this.tail.next = this.head;
			this.size--;
			return temp;
		}

		// Removing the last element
		if (index == size - 1) {
			temp = this.tail.item;

			Node<E> curr = this.head;
			while (--index != 0) {
				curr = curr.next;
			}
			curr.next = this.head;
			this.tail = curr;
			this.size--;
			return temp;
		}

		// Removing an element from the middle
		Node<E> curr = this.head;
		while (--index != 0) {
			curr = curr.next;
		}
		temp = curr.next.item;
		curr.next = curr.next.next;
		this.size--;

		return temp;
	}

	// Stringify your list
	public String toString() {
		StringBuilder result = new StringBuilder();

		Node curr = this.head;
		while (curr != this.tail) {
			result.append(String.valueOf(curr.item));
			result.append(" ==> ");
			curr = curr.next;
		}
		// Append the tail item
		result.append(String.valueOf(curr.item));
		result.append(" ==> ");

		return result.toString();
	}

	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	// This class is not static because it needs to reference its parent class
	private class ListIterator<E> implements Iterator<E> {
		Node<E> nextItem;
		Node<E> prev;
		int index;

		@SuppressWarnings("unchecked")
		// Creates a new iterator that starts at the head of the list
		public ListIterator() {
			nextItem = (Node<E>) head;
			index = -1;
		}

		// Returns true if there is a next node
		public boolean hasNext() {
			return nextItem != null;
		}

		// Advances the iterator to the next item
		// Should wrap back around to the head
		public E next() {
			index = ++index % CircularLinkedList.this.size;
			nextItem = nextItem.next;
			return nextItem.item;
		}

		// Remove the last node visted by the .next() call
		// For example, if we had just created an iterator,
		// the following calls would remove the item at index 1 (the second person in
		// the ring)
		// next() next() remove()
		// Use CircularLinkedList.this to reference the CircularLinkedList parent class
		public void remove() {
			CircularLinkedList.this.remove(index);
			index--;
		}
	}

	// The Node class
	private static class Node<E> {
		E item;
		Node<E> next;

		public Node(E item) {
			this.item = item;
			this.next = null;
		}
	}

	// Main method
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n, k;

		// Take inputs
		System.out.print("Enter number of soldiers: ");
		n = sc.nextInt();

		System.out.print("Enter the count: ");
		k = sc.nextInt();

		// Build the circular list
		CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
		for (int i = 1; i <= n; ++i) {
			list.add(i);
		}
		System.out.println(list);

		Iterator<Integer> iter = list.iterator();

		// Solve the josephus problem
		while (list.size != 2) {
			for (int i = 1; i <= k; ++i) {
				iter.next();
			}
			iter.remove();
			System.out.println(list);
		}

		sc.close();
	}
}
