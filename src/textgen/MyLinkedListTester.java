package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * @author Margarita Ostrovskaia
 * date 03/28/2019
 */
public class MyLinkedListTester {
	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> oneList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/** @throws java.lang.Exception */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		emptyList = new MyLinkedList<Integer>();
		
		oneList = new MyLinkedList<Integer>();
		oneList.add(8);
		
		longerList = new MyLinkedList<Integer>();
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
			longerList.add(i);
		
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
	}
	
	/** Test if the get method is working correctly.*/
	/*You should not need to add much to this method. We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first in shortList", "A", shortList.get(0));
		assertEquals("Check second in shortList", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ )
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct in list1", 65, a);
		assertEquals("Remove: check element 0 is correct in list1", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct in list1", 2, list1.size());
		
		// TODO: Add more 'testRemove' tests here
		// incorrect index
		try {
			list1.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// remove last element
		int b = longerList.remove(LONG_LIST_LENGTH-1);
		assertEquals("Remove: check a is correct in longerList", 9, b);
		assertEquals("Remove: check element 9 is correct in longerList", (Integer)8, longerList.get(LONG_LIST_LENGTH-2));
		assertEquals("Remove: check size is correct in longerList", LONG_LIST_LENGTH-1, longerList.size());
		
		// remove element
		int c = oneList.remove(0);
		assertEquals("Remove: check c is correct in oneList", 8, c);
		assertEquals("Remove: check size is correct in oneList", 0, oneList.size());
	}
	
	/** Test adding an element into the end of the list, specifically public boolean add(E element) */
	@Test
	public void testAddEnd()
	{
        // TODO: implement 'testAddEnd' test
		boolean isAdd1 = list1.add(35);
		assertEquals("Add: check element '35' was add in list1", true, isAdd1);
		assertEquals("Add: check element '35' is correct in list1", (Integer)35, list1.get(3));
		
		// null element
		try {
			list1.add(null);
			fail("Null element");
		}
		catch (NullPointerException e) {
		}
		
		assertEquals("Add: check size is correct in list1", 4, list1.size());
		
		// check add element to empty list
		boolean isAdd2 = emptyList.add(55);
		assertEquals("Add: check element '55' was add in emptyList", true, isAdd2);
		assertEquals("Add: check element '55' is correct in emptyList", (Integer)55, emptyList.get(0));
		assertEquals("Add: check size is correct in emptyList", 1, emptyList.size());
	}

	/** Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element) */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement 'testAddAtIndex' test
		list1.add(1, 66);
		assertEquals("Add: check element '66' is correct in list1", (Integer)66, list1.get(1));

		// null element
		try {
			list1.add(2, null);
			fail("Null element");
		}
		catch (NullPointerException e) {
		}

		// wrong index
		try {
			list1.add(10, 33);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			list1.add(-1, 22);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		assertEquals("Add: check size is correct in list1", 4, list1.size());
		
		// add element to empty list on 0 position
		emptyList.add(0, 75);
		assertEquals("Add: check element '75' is correct in emptyList", (Integer)75, emptyList.get(0));
		assertEquals("Add: check size is correct in emptyList", 1, emptyList.size());
	}
	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement 'testSize' test
		assertEquals("Size: check size is correct in list1", 3, list1.size());
		assertEquals("Size: check size is correct in emptyList", 0, emptyList.size());
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement 'testSet' test
		int a = list1.set(0, 70);
		assertEquals("Set: check a is correct in list1", 65, a);
		assertEquals("Set: check element '70' is correct in list1", (Integer)70, list1.get(0));
		assertEquals("Set: check element '21' is correct in list1", (Integer)21, list1.get(1));
		assertEquals("Set: check size is correct in list1", 3, list1.size());
		
		// incorrect index
		try {
			list1.set(5,50);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			list1.set(-1,50);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	// TODO: Optionally add more test methods.
}