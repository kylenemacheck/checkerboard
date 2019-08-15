package gamestuff;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class MoveTest 
{
	private GamePiece piece = new Knight();
	private GameBoard board = null;
	private Point oldLoc = new Point(4, 4);
	private Point newLoc = new Point(6, 5);
	private Move move = new Move(piece, oldLoc, newLoc);
	
	@BeforeEach
	void setUp() throws Exception 
	{
		board = new GameBoard(8, Color.IVORY, Color.BLACK);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		board = null;
	}

	@Test
	void testConstructor() 
	{
		assertTrue(move.getOldLocation().equals(oldLoc));
		assertTrue(move.getNewLocation().equals(newLoc));
		assertTrue(move.getPiece().equals(piece));
	}
	
	@Test
	void testMakeAMove()
	{
		
		board.placePiece(new Point(oldLoc), piece);
		move.make(board);
		assertTrue(board.isEmpty(oldLoc));
		assertTrue(board.getPiece(newLoc) == piece);
	}
	
	@Test
	void testRetractAMove()
	{
		board.placePiece(newLoc, piece);
		move.retract(board);
		assertTrue(board.isEmpty(newLoc));
		assertTrue(board.getPiece(oldLoc) == piece);
	}
	@Test
	void testPlacePeiceMove()
	{
		Move placePiece = new Move(piece, null, oldLoc);
		assertTrue(board.isEmpty(oldLoc));
		placePiece.make(board);
		assertFalse(board.isEmpty(oldLoc));
	}
}
