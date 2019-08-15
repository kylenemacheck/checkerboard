package gamestuff;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class PuzzleSolver 
{
	protected GameBoard board;
	protected ObservableList<Move> moves;
	
	public PuzzleSolver(GameBoard aBoard)
	{
		board = aBoard;
		moves = FXCollections.observableArrayList();
	}
	
	@Override
	public abstract String toString();
	public abstract boolean hasWon();
	public abstract List<Move> nextLegalMoves();
	
	public ObservableList<Move> getMoves()
	{
		return moves;
	}
	
	public void makeMove(Move aMove) 
	{
		aMove.make(board);
		moves.add(aMove);
	}
	
	public void undoLastMove() 
	{
		Move lastMove = moves.get(moves.size() - 1);
		lastMove.retract(board);
		moves.remove(lastMove);
	}
	
	public GameBoard getBoard()
	{
		return board;
	}
	
	public boolean solve() 
	{
		if(hasWon())
			return true;
		
		List<Move> nextMoves = nextLegalMoves();
		for(Move eachMove: nextMoves) 
		{
			makeMove(eachMove);
			if(solve()) 
				return true;
			undoLastMove();
		}
		return false;
	}
}