package util;
import java.util.ArrayList;
import search.Node;
import search.MyNode;

/**
 * A stack for "first in, first out" sequencing of inputs.
 * @author Bradley Olson
 * @version 2/4/2019
 *
 */
public class Stack implements OrderedCollection {
	private ArrayList<Node> stack;
	
	/**
	 * Creates a new empty stack.
	 */
	public Stack() {
		stack = new ArrayList<Node>();
	}
	/**
	 * Adds to the head of the stack.
	 */
	public void push(Node u) {
		stack.add(u);
	}
	
	/**
	 * Checks whether the stack is empty.
	 */
	public boolean isEmpty(){
		if(stack.size()==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Removes head of the stack.
	 */
	public Node pop(){
		return stack.remove(stack.size()-1);
	}
	
	public String toString() {
		String returnString = "";
		returnString += stack.toString();
		return returnString;
	}
	
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
		//Initializing stack
		Stack a = new Stack();
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
