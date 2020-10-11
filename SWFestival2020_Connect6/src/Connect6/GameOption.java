package Connect6;

public class GameOption {
	private int boardImage;		// 0 : default, 1 : white, 2 : jihoon
	private int player1Color;	// 0 : Èæ, 1 : ¹é, 2 : Ã», 3 : ³ë
	private int player2Color;	// 0 : Èæ, 1 : ¹é, 2 : Ã», 3 : ³ë
	private int timeLimit;		// 15 ~ 30s
	
	GameOption() {
		boardImage = 0;
		player1Color = 0;
		player2Color = 0;
		timeLimit = 15;
	}
	
	public int getBoardImage() {
		return boardImage;
	}
	public void setBoardImage(int boardImage) {
		this.boardImage = boardImage;
	}
	public int getPlayer1Color() {
		return player1Color;
	}
	public void setPlayer1Color(int player1Color) {
		this.player1Color = player1Color;
	}
	public int getPlayer2Color() {
		return player2Color;
	}
	public void setPlayer2Color(int player2Color) {
		this.player2Color = player2Color;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	
}
