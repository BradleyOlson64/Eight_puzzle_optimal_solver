package util;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import heuristic.Heuristic;
import search.Node;

import java.util.List;

/**
 * A minimum priority queue for nodes. Nodes are ranked according to the 
 * supplied heuristic with lower heuristic values having higher priority.
 * 
 * @author alchambers
 * @version sp19
 *
 */
public class PriorityQueue implements OrderedCollection {

	private Map<Node, Integer> location;
	private List<Pair<Integer, Node>> heap;
	private Heuristic heuristic;
	
	/**
	 *  Constructs an empty priority queue
	 */
	public PriorityQueue(Heuristic h) {
		location = new HashMap<Node, Integer>();
		heap = new ArrayList<Pair<Integer, Node>>();
		heuristic = h;
	}

	/**
	 *  Insert a new element into the queue with the
	 *  given priority.
	 *
	 *	@param priority priority of element to be inserted
	 *	@param element element to be inserted
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The element does not already appear in the priority queue.</li>
	 *	</ul>
	 *  
	 */
	@Override
	public void push(Node element) {
		// element must be unique
		assert(!location.containsKey(element));	
		
		int priority = heuristic.evaluate(element);
		
		// add the new element to the end of the list
		heap.add(new Pair<Integer,Node>(priority, element));
		location.put(element,heap.size()-1);		

		// percolate up newly added value
		percolateUpLeaf();
	}

	/**
	 *  Remove the highest priority element
	 */
	@Override
	public Node pop(){
		// heap must be non-empty
		assert (heap.size() > 0);
				
		Node element = heap.get(0).element;
		int priority = heap.get(0).priority;

		// remove element from hash map
		location.remove(element);	

		// copy leaf node to root
		heap.set(0, heap.get(heap.size()-1));
		location.put(heap.get(0).element, 0);
		
		// now we can remove leaf node from heap
		heap.remove(heap.size()-1);
		
		// push new root down to proper place
		pushDownRoot();
		
		return element;		
	}


	/**
	 *  Returns true if the priority queue contains no elements
	 *  @return true if the queue contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		return heap.size() == 0;
	}

	
	/*************************************************
	 * 			Private Helper Methods
	 *************************************************/
	
	/**
	 * Push down the root element
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDownRoot() {
		return pushDown(0);
	}

	/**
	 * Percolate up the last leaf in the heap, i.e. the most recently 
	 * added element which is stored in the last slot in the list
	 * 
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUpLeaf(){	
		return percolateUp(heap.size()-1);
	}

	/**
	 * Push down a given element 
	 * @param start_index the index of the element to be pushed down
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDown(int start_index) {
		int curr = start_index;
		int l = left(curr);
		int r = right(curr);
		int swap_index;

		while(hasTwoChildren(curr)) {
			swap_index = (heap.get(l).priority < heap.get(r).priority) ? l : r;
			
			// swap_index now holds the index of the child with the numerically smallest priority value			

			if(heap.get(curr).priority <= heap.get(swap_index).priority) {
				break;
			}
			else{

				// move curr to the position of its largest child
				// move its largest child up to now occupy the parent spot
				swap(curr, swap_index);

				// update in preparation for the next iteration
				curr = swap_index;
				l = left(curr);
				r = right(curr);
			}
		}

		
		// Check if curr needs to be swapped with its one (left) child
		if(l < heap.size() && heap.get(l).priority < heap.get(curr).priority){
			swap(curr, l);
			curr = l;
		}
		return curr;
	}

	/**
	 * Percolate up a given element
	 * @param start_index the element to be percolated up
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUp(int start_index) {
		int curr = start_index;
		int p = parent(curr);
		while(curr > 0 && heap.get(curr).priority < heap.get(p).priority){
			swap(curr, p);
			curr = p;
			p = parent(curr);
		}
		return curr;
	}

	/**
	 * Returns true if element is a leaf in the heap
	 * @param i index of element in heap
	 * @return true if element is a leaf
	 */
	private boolean isLeaf(int i){
		return (left(i) > heap.size()) && (right(i) > heap.size());
	}

	/**
	 * Returns true if element has two children in the heap
	 * @param i index of element in the heap
	 * @return true if element in heap has two children
	 */
	private boolean hasTwoChildren(int i) {
		return (left(i) < heap.size()) && (right(i) < heap.size());
	}

	/**
	 * Swaps two elements in the priority queue by updating BOTH
	 * the list representing the heap AND the map
	 * @param i element to be swapped
	 * @param j element to be swapped
	 */
	private void swap(int i, int j) {
		Pair<Integer, Node> temp = heap.get(i);

		heap.set(i, heap.get(j));
		heap.set(j, temp);

		Node key = heap.get(j).element;
		location.put(key, j);

		key = heap.get(i).element;
		location.put(key, i);
	}

	/**
	 * Computes the index of the element's left child
	 * @param parent index of element in list
	 * @return index of element's left child in list
	 */
	private int left(int parent) {
		return 2*parent + 1;

	}

	/**
	 * Computes the index of the element's right child
	 * @param parent index of element in list
	 * @return index of element's right child in list
	 */
	private int right(int parent) {
		return 2*parent + 2;

	}

	/**
	 * Computes the index of the element's parent
	 * @param child index of element in list
	 * @return index of element's parent in list
	 */

	private int parent(int child) {
		return (child-1)/2;
	}


	/**
	 * The Pair class is only ever used inside the PriorityQueue class
	 * As a result, I made it an inner class
	 */
	private class Pair<P, E> {
		public P priority;
		public E element;
		
		public Pair(P p, E e) {
			priority = p;
			element = e;
		}
	}
	
}





