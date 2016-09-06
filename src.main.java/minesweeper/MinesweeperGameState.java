package minesweeper;

public class MinesweeperGameState {
	
	/**
     * board represents a instance of the board class
     * gameEnded represents if the game is ended or not
     */
    private MinesweeperBoard board;
    private boolean gameEnded;

    /**
     * constructor create the default state game
     * board: 10x10, mines: 8, all cells closed and none marked
     */
    public MinesweeperGameState() {
      
    	board = new MinesweeperBoard(10, 10, 8);
    	gameEnded = false;
    }
    
    /**
     * constructor create the state game with the params
     * @param rows - number of rows of the board
     * @param cols - number of colums of the board
     * @param mines - number of mines of the board
     */
    
    public MinesweeperGameState(int rows, int cols, int mines){
    	board = new MinesweeperBoard(rows, cols, mines);
    	gameEnded = false;
    	
    }
    
    public MinesweeperGameState(MinesweeperBoard b){
    	if(board.getClosedCellsCount() == board.getMineCount()){ //if the number of closed cell is equal to number of mines
    		throw new IllegalArgumentException("the board must not be ended");
    	}
    	else{
    		board = b;
    		gameEnded = false;
    	}  	
    }
    
    /**
     * 
     * @return return the number of the rows of the board
     */

    public int numberOfRows() {
        return board.getRowCount();
    }
    
    /**
     * 
     * @return return the number of columns of the board
     */
    public int numberofColumns() {
        return board.getColCount();
    }

    /**
     * 
     * @return returns the number of mines on the board
     */
    public int numberOfMines() {
        return board.getMineCount();
    }
    
    /**
     * Return true if the game is ended.This method is used to see the 
     * outside of class if the game is ended or not
     * @return true if this.gameEnded is true
     */
    public boolean gameEnded() {
        return gameEnded;
    }

    /**
     * Returns true if the cell located at the given position is Marked. This method
     * allows you to see outside of class if a cell of the board is mark or not.
     * @param row file number of the board where the cell is located.
     * @param col column number of the board where the cell is located.
     * @return if the cell located at the given position is Marked.
     */
    public boolean isMarked(int row, int col) {
        return board.isMarked(row, col);
    }
    
    /**
     * Change state of a cell to blocked if the game is not ended
     * @param row row number of the board where the cell is located.
     * @param col column number of the board where the cell is located.
     */
    public void mark(int row, int col) { 
    	if(!gameEnded){
    		board.mark(row, col);
    	}
    }

    /**
     * This method return a if the cell is opened or not.
     * @param row file number of the board where the cell is located.
     * @param col column number of the board where the cell is located.
     * @return true if the cell located at the given position is Opened.
     */
    public boolean isOpened(int row, int col) {
        return board.isOpened(row, col);
    }

    /**
     * @param row file number of the board where the cell is located.
     * @param col column number of the board where the cell is located.
     * This method open a cell in a position.
     */
    public void open(int row, int col) {
    	if( board.isValidCoordinate(row, col) && !board.isOpened(row, col) && !board.isMarked(row, col)){ //if the cell is not open or marked
    		board.open(row, col);//open the cell
    		if(!board.hasMine(row, col)){//if the cell has not a mine
    			board.openNeighboringMines(row, col);//open all neighbor cells that do not have mines
    			if(board.getClosedCellsCount() == board.getMineCount()){ //if the number of closed cell is equal to number of mines
    				endGame();
    			}
    		} else { //if the cell has a mine
    			endGame();
    		}
    	}
    }
    
    /**
     *This method finish the game. 
     */
    public void endGame(){
    	gameEnded = true;
    }
    
    /**
     * This method provides a text-based representation of the state of the game.
     * @return String that represent visually the state of the game.
     */
    public String toString() {
    	System.out.println(board.toString());
    	if(gameEnded){
    		return "Game Over.";
    	}else{
    		return "Game On.";
    	}
    }
}
