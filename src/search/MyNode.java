package search;

import java.util.Arrays;
/**
 * Creating a node class implementing Node.
 * @author baolson
 * @version 2/5/19
 */
public class MyNode implements Node{
	
	//Creating globals
	private int[][] goal = new int[3][3];
	private int[][] board = new int[3][3];
	private int depth;
	public Node predecessor;
	
	/**
	 * A method that generates a new node using just a given board configuration.
	 * @param board
	 */
	public MyNode(int[][] board) {
		this.board=board;
		goal[0][0] = 1;
		goal[0][1] = 2;
		goal[0][2] = 3;
		goal[1][0] = 4;
		goal[1][1] = 5;
		goal[1][2] = 6;
		goal[2][0] = 7;
		goal[2][1] = 8;
		goal[2][2] = 0;
		this.depth = 0;
		predecessor = null;
	}
	
	/**
	 * Creates a new node using a parent configuration and the move taken to get to it from the parent.
	 * @param direction
	 * @param parent
	 */
	public MyNode(String direction, MyNode parent) {
		//locating blank tile
		int blanki=0;
		int blankj=0;
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(parent.board[i][j]==0) {
					blanki = i;
					blankj = j;
				}
			}
		}
		//Checking for impossible configurations
		if(blanki == 0 && direction == Node.UP) throw new Error("No tile above to swap");
		if(blanki == 2 && direction == Node.DOWN) throw new Error("No tile below to swap");
		if(blankj == 0 && direction == Node.LEFT)throw new Error("No tile to the left to swap");
		if(blankj == 2 && direction == Node.RIGHT)throw new Error("No tile to the right to swap"); 
		//Using direction and location to exchange blank and target
		this.board = exchange(parent.board,blanki, blankj, direction);
		//Copying goal
		this.goal = parent.goal;
		this.depth = parent.depth + 1;
		this.predecessor = parent;
	}
	
	/**
	 * Returns the 4 successors of the current node which correspond to the blank tile being moved
	 * up, down, left, or right. If a particular direction is illegal, the corresponding entry in
	 * the return array is null.
	 * 
	 * @return An array containing successor nodes or null if an action is illegal 
	 */
	public Node[] getSuccessors() {
		//locating blank tile
		int blanki=0;
		int blankj=0;
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==0) {
					blanki = i;
					blankj = j;
				}
			}
		}
		//Initializing successors list
		Node[] successors = new Node[4];
		//Filling successors list
		if(blanki!=0) successors[0] = new MyNode(Node.UP, this);
		if(blanki!=2) successors[1] = new MyNode(Node.DOWN, this);
		if(blankj!=0) successors[2] = new MyNode(Node.LEFT, this);
		if(blankj!=2) successors[3] = new MyNode(Node.RIGHT, this);
		return successors;
	}
	
	
	/**
	 * Returns the board corresponding to the state
	 * @return  A particular state of the 8-puzzle
	 */
	public int[][] getBoard(){
		return board;
	}

	
	/**
	 * Returns the board corresponding to the goal configuration
	 * @return The goal state
	 */
	public int[][] getGoal(){
		return goal;
	}

	
	/**
	 * Returns the number of moves from the initial start node to the current node 
	 * @return The number of moves from the initial node to the current node
	 */
	public int getDepth() {
		return depth;
	}
	
	
	/**
	 * Returns true if the state is the goal state and false otherwise
	 * @return True if the state is the goal, false otherwise
	 */
	public boolean isGoal() {
		if(Arrays.equals(this.board[0],goal[0]) && Arrays.equals(this.board[1], goal[1]) && Arrays.equals(this.board[2],goal[2])) return true;
		return false;
	}
	
	
	/**
	 * Default implementations of these 3 methods are provided by Java's Object class however you will
	 * need to override them so that nodes can be compared to one another. The hashcode and equals methods
	 * should use the board. That is, two nodes are equal if they have the same board configuration. 
	 */
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + Arrays.deepHashCode(board);
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MyNode) {
			MyNode theObj = (MyNode) obj;
			if(Arrays.equals(this.board[0],theObj.board[0]) && Arrays.equals(this.board[1], theObj.board[1]) && Arrays.equals(this.board[2],theObj.board[2])) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String returnString = "";
		for(int i=0;i<3;i++) {
			if(i!=0) returnString = returnString + "\n";
			for(int j=0;j<3;j++) {
				returnString = returnString + board[i][j] + " ";
			}
		}
		returnString += "\n";
		return returnString;
	}
	
	private int[][] exchange(int[][] initboard,int blanki, int blankj, String direction){
		int[][] board = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j] = initboard[i][j];
			}
		}
		int otheri =0;
		int otherj =0;
		if(direction.equals(Node.UP)) {
			otheri = blanki-1;
			otherj = blankj;
		}
		else {
			if(direction.equals(Node.DOWN)) {
				otheri = blanki+1;
				otherj = blankj;
				
			}
			else {
				if(direction.equals(Node.LEFT)) {
					otheri = blanki;
					otherj = blankj-1;
				}
				else {
					if(direction.equals(Node.RIGHT)) {
						otheri = blanki;
						otherj = blankj + 1;
					}
				}
			}
		}
		int holding = board[blanki][blankj];
		board[blanki][blankj] = board[otheri][otherj];
		board[otheri][otherj] = holding;
		return board;
	}

	/**
	 * Testing everything out.
	 * @param args
	 */
	public static void main(String args[]) {
		int[][] initBoard = new int[3][3];
		initBoard[0][0] = 1;
		initBoard[0][1] =2;
		initBoard[0][2] =3;
		initBoard[1][0] = 4;
		initBoard[1][1] = 5;
		initBoard[1][2] = 6;
		initBoard[2][0] = 7;
		initBoard[2][1] = 8;
		initBoard[2][2] = 0;
		MyNode yeet1 = new MyNode(initBoard);
		MyNode yeet2 = new MyNode(initBoard);
		System.out.println("Initial:");
		System.out.println(yeet1.toString());
//		System.out.println(yeet1.equals(yeet2));
//		Node[] successors = yeet1.getSuccessors();
//		System.out.println(successors[0]);
//		System.out.println(successors[1]);
//		System.out.println(successors[2]);
//		System.out.println(successors[3]);
		System.out.println(yeet1.isGoal());
	}
}
