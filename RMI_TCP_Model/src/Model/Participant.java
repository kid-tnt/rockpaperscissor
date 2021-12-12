package Model;
import java.io.Serializable;

	public class Participant implements Serializable {
		 private static final long serialVersionUID = 20210811004L;
	   
		private int id;
	    private Player player;
	    private Game game;
		private float score;


		public Participant() {
			super();
		}

		public Participant(int id, Player player, Game game, float score) {
			super();
			this.id = id;
			this.player = player;
			this.game = game;
			this.score = score;
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


		public Game getGame() {
			return game;
		}


		public void setGame(Game game) {
			this.game = game;
		}


		public float getScore() {
			return score;
		}


		public void setScore(float score) {
			this.score = score;
		}
	    
	
}
