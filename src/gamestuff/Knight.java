package gamestuff;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Knight extends GamePiece 
{
	public Knight()
	{
		super(new Image("file:///C:/Users/Kyle/eclipse-workspace/CheckerBoard/knight.png"));
	}
	private Point[] locationOffsets = {
				new Point(2,-1), new Point(2, 1), new Point(1, 2), new Point(-1, 2),
				new Point(-2, 1), new Point(-2, -1), new Point(-1, -2), new Point(1, -2)};
	
	public List<Move> nextMovesFrom(Point aLocation, GameBoard board)
	{
		List<Move> nextMoves = new ArrayList<Move>();
		for(Point eachLocationOffset: locationOffsets) 
		{
			Point possibleLocation = new Point(aLocation.x + eachLocationOffset.x, aLocation.y + eachLocationOffset.y);
			if(isOnBoard(possibleLocation, board))
				nextMoves.add(new Move(this, aLocation, possibleLocation));
		}
		
		return nextMoves;
	}
	
	private boolean isOnBoard(Point aLocation, GameBoard board) 
	{
		return (aLocation.x >= 0 && aLocation.x < board.getSize()) 
				&& (aLocation.y >= 0 && aLocation.y < board.getSize());
	}
	
	@Override
	public String toString()
	{
		return "Knight";
	}
}
