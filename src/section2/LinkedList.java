package section2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable interface allows to iterate using the advanced for loop
 */
public class LinkedList<E> implements Iterable<E> {
	int length = 0;
	Node<E>[] lastModifiedNode;
	Node<E> first;
	Node<E> last;
	
	/**
	 * Creates new empty node
	 */
	protected Node<E> getNewNode() {
		Node<E> node = new Node<>();
		lastModifiedNode = new Node[]{node};
		return node;
	}
	
	/**
	 * Insertion at the end
	 */
	public Node<E> appendLast(E value) {
		Node<E> node = getNewNode();
		node.value = value;
		if(last != null) {
			last.next = node;
			last = node;
		}
		if(first == null) {
			first = node;
		}
		length++;
		return node;
	}
	
	/**
	 * Insertion at the beginning
	 */
	public Node<E> appendFirst(E value) {
		Node<E> node = getNewNode();
		node.value = value;
		node.next = first;
		first = node;
		if(length == 0) {
			last = node;
		}
		length++;
		return node;
	}
	
	/**
	 * Insertion of node at the desired position
	 */
	public Node<E> insert(int index, E value) {
		Node<E> node = getNewNode();
		if(index < 0 || index > length) {
			throw new IllegalArgumentException("Invalid idex for insertion");
		}
		else if(index == length) {
			return appendLast(value);
		}
		else if(index == 0) {
			return appendFirst(value);
		}
		else { // append at desired position
			Node<E> result = first;
			while(index > 1) {
				index--;
				// Result is the node at which we have to add our new node
				result = result.next;
			}
			node.value = value;
			node.next = result.next;
			// Result will be behind our node which we add right now
			result.next = node;
			length++;
			return node;
		}
	}
	
	/**
	 * Find the node's value at the given index
	 */
	public E findAtIndex(int index) {
		Node<E> result = first;
		while(index >= 0) {
			if(result == null) {
				throw new NoSuchElementException();
			}
			else if(index == 0) {
				return result.value;
			}
			else {
				index--;
				result = result.next;
			}
		}
		return result.value;
	}
	
	public Node<E> removeFirst() {
		if(length == 0) {
			throw new NoSuchElementException();
		}
		Node<E> origFirst = first;
		first = first.next;
		length--;
		if(length == 0) {
			last = null;
		}
		return origFirst;
	}
	
	public Node<E> removeAtIndex(int index) {
		if(index == length || index < 0) {
			throw new NoSuchElementException();
		}
		if(index == 0) {
			return removeFirst();
		}
		Node<E> justBeforeIt = first;
		while(--index > 0) {
			justBeforeIt = justBeforeIt.next;
		}
		Node<E> nodeRemoved = justBeforeIt.next;
		if(justBeforeIt.next == last) {
			last = justBeforeIt;
		}
		justBeforeIt.next = justBeforeIt.next.next;
		length--;
		return nodeRemoved;
	}
	
	public int getLength() {
		return length;
	}
	
	public Node<E> getFirst() {
		if(length == 0) {
			throw new NoSuchElementException();
		}
		return first;
	}
	
	public Node<E> getLast() {
		if(length == 0) {
			throw new NoSuchElementException();
		}
		return last;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}
	
	private static class Node<E> {
		private E value;
		private Node<E> next;
		public E getValue() {
			return value;
		}
		public Node getNext() {
			return next;
		}
		@Override
		public String toString() {
			return value.toString();
		}
	}
	
	/**
	 * Fetches elements one by one
	 */
	private class ListIterator implements Iterator<E> {
		private Node<E> nextNode = first;
		private Node<E> currentNode = null;
		private Node<E> prevNode = null;
		
		@Override 
		public boolean hasNext() {
			if(nextNode != null) {
				return true;
			}
			return false;
		}
		
		@Override
		public E next() {
			if(!hasNext()) {
				throw new IllegalStateException();
			}
			prevNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.next;
			return currentNode.value;
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> anotherList = new LinkedList<>();
		anotherList.appendFirst(4);
		anotherList.appendFirst(1);
		anotherList.appendFirst(2);
		anotherList.appendFirst(3);
		anotherList.appendLast(8);
		anotherList.appendLast(7);
		anotherList.appendLast(1);
		anotherList.appendLast(2);
		anotherList.appendLast(3);
		anotherList.appendLast(18);
		anotherList.insert(2, 99);
		
		for(Integer x: anotherList) {
			System.out.println(x);
		}
		
		System.out.println("First Element:" + anotherList.getFirst());
		System.out.println("Last Element:" + anotherList.getLast());
		
		System.out.println("After removing the first element and the element at index 4");
		anotherList.removeFirst();
		anotherList.removeAtIndex(4);
		
		for(Integer x: anotherList) {
			System.out.println(x);
		}
		
		System.out.println("Element at index 2:" + anotherList.findAtIndex(2));
		
	}
}
