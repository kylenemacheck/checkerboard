package gamestuff;

import javafx.scene.image.Image;

public abstract class GamePiece
{
	private Image image;
	public GamePiece(Image image)
	{
		this.image = image;
	}
	
	public Image getImage()
	{
		return image;
	}
	@Override
	public abstract String toString();
}