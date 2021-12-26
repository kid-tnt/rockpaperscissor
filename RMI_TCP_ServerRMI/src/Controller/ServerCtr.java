package Controller;

import java.net.InetAddress;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
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
import View.ServerMainFrm;
public class ServerCtr extends UnicastRemoteObject implements PlayerInterface,FriendInterface, RankInterface,GameInterface,ParticipantInterface{
	private static final long serialVersionUID = 1L;
	private IPAddress myAddress = new IPAddress("localhost", 9999);     // default server host/port
    private Registry registry;
    private ServerMainFrm view;
    private String rmiService = "rmiServer";    // default rmi service key
     
    public ServerCtr(ServerMainFrm view) throws RemoteException{
        this.view = view;   
    }
     
    public ServerCtr(ServerMainFrm view, int port, String service) throws RemoteException{
        this.view = view;   
        myAddress.setPort(port);
        this.rmiService = service;
    }
     
    public void start() throws RemoteException{
        // registry this to the localhost
        try{
            try {
                //create new one
                registry = LocateRegistry.createRegistry(myAddress.getPort());
            }catch(ExportException e) {//the Registry exists, get it
                registry = LocateRegistry.getRegistry(myAddress.getPort());
            }
            registry.rebind(rmiService, this);
            myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
            view.showServerInfo(myAddress, rmiService);
            view.showMessage("The RMI has registered the service key: " + rmiService + ", at the port: " + myAddress.getPort());
        }catch(RemoteException e){
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
     
    public void stop() throws RemoteException{
        // unbind the service
        try{
            if(registry != null) {
                registry.unbind(rmiService);
                UnicastRemoteObject.unexportObject(this,true);
            }
            view.showMessage("The RMI has unbinded the service key: " + rmiService + ", at the port: " + myAddress.getPort());
        }catch(RemoteException e){
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
     
     
  











	@Override
	public boolean updateFriend(Friend fr) throws RemoteException {
		// TODO Auto-generated method stub
		return new FriendDAO().updateFriend(fr);
	}

	@Override
	public boolean addPlayer(Player player) throws RemoteException {
		// TODO Auto-generated method stub
		return new PlayerDAO().addPlayer(player);
	}



	@Override
	public Player getplayerbyid(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return new PlayerDAO().getplayerbyid(id);
	}




	@Override
	public boolean isExistName(Player player) throws RemoteException {
	return new PlayerDAO().isExistName(player);
	}

	@Override
	public ArrayList<Rank> showRank() throws RemoteException {
		// TODO Auto-generated method stub
		return new ParticipantDAO().showRank();
	}

	@Override
	public boolean addFriend(Player rq, Player ac) throws RemoteException {
		// TODO Auto-generated method stub
		return new FriendDAO().addFriend(rq, ac);
	}

	@Override
	public boolean deleteRequest(Friend fr) {
		// TODO Auto-generated method stub
		return new FriendDAO().deleteRequest(fr);
	}

	@Override
	public Friend getFriendById(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return new FriendDAO().getFriendById(id);
	}

	@Override
	public boolean isfriend(Player rq, Player ac) throws RemoteException {
		// TODO Auto-generated method stub
		return new FriendDAO().isfriend(rq, ac);
	}

	@Override
	public ArrayList<Friend> listYourSentRequest(Player player) throws RemoteException {
		// TODO Auto-generated method stub
		return new FriendDAO().listYourSentRequest(player);
	}

	@Override
	public ArrayList<Friend> listrequest(Player player) throws RemoteException {
		// TODO Auto-generated method stub
		return new FriendDAO().listrequest(player);
	}

	@Override
	public ArrayList<Player> listfriend(Player player) throws RemoteException {
		// TODO Auto-generated method stub
		return new PlayerDAO().listfriend(player);
	}

	@Override
	public Player checkLogin(Player player) throws RemoteException {
		// TODO Auto-generated method stub
		Player pl=new Player();
		if(new PlayerDAO().checkLogin(player)) {
			pl=player;
			return pl;
		}
		return null;
	}


	@Override
	public Participant createParticipant(Participant p) throws RemoteException {
		// TODO Auto-generated method stub
		if(new ParticipantDAO().createParticipant(p))
			return p;
		return null;
	}

	@Override
	public boolean updateParticipant(Participant p) throws RemoteException {
		// TODO Auto-generated method stub
		return new ParticipantDAO().updateParticipant(p);
	}

	@Override
	public Game createGame(Game g) throws RemoteException {
		// TODO Auto-generated method stub
		if(new GameDAO().createGame(g))
			return g;
		return null;
	}

	@Override
	public boolean updateGame(Game g) throws RemoteException {
		// TODO Auto-generated method stub
		return new GameDAO().updateGame(g);
	}
	@Override
	public ArrayList<Player> searchPlayer(String key) throws RemoteException {
		// TODO Auto-generated method stub
		//System.out.println(key+ new PlayerDAO().searchPlayer(key).size()+ " test server rmi");
		return new PlayerDAO().searchPlayer(key);
		
	}


	
	


	


	
    
}
