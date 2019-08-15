package gamestuff;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

class KnightTest 
{
	private GameBoard board;
	private Point centerLoc = new Point(3, 3);
	private Point cornerLoc = new Point(7, 0);
	private Point sideEdgeLoc = new Point(4, 0);
	private Point topEdgeLoc = new Point(7, 3);
	private Knight knight = new Knight();
	
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

	private boolean isLocationInMoveList(Point aLocation, List<Move> moves)
	{
		for (Move eachMove: moves)
		{
			if (eachMove.getNewLocation().equals(aLocation))
				return true;
		}
		return false;
	}
	
	@Test
	void testNextMovesFromCenter()
	{
		board.placePiece(centerLoc, knight);
		List<Move> moves = knight.nextMovesFrom(centerLoc, board);
		assertTrue(moves.size() == 8);
		assertTrue(isLocationInMoveList(new Point(1, 2), moves));
		assertTrue(isLocationInMoveList(new Point(1, 4), moves));
		assertTrue(isLocationInMoveList(new Point(2, 1), moves));
		assertTrue(isLocationInMoveList(new Point(2, 5), moves));
		assertTrue(isLocationInMoveList(new Point(4, 1), moves));
		assertTrue(isLocationInMoveList(new Point(4, 5), moves));
		assertTrue(isLocationInMoveList(new Point(5, 2), moves));
		assertTrue(isLocationInMoveList(new Point(5, 4), moves));
	}

	@Test
	void testNextMovesFromCorner()
	{
		board.placePiece(cornerLoc, knight);
		List<Move> moves = knight.nextMovesFrom(cornerLoc, board);
		assertTrue(moves.size() == 2);
		assertTrue(isLocationInMoveList(new Point(5, 1), moves));
		assertTrue(isLocationInMoveList(new Point(6, 2), moves));
	}
	
	@Test
	void nextMovesFromSideEdge()
	{
		board.placePiece(sideEdgeLoc, knight);
		List<Move> moves = knight.nextMovesFrom(sideEdgeLoc, board);
		assertTrue(isLocationInMoveList(new Point(2, 1), moves));
		assertTrue(isLocationInMoveList(new Point(3, 2), moves));
		assertTrue(isLocationInMoveList(new Point(5, 2), moves));
		assertTrue(isLocationInMoveList(new Point(6, 1), moves));
	}
	
	@Test
	void nextMovesFromTopEdge()
	{
		board.placePiece(topEdgeLoc, knight);
		List<Move> moves = knight.nextMovesFrom(topEdgeLoc, board);
		assertTrue(isLocationInMoveList(new Point(6, 1), moves));
		assertTrue(isLocationInMoveList(new Point(5, 2), moves));
		assertTrue(isLocationInMoveList(new Point(5, 4), moves));
		assertTrue(isLocationInMoveList(new Point(6, 5), moves));
	}
}
