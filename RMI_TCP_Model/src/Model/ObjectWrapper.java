package Model;

import java.io.Serializable;

public class ObjectWrapper implements Serializable{
	 private static final long serialVersionUID = 20210811011L;
	    public static final int LOGIN_PLAYER = 1;
	    public static final int REPLY_LOGIN_PLAYER = 2;
	    public static final int SIGNUP_PLAYER = 3;
	    public static final int REPLY_SIGNUP_PLAYER = 4;
	    public static final int SENT_START_GAME=5;
	    public static final int SENT_FRIEND_LIST=6;
	    public static final int REPLY_FRIEND_LIST=7;
	    public static final int PLAYER_ONLINE=8;
	    public static final int SENT_FRIEND_REQUEST = 9;
	    public static final int REPLY_FRIEND_REQUEST = 10;
	    public static final int REPLY_FRIEND_REQUEST_RANK = 36;
	    public static final int SENT_LIST_REQUEST_SENDED=32;
	    public static final int REPLY_LIST_REQUEST_SENDED=33;
	    public static final int SENT_CONFIRM_FRIEND=11;
	    public static final int REPLY_CONFIRM_FRIEND=12;
	    public static final int SENT_REFUSE_FRIEND=13;
	    public static final int REPLY_REFUSE_FRIEND=14;
	    public static final int SENT_CHALLENGE =15;
	    public static final int REPLY_SENT_CHALLENGE = 16;
	    public static final int SENT_ACCEPT_CHALLENGE=17;
	    public static final int REPLY_ACCEPT_CHALLENGE=18;
	    public static final int SENT_REFUSE_CHALLENGE=19;
	    public static final int REPLY_REFUSE_CHALLENGE=20;
	    public static final int SERVER_INFORM_CLIENT_NUMBER = 21;
	    public static final int SENT_SELECTED_WEAPON=22;
	    public static final int SENT_RANK=23;
	    public static final int REPLY_RANK=24;
	    public static final int SENT_LIST_REQUEST=25;
	    public static final int REPLY_LIST_REQUEST=26;
	    public static final int PLAYER_BUSY =27;
	    public static final int SENT_SELECTED=28;
	    public static final int SENT_GET_RESULT=32;
	    public static final int REPLY_RESULT=29;
	    public static final int SENT_REQUEST_HISTORY=30;
	    public static final int REPLY_HISTORY=31;
	    public static final int SEARCH_PLAYER=34;
	    public static final int REPLY_SEARCH_PLAYER=35;
	    
	    

	    
	    
	     
	    private int performative;
	    private Object data;
	    public ObjectWrapper() {
	        super();
	    }
	    public ObjectWrapper(int performative, Object data) {
	        super();
	        this.performative = performative;
	        this.data = data;
	    }
	    public int getPerformative() {
	        return performative;
	    }
	    public void setPerformative(int performative) {
	        this.performative = performative;
	    }
	    public Object getData() {
	        return data;
	    }
	    public void setData(Object data) {
	        this.data = data;
	    }   

}
