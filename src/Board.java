import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/**  Board GUI for implementation with various games
 *   Author: Kirill Levin, Troy Vasiga, Chris Ingram
 *		Mr. Benum (modifications for writing text)
 */

public class Board extends JFrame
{
  private static final int X_DIM = 60;
  private static final int Y_DIM = 60;
  private static final int X_OFFSET = 30;
  private static final int Y_OFFSET = 70;
  private static final int FONT_SIZE = 20;
  private static final int FONT_OFFSET = 13;
  private static final int PEG_DIAMETER = 17;
  
  // Grid colours
  private static final Color GRID_COLOR_A = new Color(210,105,30);
  private static final Color GRID_COLOR_B = new Color(255,228,181);
  private static final Color HIGHLIGHT_COLOR = new Color(255,255,153);
  private static final Color PEG_COLOR = new Color(72,209,204);
  private static final Color KILL_COLOR = new Color(220, 39, 39);
  
  // Colour to use if a match is not found
  private Coordinate lastClick;  // How the mouse handling thread communicates 
                                 // to the board where the last click occurred
  private String message = "";
  private int numLines = 0;
  private int[][] line = new int[4][100];  // maximum number of lines is 100
  private int columns, rows;
  private JFrame frame;  
  
  private ChessPiece emptySpace = new NoPiece();
  
  private Image blackPawn = (new ImageIcon("Black Pawn.png")).getImage();
  private ChessPiece bPawn1 = new Pawn(blackPawn, false);
  private ChessPiece bPawn2 = new Pawn(blackPawn, false);
  private ChessPiece bPawn3 = new Pawn(blackPawn, false);
  private ChessPiece bPawn4 = new Pawn(blackPawn, false);
  private ChessPiece bPawn5 = new Pawn(blackPawn, false);
  private ChessPiece bPawn6 = new Pawn(blackPawn, false);
  private ChessPiece bPawn7 = new Pawn(blackPawn, false);
  private ChessPiece bPawn8 = new Pawn(blackPawn, false);
  
  Image blackRook = (new ImageIcon("Black Rook.png")).getImage();
  private ChessPiece bRook1 = new Rook(blackRook, false);
  private ChessPiece bRook2 = new Rook(blackRook, false);
  
  Image blackKnight = (new ImageIcon("Black Knight.png")).getImage();
  private ChessPiece bKnight1 = new Knight(blackKnight, false);
  private ChessPiece bKnight2 = new Knight(blackKnight, false);
  
  Image blackBishop = (new ImageIcon("Black Bishop.png")).getImage();
  private ChessPiece bBishop1 = new Bishop(blackBishop, false);
  private ChessPiece bBishop2 = new Bishop(blackBishop, false);
  
  Image blackQueen = (new ImageIcon("Black Queen.png")).getImage();
  private ChessPiece bQueen1 = new Queen(blackQueen, false);
  
  Image blackKing = (new ImageIcon("Black King.png")).getImage();
  private ChessPiece bKing = new King(blackKing, false);
  
  Image whitePawn = (new ImageIcon("White Pawn.png")).getImage();
  private ChessPiece wPawn1 = new Pawn(whitePawn, true);
  private ChessPiece wPawn2 = new Pawn(whitePawn, true);
  private ChessPiece wPawn3 = new Pawn(whitePawn, true);
  private ChessPiece wPawn4 = new Pawn(whitePawn, true);
  private ChessPiece wPawn5 = new Pawn(whitePawn, true);
  private ChessPiece wPawn6 = new Pawn(whitePawn, true);
  private ChessPiece wPawn7 = new Pawn(whitePawn, true);
  private ChessPiece wPawn8 = new Pawn(whitePawn, true);
  
  Image whiteRook = (new ImageIcon("White Rook.png")).getImage();
  private ChessPiece wRook1 = new Rook(whiteRook, true);
  private ChessPiece wRook2 = new Rook(whiteRook, true);
  
  Image whiteKnight = (new ImageIcon("White Knight.png")).getImage();
  private ChessPiece wKnight1 = new Knight(whiteKnight, true);
  private ChessPiece wKnight2 = new Knight(whiteKnight, true);
  
