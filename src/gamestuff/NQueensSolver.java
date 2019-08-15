package gamestuff;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class NQueensSolver extends PuzzleSolver
{	
	public NQueensSolver(GameBoard aBoard)
	{
		super(aBoard);
	}
	
	public boolean hasWon()
	{
		return moves.size() == board.getSize();
	}
	
	public List<Move> nextLegalMoves()
	{
		List<Move> nextMoves = new ArrayList<Move>();
		
		for (int col = 0; col < board.getSize(); col++)
		{
			Point aLocation = new Point(currentRow(), col);
			if (isSafe(aLocation))
			{
				Move move = new Move(new Queen(), null, aLocation);
				nextMoves.add(move);
			}
		}
		
		return nextMoves;
	}
	
	public int currentRow()
	{
		return moves.size();
	}
	
	public boolean isThreatened(Point queenLocation, Point aLocation)
	{
		return (queenLocation.x == aLocation.x) || (queenLocation.y == aLocation.y) ||
			   (Math.abs(aLocation.y - queenLocation.y) == Math.abs(aLocation.x - queenLocation.x));
	}
	
	public boolean isSafe(Point aLocation)
	{
		for (Move eachMove: moves)
		{
			if (isThreatened(eachMove.getNewLocation(), aLocation))
				return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		return "NQueensSolver";
	}
}
