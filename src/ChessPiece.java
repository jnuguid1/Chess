import java.awt.Image;

public abstract class ChessPiece {
	private Image pieceImg;
	private boolean highlighted;
	private boolean whitePiece;
	private boolean hasMoved;
	
	public ChessPiece(Image img, boolean whitePiece) {
		this.whitePiece = whitePiece;
		pieceImg = img;
		highlighted = false;
		hasMoved = false;
	}
	
	public ChessPiece() {
	}
	
	/**
	 * Method that will be overridden by the chessPiece's subclasses.
	 * Creates the char array for possible moves and fills the array with
	 * char 'E' to represent invalid moves
	 * @param arr  Array of chess pieces used to find possible moves
	 * @param c  Coordinate of the piece that will have its possible moves found
	 * @param emptySpace  Object that represents the empty spaces of the ChessPiece array
	 * @return the filled char array
	 */
	public char[][] getPossibleMoves(ChessPiece[][]arr, Coordinate c, ChessPiece emptySpace) {		
		char[][] moves = new char[arr.length][arr[0].length];
		
		for (int j = 0; j < moves.length; j++) {
			for (int k = 0; k < moves[j].length; k++) {
				moves[j][k] = 'E';
			}
		}
		return moves;
	}
	
	/**
	 * Returns the ChessPiece image
	 * @return corresponding image of the ChessPiece
	 */
	public Image getImage() {
		return pieceImg;
	}
	
	/**
	 * highlighted is set to true. Variable is used to let the ChessPiece know it has been selected.
	 */
	public void highlight() {
		highlighted = true;
	}
	
	/**
	 * highlighted is set to false
	 */
	public void removeHighlight() {
		highlighted = false;
	}
	
	/**
	 * Returns the highlighted boolean
	 * @return the value of the highlighted boolean
	 */
	public boolean isHighlighted() {
		return highlighted;
	}
	
	/**
	 * Returns the whitePiece boolean
	 * @return the boolean that determines if a ChessPiece is a white piece
	 */
	public boolean isWhitePiece() {
		return whitePiece;
	}
	
	/**
	 * Used to determine pawn movement. Sets hasMoved to true.
	 */
	public void moved() {
		hasMoved = true;
	}
	
	/**
	 * @return the value of hasMoved.
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
}
