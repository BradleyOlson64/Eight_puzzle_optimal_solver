package heuristic;

import search.MyNode;
import search.Node;
import util.Queue;

/**
 * This class implements the NumMisplaced heuristic.
 * @author baolson
 *
 */
public class NumMisplaced implements Heuristic{
	/**
	 * Implementing NumMisplaced for a Node, returning it's number of misplaced tiles.
	 * @return the heuristic value 
	 */
	public int evaluate(Node node) {
		int count = 0;
		//Getting node info
		int[][] goal = node.getGoal();
		int[][] board = node.getBoard();
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(goal[i][j]!=0) {
					if(goal[i][j]!= board[i][j]) count++;
				}
			}
		}
		count = count + node.getDepth();
		return count;
	}
	
	/**
	 * Testing out the results.
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
		//Creating heuristic instance
		NumMisplaced a = new NumMisplaced();
		//Making node to use
		Node a1 = new MyNode(initBoard);
		System.out.println(a1.toString());
		System.out.println(a.evaluate(a1));
	}
}
