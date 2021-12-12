package Interface;


import java.rmi.Remote;
import java.rmi.RemoteException;

import Model.Game;

public interface GameInterface extends Remote{
	public Game createGame(Game game) throws RemoteException;
	public boolean updateGame(Game game) throws RemoteException;
	

}
