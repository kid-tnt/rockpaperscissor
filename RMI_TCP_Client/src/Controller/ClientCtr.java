package Controller;

import java.io.ObjectInputStream;




import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import Model.IPAddress;
import Model.ObjectWrapper;
import Model.Player;

import View.ConfirmView;
import View.DetailSearchView;
import View.DetailView;
import View.FriendListView;
import View.GameView;
import View.LoginView;
import View.ProfilePlayerView;
import View.RankView;
import View.RequestView;
import View.SearchPlayer;
import View.SignUpView;
public class ClientCtr {
	private Socket mySocket;
 //  private ClientMainFrm view;
    private ClientListening myListening;                            // thread to listen the data from the server
    private ArrayList<ObjectWrapper> myFunction;                  // list of active client functions
    private IPAddress serverAddress = new IPAddress("localhost",8888);  // default server host and port
	private LoginView view;
//	private ArrayList<Integer> playerOnline;
     
   // public ClientCtr(ClientMainFrm view){
  //      super();
  //      this.view = view;
    //    myFunction = new ArrayList<ObjectWrapper>();  
    //}
     
    public ClientCtr(LoginView view, IPAddress serverAddr) {
        super();
        this.view = view;
        this.serverAddress = serverAddr;
        myFunction = new ArrayList<ObjectWrapper>();
      //  playerOnline=new ArrayList<Integer>();
    }
 
 
 
    public boolean openConnection(){        
        try {
            mySocket = new Socket(serverAddress.getHost(), serverAddress.getPort());  
            myListening = new ClientListening();
            myListening.start();
     System.out.println("Connected to the server at host: " + serverAddress.getHost() + ", port: " + serverAddress.getPort());
        } catch (Exception e) {
            //e.printStackTrace();
           System.out.println("Error when connecting to the server!");
            return false;
        }
        return true;
    }
     
