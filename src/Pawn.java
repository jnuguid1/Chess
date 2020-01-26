import java.awt.Image;

public class Pawn extends ChessPiece{
	
	
	public Pawn (Image img, boolean whitePiece){
		super(img, whitePiece);
	}

	/**
	 * The method takes the coordinates of the selected piece and tests if the positions 1 or 2 space above
	 * or below the pawn are possible moves. Moves are valid if there is no other piece obstructing its path or if its within
	 * the confines of the board. Possible moves are recorded in the char array with an M. A possible move
	 * will become a kill move when a piece of the opposite color is diagonally ahead of the pawn's position. Kill moves are
	 * recorded in the char array with a K. 
	 * @return a char array of the selected pawn's possible moves
	 */
	public char[][] getPossibleMoves(ChessPiece[][] arr, Coordinate c, ChessPiece emptySpace) {
		int xPos = c.getCol();
		int yPos = c.getRow();
		
		char[][] moves = super.getPossibleMoves(arr, c, emptySpace);
		
		if (arr[xPos][yPos].isWhitePiece() == true) {
			if (yPos-1 >= 0 && arr[xPos][yPos-1].equals(emptySpace)) {
				moves[xPos][yPos-1] = 'M';
				if (arr[xPos][yPos].hasMoved() == false && (arr[xPos][yPos-2].equals(emptySpace))) {
					moves[xPos][yPos-2] = 'M';
				}
			}
			if (xPos+1 < arr[yPos].length && yPos-1 >= 0 && arr[xPos+1][yPos-1].isWhitePiece() == false && !(arr[xPos+1][yPos-1].equals(emptySpace))) {
				moves[xPos+1][yPos-1] = 'K';
			}
			if (xPos-1 >= 0 && yPos-1 >= 0 && arr[xPos-1][yPos-1].isWhitePiece() == false && !(arr[xPos-1][yPos-1].equals(emptySpace))) {
				moves[xPos-1][yPos-1] = 'K';
			}
		} else if (arr[xPos][yPos].isWhitePiece() == false ){
			if (yPos+1 < arr[yPos].length && arr[xPos][yPos+1].equals(emptySpace)) {
				moves[xPos][yPos+1] = 'M';
				if (arr[xPos][yPos].hasMoved() == false && (arr[xPos][yPos+2].equals(emptySpace))) {
					moves[xPos][yPos+2] = 'M';
				}
			}
			if (xPos+1 < arr[yPos].length && yPos+1 < arr.length && arr[xPos+1][yPos+1].isWhitePiece() == true && !(arr[xPos+1][yPos+1].equals(emptySpace))) {
				moves[xPos+1][yPos+1] = 'K';
			}
			if (xPos-1 >= 0 && yPos+1 < arr.length && arr[xPos-1][yPos+1].isWhitePiece() == true && !(arr[xPos-1][yPos+1].equals(emptySpace))) {
				moves[xPos-1][yPos+1] = 'K';
			}
		}
		
		return moves;
	}
	
	
}
