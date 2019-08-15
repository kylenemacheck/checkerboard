package gamestuff;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class KnightsTourSolver extends PuzzleSolver
{

	private Knight knight;
	
	public KnightsTourSolver(GameBoard aBoard, Knight aKnight, Point startLocation) 
	{
		super(aBoard);
		knight = aKnight;
		Move firstMove = new Move(aKnight, null, startLocation);
		makeMove(firstMove);
	}
	
	@Override
	public boolean hasWon() 
	{
		return moves.size() == board.getSize() * board.getSize();
	}
	
	@Override
	public List<Move> nextLegalMoves()
	{
		Point currentLocation = moves.get(moves.size() - 1).getNewLocation();
		List<Move> nextMoves = new ArrayList<Move>();
		List<Move> possibleMoves = knight.nextMovesFrom(currentLocation, board);
		for(Move eachMove: possibleMoves) 
		{
			if(!hasVisitedLocation(eachMove.getNewLocation())) 
				nextMoves.add(eachMove);
		}
		return nextMoves;
	}
	
	public boolean hasVisitedLocation(Point aLocation) 
	{
		for(Move eachMove : moves) 
		{
			if(eachMove.getNewLocation().equals(aLocation))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return "KnightsTourSolver";
	}
}





