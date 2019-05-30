package heuristic;

import search.MyNode;
import search.Node;
import java.lang.Math;

/**
 * This class implements the manhattan distance heuristic. This entails summing the number of tile 
 * swaps worth of space between each number and the position it belongs in.
 * @author baolson
 *
 */
public class ManhattanDistance implements Heuristic{
	/**
	 * Finding the manhattan distance.
	 */
	public int evaluate(Node node) {
		//making counter
		int count = 0;
		//Getting node info
		int[][] goal = node.getGoal();
		int[][] board = node.getBoard();
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(goal[i][j]!=0) {
					for(int k=0;k<3;k++) {
						for(int l=0;l<3;l++) {
							if(goal[i][j]==board[k][l]) {
								count = count + Math.abs(i-k) + Math.abs(j-l);
							}
						}
					}
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
		ManhattanDistance a = new ManhattanDistance();
		//Making node to use
		Node a1 = new MyNode(initBoard);
		System.out.println(a1.toString());
		System.out.println(a.evaluate(a1));
	}
}
