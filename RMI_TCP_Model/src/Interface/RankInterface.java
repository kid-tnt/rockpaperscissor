package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Model.Rank;

public interface RankInterface extends Remote{
	public ArrayList<Rank> showRank() throws RemoteException;

}
