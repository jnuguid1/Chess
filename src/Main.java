import javax.swing.JFrame;

public class Main {
	final static int ROWS = 8;
    final static int COLS = 8;
	static Board b;
	JFrame frame; 
	
    public static void main(String[] args) {

        b = new Board(ROWS,COLS);
        Coordinate c;
        int row, col;
        while(true){
            c=b.getClick();
            row = c.getRow();
            col = c.getCol();
            b.displayMessage(row + ", " + col);
            //Deselects the selected piece if the clicked coordinate isn't a valid move
            if (b.getLastCord() != null && b.canMove(c) == false && b.isEmptySpace(c)) { 
            	b.deselectPiece(b.getLastCord());
            } 
            //Moves the selected piece if the clicked coordinate is a valid position to move to
            else if (b.getLastCord() != null && b.isHighlighted(b.getLastCord()) && !(c.equals(b.getLastCord())) && b.canMove(c)) {
            	b.movePiece(b.getLastCord(), c);
            	b.deselectPiece(c);
        	}
            //Moves the selected piece onto the piece at the clicked coordinate (kill move) if it is a valid mvoe
            else if (b.getLastCord() != null && b.isHighlighted(b.getLastCord()) && !(c.equals(b.getLastCord())) && b.canKill(c)) {
        		b.movePiece(b.getLastCord(), c);
        		b.deselectPiece(c);
        	} 
            //Deselects the already selected piece
            else if (b.isHighlighted(c)) {
            	b.deselectPiece(c);
            } 
            //Selects a new piece if it is the same color as the previously selected piece
            else if (b.getLastCord() != null && b.isHighlighted(b.getLastCord()) && b.isEmptySpace(c) == false) {
            	b.deselectPiece(b.getLastCord());
            	b.selectPiece(c);
            } 
            //Selects the piece at the clicked coordinate if nothing else has been selected
            else {
            	b.selectPiece(c);
            }
        }
    }
    
    

}
