package search;

import java.util.ArrayList;
import java.util.Arrays;

import util.Queue;
import util.Stack;
import util.PriorityQueue;
import util.OrderedCollection;
import heuristic.ManhattanDistance;
import heuristic.NumMisplaced;
import java.util.HashSet;

/**
 * Implements the GraphSearch algorithm
 * @author Bradley Olson
 */
public class GraphSearch {
	
	/**
	 * This method takes in a puzzle and returns the solution in the form of a string of directions (U, D, L, R)
	 * The directions specify the movements of the blank tile. 
	 * 
	 * @param puzzle 
	 * 		A starting puzzle configuration
	 * @return 
	 * 		A string representing the directions for solving the puzzle or null if the puzzle is unsolvable
	 */
	public String solvePuzzle(int[][] puzzle) {
		//Initializing necessary heuristics
		ManhattanDistance a = new ManhattanDistance();
		NumMisplaced b = new NumMisplaced();
		//Creating initial node
		Node initialState = new MyNode(puzzle);
		//Defining each frontier type, leaving them to be uncommented for desired functionality
//		Queue frontier = new Queue();
//		Stack frontier = new Stack();
//		PriorityQueue frontier = new PriorityQueue(b);
		PriorityQueue frontier = new PriorityQueue(a);
		
		return graphSearch(initialState, frontier);
	}

	
	/**
	 * Implements the GraphSearch algorithm
	 * 
	 * @param initialState 
	 * 		The initial starting state
	 * @param frontier 
	 * 		An ordered collection of nodes used to keep track of unexpanded nodes
	 * @return 
	 * 		A string representing the directions for solving the puzzle or null if the puzzle is unsolvable
	 */
	private static String graphSearch(Node initialState, OrderedCollection frontier){
		HashSet<Node> explored = new HashSet<>();
		frontier.push(initialState);
		
		while(!frontier.isEmpty()) {
			Node u = frontier.pop();
			if(u.isGoal()) {
				System.out.println(explored.size());
				return constructPath(u);
			}
			if(!explored.contains(u)) {
				explored.add(u);
				Node[] successors = u.getSuccessors();
				for(int i=0;i<4;i++) {
					if(successors[i]!= null) {
						frontier.push(successors[i]);
					}
				}
			}
		}
		return null;
	}
	//Determines the return string, a continuous string of directions with no spaces or commas.
	private static String constructPath(Node finishNode) {
		//Initializing current node and return string
		MyNode currNode = (MyNode) finishNode;
		String returnString = "";
		//Creating back tracking loop
		while(currNode.predecessor != null) {
			//finding action between predecessor and current node, then adding it 
			Node[] possible = currNode.predecessor.getSuccessors();
			MyNode[] rightPossible = new MyNode[4];
			for(int i=0;i<4;i++) {
				rightPossible[i]= (MyNode) possible[i];
			}
			for(int i=0;i<4;i++) {
				if(rightPossible[i]!= null) {
					if(rightPossible[i].equals(currNode)) {
						switch(i) {
						case 0:
							returnString = Node.UP + returnString;
							break;
						case 1:
							returnString = Node.DOWN + returnString;
							break;
						case 2:
							returnString = Node.LEFT + returnString;
							break;
						case 3:
							returnString = Node.RIGHT + returnString;
							break;
						default:
							break;
						}
					}
				}
			}
			currNode = (MyNode) currNode.predecessor;
		}
		return returnString;
	}

	/**
	 * A method to perform unit testing of graph search. This is not the full program main method!
	 * @param args
	 */
	public static void main(String args[]) {
		//Making a board to play with
		int[][] initBoard = new int[3][3];
		initBoard[0][0] = 1;
		initBoard[0][1] =2;
		initBoard[0][2] =3;
		initBoard[1][0] = 4;
		initBoard[1][1] = 5;
		initBoard[1][2] = 6;
		initBoard[2][0] = 7;
		initBoard[2][1] = 0;
		initBoard[2][2] = 8;
		GraphSearch a = new GraphSearch();
		//Making nodes to use
//		Node a1 = new MyNode(initBoard);
//		Node[] successors = a1.getSuccessors();
//		Node[] successors2 = successors[0].getSuccessors();
//		Node[] successors3 = successors2[0].getSuccessors();
//		MyNode suc = (MyNode) successors2[0];
//		System.out.println(suc.predecessor.toString());
//		System.out.println(constructPath(successors3[2]));
		System.out.println(a.solvePuzzle(initBoard));
	}
	
}
