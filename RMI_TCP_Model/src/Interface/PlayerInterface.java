package Interface;
import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Model.Player;
public interface PlayerInterface extends Remote {
	public Player checkLogin(Player player) throws RemoteException;
	public boolean addPlayer(Player player) throws RemoteException;
	public Player getplayerbyid (int inid) throws RemoteException;
	public boolean isExistName(Player player) throws RemoteException;
	 public ArrayList<Player> listfriend(Player player) throws RemoteException;
	 public ArrayList<Player> searchPlayer(String s) throws RemoteException;
	
	
	

}
