
package View;

import java.awt.BorderLayout;
import Model.Game;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ClientCtr;
import Model.ObjectWrapper;
import Model.Participant;
import Model.Player;
import Model.Rank;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DetailView extends JFrame {

	private JPanel contentPane;
	private ClientCtr mysocket;
	private Player pl;
	private ArrayList<Integer> playerOnline;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public DetailView(ClientCtr mysocket, Player player,Player playerdetail,ArrayList<Integer> playerOnline) {
		this.mysocket=mysocket;
		this.pl=playerdetail;
		this.playerOnline=playerOnline;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Information of "+pl.getUsername());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(83, 24, 357, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id:"+ pl.getId());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(47, 116, 71, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Rating: "+ pl.getRating());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(312, 110, 91, 32);
		contentPane.add(lblNewLabel_2);
		
		JButton btninvite = new JButton("Invite");
		btninvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogbt=JOptionPane.showConfirmDialog(null, "Would you like invite "+ playerdetail.getUsername()+" to game?", "Invite", JOptionPane.YES_NO_OPTION);
				if(dialogbt==JOptionPane.YES_OPTION) {
					Game g=new Game();
					//g.setPlayer1(player);
					Participant p1=new Participant();
					p1.setPlayer(player);
					g.setPlayer1(p1);
					Participant p2=new Participant();
					p2.setPlayer(playerdetail);
					g.setPlayer2(p2);
				//	g.getPlayer1().setPlayer(player);
					//g.setPlayer2(playerdetail);
					//g.getPlayer2().setPlayer(playerdetail);
					//System.out.println(g.getPlayer1().getPlayer().getUsername());
				//	System.out.println(g.getPlayer2().getPlayer().getUsername());
					mysocket.sendData(new ObjectWrapper(ObjectWrapper.SENT_CHALLENGE, g));
				}
				
			}
		});
		btninvite.setFont(new Font("Tahoma", Font.BOLD, 15));
		btninvite.setBounds(214, 284, 102, 45);
		contentPane.add(btninvite);
		btninvite.setEnabled(false);
		if(playerOnline.contains(pl.getId())) {
		//	btnchat.setEnabled(true);
			btninvite.setEnabled(true);
		}
	//	if(player);)
	
	
	}
}
