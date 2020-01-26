import java.awt.Image;

public class Knight extends ChessPiece{

	public Knight (Image img, Boolean whitePiece){
		super(img, whitePiece);
	}
	
	/**
	 * The method takes the coordinates of the selected piece and tests if the positions that form an L around the
	 * knight are possible moves. Moves are valid if there is no other piece obstructing its path or if its within
	 * the confines of the board. Possible moves are recorded in the char array with an M. A possible move
	 * will become a kill move when a piece of the opposite color is within its possible move sets. Kill moves are
	 * recorded in the char array with a K. 
	 * @return a char array of the selected knight's possible moves
	 */
	public char[][] getPossibleMoves(ChessPiece[][] arr, Coordinate c, ChessPiece emptySpace) {
		int xPos = c.getCol();
		int yPos = c.getRow();
		
		char[][] moves = super.getPossibleMoves(arr, c, emptySpace);
		
		
		if (yPos-2 >= 0 && xPos-1 >= 0) {
			if (arr[xPos-1][yPos-2].equals(emptySpace)) {
				moves[xPos-1][yPos-2] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos-1][yPos-2].isWhitePiece() == false) {
				moves[xPos-1][yPos-2] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos-1][yPos-2].isWhitePiece()) {
				moves[xPos-1][yPos-2] = 'K';
			}	
		}
		if (yPos-2 >= 0 && xPos+1 < arr[yPos].length) {
			if (arr[xPos+1][yPos-2].equals(emptySpace)) {
				moves[xPos+1][yPos-2] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos+1][yPos-2].isWhitePiece() == false) {
				moves[xPos+1][yPos-2] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos+1][yPos-2].isWhitePiece()) {
				moves[xPos+1][yPos-2] = 'K';
			}
		}
		if (xPos+2 < arr[yPos].length && yPos-1 >= 0) {
			if (arr[xPos+2][yPos-1].equals(emptySpace)) {
				moves[xPos+2][yPos-1] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos+2][yPos-1].isWhitePiece() == false) {
				moves[xPos+2][yPos-1] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos+2][yPos-1].isWhitePiece()) {
				moves[xPos+2][yPos-1] = 'K';
			}
		}
		if (xPos+2 < arr[yPos].length && yPos+1 < arr.length) {
			if (arr[xPos+2][yPos+1].equals(emptySpace)) {
				moves[xPos+2][yPos+1] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos+2][yPos+1].isWhitePiece() == false) {
				moves[xPos+2][yPos+1] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos+2][yPos+1].isWhitePiece()) {
				moves[xPos+2][yPos+1] = 'K';
			}
		}
		if (yPos+2 < arr.length && xPos-1 >= 0) {
			if (arr[xPos-1][yPos+2].equals(emptySpace)) {
				moves[xPos-1][yPos+2] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos-1][yPos+2].isWhitePiece() == false) {
				moves[xPos-1][yPos+2] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos-1][yPos+2].isWhitePiece()) {
				moves[xPos-1][yPos+2] = 'K';
			}
		}
		if (yPos+2 < arr.length && xPos+1 < arr[yPos].length) {
			if (arr[xPos+1][yPos+2].equals(emptySpace)) {
				moves[xPos+1][yPos+2] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos+1][yPos+2].isWhitePiece() == false) {
				moves[xPos+1][yPos+2] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos+1][yPos+2].isWhitePiece()) {
				moves[xPos+1][yPos+2] = 'K';
			}
		}
		if (xPos-2 >= 0 && yPos-1 >= 0) {
			if (arr[xPos-2][yPos-1].equals(emptySpace)) {
				moves[xPos-2][yPos-1] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos-2][yPos-1].isWhitePiece() == false) {
				moves[xPos-2][yPos-1] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos-2][yPos-1].isWhitePiece()) {
				moves[xPos-2][yPos-1] = 'K';
			}
		}
		if (xPos-2 >= 0 && yPos+1 < arr.length) {
			if (arr[xPos-2][yPos+1].equals(emptySpace)) {
				moves[xPos-2][yPos+1] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos-2][yPos+1].isWhitePiece() == false) {
				moves[xPos-2][yPos+1] = 'K';
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos-2][yPos+1].isWhitePiece()) {
				moves[xPos-2][yPos+1] = 'K';
			}
		}
		
		return moves;
	}

}
