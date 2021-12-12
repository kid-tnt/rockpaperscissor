package Model;

import java.io.Serializable;


public class Player implements Serializable {
	 private static final long serialVersionUID = 20210811004L;

	private int id;

	private String username;

	private String pasword;

	private Integer rating;


   
	public Player() {
		
		super();
		
	}
	public Player(int id, String username, String pasword, int rating) {
		super();
		this.id = id;
		this.username = username;
		this.pasword = pasword;
		this.rating = rating;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
	

}
