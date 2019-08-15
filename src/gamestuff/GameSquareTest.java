package gamestuff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

class GameSquareTest 
{

	private GameSquare lightSquare;
	private GameSquare darkSquare;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		lightSquare = new GameSquare(Color.WHITE);
		darkSquare = new GameSquare(Color.BLACK);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		lightSquare = null;
		darkSquare = null;
	}

	@Test
	void testConstructor() 
	{
		assertTrue(lightSquare.getColor().equals(Color.WHITE));
		assertTrue(darkSquare.getColor().equals(Color.BLACK));
		assertTrue(lightSquare.isEmpty());
		assertTrue(darkSquare.isEmpty());
	}
	
	@Test
	void testPieceManagement() 
	{
		GamePiece piece = new Knight();
		GamePiece returnedPiece;
		assertTrue(lightSquare.isEmpty());
		lightSquare.placePiece(piece);
		assertFalse(lightSquare.isEmpty());
		assertTrue(lightSquare.getPiece() == piece);
		returnedPiece = lightSquare.removePiece();
		assertTrue(returnedPiece == piece);
		assertTrue(lightSquare.isEmpty());
	}
}