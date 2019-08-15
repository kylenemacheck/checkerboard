package gamestuff;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

class KnightsTourSolverTest 
{
	private GameBoard board;
	private KnightsTourSolver solver;
	private Knight knight = new Knight();
	private Point oldLocation = new Point(3, 3);
	private Point newLocation = new Point(5, 4);
	private Move move = new Move(knight, oldLocation, newLocation);
	
	@BeforeEach
	void setUp() throws Exception 
	{
		board = new GameBoard(6, Color.IVORY, Color.BLACK);
		solver = new KnightsTourSolver(board, knight, oldLocation);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		board = null;
	}

	@Test
	void testConstructor() 
	{
		assertFalse(board.isEmpty(oldLocation));
		assertTrue(solver.getMoves().size() == 1);
	}
	
	@Test
	void testHasWon() 
	{
		for (int i = 0; i < (board.getSize() * board.getSize()) - 1; i++)
		{
			assertFalse(solver.hasWon());
			solver.getMoves().add(move);
		}
		assertTrue(solver.hasWon());
	}

	@Test
	void testMakeMove()
	{
		assertTrue(solver.getMoves().size() == 1);
		assertTrue(board.getPiece(oldLocation) == knight);
		assertTrue(board.isEmpty(newLocation));
		solver.makeMove(move);
		assertTrue(solver.getMoves().size() == 2);
		assertTrue(board.isEmpty(oldLocation));
		assertTrue(board.getPiece(newLocation) == knight);
	}
	
	@Test
	void testUndoMove()
	{
		solver.makeMove(move);
		assertTrue (solver.getMoves().size() == 2);
		solver.undoLastMove();
		assertTrue(solver.getMoves().size() == 1);
		assertTrue(board.isEmpty(newLocation));
		assertTrue(board.getPiece(oldLocation) == knight);
	}
	
	@Test
	void testHasVisitedLocation()
	{
		assertFalse(solver.hasVisitedLocation(newLocation));
		solver.makeMove(move);
		assertTrue(solver.hasVisitedLocation(newLocation));
		solver.undoLastMove();
		assertFalse(solver.hasVisitedLocation(newLocation));
	}
	
	@Test
	void testNextLegalMoves()
	{
		// nextLegalMoves assumes that the last move made is
		// where the knight currently resides.
		// this test is designed for the knight to be at (3, 3)
		// we will move the first move to be the last
		// move goes to (5, 4)
		Move lastMove = solver.getMoves().remove(0);
		Point loc2 = new Point(4, 5);
		Move move2 = new Move(knight, oldLocation, loc2);
		Point loc3 = new Point(1, 4);
		Move move3 = new Move(knight, oldLocation, loc3);
		Point loc4 = new Point(2, 1);
		Move move4 = new Move(knight, oldLocation, loc4);
		solver.getMoves().add(move);
		solver.getMoves().add(move2);
		solver.getMoves().add(move3);
		solver.getMoves().add(move4);
		solver.getMoves().add(lastMove);
		List<Move> nextMoves = solver.nextLegalMoves();
		assertTrue(nextMoves.size() == 4);
	}
	
	@Test
	void testSolve()
	{
		boolean result = solver.solve();
		assertTrue(result);
	}
}
