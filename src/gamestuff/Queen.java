package gamestuff;

import javafx.scene.image.Image;

public class Queen extends GamePiece 
{
	public Queen()
	{
		super(new Image("file:///C:/Users/Kyle/eclipse-workspace/CheckerBoard/queen.png"));
	}
	@Override
	public String toString()
	{
		return "Queen";
	}
}