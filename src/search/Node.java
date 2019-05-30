package search;


/**
 * This interface specifies all necessary methods for a node in the search tree
 * 
 * @author alchambers
 * @version sp19
 *
 */
public interface Node{	
	public static final String UP = "U";
	public static final String DOWN = "D";
	public static final String LEFT = "L";
	public static final String RIGHT = "R";
	
	/**
	 * Returns the 4 successors of the current node which correspond to the blank tile being moved
	 * up, down, left, or right. If a particular direction is illegal, the corresponding entry in
	 * the return array is null.
	 * 
	 * @return An array containing successor nodes or null if an action is illegal 
	 */
	public Node[] getSuccessors();
	
	
	/**
	 * Returns the board corresponding to the state
	 * @return  A particular state of the 8-puzzle
	 */
	public int[][] getBoard();

	
	/**
	 * Returns the board corresponding to the goal configuration
	 * @return The goal state
	 */
	public int[][] getGoal();

	
	/**
	 * Returns the number of moves from the initial start node to the current node 
	 * @return The number of moves from the initial node to the current node
	 */
	public int getDepth();
	
	
	/**
	 * Returns true if the state is the goal state and false otherwise
	 * @return True if the state is the goal, false otherwise
	 */
	public boolean isGoal();
	
	
	/**
	 * Default implementations of these 3 methods are provided by Java's Object class however you will
	 * need to override them so that nodes can be compared to one another. The hashcode and equals methods
	 * should use the board. That is, two nodes are equal if they have the same board configuration. 
	 */
	public int hashCode();
	
	public boolean equals(Object obj);
		
	public String toString();
}