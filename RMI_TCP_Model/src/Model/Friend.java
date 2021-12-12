package Model;

import java.io.Serializable;



public class Friend implements Serializable{
	private static final long serialVersionUID = 20210811004L;
		private int id;
		private String daterequest;
		private String dateaccept;
	    private Player request;
	    private Player accept;
		public Friend() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Friend(int id, String daterequest, String dateaccept, Player request, Player accept) {
			super();
			this.id = id;
			this.daterequest = daterequest;
			this.dateaccept = dateaccept;
			this.request = request;
			this.accept = accept;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDaterequest() {
			return daterequest;
		}
		public void setDaterequest(String daterequest) {
			this.daterequest = daterequest;
		}
		public String getDateaccept() {
			return dateaccept;
		}
		public void setDateaccept(String dateaccept) {
			this.dateaccept = dateaccept;
		}
		public Player getRequest() {
			return request;
		}
		public void setRequest(Player request) {
			this.request = request;
		}
		public Player getAccept() {
			return accept;
		}
		public void setAccept(Player accept) {
			this.accept = accept;
		}
	
	    
	    
		
}
