package textgen;

import java.util.AbstractList;

/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Margarita Ostrovskaia
 * date 03/28/2019
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement 'MyLinkedList' method
		size = 0;
		
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		head.next = tail;
		tail.prev = head;
	}

    private void addNewNode(LLNode<E> target, E data) {
    		LLNode<E> node = new LLNode<E>(data);
        node.prev = target;
        node.next = target.next;

        target.next.prev = node;
        target.next = node;

        size += 1;
    }

	/** Appends an element to the end of the list
	 * @param element The element to add */
	public boolean add(E element) 
	{
		// TODO: Implement 'add' method
		if (element == null)
			throw new NullPointerException();
		
		addNewNode(tail.prev, element);
		
		return true;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) 
	{
		// TODO: Implement 'add' method
		if (element == null)
			throw new NullPointerException();
		
		if (index<0 || index>size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> target;
		if (index == 0)
			target = head;
		else
			target = findNodeByIndex(index-1);
		
		addNewNode(target, element);
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement 'get' method.
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> node = findNodeByIndex(index);
		
		return node.data;
	}

	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement 'size' method
		return size;
	}
	
	private LLNode<E> findNodeByIndex(int index) {
		if (index<0 || index>size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> target = head;
		for(int i=0; i<=index; i++)
			target = target.next;
		
		return target;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list */
	public E remove(int index) 
	{
		// TODO: Implement 'remove' method
		if (index<0 || index>=size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> node = findNodeByIndex(index);
		
		if(index == 0 ) {
			if (size == 1) {
				head.next = tail;
				tail.prev = head;
			}
			else {
				node.next.prev = head;
				head.next = node.next;
			}
		}
		else if (index == size-1) {
			tail.prev = node.prev;
			node.prev.next = tail;
		}
		else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		
		size--;
		
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E set(int index, E element) 
	{
		// TODO: Implement 'set' method
		if (element == null)
			throw new NullPointerException();
		
		if (index<0 || index>size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> node = findNodeByIndex(index);
		E lastData = node.data;
		node.data = element;
		
		return lastData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}