package Controller;

import java.rmi.RemoteException;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import Interface.PlayerInterface;
import Interface.RankInterface;
import Interface.FriendInterface;
import Interface.GameInterface;
import Interface.ParticipantInterface;
import Model.Friend;
import Model.Game;
import Model.IPAddress;
import Model.Participant;
import Model.Player;
import Model.Rank;
 
public class ClientCtrRMI {
 //   private LoginView view;
    private PlayerInterface playerRO;
 //   private RMILoginInterface loginRO;
    private FriendInterface friendRO;
    private RankInterface rankRO;
    private GameInterface gameRO;
    private ParticipantInterface paRO;
    private IPAddress serverAddress = new IPAddress("localhost", 9999); //default server address
    private String rmiService = "rmiServer";                            //default server service key
     
  //  public ClientCtr(LoginView view){
    //    this.view = view;
   // }
         
 //   public ClientCtr(LoginView view, String serverHost, int serverPort, String service){
      //  this.view = view;
     //   serverAddress.setHost(serverHost);
    //    serverAddress.setPort(serverPort);
   //     rmiService = service;
 //   }   
     
    public boolean init(){
        try{
            // get the registry
            Registry registry = LocateRegistry.getRegistry(serverAddress.getHost(), serverAddress.getPort());
            // lookup the remote objects
            playerRO =(PlayerInterface)(registry.lookup(rmiService));  
            friendRO= (FriendInterface) registry.lookup(rmiService);
            rankRO= (RankInterface) registry.lookup(rmiService);
            gameRO=(GameInterface) registry.lookup(rmiService);
            paRO=(ParticipantInterface) registry.lookup(rmiService);
           
        //   view.setServerHost(serverAddress.getHost(), serverAddress.getPort()+"", rmiService);
            System.out.println("Found the remote objects at the host: " + serverAddress.getHost() + ", port: " + serverAddress.getPort());
        }catch(Exception e){
            e.printStackTrace();
          System.out.println("Error to lookup the remote objects!");
            return false;
        }
        return true;
    }
    public Player remoteLoginPlayer(Player player) {
        try {
        return playerRO.checkLogin(player);
        }catch(RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
   public String remoteAddPlayer(Player player) {
	   try {
		   if(playerRO.isExistName(player)) {
			   return "err";
		   }
		   if(playerRO.addPlayer(player)) {
			   return "ok";
			   
		   }
		   else {
			   return "failed";
		   }
		
	} catch (RemoteException e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
		
	}
   }
   public ArrayList<Player> remotelistFriend(Player player){
	   try {
		 return playerRO.listfriend(player);
		 
	} catch (RemoteException e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}
   }
   public ArrayList<Rank> remoteShowRank(){
	   try {
		   return rankRO.showRank();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}
   }
   public boolean remoteIsFriend(Player rq, Player ac) {
	   try {
		   return friendRO.isfriend(rq, ac);
		
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		return false;
	}
   }
   public String remoteAddFriend(Player rq, Player ac) {
	   try {
		   if(friendRO.isfriend(rq, ac)) {
			   return "friend";
		   }
		   else if(friendRO.addFriend(rq, ac))
			   return "ok";
		
	} 
	   catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
	}
	   return "err";
   }
   public ArrayList<Friend> remoteListRequest(Player player){
	   try {
		   return friendRO.listrequest(player);
	   }
	   catch (Exception e) {
		   e.printStackTrace();
		   return null;
	   }
   }
   public ArrayList<Friend> remoteYourSentRequest(Player player){
	   try {
		   return friendRO.listYourSentRequest(player);
	   }
	   catch(Exception e) {
		   e.printStackTrace();
		   return null;
	   }
   }
 public String remoteUpdateFriend(Friend fr) {
	 try {
		 if(friendRO.updateFriend(fr)) 
			 return "ok";
	 }
	 catch(Exception e) {
		   e.printStackTrace();
	   }
	 return "err";
 }
 public String remoteDeleteRequest(Friend fr) {
	 try {
		 if(friendRO.deleteRequest(fr))
		 return "ok";
	 }
	 catch(Exception e) {
		   e.printStackTrace();
	   }
	 return "err";
 }
 public Game remoteCreateGame(Game game) {
	 try {
		 return gameRO.createGame(game);
			 
		 
	 }
	 catch(Exception e) {
		 e.printStackTrace();
		 return null;
	 }
 }
 public boolean remoteUpdate(Game game) {
	 try {
		return gameRO.updateGame(game);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return false;
	}
 }
 public Participant remoteCreateParticipant(Participant p) {
	 try {
		 return paRO.createParticipant(p);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}
 }
 public boolean remoteUpdateParticipant(Participant p) {
	 try {
		 return paRO.updateParticipant(p);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return false;
	}
 }
 
 public ArrayList<Player> remoteSearchPlayer(String key) {
	 try {
		// System.out.println(key+playerRO.searchPlayer(key).size()+" test clientRMI");
		 return playerRO.searchPlayer(key);
		 
	 }catch(Exception e) {
		 e.printStackTrace();
		 return null;
		 
	 }
 }
 
}

