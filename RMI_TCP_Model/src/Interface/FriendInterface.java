package Interface; 

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Model.Friend;
import Model.Player;

public interface FriendInterface extends Remote {
	public Friend getFriendById(int inid) throws RemoteException;
	public boolean isfriend(Player request,Player accept) throws RemoteException; 
	public boolean addFriend(Player request,Player accept) throws RemoteException;
	public ArrayList<Friend> listrequest(Player player)throws RemoteException; 
	 public ArrayList<Friend> listYourSentRequest(Player player)throws RemoteException;
	 public boolean updateFriend(Friend fr) throws RemoteException;
	 public boolean deleteRequest(Friend fr) throws RemoteException ;
	

}
