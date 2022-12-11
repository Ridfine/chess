package kurs2_task2.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Board {
	private TreeMap<String, Cell> board = new TreeMap<>();
	private String[] ABC = { "A", "B", "C", "D", "E", "F", "G", "H"};


	public TreeMap<String, Cell> getBoard() {
		return board;
	}

	public void setBoard(TreeMap<String, Cell> board) {
		this.board = board;
	}
	public Cell getCell(String cord){
		return board.get(cord);
	}
	public void buildBoard() {
		String[] tempABC = { "-A", "A", "B", "C", "D", "E", "F", "G", "H", "-H"};
		creationCell(tempABC);
		linkingCell(tempABC);
		clearCell(tempABC);
	}

	public void creationCell(String[] tempABC){
		Boolean tempColor = true;
		for(int i = 9; i >= 0; i--) { // 8 - 1
			for(int j = 0; j < 10; j++) { // a - h
				board.put(i + tempABC[j], new Cell(tempColor, i + tempABC[j]));
				tempColor = !tempColor;
			}
		}
	}
	public void linkingCell(String[] tempABC){
		//   0         1          2            3      	   4       	    5        	6             7
		//toUPLeft    toUP,    toUPRight,   toRight,   toDownRight,   toDown,   toDownLeft,     toLeft,
		ArrayList<Cell> tempArrayLinks = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null));
		for(int i = 8; i > 0; i--) { // 8 - 1
			for(int j = 1; j < 9; j++) { // A - H
				tempArrayLinks.set(0, board.get(i+1 + tempABC[j-1]));
				tempArrayLinks.set(1, board.get(i+1 + tempABC[j]));
				tempArrayLinks.set(2, board.get(i+1 + tempABC[j+1]));
				tempArrayLinks.set(3, board.get(i + tempABC[j+1]));
				tempArrayLinks.set(4, board.get(i-1 + tempABC[j+1]));
				tempArrayLinks.set(5, board.get(i-1 + tempABC[j]));
				tempArrayLinks.set(6, board.get(i-1 + tempABC[j-1]));
				tempArrayLinks.set(7, board.get(i + tempABC[j-1]));


				if(i + 1 > 8) {
					tempArrayLinks.set(0, null);
					tempArrayLinks.set(1, null);
					tempArrayLinks.set(2, null);
				}
				if(i - 1 < 1) {
					tempArrayLinks.set(4, null);
					tempArrayLinks.set(5, null);
					tempArrayLinks.set(6, null);
				}
				if(j - 1 < 1) {
					tempArrayLinks.set(0, null);
					tempArrayLinks.set(6, null);
					tempArrayLinks.set(7, null);
				}
				if(j + 1 > 8) {
					tempArrayLinks.set(2, null);
					tempArrayLinks.set(3, null);
					tempArrayLinks.set(4, null);
				}
				board.get(i + tempABC[j]).setLink(tempArrayLinks);
			}
		}
	}
	public void clearCell(String[] tempABC){
		for (int i = 0; i < 10; i++) {
			board.remove(9 + tempABC[i]);
		}
		for (int i = 0; i < 10; i++) {
			board.remove(0 + tempABC[i]);
		}
		for (int i = 1; i < 9; i++) {
			board.remove(i + tempABC[0]);
		}
		for (int i = 1; i < 9; i++) {
			board.remove(i + tempABC[9]);
		}
	}

	public void printBoard(HashMap<Cell, Piece> cellPiece ){
		System.out.println(" \t╦═══════╦═══════╦═══════╦═══════╦═══════╦═══════╦═══════╦═══════╗");
		for (int i = 8; i > 0; i--) {
			System.out.print(i + "\t║");
			for (int j = 0; j < 8; j++) {
				if (cellPiece.get(board.get(i + ABC[j])) != null) {
					System.out.print("\t" + cellPiece.get(board.get(i + ABC[j])).getIcon() + "\t║");
				}else {
					System.out.print("\t" + " " + "\t║");
				}

			}
			System.out.print("\n");
			System.out.println(" \t╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╣");

		}
		System.out.println(" \t \tA\t \tB\t \tC\t \tD\t \tE\t \tF\t \tG\t \tH\t ");
	}
}
