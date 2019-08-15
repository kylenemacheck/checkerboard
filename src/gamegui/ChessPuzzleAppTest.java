package gamegui;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Point;
 
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamestuff.Knight;
import gamestuff.Move;
import gamestuff.Queen;

class ChessPuzzleAppTest 
{

	Queen queen = new Queen();
	Knight knight = new Knight();
	Move placePieceMove = new Move(queen, null, new Point(0,0));
	Move pieceMove = new Move(knight, new Point(0,0), new Point(2,1));
	
	@BeforeEach
	void setUp() throws Exception 
	{
	}

	@AfterEach
	void tearDown() throws Exception 
	{
	}

	@Test
	void testQueenPrinting() 
	{
		assertTrue(queen.toString().equals("Queen"));
	}

	@Test
	void testKnightPrinting() 
	{
		assertTrue(knight.toString().equals("Knight"));
	}
	
	@Test
	void testPieceMovePrinting() 
	{
		assertTrue(pieceMove.toString().equals("Knight (0, 0)->(2, 1)"));
	}
	
	@Test
	void testPlacePieceMovePrinting() 
	{
		assertTrue(placePieceMove.toString().equals("Queen (0, 0)"));
	}
}