package Objects;
public class Player {
	private int wins;
	private int losses;
	private int rowNum;
	private int colomnNum;
	private boolean bingo;
	public Player(int wins, int losses, int rowNum, int coloumnNum, boolean bingo) {
		this.wins = wins;
		this.losses = losses;
		this.rowNum = rowNum;
		this.colomnNum = colomnNum;
		this.bingo = bingo;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColomnNum() {
		return colomnNum;
	}
	public void setColomnNum(int colomnNum) {
		this.colomnNum = colomnNum;
	}
	public boolean isBingo() {
		return bingo;
	}
	public void setBingo(boolean bingo) {
		this.bingo = bingo;
	}
	public String toString() {
		String record;
		record = "wins: " + this.wins + "\n" + "losses: " + this.losses;
		return record;
	}
}
