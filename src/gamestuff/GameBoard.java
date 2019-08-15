package gamestuff;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;

public class GameBoard implements Observable
{
	private GameSquare[][] squares;
	private List<InvalidationListener> listeners = new ArrayList<InvalidationListener>();
	
	public GameBoard(int size, Color lightColor, Color darkColor)
	{
		Color[] colors = {darkColor, lightColor};
		squares = new GameSquare[size][size];
		for (int row = 0; row < size; row++) 
		{
			for (int column = 0; column < size; column++) 
				squares[row][column] = new GameSquare(colors[(row + column) % 2]);
		}
	}
	
	public int getSize() 
	{
		return squares.length;
	}
	
	public GamePiece getPiece(Point aLocation) 
	{
		return getSquare(aLocation).getPiece();
	}
	
	public GameSquare getSquare(Point aLocation) 
	{
		return squares[aLocation.x][aLocation.y];
	}
	
	public boolean isEmpty(Point aLocation) 
	{
		return getSquare(aLocation).isEmpty();
	}
	
	public void placePiece(Point aLocation, GamePiece aPiece) 
	{
		notifyListeners();
		getSquare(aLocation).placePiece(aPiece);
	}
	
	public GamePiece removePiece(Point aLocation) 
	{
		notifyListeners();
		return getSquare(aLocation).removePiece();
	}
	
	public void clearBoard()
	{
		for (int row = 0; row < getSize(); row++)
		{
			for (int col = 0; col < getSize(); col++)
			{
				if (!isEmpty(new Point(row, col)))
					removePiece(new Point(row, col));
			}
		}
	}
	
	@Override
	public void addListener(InvalidationListener listener) 
	{
		listeners.add(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) 
	{
		listeners.remove(listener);
	}
	
	public void notifyListeners() 
	{
		for (InvalidationListener eachListener: listeners) 
			eachListener.invalidated(this);
	}
}