  Image whiteBishop = (new ImageIcon("White Bishop.png")).getImage();
  private ChessPiece wBishop1 = new Bishop(whiteBishop, true);
  private ChessPiece wBishop2 = new Bishop(whiteBishop, true);
  
  Image whiteQueen = (new ImageIcon("White Queen.png")).getImage();
  private ChessPiece wQueen1 = new Queen(whiteQueen, true);
  
  Image whiteKing = (new ImageIcon("White King.png")).getImage();
  private ChessPiece wKing = new King(whiteKing, true);
  
  Image background = (new ImageIcon("pleasant-background.png")).getImage();
  
  //2D array to store all of the chess piece objects that will be displayed on the board
  private ChessPiece[][] piecePositions;
  
  //Names of the players which will be displayed on the board
  private String player1;
  private String player2;
  
  private Coordinate lastCord;
  private ChessPiece lastPiece;
  
  private boolean firstClick = false;
  private boolean isWhiteTurn = true;
  
  /** A constructor to build a 2D board.
   */
  public Board (int rows, int cols)
  {
    this.columns = cols;
    this.rows = rows;
    this.setSize(2*X_OFFSET+X_DIM*cols,2*Y_OFFSET+Y_DIM*rows);

    this.setTitle("Chess");
    this.setResizable(false);
    
    piecePositions = new ChessPiece[cols][rows];
    
    //initialization of all the ChessPiece object location into the array
    piecePositions[0][0] = bRook1;
    piecePositions[1][0] = bKnight1;
    piecePositions[2][0] = bBishop1;
    piecePositions[3][0] = bQueen1;
    piecePositions[4][0] = bKing;
    piecePositions[5][0] = bBishop2;
    piecePositions[6][0] = bKnight2;
    piecePositions[7][0] = bRook2;
    piecePositions[0][1] = bPawn1;
    piecePositions[1][1] = bPawn2;
    piecePositions[2][1] = bPawn3;
    piecePositions[3][1] = bPawn4;
    piecePositions[4][1] = bPawn5;
    piecePositions[5][1] = bPawn6;
    piecePositions[6][1] = bPawn7;
    piecePositions[7][1] = bPawn8;
    
    piecePositions[0][7] = wRook1;
    piecePositions[1][7] = wKnight1;
    piecePositions[2][7] = wBishop1;
    piecePositions[3][7] = wKing;
    piecePositions[4][7] = wQueen1;
    piecePositions[5][7] = wBishop2;
    piecePositions[6][7] = wKnight2;
    piecePositions[7][7] = wRook2;
    piecePositions[0][6] = wPawn1;
    piecePositions[1][6] = wPawn2;
    piecePositions[2][6] = wPawn3;
    piecePositions[3][6] = wPawn4;
    piecePositions[4][6] = wPawn5;
    piecePositions[5][6] = wPawn6;
    piecePositions[6][6] = wPawn7;
    piecePositions[7][6] = wPawn8;
    
    for (int i = 0; i < piecePositions.length; i++){
    	for (int k = 0; k < piecePositions[i].length; k++) {
    		if (piecePositions[i][k] == null) {
    			piecePositions[i][k] = emptySpace;
    		}
    	}
    }
    
    //Instructions for players
    JOptionPane.showMessageDialog(frame, "How to play chess:\n"
    		+ "Chess is a game played between two opponents on opposite sides of a board containing 64 squares of alternating colors.\n"
    		+ "Each player has 16 pieces: 1 king, 1 queen, 2 rooks, 2 bishops, 2 knights, and 8 pawns. The goal of the game is to\n"
    		+ "checkmate the other king. Checkmate happens when the king is in a position to be captured (in check) and cannot \n"
    		+ "escape from capture.\n\n"
    		+ "The king can only move one square in any direction - up, down, to the sides, and diagonally. The queen is the most powerful\n"
    		+ "piece. She can move in any one straight direction - forward, backward, sideways, or diagonally - as far as possible as long\n"
    		+ "as she does not move through any of her own pieces.The rook may move as far as it wants, but only forward, backward, and to \n"
    		+ "the sides. The bishop may move as far as it wants, but only diagonally.Knights move in a very different way from the other \n"
    		+ "pieces – going two squares in one direction, and then one more move at a 90 degree angle, just like the shape of an “L”. \n "
    		+ "Pawns are unusual because they move and capture in different ways: they move forward, but capture diagonally. \n"
    		+ "Pawns can only move forward one square at a time, except for their very first move where they can move forward two squares.\n\n"
    		+ "As stated before, the purpose of the game is to checkmate the opponent’s king. This happens when the king is put into check and\n"
    		+ "cannot get out of check. There are only three ways a king can get out of check: move out of the way (though he cannot castle!), \n"
    		+ "block the check with another piece, or capture the piece threatening the king.");
    player1 = JOptionPane.showInputDialog("Enter the name of the person who will go first: ");
    player2 = JOptionPane.showInputDialog("Enter the name of the person who will go second: ");
    
    addMouseListener(
      new MouseInputAdapter() 
      {
        /** A method that is called when the mouse is clicked
         */
        public void mouseClicked(MouseEvent e) 
        { 
          int x = (int)e.getPoint().getX();
          int y = (int)e.getPoint().getY();
      
          // We need to by synchronized to the parent class so we can wake
          // up any threads that might be waiting for us
          synchronized(Board.this) 
          {
            // Subtract one from high end so clicks on the black edge
            // don't yield a row or column outside of board because of
            // the way the coordinate is calculated.
            if (x >= X_OFFSET &&
              x <= (X_OFFSET + (columns * X_DIM) - 1) &&
              y >= Y_OFFSET &&
              y <= (Y_OFFSET + (Board.this.rows * Y_DIM) - 1)) {
              lastClick = new Coordinate((int)e.getPoint().getY(),
                                         (int)e.getPoint().getX());
              // Notify any threads that would be waiting for a mouse click
              Board.this.notifyAll() ;
            } /* if */
          } /* synchronized */
        } /* mouseClicked */
      } /* anonymous MouseInputAdapater */
    );
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  
  /**
   * Selects a piece of the ChessPiece of the piecePosition
   * array. Selecting a piece will tell the board class
   * which piece is in use and to highlight it.
   * @param c Coordinate of the location of the player's click
   */
  public void selectPiece(Coordinate c) {
	  if(piecePositions[c.getCol()][c.getRow()].isWhitePiece() && isWhiteTurn == true ||
			  piecePositions[c.getCol()][c.getRow()].isWhitePiece() == false && isWhiteTurn == false) {
		  piecePositions[c.getCol()][c.getRow()].highlight();
		  if (lastCord != null) {
			  piecePositions[lastCord.getCol()][lastCord.getRow()].removeHighlight();		  
		  }
		  lastPiece = piecePositions[c.getCol()][c.getRow()];
		  lastCord = c;
	  }
	  
	  
  }
  
  /**
   * Deselects a ChessPiece of the piecePosition array. When a piece is 
   * deselected the highlight is also removed.
   * @param c Coordinate of the location of the player's click
   */
  
  public void deselectPiece(Coordinate c) {
	  if (!(piecePositions[c.getCol()][c.getRow()].equals(emptySpace))) {
		  piecePositions[c.getCol()][c.getRow()].removeHighlight();
	  }
	  lastCord = null;
  }
  
  /**
   * Returns the last coordinate that was selected
   * @return last coordinate
   */
  public Coordinate getLastCord() {
	  return lastCord;
  }
  
  /**
   * Determines if the ChessPiece at the passed coordinate
   * is selected. 
   * @param c Coordinate of the location of the player's click
   * @return Returns true if the piece at the coordinate c is highlighted. Otherwise returns false. 
   */
  public boolean isHighlighted(Coordinate c) {
	  if (!(piecePositions[c.getCol()][c.getRow()].equals(emptySpace)) && piecePositions[c.getCol()][c.getRow()].isHighlighted()) {
		  return true;
	  }
	  return false;
  }
  
  /**
   * Determines if a piece exists at the passed coordinate location.
   * @param c Coordinate of the location of the player's click
   * @return Returns true if the coordinate is empty. Otherwise returns false.
   */
  public boolean isEmptySpace(Coordinate c) {
	  if (piecePositions[c.getCol()][c.getRow()].equals(emptySpace)) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  /**
   * Moves the piece at cInitial to the cFinal coordinate. After the piece is 
   * removed, the image at the original location is also erased. ChessPiece's moved()
   * method is used to tell a pawn that they've moved. In addition, the method
   * checks if a pawn can be promoted, searches for any check mates, and switches to 
   * the next player's turn.
   * @param cInitial The coordinate of the piece that will be moved
   * @param cFinal The coordinate of that the piece will be moved to
   */
  public void movePiece(Coordinate cInitial, Coordinate cFinal) {
	  piecePositions[cFinal.getCol()][cFinal.getRow()] = piecePositions[cInitial.getCol()][cInitial.getRow()];
	  piecePositions[cInitial.getCol()][cInitial.getRow()] = emptySpace;
	  piecePositions[cFinal.getCol()][cFinal.getRow()].moved();
	  if (canPromote(cFinal)) {
		  promotePawn(cFinal);
	  }
	  if (isCheckmate()) {
		  System.exit(1);
	  }
	  changeTurn();
			  
  }
  
  /**
   * Checks the possible moves of the piece at coordinate c and
   * determines if it can move
   * If the selected piece is a king, the method will prevent the king
   * from putting itself into check mate
   * @param c Coordinate of the location of the player's click
   * @return Returns true if coordinate c is a move in the array of the piece's possible moves. If the selected piece
   * is a king, return false if the possible move will also result in a check
   */
  public boolean canMove(Coordinate c) {
	  if (piecePositions[lastCord.getCol()][lastCord.getRow()].equals(wKing) || piecePositions[lastCord.getCol()][lastCord.getRow()].equals(bKing)) {
		  if (getMoves()[c.getCol()][c.getRow()] == 'M' && searchChecks()[c.getCol()][c.getRow()] != 'C') { 
			  return true;
		  } else {
			  return false;
		  }
	  } else {
		  if (getMoves()[c.getCol()][c.getRow()] == 'M') {
			  return true;
		  } else {
			  return false;
		  }
	  }
	  
		
  }
  /**
   * Checks the possible moves the selected piece at c for
   * kill moves
   * @param c Coordinate of the location of the player's click
   * @return Returns true if the coordinate c is a kill move in the corresponding char list of possible moves.
   * Otherwise returns false. 
   */
  public boolean canKill(Coordinate c) {
	  
	  if (getMoves()[c.getCol()][c.getRow()] == 'K') {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  /**
   * Searches for the positions in which either king would be in
   * check mate
   * @return A char array of all the possible check locations
   */
  public char[][] searchChecks() {
	  char[][] moves;
	  char[][] checkPositions = new char[piecePositions.length][piecePositions[0].length];
	  
	  for (int i = 0; i < checkPositions.length; i++) {
		  for (int j = 0; j < checkPositions[0].length; j++) {
			  checkPositions[i][j] = 'E';
		  }
	  }
	  
	  for (int i = 0; i < checkPositions.length; i++) {
		  for (int j = 0; j < checkPositions[0].length; j++) {
			  checkPositions[i][j] = 'E';
		  }
	  }
	  
	  for (int i = 0; i < piecePositions.length; i++) {
		  for (int j = 0; j < piecePositions[i].length; j++) {
			  if (isWhiteTurn == true) {
				  if (piecePositions[i][j].isWhitePiece() == false) {
					  moves = piecePositions[i][j].getPossibleMoves(piecePositions, new Coordinate(i,j), emptySpace);
					  for (int m = 0; m < moves.length; m++) {
						  for (int n = 0; n < moves[m].length; n++) {
							  if (moves[m][n] == 'K' && piecePositions[m][n].equals(wKing)) {
								  checkPositions[m][n] = 'L';								  
							  } else if (moves[m][n] == 'M') {
								  checkPositions[m][n] = 'C';
								 
							  }
						  }
					  }
				  }
			  } else if (isWhiteTurn == false){
				  if (piecePositions[i][j].isWhitePiece()) {
					  moves = piecePositions[i][j].getPossibleMoves(piecePositions, new Coordinate(i,j), emptySpace);
					  for (int m = 0; m < moves.length; m++) {
						  for (int n = 0; n < moves[m].length; n++) {
							  if (moves [m][n] == 'K' && piecePositions[m][n].equals(bKing)) {
								  checkPositions[m][n] = 'L';
							  }
							  if (moves[m][n] == 'M') {
								  checkPositions[m][n] = 'C';
							  }
						  }
					  }
				  }
			  }
		  }
	  }
	  for (int m = 0; m < checkPositions.length; m++) {
		  for (int n = 0; n < checkPositions[m].length; n++) {
		  }
		 
	  }
	  return checkPositions;
  }
  
  /**
   * Stores the possible moves of a piece into a char array
   * @return char array of possible moves
   */
  private char[][] getMoves() {
	  char[][] moves = new char[piecePositions.length][piecePositions[0].length];
		if (lastCord != null) {
			for (int i = 0; i < piecePositions.length; i++) {
				for (int j = 0; j < piecePositions[i].length; j++) {
					moves[i][j] = lastPiece.getPossibleMoves(piecePositions, lastCord, emptySpace)[i][j];
				}
			}
		}
		return moves;
  }
  
  /**
   * Switches the turn from player to player
   */
  public void changeTurn() {
	  if (isWhiteTurn == true) {
		  isWhiteTurn = false;
	  } else {
		  isWhiteTurn = true;
	  }
  }
  
  /**
   * Checks if a pawn can be promoted (if it reaches the end of the board)
   * @param c - Coordinate of the location of the player's click
   * @return Return true if a pawn reaches the opposite side of the board. Otherwise returns false.
   */
  private boolean canPromote(Coordinate c) {
	  if (c.getRow() == 0 && (lastPiece.equals(wPawn1)||lastPiece.equals(wPawn2)||lastPiece.equals(wPawn3)||lastPiece.equals(wPawn4)
			  || lastPiece.equals(wPawn5)||lastPiece.equals(wPawn6)||lastPiece.equals(wPawn7)||lastPiece.equals(wPawn8))) {
		  return true;
	  } else if (c.getRow() == piecePositions.length-1 && (lastPiece.equals(bPawn1)||lastPiece.equals(bPawn2)||lastPiece.equals(bPawn3)
			  ||lastPiece.equals(bPawn4)||lastPiece.equals(bPawn5)||lastPiece.equals(bPawn6)||lastPiece.equals(bPawn7)
			  ||lastPiece.equals(bPawn8))) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  /**
   * Promotes the pawn at coordinate c to the piece
   * of the player's choice
   * @param c Coordinate of the location of the player's click
   */
  public void promotePawn(Coordinate c) {
	  Object[] possibilities = {"Rook", "Knight", "Bishop", "Queen"};
	  String s = (String)JOptionPane.showInputDialog( //s holds the player's choice
	                      frame,
	                      "Promote your pawn: ",
	                      "Pawn Promotion",
	                      JOptionPane.PLAIN_MESSAGE,
	                      null,
	                      possibilities,
	                      "Rook");
	  if (lastPiece.isWhitePiece()) {
		  if (s == "Queen") {
			  piecePositions[c.getCol()][c.getRow()] = new Queen(whiteQueen, true);
		  } else if (s == "Bishop") {
			  piecePositions[c.getCol()][c.getRow()] = new Bishop(whiteBishop, true);
		  } else if (s == "Knight") {
			  piecePositions[c.getCol()][c.getRow()] = new Knight(whiteKnight, true);
		  } else {
			  piecePositions[c.getCol()][c.getRow()] = new Rook(whiteRook, true);
		  }
	  } else if (lastPiece.isWhitePiece() == false) {
		  if (s == "Queen") {
			  piecePositions[c.getCol()][c.getRow()] = new Queen(blackQueen, false);
		  } else if (s == "Bishop") {
			  piecePositions[c.getCol()][c.getRow()] = new Bishop(blackBishop, false);
		  } else if (s == "Knight") {
			  piecePositions[c.getCol()][c.getRow()] = new Knight(blackKnight, false);
		  } else {
			  piecePositions[c.getCol()][c.getRow()] = new Rook(blackRook, false);
		  }
	  }
	  repaint();

  }
  
  /**
   * Checks for a check mate move by determining if either king is present on the board. If one king
   * isn't found, the game is ended and the winner of the game is announced.
   * @return Returns true if check mate is found. Otherwise returns false.
   */
  public boolean isCheckmate() {
	  for (int i = 0; i < piecePositions.length; i++) {
		  for (int j = 0; j < piecePositions[i].length; j++) {
	    	  if (isWhiteTurn == false && piecePositions[i][j].equals(wKing) || isWhiteTurn == true && piecePositions[i][j].equals(bKing)) {
	    		  return false;
	    	  }
	      }
	  }
	  if (isWhiteTurn == true) {
		  JOptionPane.showMessageDialog(frame, player1+" wins the game.");
	  } else {
		  JOptionPane.showMessageDialog(frame, player2+" wins the game.");
	  }
	  
	  return true;
  }
  
  private void paintText(Graphics g)
  {
  }
  
  private void paintGrid(Graphics g)
  {
	  //initializes any graphics that doesn't need to be constantly updated
	  if (firstClick == false) {
		  g.drawImage(background, 0, 0, null);
		  g.setColor(Color.WHITE);
		  g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		  g.drawString(player2, X_OFFSET, Y_OFFSET - FONT_OFFSET);
		  g.drawString(player1, X_OFFSET, Y_OFFSET + piecePositions.length * Y_DIM + FONT_OFFSET * 2);
		  firstClick = true;
	  }
	  //Drawing of the checkered board
	  for (int i = 0; i < piecePositions.length; i++) {
		  for (int j = 0; j < piecePositions[i].length; j++) {
			  if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
				  g.setColor(GRID_COLOR_A);
				  g.fillRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM, Y_DIM);
			  } else {
				  g.setColor(GRID_COLOR_B);
				  g.fillRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM, Y_DIM);
			  }
			  if (!(piecePositions[i][j].equals(emptySpace)) && piecePositions[i][j].isHighlighted()) {
				  g.setColor(HIGHLIGHT_COLOR);
				  g.fillRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM, Y_DIM);
			  }
			  if (lastPiece != null) {
				  //color that denotes that a king is in check
				  if (searchChecks()[i][j] == 'L') {
					  g.setColor(Color.RED);
					  g.fillRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM, Y_DIM);
				  }
				  //removes pegs for the king that will result in a check mate
				  if (lastPiece.equals(wKing) || lastPiece.equals(bKing)) {
					  if (searchChecks()[i][j] != 'C' && getMoves()[i][j] == 'M' && lastPiece.isHighlighted()) {
						  g.setColor(PEG_COLOR);
						  g.fillOval(X_OFFSET + X_DIM * i + X_DIM / 2 - PEG_DIAMETER / 2,
								Y_OFFSET + Y_DIM * j + Y_DIM / 2 - PEG_DIAMETER / 2, PEG_DIAMETER, PEG_DIAMETER);
					  } else if (getMoves()[i][j] == 'K' && lastPiece.isHighlighted()) {
						  g.setColor(KILL_COLOR);
						  g.fillRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM, Y_DIM);
						  g.setColor(Color.BLACK);
						  g.drawRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM - 1, Y_DIM - 1);
					  }
				  } else {
					  //pegs are drawn for the possible moves a selected piece
					  if (getMoves()[i][j] == 'M' && lastPiece.isHighlighted()) {
						  g.setColor(PEG_COLOR);
						  g.fillOval(X_OFFSET + X_DIM * i + X_DIM / 2 - PEG_DIAMETER / 2,
								  Y_OFFSET + Y_DIM * j + Y_DIM / 2 - PEG_DIAMETER / 2, PEG_DIAMETER, PEG_DIAMETER);
						  //the rectangles of pieces that can be killed are filled with dark red
					  } else if (getMoves()[i][j] == 'K' && lastPiece.isHighlighted()) {
						  g.setColor(KILL_COLOR);
						  g.fillRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM, Y_DIM);
						  g.setColor(Color.BLACK);
						  g.drawRect(X_OFFSET + X_DIM * i, Y_OFFSET + Y_DIM * j, X_DIM - 1, Y_DIM - 1);
					  }
				  }
			  }
	    	
        
			  g.drawImage(piecePositions[i][j].getImage(), X_OFFSET+X_DIM*i, Y_OFFSET+Y_DIM*j, null);
		  }
     
  }
    
     
  }
  
  private void drawLine(Graphics g)
  {
    for (int i =0; i < numLines; i++ ) 
    {
      ((Graphics2D) g).setStroke( new BasicStroke(5.0f) );
      g.drawLine(X_OFFSET+X_DIM/2+line[0][i]*X_DIM, Y_OFFSET+Y_DIM/2+line[1][i]*Y_DIM, X_OFFSET+X_DIM/2+line[2][i]*X_DIM, Y_OFFSET+Y_DIM/2+line[3][i]*Y_DIM);
      ((Graphics2D) g).setStroke( new BasicStroke(0.5f) );
    }
  }
  
  /** The method that draws everything
   */
  public void paint( Graphics g ) 
  {
    this.paintGrid(g);
    this.paintText(g);
    this.drawLine(g);
     
  }
  
  /** Sets the message to be displayed under the board
   */
  public void displayMessage(String theMessage)
  {
    message = theMessage;
    repaint();
  }
   
  /** Draws a line on the board using the given co-ordinates as endpoints
   */
  public void drawLine(int row1, int col1, int row2, int col2)
  {
    line[0][numLines]=col1;
    line[1][numLines]=row1;
    line[2][numLines]=col2;
    line[3][numLines]=row2;
    numLines++;
    repaint();
  }

  /** Removes one line from a board given the co-ordinates as endpoints
   * If there is no such line, nothing happens
   * If multiple lines, all copies are removed
   */
  
  public void removeLine(int row1, int col1, int row2, int col2) 
  {
    int curLine = 0;
    while (curLine < numLines) 
    {
      // Check for either endpoint being specified first in our line table
      if ( (line[0][curLine] == col1 && line[1][curLine] == row1 &&
            line[2][curLine] == col2 && line[3][curLine] == row2)   || 
           (line[2][curLine] == col1 && line[3][curLine] == row1 &&
            line[0][curLine] == col2 && line[1][curLine] == row2) )
      {
        // found a matching line: overwrite with the last one
        numLines--;
        line[0][curLine] = line[0][numLines];
        line[1][curLine] = line[1][numLines];
        line[2][curLine] = line[2][numLines];
        line[3][curLine] = line[3][numLines];
        curLine--; // perhaps the one we copied is also a match
      }
      curLine++;
       
    }
    repaint();
  }
  
  /** Waits for user to click somewhere and then returns the click.
   */
  public Coordinate getClick()
  {
      Coordinate returnedClick = null;
      synchronized(this) {
          lastClick = null;
          while (lastClick == null)
          {
              try {
                  this.wait();
              } catch(Exception e) {
                  // We'll never call Thread.interrupt(), so just consider
                  // this an error.
                  e.printStackTrace();
                  System.exit(-1) ;
              } /* try */
          }
    
          int col = (int)Math.floor((lastClick.getCol()-X_OFFSET)/X_DIM);
          int row = (int)Math.floor((lastClick.getRow()-Y_OFFSET)/Y_DIM);
    
          // Put this into a new object to avoid a possible race.
          returnedClick = new Coordinate(row,col);
      }
      return returnedClick;
  }  
}

