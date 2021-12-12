package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ClientCtr;
import Model.Friend;
import Model.ObjectWrapper;
import Model.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailSearchView extends JFrame {

	private JPanel contentPane;
	private ClientCtr socket;
	private Player player;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public DetailSearchView(ClientCtr socket, Player player, Player detail) {
		this.socket=socket;
		this.player=player;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(""+ detail.getUsername());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 31, 202, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("AddFriend");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Friend fr=new Friend();
				fr.setRequest(player);
				fr.setAccept(detail);
			ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SENT_FRIEND_REQUEST, fr);
			socket.sendData(data);
	dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(138, 166, 161, 51);
		contentPane.add(btnNewButton);
		socket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FRIEND_REQUEST, this));
	}
	public void receiveDatarequest(ObjectWrapper data) {
		 
		 String rs=(String) data.getData();
		 if(rs.equals("friend")) {
			 JOptionPane.showMessageDialog(contentPane, " Can't sent requets Because you two have been friend !");
			 dispose();
		 }
		  if(rs.equals("ok")) {
			 JOptionPane.showMessageDialog(contentPane, "Sent friend request successfully !");
		 }
		
	 
	
}
}
