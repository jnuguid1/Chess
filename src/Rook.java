import java.awt.Image;

public class Rook extends ChessPiece{

	public Rook (Image img, Boolean whitePiece){
		super(img, whitePiece);
	}

	/**
	 * The method takes the coordinates of the selected piece and tests if the positions to left, above, below and to the right of
	 *  the rook are possible moves. Moves are valid if there is no other piece obstructing its path or if its within
	 * the confines of the board. Possible moves are recorded in the char array with an M. A possible move
	 * will become a kill move when a piece of the opposite color is within its possible move sets. Kill moves are
	 * recorded in the char array with a K. 
	 * @return a char array of the selected rook's possible moves
	 */
	public char[][] getPossibleMoves(ChessPiece[][] arr, Coordinate c,
			ChessPiece emptySpace) {
		int xPos = c.getCol();
		int yPos = c.getRow();
		
		char[][] moves = super.getPossibleMoves(arr, c, emptySpace);
		
		int i = 1;
		
		while (yPos+i < arr.length) {
			if (arr[xPos][yPos+i].equals(emptySpace)) {
				moves[xPos][yPos+i] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos][yPos+i].isWhitePiece() == false) {
				moves[xPos][yPos+i] = 'K';
				break;
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos][yPos+i].isWhitePiece()) {
				moves[xPos][yPos+i] = 'K';
				break;
			} else {
				break;
			}
			
			i++;
		}
		
		i = 1;
		
		while (yPos-i >= 0) {
			if (arr[xPos][yPos-i].equals(emptySpace)) {
				moves[xPos][yPos-i] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos][yPos-i].isWhitePiece() == false) {
				moves[xPos][yPos-i] = 'K';
				break;
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos][yPos-i].isWhitePiece()) {
				moves[xPos][yPos-i] = 'K';
				break;
			} else {
				break;
			}	
			i++;
		}
		
		i = 1;
		
		while (xPos+i < arr[yPos].length) {
			if (arr[xPos+i][yPos].equals(emptySpace)) {
				moves[xPos+i][yPos] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos+i][yPos].isWhitePiece() == false) {
				moves[xPos+i][yPos] = 'K';
				break;
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos+i][yPos].isWhitePiece()) {
				moves[xPos+i][yPos] = 'K';
				break;
			} else {
				break;
			}	
			i++;
		}
		
		i = 1;
		
		while (xPos-i >= 0) {
			if (arr[xPos-i][yPos].equals(emptySpace)) {
				moves[xPos-i][yPos] = 'M';
			} else if(arr[xPos][yPos].isWhitePiece() && arr[xPos-i][yPos].isWhitePiece() == false) {
				moves[xPos-i][yPos] = 'K';
				break;
			} else if (arr[xPos][yPos].isWhitePiece() == false && arr[xPos-i][yPos].isWhitePiece()) {
				moves[xPos-i][yPos] = 'K';
				break;
			} else {
				break;
			}	 
			i++;
		}
		
		i = 0;
		
		
		return moves;
	}

}
