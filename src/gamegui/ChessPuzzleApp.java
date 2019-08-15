package gamegui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import gamestuff.GameBoard;
import gamestuff.Knight;
import gamestuff.KnightsTourSolver;
import gamestuff.Move;
import gamestuff.PuzzleSolver;

public class ChessPuzzleApp extends Application 
{
	private int nextStep;
	private Point point = new Point(0, 0);
	private GameBoard board = new GameBoard(6, Color.IVORY, Color.BLACK);
	private PuzzleSolver solver = new KnightsTourSolver(board, new Knight(), point);
	private ListView<Move> listView;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		solver.solve();
		Scene scene = new Scene(sceneGraph(), 500, 350);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Puzzle Solver App");
		primaryStage.show();
	}
	
	private Parent sceneGraph()
	{
		VBox sceneRoot = new VBox();
		sceneRoot.getChildren().add(buildMenuBar());
		sceneRoot.getChildren().add(buildSolverView());
		sceneRoot.getChildren().add(buildButtonBar());
		return sceneRoot;
	}
	
	private MenuBar buildMenuBar()
	{
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(buildFileMenu());
		menuBar.getMenus().add(buildConfigureMenu());
		menuBar.getMenus().add(buildGameMenu());
		return menuBar;
	}
	
	private Menu buildFileMenu()
	{
		Menu file = new Menu("File");
		MenuItem saveItem = new MenuItem("Save");
		saveItem.setOnAction((event)->handleSave());
		file.getItems().add(saveItem);
		return file;
	}
	
	private Menu buildConfigureMenu()
	{
		Menu configure = new Menu("Configure");
		MenuItem positionItem = new MenuItem("Starting Position");
		positionItem.setOnAction((event)->handlePosition());
		configure.getItems().add(positionItem);
		positionItem.setDisable(true);
		return configure;
	}
	
	private Menu buildGameMenu()
	{
		Menu Game = new Menu("Game");
		MenuItem KnightsTourItem = new MenuItem("Knight's Tour");
		KnightsTourItem.setOnAction((event)->handleKnightsTourItem());
		Game.getItems().add(KnightsTourItem);
		MenuItem NQueensItem = new MenuItem("8 Queens");
		NQueensItem.setOnAction((event)->handleNQueensItem());
		Game.getItems().add(NQueensItem);
		return Game;
	}
	
	private HBox buildSolverView()
	{
		HBox contentArea = new HBox();
		GameBoardView gameBoardView = new GameBoardView(300, 300, board);
		contentArea.getChildren().add(gameBoardView);
		contentArea.getChildren().add(buildMoveList());
		doReset();
		return contentArea;
	}
	
	private ListView<Move> buildMoveList()
	{
		ObservableList<Move> names = FXCollections.observableArrayList(solver.getMoves());
		listView = new ListView<Move>(names);
		listView.getSelectionModel().select(0);
		return listView;
	}
	
	private HBox buildButtonBar()
	{
		HBox buttonBar = new HBox(8);
		buttonBar.getChildren().add(makeResetButton());
		buttonBar.getChildren().add(makeStepButton());
		buttonBar.setAlignment(Pos.CENTER);
		return buttonBar;
	}
	
	private Button makeResetButton()
	{
		Button buttonReset = new Button("Reset");
		buttonReset.setOnAction((event)->doReset());
		return buttonReset;
	}
	
	private Button makeStepButton()
	{
		Button buttonStart = new Button("Step");
		buttonStart.setOnAction((event)->doStep());
		return buttonStart;
	}
	
	private void doReset()
	{
		nextStep = 0;
		listView.getSelectionModel().clearAndSelect(nextStep);
		board.clearBoard();
		board.notifyListeners();
	}
	
	private void doStep()
	{
		if (nextStep >= solver.getMoves().size())
			return;
		listView.getSelectionModel().clearAndSelect(nextStep);
		solver.getMoves().get(nextStep).make(board);
		board.notifyListeners();
		nextStep++;
	}
	
	private void handleSave() 
	{
 		TextInputDialog dialog = new TextInputDialog("moveList.txt");
 		dialog.setHeaderText("Enter file name");
 		Optional<String> result = dialog.showAndWait();
 		if(!result.isPresent())
 			return;
 		String fileName = result.get();
 		PrintWriter writer = null;
 		try
 		{
 			writer = new PrintWriter(fileName);
 			writer.println(solver);
 			solver.solve();
 			for (Move eachMove: solver.getMoves())
 				writer.println(eachMove);
 		} 
 		catch(IOException ex) 
 		{
 			Alert alertDialog = new Alert(AlertType.INFORMATION);
 			alertDialog.setContentText("Could not save file!");
 			alertDialog.showAndWait();
 		}
 		finally 
 		{
 			if(writer != null) 
 				writer.close();
 		}
	}
	
	private void handleKnightsTourItem()
	{
		return;
	}
	
	private void handleNQueensItem()
	{
		return;
	}
	
	private void handlePosition()
	{
		// not in use
		/*
		TextInputDialog dialog = new TextInputDialog("0, 0");
		dialog.setHeaderText("Enter file name");
 		Optional<String> result = dialog.showAndWait();
 		int x = Integer.parseInt(result.get().substring(0, 1));
 		int y = Integer.parseInt(result.get().substring(3, 4));
 		setPoint(new Point(x, y));
 		board.notifyListeners();
 		*/
 		return;
	}
	
	// not in use
	/*
	private boolean isNQueens()
	{
		return solver.toString().equals("NQueensSolver");
	}
	
	private Point setPoint(Point point)
	{
		return point;
	}
	*/
}
