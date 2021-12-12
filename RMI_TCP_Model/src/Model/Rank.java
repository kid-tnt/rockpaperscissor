package Model;

import java.io.Serializable;


public class Rank implements Serializable {
	 private static final long serialVersionUID = 20210811004L;

	private int id;

	private Player player;

	private Float Score;


   
	public Rank() {
		
		super();
		
	}



	public Rank(int id, Player player, Float score) {
		super();
		this.id = id;
		this.player = player;
		Score = score;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Player getPlayer() {
		return player;
	}



	public void setPlayer(Player player) {
		this.player = player;
	}



	public Float getScore() {
		return Score;
	}



	public void setScore(Float score) {
		Score = score;
	}
	
	

	
	

}
