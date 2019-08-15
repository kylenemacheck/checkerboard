package gamestuff;

import javafx.scene.paint.Color;

public class GameSquare 
{
	private Color color;
	private GamePiece piece;
	
	public GameSquare(Color aColor) 
	{
		color = aColor;
		piece = null;
	}
	
	public boolean isEmpty() 
	{
		return piece == null;
	}

	public  Color getColor() 
	{
		return color;
	}
	
	public void placePiece(GamePiece aPiece) 
	{
		piece = aPiece;
	}
	
	public GamePiece getPiece() 
	{
		return piece;
	}
	
	public GamePiece removePiece() 
	{
		GamePiece result = piece;
		piece = null;
		return result;
	}
}