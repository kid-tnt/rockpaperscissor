package Model;
import java.io.Serializable;

	public class Participant implements Serializable {
		 private static final long serialVersionUID = 20210811004L;
	   
		private int id;
	    private Player player;
		private float score;
		public Participant() {
			super();
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
		public float getScore() {
			return score;
		}
		public void setScore(float score) {
			this.score = score;
		}
		
		
		
	    
	
}
