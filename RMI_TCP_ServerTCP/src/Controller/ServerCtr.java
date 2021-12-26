package Controller;

import java.io.EOFException;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Controller.ServerCtr.ServerProcessing;
import Model.Friend;
import Model.Game;
import Model.IPAddress;
import Model.ObjectWrapper;
import Model.Participant;
import Model.Player;
import Model.Rank;
import View.ServerMainFrm;
import Controller.ClientCtrRMI;

public class ServerCtr {
    private ServerMainFrm view;
    private ServerSocket myServer;
    private ServerListening myListening;
    private ArrayList<ServerProcessing> myProcess;
    private IPAddress myAddress = new IPAddress("localhost",8888);  //default server host and port
    private ArrayList<Integer> playerOnline;//save list online
    private ArrayList<Integer> playerBusy;//save list playing
    private ArrayList<Game> listGame;

    public ServerCtr(ServerMainFrm view){
        myProcess = new ArrayList<ServerProcessing>();
        playerOnline=new ArrayList<Integer>();
        playerBusy=new ArrayList<Integer>();
        listGame=new ArrayList<Game>();
        this.view = view;
        openServer();       
    }     
    public ServerCtr(ServerMainFrm view, int serverPort){
        myProcess = new ArrayList<ServerProcessing>();
        this.view = view;
        myAddress.setPort(serverPort);
        openServer();       
    }
    private void openServer(){
        try {
            myServer = new ServerSocket(myAddress.getPort());
            myListening = new ServerListening();
            myListening.start();
            myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
            view.showServerInfor(myAddress);
            view.showMessage("TCP server is running at the port " + myAddress.getPort() +"...");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }  
    public void stopServer() {
        try {
            for(ServerProcessing sp:myProcess)
                sp.stop();
            myListening.stop();
            myServer.close();
            view.showMessage("TCP server is stopped!");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void publiclistOnline() {
   	 ObjectWrapper data = new ObjectWrapper(ObjectWrapper.PLAYER_ONLINE, playerOnline);
        for (ServerProcessing sp : myProcess) {
            sp.sendData(data);
        }
   }
    public void publiclistBusy() {
      	 ObjectWrapper data = new ObjectWrapper(ObjectWrapper.PLAYER_BUSY, playerBusy);
           for (ServerProcessing sp : myProcess) {
               sp.sendData(data);
           }
      }
    public void publicClientNumber() {
        ObjectWrapper data = new ObjectWrapper(ObjectWrapper.SERVER_INFORM_CLIENT_NUMBER, myProcess.size());
        for(ServerProcessing sp : myProcess) {
            sp.sendData(data);
            
        }
    }
    
 public ServerProcessing getServerProcessingByIdUser(int iduser) {
	 for(ServerProcessing sp : myProcess) {
         if(sp.getPlayer().getId()==iduser) {
        	 return sp;
         }
         
     }
	 return null;
 }

    class ServerListening extends Thread{// thread to process multiple client 
        public ServerListening() {
            super();
        }
        public void run() {
            view.showMessage("server is listening... ");
            try {
                while(true) {
                    Socket clientSocket = myServer.accept();
                    ServerProcessing sp = new ServerProcessing(clientSocket);
                    sp.start();
                  //when server accept client create new thread processing to process for this client
                    myProcess.add(sp); //add process to list myProcess
                    view.showMessage("Number of client connecting to the server: " + myProcess.size());
                    publicClientNumber();//send data to all client connect to server
                    publiclistOnline();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  
    class ServerProcessing extends Thread{//thread process for a client
        private Socket mySocket;
        private Player player;
     //   private ArrayList<Selected> listSe;
     //   private Selected se1,se2;
        public ServerProcessing(Socket s) {
            super();
            mySocket = s;  
            
        }
  	public Socket getMySocket() {
			return mySocket;
		}
		public Player getPlayer() {
			return player;
		}
		public void sendData(Object obj) {
            try {
                ObjectOutputStream oos= new ObjectOutputStream(mySocket.getOutputStream());
                oos.writeObject(obj);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
		public void sendDataToUser(Object obj, Integer userid) {
			ServerProcessing sp= getServerProcessingByIdUser(userid);
            try {
                ObjectOutputStream oos= new ObjectOutputStream(sp.getMySocket().getOutputStream());
                oos.writeObject(obj);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }  
		public Game getResult(int gameid) {
			if(listGame.size()!=0) {
				for(Game g: listGame) {
					if(g.getId()==gameid)
						return g;
				}
			}
			return null;
			
		}
        public void run() { 
        	Player player=new Player();
        	//Create client RMI and connect to RMI server
        	ClientCtrRMI rmi=new ClientCtrRMI();
        	rmi.init();
            try {
                while(true) {
                    ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                    Object o = ois.readObject();
                    if(o instanceof ObjectWrapper){
                        ObjectWrapper data = (ObjectWrapper)o;
 
                        switch(data.getPerformative()) {
                        
                        case ObjectWrapper.PLAYER_ONLINE:
                        	 sendData(new ObjectWrapper(ObjectWrapper.PLAYER_ONLINE, playerOnline));
                          publiclistOnline();
                           break;
                        case ObjectWrapper.PLAYER_BUSY:
                        	sendData(new ObjectWrapper(ObjectWrapper.PLAYER_BUSY,playerBusy));
                        	publiclistBusy();
                        	break;
                        case ObjectWrapper.LOGIN_PLAYER:
                        player = (Player)data.getData();
                        Player plrmi=rmi.remoteLoginPlayer(player);
                        if(plrmi!=null) {
                        	playerOnline.add(plrmi.getId());//add player login successful to list online
       	            		this.player=plrmi;//asign player for processing
       	            		sendData(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_PLAYER,plrmi));
                        }
                        else
                    	   sendData(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_PLAYER,"false"));
                         break;
                         
                       case ObjectWrapper.SIGNUP_PLAYER: 
                        	player =(Player) data.getData();
                        	String rs=rmi.remoteAddPlayer(player);
                        sendData(new ObjectWrapper(ObjectWrapper.REPLY_SIGNUP_PLAYER,rs));
                        break;
                        case ObjectWrapper.SENT_FRIEND_LIST:
                        	player=(Player) data.getData();
                        	ArrayList<Player> listfriend=rmi.remotelistFriend(player);
                        	sendData(new ObjectWrapper(ObjectWrapper.REPLY_FRIEND_LIST, listfriend));
                        	break;
                       case ObjectWrapper.SENT_FRIEND_REQUEST:
                        	//ArrayList<Player> lisent=(ArrayList<Player>) data.getData();
                    	   Friend frrq=(Friend) data.getData();
                        	Player req=frrq.getRequest();
                        	Player ac=frrq.getAccept();
                        	String rsfr=rmi.remoteAddFriend(req, ac);
                        	sendData(new ObjectWrapper(ObjectWrapper.REPLY_FRIEND_REQUEST, rsfr));
                        	break;
                        	
                       case ObjectWrapper.SENT_RANK:
            			ArrayList<Rank> lirank= rmi.remoteShowRank();
            			sendData(new ObjectWrapper(ObjectWrapper.REPLY_RANK, lirank));
                        
                        break;
                       case ObjectWrapper.SENT_LIST_REQUEST: 
                        	player=(Player) data.getData();
                        	ArrayList<Friend> friend= rmi.remoteListRequest(player);
                        	sendData(new ObjectWrapper(ObjectWrapper.REPLY_LIST_REQUEST, friend));	
                        	
                        	break;
                        case ObjectWrapper.SENT_CONFIRM_FRIEND: 
                        	Friend fr= (Friend) data.getData();
                        	sendData(new ObjectWrapper(ObjectWrapper.REPLY_CONFIRM_FRIEND, rmi.remoteUpdateFriend(fr)));
                        	break;
                      case ObjectWrapper.SENT_REFUSE_FRIEND:
                        	Friend request2= (Friend) data.getData();
                        		sendData(new ObjectWrapper(ObjectWrapper.REPLY_REFUSE_FRIEND, rmi.remoteDeleteRequest(request2)));
                        	break;
                      case ObjectWrapper.SENT_CHALLENGE:
                    	Game g=(Game) data.getData();
                    	  if(playerBusy.contains(g.getPlayer2().getId())) {//check player 2 busy
                    		  sendData(new ObjectWrapper(ObjectWrapper.REPLY_SENT_CHALLENGE, "busy"));
                    		  break;
                    	  }
                    	  else {
                    		  sendDataToUser(new ObjectWrapper(ObjectWrapper.REPLY_SENT_CHALLENGE,g), g.getPlayer2().getPlayer().getId());
                        	  break;
                    	  }
                    	 
                      case ObjectWrapper.SENT_ACCEPT_CHALLENGE: 
                    	Game gs=(Game) data.getData();
                    	  playerBusy.add(gs.getPlayer1().getPlayer().getId());
                    	  playerBusy.add(gs.getPlayer2().getPlayer().getId());
                    	  publiclistBusy();
                    	  SimpleDateFormat spd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    	  gs.setStartTime(spd.format(new Date()));
                    	  Participant p1=rmi.remoteCreateParticipant(gs.getPlayer1());
                    	  Participant p2=rmi.remoteCreateParticipant(gs.getPlayer2());
                    	  gs.setPlayer1(p1);
                    	  gs.setPlayer2(p2);
                    	  gs=rmi.remoteCreateGame(gs);//save to CSDL
                    	  listGame.add(gs);
                 
                    	  sendDataToUser(new ObjectWrapper(ObjectWrapper.SENT_START_GAME, gs), gs.getPlayer1().getPlayer().getId());
                    	  sendDataToUser(new ObjectWrapper(ObjectWrapper.SENT_START_GAME, gs),gs.getPlayer2().getPlayer().getId());
                    	 
                    	  break;
                      case ObjectWrapper.SENT_REFUSE_CHALLENGE: 
                    	 Game gre=(Game) data.getData();
                    	  sendDataToUser(new ObjectWrapper(ObjectWrapper.REPLY_REFUSE_CHALLENGE,"none"), gre.getPlayer1().getPlayer().getId());
                    	  
                    	  break;
                    case ObjectWrapper.SENT_SELECTED_WEAPON:
                    	Game gamesent=(Game) data.getData();
                    //	System.out.println("Test serverTCP game id "+ gamesent.getId()
                    //	+ "test paid"+ gamesent.getPlayer1().getId()+ " vs "+ gamesent.getPlayer2().getId()+" "+ gamesent.getSe1() +"vs"+ gamesent.getSe2());
                    //	rmi.remoteUpdate(gamesent);
                    	 // Selected sesent= (Selected) data.getData();
                    	// sesent= rmi.remoteCreateSelected(sesent);
                    	//  System.out.println("Test se weapon "+ sesent.getId());
                    	  for(Game gg: listGame) {
                    		
                    			  if(gg.getId()==gamesent.getId()) {
                    				
                    			//	  System.out.println("Test thông tin game trong list");
                    			//	  System.out.println(gg.getId()+" "+gg.getPlayer1().getId()+ " "+ gg.getPlayer2().getId()+ " ");
                    				//  int s1=gamesent.getSe1();
                         			 if(gg.getSe1()==0) {
                         				gg.setSe1(gamesent.getSe1());
                         			 }
                         			 if(gg.getSe2()==0) {
                         				 gg.setSe2(gamesent.getSe2());
                         			 }
                         			// System.out.println("Test Server TCP select"+ gg.getSe1()+ "vs"+ gg.getSe2());
                         			
                         		  rmi.remoteUpdate(gg);
							}
                    		 
                    			  
                    		  
                    	  } 
                    	 // rmi.remoteUpdate(gamesent);
                    	 
                    	  break;
                    case ObjectWrapper.SENT_GET_RESULT: 
                    	//Participant prs=(Participant) data.getData();
                    	Game prs=(Game) data.getData();
                    	Game tmp=getResult(prs.getId());// get game in listgame
                    	if(tmp.getSe1()==0|| tmp.getSe2()==0)
							break;
                    	else {
                    		//System.out.println("test tmp "+tmp.getId()+" "+tmp.getSe1()+" "+tmp.getSe2()+" " + tmp.getResult()+" "+ tmp.getEndTime());
                        	int wp1=tmp.getSe1();
                        	int wp2=tmp.getSe2();
                        //	if(prs.getGame().getPlaye)
                        	if(wp1==wp2) {
                        		tmp.setResult("Draw. same weapon");
                        		tmp.getPlayer1().setScore(0.5f);
                        		tmp.getPlayer2().setScore(0.5f);
                        	}
                        	if(wp1==1 && wp2==2) {
                        		tmp.setResult(tmp.getPlayer2().getPlayer().getUsername()+ " win");
                        		tmp.getPlayer1().setScore(0f);
                        		tmp.getPlayer2().setScore(1f);
                        		
                        	}
                        	if(wp1==1 && wp2==3) {
                        		tmp.setResult(tmp.getPlayer1().getPlayer().getUsername()+" win");
                        		tmp.getPlayer1().setScore(1f);
                        		tmp.getPlayer2().setScore(0f);
                        	}
                        	if(wp1==2 && wp2==3) {
                        		tmp.setResult(tmp.getPlayer2().getPlayer().getUsername()+ " win");
                        		tmp.getPlayer1().setScore(0f);
                        		tmp.getPlayer2().setScore(1f);
                        	}
                        	if(wp1==2 && wp2==1) {
                        		tmp.setResult(tmp.getPlayer1().getPlayer().getUsername()+ " win");
                        		tmp.getPlayer1().setScore(1f);
                        		tmp.getPlayer2().setScore(0f);
                        	}
                        	if(wp1==3 && wp2==1) {
                        		tmp.setResult(tmp.getPlayer2().getPlayer().getUsername()+" win");
                        		tmp.getPlayer1().setScore(0f);
                        		tmp.getPlayer2().setScore(1f);
                        	}
                        	if(wp1==3 && wp2==2) {
                        		tmp.setResult(tmp.getPlayer1().getPlayer().getUsername()+ " win");
                        		tmp.getPlayer1().setScore(1f);
                        		tmp.getPlayer2().setScore(0f);
                        	}
                        	//tmp.setResult(prs.getResult());
                    	
                    	
                    	
                    	
                    	SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    	tmp.setEndTime(spf.format(new Date()));
                    	rmi.remoteUpdate(tmp);
                    	rmi.remoteUpdateParticipant(tmp.getPlayer1());
                    	rmi.remoteUpdateParticipant(tmp.getPlayer2());
                    //	System.out.println("Test result"+ prs.getGame().getResult());
                    //	System.out.println("Test Score participant "+ prs.getScore());
                    	
                    	//sendDataT(new ObjectWrapper(ObjectWrapper.REPLY_RESULT, tmp));
                    	sendDataToUser(new ObjectWrapper(ObjectWrapper.REPLY_RESULT, tmp), tmp.getPlayer1().getPlayer().getId());
                    	sendDataToUser(new ObjectWrapper(ObjectWrapper.REPLY_RESULT, tmp), tmp.getPlayer2().getPlayer().getId());
                    	listGame.remove(tmp);
                    	}
                    		
                        
                    	//playerBusy.remove(new Integer(prs.getPlayer().getId()));
                    	break;
                    case ObjectWrapper.SEARCH_PLAYER:
                    	String key=(String) data.getData();
                    	//System.out.println(key+ " test key");
                    
                    	ArrayList<Player> listplayer=rmi.remoteSearchPlayer(key);
                    	//System.out.println(listplayer.size()+ " Test size TCP server");
                    	sendData(new ObjectWrapper(ObjectWrapper.REPLY_SEARCH_PLAYER, listplayer));
                    	break;
                    	
                   
                        	
                        
                        }
                       
 
                    }
           
                }
            }catch (EOFException | SocketException e) {             
                e.printStackTrace();
            	playerOnline.remove(new Integer(player.getId()));
            	playerBusy.remove(new Integer(player.getId()));
                myProcess.remove(this);
                view.showMessage("Number of client connecting to the server: " + myProcess.size());
                publicClientNumber();
                publiclistOnline();
                try {
                    mySocket.close();
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                this.stop();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  

}
