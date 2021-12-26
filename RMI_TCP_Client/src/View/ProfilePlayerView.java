package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ClientCtr;
import Model.Game;
import Model.ObjectWrapper;
import Model.Participant;
import Model.Player;
import Model.Rank;
import View.RankView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfilePlayerView extends JFrame {

	private JPanel contentPane;
	private static ClientCtr mysocket;
	private Player player;
	private ArrayList<Integer> playerOnline;
	
	
	public ProfilePlayerView(ClientCtr mysocket, Player player) {
		this.mysocket=mysocket;
		this.player=player;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rock Paper Scrissor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(197, 10, 276, 67);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hi "+player.getUsername());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(35, 87, 572, 55);
		contentPane.add(lblNewLabel_1);
		
		JButton btnFriend = new JButton("ListFriend");
		btnFriend.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FriendListView flv=new FriendListView(mysocket, player);		
				flv.setVisible(true);
				
			}
		});
		btnFriend.setBounds(141, 145, 140, 38);
		contentPane.add(btnFriend);
		
		JButton btnSearchPlayer = new JButton("SearchPlayer");
		btnSearchPlayer.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSearchPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchPlayer sp=new SearchPlayer(mysocket,player);
				sp.setVisible(true);
			}
		});
		btnSearchPlayer.setBounds(333, 145, 128, 38);
		contentPane.add(btnSearchPlayer);
		
		JButton btnRequestReceive = new JButton("Request Received");
		btnRequestReceive.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRequestReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestView rqv= new RequestView(mysocket, player);
				rqv.setVisible(true);
			}
		});
		btnRequestReceive.setBounds(153, 238, 128, 38);
		contentPane.add(btnRequestReceive);
		
		JButton btnRank = new JButton("Rank");
		btnRank.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankView rv=new RankView(mysocket, player);
				rv.setVisible(true);
			//	ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SENT_RANK, null);
			//	mysocket.sendData(data);
			//	mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FRIEND_LIST, this));
			//	System.out.println("gui data yeu cau rank ok");
				
			}
		});
		
		btnRank.setBounds(341, 238, 120, 38);
		contentPane.add(btnRank);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					mysocket.closeConnection();
					mysocket.getActiveFunction().clear();
				mysocket.closeConnection();
				System.exit(0);
			}
		});
		btnExit.setBounds(254, 330, 120, 38);
		contentPane.add(btnExit);
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SENT_CHALLENGE, this));
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_ACCEPT_CHALLENGE, this));
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_REFUSE_CHALLENGE, this));
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.SENT_START_GAME, this));
		//mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.PLAYER_ONLINE, this));
		  mysocket.sendData(new ObjectWrapper(ObjectWrapper.PLAYER_ONLINE, null));
		  mysocket.sendData(new ObjectWrapper(ObjectWrapper.PLAYER_BUSY, null));
	}
	public void receiveDataInvite(ObjectWrapper data) {
		if(data.getData() instanceof String) {
			JOptionPane.showMessageDialog(contentPane, " Player busy!!!");
		}
		
		Game g=(Game) data.getData();
			int bt=JOptionPane.showConfirmDialog(null,"Player "+g.getPlayer1().getPlayer().getUsername()+" invite you to game" , player.getUsername()+"'s Confirm invite", JOptionPane.YES_NO_OPTION);
			if(bt==JOptionPane.YES_OPTION) {
				mysocket.sendData(new ObjectWrapper(ObjectWrapper.SENT_ACCEPT_CHALLENGE, g));
				
			}
			if(bt==JOptionPane.NO_OPTION) {
				mysocket.sendData(new ObjectWrapper(ObjectWrapper.SENT_REFUSE_CHALLENGE, g));
			}
			
		
		
	}
	public void receiveDataReplyChallenge(ObjectWrapper data) {
		String rs=(String) data.getData();
		if(rs.equals("none")) {
			JOptionPane.showMessageDialog(contentPane, "Invitation declined!!!");
			
		}
		//if(rs.equals("ok")) {
		//	JOptionPane.showMessageDialog(contentPane, "Go Play game");
		//}
	}
	public void receiveDataStartGamePlay(ObjectWrapper data) {
	Game p= (Game) data.getData();
		
		GameView gv=new GameView(mysocket,player,p);
		gv.setVisible(true);
	}
}
