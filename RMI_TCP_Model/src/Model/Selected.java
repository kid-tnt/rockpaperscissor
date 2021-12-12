package Model;

import java.io.Serializable;
public class Selected implements Serializable {
	 private static final long serialVersionUID = 20210811004L;

	private int id;
    private Participant participant;
    private Game game;
    private Weapon weapon;
	public Selected() {
		super();
	}
	public Selected(int id, Participant participant, Game game, Weapon weapon) {
		super();
		this.id = id;
		this.participant = participant;
		this.game = game;
		this.weapon = weapon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}


}