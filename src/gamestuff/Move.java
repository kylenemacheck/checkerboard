package gamestuff;

import java.awt.Point;

public class Move 
{
	private GamePiece piece;
	private Point oldLocation;
	private Point newLocation;
	
	public Move(GamePiece aPiece, Point anOldLoc, Point aNewLoc) 
	{
		piece = aPiece;
		oldLocation = anOldLoc;
		newLocation = aNewLoc;
	}
	
	public GamePiece getPiece() 
	{
		return piece;
	}
	
	public Point getOldLocation() 
	{
		return oldLocation;
	}
	
	public Point getNewLocation() 
	{
		return newLocation;
	}
	
	public void make(GameBoard board) 
	{
		if (oldLocation != null) 
			board.removePiece(oldLocation);
		board.placePiece(newLocation, piece);
	}
	
	public void retract(GameBoard board) 
	{
		board.removePiece(newLocation);
		if (oldLocation != null)
			board.placePiece(oldLocation, piece);
	}
	
	private String newLocToString()
	{
		return "(" + getNewLocation().x + ", " + getNewLocation().y + ")";
	}
	
	private String oldLocToString()
	{
		return "(" + getOldLocation().x + ", " + getOldLocation().y + ")";
	}
	
	@Override
	public String toString()
	{
		if (piece.toString().equals("Queen") || getOldLocation() == null)
		{
			return piece.toString() + " " + newLocToString();
		}
		else
			return piece.toString() + " " + oldLocToString() + "->" + newLocToString();
	}
}