package gamegui;

import java.awt.Point;

import gamestuff.GameBoard;
import gamestuff.GameSquare;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameBoardView extends Canvas 
{
	private GameBoard aBoard;
	
	public GameBoardView(double width, double height, GameBoard aBoard)
	{
		super(width, height);
		this.aBoard = aBoard;
		aBoard.addListener((board)->redraw());
		redraw();
	}
	
	public void redraw()
	{
		for (int row = 0; row < aBoard.getSize(); row++)
		{
			for (int col = 0; col < aBoard.getSize(); col++)
				drawSquareAt(new Point(row, col));
		}
	}
	
	public void drawSquareAt(Point aPoint)
	{
		GraphicsContext gc = getGraphicsContext2D();
		GameSquare square = aBoard.getSquare(aPoint);
		int size = 50;
		int xCoord = aPoint.x * size;
		int yCoord = aPoint.y * size;
		gc.setFill(square.getColor());
		gc.fillRect(xCoord, yCoord, size, size);
		
		if (!square.isEmpty())
			gc.drawImage(square.getPiece().getImage(), xCoord, yCoord, size, size);
	}
}
