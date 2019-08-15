package gamestuff;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

class NQueensSolverTest 
{
	private GameBoard board;
	private NQueensSolver solver;
	private Move move1 = new Move(new Queen(), null, new Point(0, 0));
	private Move move2 = new Move(new Queen(), null, new Point(1, 2));
	
	@BeforeEach
	void setUp() throws Exception 
	{
		board = new GameBoard(8, Color.IVORY, Color.BLACK);
		solver = new NQueensSolver(board);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		board = null;
		solver = null;
	}

	@Test
	void testHasWon() 
	{
		for (int i = 0; i < board.getSize(); ++i) 
		{
			assertFalse(solver.hasWon());
			solver.getMoves().add(move1);
		}
		assertTrue(solver.hasWon());
	}

	@Test
	void testCurrentRow() 
	{
		assertTrue(solver.currentRow() == 0);
		solver.getMoves().add(move1);
		assertTrue(solver.currentRow() == 1);
		solver.getMoves().add(move1);
		assertTrue(solver.currentRow() == 2);
	}
	
	@Test
	void testIsThreatened() 
	{
		solver.makeMove(move1);
		assertTrue(solver.isThreatened(new Point(0,0), new Point(2,0)));
		assertFalse(solver.isThreatened(new Point(0,0), new Point(2,1)));
		assertTrue(solver.isThreatened(new Point(0,0), new Point(2,2)));
	}
	
	@Test
	void testIsSafe() 
	{
		solver.makeMove(move1);
		solver.makeMove(move2);
		assertFalse(solver.isSafe(new Point(2, 0)));
		assertFalse(solver.isSafe(new Point(2, 1)));
		assertFalse(solver.isSafe(new Point(2, 2)));
		assertFalse(solver.isSafe(new Point(2, 3)));
		assertTrue(solver.isSafe(new Point(2, 4)));
		assertTrue(solver.isSafe(new Point(2, 5)));
		assertTrue(solver.isSafe(new Point(2, 6)));
		assertTrue(solver.isSafe(new Point(2, 7)));
	}
	
	@Test
	void testNextLegalMoves() 
	{
		solver.makeMove(move1);
		solver.makeMove(move2);
		List<Move> nextMoves = solver.nextLegalMoves();
		assertTrue(nextMoves.size() == 4);
		// legal moves should be (2,4) (2,5) (2,6) (2,7)
	}
	
	@Test
	void testSolve() 
	{
		assertTrue(solver.solve());
	}
}