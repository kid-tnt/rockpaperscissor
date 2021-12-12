package Interface;

import java.rmi.Remote;

import java.rmi.RemoteException;
import Model.Selected;

public interface SelectedInterface extends Remote {

	public Selected createSelected(Selected selected) throws RemoteException;
	public boolean updateSelected(Selected selected) throws RemoteException;
}
