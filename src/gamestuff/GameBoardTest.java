package gamestuff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import java.awt.Point;

class GameBoardTest {

	private GameBoard board;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		board = new GameBoard(8, Color.WHITE, Color.BLACK);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		board = null;
	}

	@Test
	void testConstructor() 
	{
		assertTrue(board.getSize() == 8);
		GameSquare square = board.getSquare(new Point(0,0));
		assertTrue(square.getColor().equals(Color.BLACK));
		square = board.getSquare(new Point(0,7));
		assertTrue(square.getColor().equals(Color.WHITE));
		square = board.getSquare(new Point(7,0));
		assertTrue(square.getColor().equals(Color.WHITE));	
		square = board.getSquare(new Point(7,7));
		assertTrue(square.getColor().equals(Color.BLACK));	
		square = board.getSquare(new Point(0,3));
		assertTrue(square.getColor().equals(Color.WHITE));	
		square = board.getSquare(new Point(3,7));
		assertTrue(square.getColor().equals(Color.BLACK));	
		square = board.getSquare(new Point(3,4));
		assertTrue(square.getColor().equals(Color.WHITE));	
	}
	
	@Test
	void testPiecePlacement() 
	{
		GamePiece piece = new Knight();
		Point aLocation = new Point(3, 4);
		GamePiece returnedPiece;
		assertTrue(board.isEmpty(aLocation));
		board.placePiece(aLocation, piece);
		assertFalse(board.isEmpty(aLocation));
		assertTrue(board.getPiece(aLocation) == piece );
		returnedPiece = board.removePiece(aLocation);
		assertTrue(returnedPiece == piece);
		assertTrue(board.isEmpty(aLocation));
	}

}