package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Model.Participant;

public interface ParticipantInterface extends Remote {
public Participant createParticipant(Participant p) throws RemoteException;
public boolean updateParticipant (Participant p) throws RemoteException;
}
