package heuristic;
import search.Node;

/**
 * A heuristic is a function that takes in a node and returns an integer
 * that measures how "promising" or "desirable" the node is -- i.e., how 
 * likely it is that the node will lead to the goal. Heuristics use outside
 * information not specified by the problem itself. 
 * 
 * @author alchambers
 * @version sp2019
 */
public interface Heuristic {
	public int evaluate(Node node);
}
