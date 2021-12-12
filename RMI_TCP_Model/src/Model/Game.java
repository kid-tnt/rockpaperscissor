package Model;

import java.io.Serializable;






public class Game implements Serializable {
	 private static final long serialVersionUID = 20210811004L;

	private int id;
	private String startTime;
	private String endTime;
    private String result;
    private Player player1;
    private Player player2;
    private int se1;
    private int se2;
	public Game() {
		super();
	}
	
	public Game(int id, String startTime, String endTime, String result, Player player1, Player player2) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.result = result;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Game(int id, String startTime, String endTime, String result, Player player1, Player player2, int se1,
			int se2) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.result = result;
		this.player1 = player1;
		this.player2 = player2;
		this.se1 = se1;
		this.se2 = se2;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getSe1() {
		return se1;
	}

	public void setSe1(int se1) {
		this.se1 = se1;
	}

	public int getSe2() {
		return se2;
	}

	public void setSe2(int se2) {
		this.se2 = se2;
	}

	
	
}
  