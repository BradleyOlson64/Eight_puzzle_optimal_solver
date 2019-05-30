package util;
import java.util.LinkedList;
import search.MyNode;
import search.Node;

/**
 * This class implements a basic queue of nodes.
 * @author baolson
 * @version 2/5/19
 */
public class Queue implements OrderedCollection{
	private LinkedList<Node> queue;
	
	/**
	 * Creates an empty queue.
	 */
	public Queue() {
		queue = new LinkedList<>();
	}
	
	/**
	 * Adds a node to the queue.
	 * @param u, the node to be added
	 */
	public void push(Node u) {
		queue.add(u);
	}
	
	/**
	 * Removes and returns a node from the queue.
	 * @return The node removed form the queue.
	 */
	public Node pop() {
		return queue.removeFirst();
	}
	
	/**
	 * Checks whether the queue is empty.
	 * @return empty or not empty
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * Prints out a string version of the queue.
	 */
	public String toString() {
		return queue.toString();
	}
	
	/**
	 * An area for testing out the queue.
	 * @param args
	 */
	public static void main(String args[]) {
	//Making a board to play with
		int[][] initBoard = new int[3][3];
		initBoard[0][0] = 4;
		initBoard[0][1] =8;
		initBoard[0][2] =3;
		initBoard[1][0] = 1;
		initBoard[1][1] = 2;
		initBoard[1][2] = 7;
		initBoard[2][0] = 5;
		initBoard[2][1] = 6;
		initBoard[2][2] = 0;
		//Initializing queue
		Queue a = new Queue();
		//Making nodes to use
		Node a1 = new MyNode(initBoard);
		Node[] successors = a1.getSuccessors();
		a.push(successors[0]);
		a.push(successors[2]);
		a.push(a1);
		System.out.println(a.toString());
		a.pop();
		System.out.println(a.toString());	
	}
}
