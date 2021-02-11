package Objects;

public class BingoCardObject extends title {
	private boolean pattern;
	private String[][] board = new String[4][4];
	public BingoCardObject(String title, String[][] board, boolean pattern) {
		super(title);
		this.board = board;
		this.pattern = pattern;
	}
	public String[][] getBoard() {
		return board;
	}
	public void setBoard(String[][] board) {
		this.board = board;
	}
	public boolean isPattern() {
		return pattern;
	}
	public void setPattern(boolean pattern) {
		this.pattern = pattern;
	}
	public String toString() {
		String array = "";
		array = title + "\n";
		array = array + "____________" + " \n";
		for (int i = 0; i < this.board.length; i++) {
			array = array + "|";
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j].length() < 2) {
					array = array + (this.board[i][j] + " " + "|");
				} else {
					array = array + (this.board[i][j] + "|");
				}
			}
			array = array + "\n" + "____________" + "\n";
		}
		return array;
	}
}