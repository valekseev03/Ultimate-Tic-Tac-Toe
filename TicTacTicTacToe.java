import java.util.Scanner;

public class TicTacTicTacToe {
	private String[][] board = new String [9][9];
	private String[][] finalBoard = new String [3][3];
	
	public TicTacTicTacToe() {
		resetBoards();
	}
	
	private void printBoard() {
		System.out.print("              ");
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");			
				
				if(j != 0 && (j + 1) % 3 == 0) {
					System.out.print(" ");
				}
			}
			System.out.println();
			if(i != 0 && (i + 1) % 3 == 0) {
				System.out.println();
			}
			
			if(i != 8) {
				System.out.print("              ");
			}
		}
	}
	
	private void resetBoards() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				board[i][j] = "-";
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				finalBoard[i][j] = "-";
			}
		}
	}
	
	private boolean checkWin(String piece) {
		String boardPieces = "";
		
		//Horizontal
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				boardPieces += finalBoard[i][j];
			}
			
			if(boardPieces.contains(piece + piece + piece)) {
				return true;
			}else {
				boardPieces = "";
			}
		}
		
		//Vertical
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						boardPieces += finalBoard[j][i];
					}
					
					if(boardPieces.contains(piece + piece + piece)) {
						return true;
					}else {
						boardPieces = "";
					}
				}
				
		//Diagonal #1
				for(int i = 0; i < 3; i++) {
					boardPieces += finalBoard[i][i];					
				}
				
				if(boardPieces.contains(piece + piece + piece)) {
					return true;
				}else {
					boardPieces = "";
				}
				
				
		//Diagonal #2
				for(int i = 0; i < 3; i++) {
					boardPieces += finalBoard[i][finalBoard.length - 1 - i];					
				}
				
				if(boardPieces.contains(piece + piece + piece)) {
					return true;
				}else {
					boardPieces = "";
				}
				
				
		return false;
	}
	
	private boolean placePiece(int i, int j, int ioffset, int joffset, String piece) {
		if(board[i + (ioffset * 3)][j + (joffset * 3)] == "-") {
			board[i + (ioffset * 3)][j + (joffset * 3)] = piece;
			return true;
		}else {		
			return false;
		}
	}
	
	private boolean preventBoardAreaTie(int ioffset, int joffset) {
		if(!checktoReplaceBoardArea(ioffset, joffset, "x") && !checktoReplaceBoardArea(ioffset, joffset, "o")) {
			boolean runCode = true;
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(board[i + (3 * ioffset)][j  + (3 * joffset)] == "-") {
						runCode =  false;
					}
				}
			}
			
			if(runCode) {
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						board[i + (3 * ioffset)][j  + (3 * joffset)] = "-";
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkIfBoardFull() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == "-") {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean checktoReplaceBoardArea(int ioffset, int joffset, String piece) {
		String boardPieces = "";
		
		//Horizontal
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				boardPieces += board[i + (3 * ioffset)][j  + (3 * joffset)];
			}
			
			if(boardPieces.contains(piece + piece + piece)) {
				return true;
			}else {
				boardPieces = "";
			}
		}
		
		//Vertical
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						boardPieces += board[j + (3 * ioffset)][i + (3 * joffset)];
					}
					
					if(boardPieces.contains(piece + piece + piece)) {
						return true;
					}else {
						boardPieces = "";
					}
				}
				
		//Diagonal #1
				for(int i = 0; i < 3; i++) {
					boardPieces += board[i + (3 * ioffset)][i + (3 * joffset)];					
				}	
					
				if(boardPieces.contains(piece + piece + piece)) {
					return true;
				}else {
					boardPieces = "";
				}
				
				
		//Diagonal #2
				for(int i = 0; i < 3; i++) {
					boardPieces += board[i + (3 * ioffset)][(3 * joffset) + 2 - i];					
				}	
				
				if(boardPieces.contains(piece + piece + piece)) {
					return true;
				}else {
					boardPieces = "";
				}
				
				
		return false;
	}
	
	private void replaceBoardAreawithPiece(int ioffset, int joffset, String piece) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i + (3 * ioffset)][j  + (3 * joffset)] = piece;
			}
		}
		
		finalBoard[ioffset][joffset] = piece;
	}
	
	public void playNewGame() {
		System.out.println("--------Welcome To Tic-Tac-Tic-Tac-Toe!-------- \n");
		resetBoards();
		Scanner scan = new Scanner(System.in);
		int turn = 0;
		String piece = "x";
		printBoard();
		
		System.out.println("Player #1 [x]:");
		System.out.println("Print X Coordinate To Place Piece (1-9):");
		int y = scan.nextInt() - 1;
		
		System.out.println("Print Y Coordinate To Place Piece (1-9):");
		int x = 8 - (scan.nextInt() - 1);
		
		placePiece(x, y, 0, 0, piece);
		int xoffset = (x + 1) % 3 - 1;
		int yoffset = (y + 1) % 3 - 1;
		turn++;
		
		while(!checkWin("o") && !checkWin("x") && !checkIfBoardFull()) {
			System.out.println("------------------------------------------------");
			printBoard();
			
			if (xoffset < 0) {
				xoffset = 2;
			}
			
			if (yoffset < 0) {
				yoffset = 2;
			}
			
			if(turn % 2 == 0) {
				System.out.println("Player #1 [x]:");
				piece = "x";
			}else {
				System.out.println("Player #2 [o]:");
				piece = "o";
			}	
			
			if(finalBoard[xoffset][yoffset] == "-") {
				System.out.println("Current Playing Board Area: [" + (yoffset + 1) + ", " + (3 - xoffset) + "]");
				System.out.println("Print X Coordinate To Place Piece (1-3):");
				y = scan.nextInt() - 1;
				
				System.out.println("Print Y Coordinate To Place Piece (1-3):");				
				x = 2 - (scan.nextInt() - 1);
				
				if(placePiece(x, y, xoffset, yoffset, piece)) {
					if(checktoReplaceBoardArea(xoffset, yoffset, piece)) {
						replaceBoardAreawithPiece(xoffset, yoffset, piece);
					}
					
					if(preventBoardAreaTie(xoffset, yoffset)) {
						System.out.println("Board Area [" + (yoffset + 1) + ", " + (3 - xoffset) + "] Full! Resetting Area!");
					}
					
					xoffset = x;
					yoffset = y;
				}else{
					System.out.println("Piece Already Placed In That Spot! Turn Skipped!");
				}
				
			}else {
				System.out.println("Board Area Filled In That Spot! Place Piece Anywhere!");
				System.out.println("Print X Coordinate To Place Piece (1-9):");
				y = scan.nextInt() - 1;
				
				System.out.println("Print Y Coordinate To Place Piece (1-9):");
				x = 8 - (scan.nextInt() - 1);
				
				if(placePiece(x, y, 0, 0, piece)) {					
					xoffset = x / 3;
					yoffset = y / 3;
					if(checktoReplaceBoardArea(xoffset, yoffset, piece)) {
						replaceBoardAreawithPiece(xoffset, yoffset, piece);
					}
					
					if(preventBoardAreaTie(xoffset, yoffset)) {
						System.out.println("Board Area [" + (yoffset + 1) + ", " + (3 - xoffset) + "] Full! Resetting Area!");
					}
					
					xoffset = (x + 1) % 3 - 1;
					yoffset = (y + 1) % 3 - 1;
				}else {
					System.out.println("Piece Already Placed In That Spot! Turn Skipped!");
				}

			}
			turn++;
		}
		
		printBoard();
		if(checkWin("x")) {
			System.out.println("Game Over! Player #1 Won!");
		}else if(checkWin("o")){
			System.out.println("Game Over! Player #2 Won!");
		}else {
			System.out.println("Game Over! It Was A Tie!");
		}
		
		scan.close();
	}
	
	
	public static void main(String [] args) {
		TicTacTicTacToe ticTacToeGame = new TicTacTicTacToe();
		ticTacToeGame.playNewGame();
	}
}
