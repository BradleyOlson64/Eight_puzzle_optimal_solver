package util;

import search.Node;

/**
 * Represents an ordered collection of nodes
 * 
 * @author alchambers
 * @version spring2019
 */
public interface OrderedCollection {
	
	/**
	 * Adds the node to the ordered collection. 
	 * @param u The node to be added
	 */
	public void push(Node u);
	
	/**
	 * Removes the head node from the ordered collection. The order in which nodes
	 * are removed depends upon the type of ordered collection: queue, stack, 
	 * priority queue, etc.
	 *  
	 * @return The head node in the ordered collection
	 */
	public Node pop();
	
	/**
	 * Returns true if the collection is empty, false otherwise
	 * @return True if the collection is empty, false otherwise
	 */
	public boolean isEmpty();
}