    public boolean sendData(Object obj){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(obj);           
             
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error when sending data to the server!");
            return false;
        }
        return true;
    }
     
    /*
    public Object receiveData(){
        Object result = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            result = ois.readObject();
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when receiving data from the server!");
            return null;
        }
        return result;
    }*/
     
    public boolean closeConnection(){
         try {
             if(myListening != null)
                 myListening.stop();
             if(mySocket !=null) {
                 mySocket.close();
               System.out.println("Disconnected from the server!");
             }
            myFunction.clear();             
         } catch (Exception e) {
             //e.printStackTrace();
           System.out.println("Error when disconnecting from the server!");
             return false;
         }
         return true;
    }
     
     
     
    public ArrayList<ObjectWrapper> getActiveFunction() {
        return myFunction;
    }
 
 
    class ClientListening extends Thread{
         
        public ClientListening() {
            super();
        }
         
        public void run() {
            try {
                while(true) {
                ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                Object obj = ois.readObject();
                if(obj instanceof ObjectWrapper) {
                    ObjectWrapper data = (ObjectWrapper)obj;
                    if(data.getPerformative() == ObjectWrapper.SERVER_INFORM_CLIENT_NUMBER)
                       System.out.println("Number of client connecting to the server: " + data.getData());
                   // if(data.getPerformative()== ObjectWrapper.PLAYER_ONLINE) {
                    //	SearchPlayer sp=new SearchPlayer();
                    //	sp.receivedDataProcessingPlayerOnline(data);
                  //  }
                    
                    else {
                    	
                      // for(ObjectWrapper fto: myFunction)
                    	 for (int i = 0; i < myFunction.size(); ++i) {
                           // if(fto.getPerformative() == data.getPerformative()) {
                    		 if(myFunction.get(i).getPerformative() == data.getPerformative()) {
                                switch(data.getPerformative()) {
                               case ObjectWrapper.REPLY_LOGIN_PLAYER:
                                 //  LoginView loginView = (LoginView)fto.getData();
                            	 
                            	  LoginView loginView = (LoginView) myFunction.get(i).getData();
                                   loginView.receivedDataProcessing(data); 
                                    break; 
                               case ObjectWrapper.REPLY_SIGNUP_PLAYER:
                                  // SignUpView sgv = (SignUpView)fto.getData();
                            	   SignUpView sgv = (SignUpView)myFunction.get(i).getData();
                                     sgv.receivedDataProcessing(data); 
                                       break; 
                           
                             case ObjectWrapper.REPLY_FRIEND_LIST:
                          
                            	   FriendListView plv= (FriendListView) myFunction.get(i).getData();
                            	   plv.receivedDataProcessing(data);
                            	   break;
                             case ObjectWrapper.PLAYER_ONLINE:
                          	   //ProfilePlayerView ppv=(ProfilePlayerView) fto.getData();
                          	   FriendListView flv=(FriendListView)myFunction.get(i).getData();
                          	   flv.receivedDataProcessingPlayerOnline(data);
                          	   break;
                             case ObjectWrapper.PLAYER_BUSY:
                            	   FriendListView flv2=(FriendListView)myFunction.get(i).getData();
                            	   flv2.receivedDataProcessingPlayerBusy(data);
                            	   break;
                             case ObjectWrapper.REPLY_RANK: 
                            	 RankView rv=(RankView) myFunction.get(i).getData();
                            	 rv.receiveDataRank(data);
                            	 break;
                             case ObjectWrapper.REPLY_FRIEND_REQUEST:
                            	 DetailSearchView dtvr= (DetailSearchView) myFunction.get(i).getData();
                            	 dtvr.receiveDatarequest(data);
                            	 break;
                             //case ObjectWrapper.REPLY_FRIEND_REQUEST_RANK: 
                            //	 DetailViewR dtvrr= (DetailViewR) myFunction.get(i).getData();
                            //	 dtvrr.receiveDatarequest(data);
                            //	 break;
                             case ObjectWrapper.REPLY_LIST_REQUEST:
                            	 RequestView rqv= (RequestView) myFunction.get(i).getData();
                            	 rqv.receiveDataListRequest(data);
                            	 break;
                             case ObjectWrapper.REPLY_CONFIRM_FRIEND:
                            	 ConfirmView cfv= (ConfirmView) myFunction.get(i).getData();
                            	 cfv.receiveConfirm(data);
                            	 break;
                             case ObjectWrapper.REPLY_REFUSE_FRIEND: 
                            	 ConfirmView cfv2= (ConfirmView) myFunction.get(i).getData();
                            	 cfv2.receiveRefuse(data);
                            	 break;   
                             case ObjectWrapper.REPLY_SENT_CHALLENGE:
                            	 ProfilePlayerView ppv= (ProfilePlayerView) myFunction.get(i).getData();
                            	 ppv.receiveDataInvite(data);
                            	 break;
                           //  case ObjectWrapper.REPLY_ACCEPT_CHALLENGE: 
                            //	 ProfilePlayerView ppv2= (ProfilePlayerView) myFunction.get(i).getData();
                            //	 ppv2.receiveDataReplyChallenge(data);
                            //	 break;
                             case ObjectWrapper.REPLY_REFUSE_CHALLENGE: 
                            	 ProfilePlayerView ppv1= (ProfilePlayerView) myFunction.get(i).getData();
                            	 ppv1.receiveDataReplyChallenge(data);
                            	 break;
                             case ObjectWrapper.SENT_START_GAME: 
                            	 ProfilePlayerView ppv3= (ProfilePlayerView) myFunction.get(i).getData();
                            	 ppv3.receiveDataStartGamePlay(data);
                            	 break;
                             case ObjectWrapper.REPLY_RESULT: 
                            	 GameView gv=(GameView) myFunction.get(i).getData();
                            	 gv.receiveResult(data);
                            	 break;
                             case ObjectWrapper.REPLY_SEARCH_PLAYER:
                            	 SearchPlayer search= (SearchPlayer) myFunction.get(i).getData();
                            	 search.receivedData(data);
                            	 break;
                            	 
                            	 
                            	 
                            	   
                           
                         
                                }
                    		 }
                        
                            
                        //view.showMessage("Received an object: " + data.getPerformative());
                    
                
                    	 }
                    }//end else
                    }
                }//end while
                
            }//end try
             catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error when receiving data from the server!");
               // closeConnection();
            //    view.resetClient();
            }
        }//end run
    }//end client thread
}
            
    


