package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Friend;
import Model.ObjectWrapper;
import Model.Player;
import Controller.ClientCtr;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ConfirmView extends JFrame {

	private JPanel contentPane;
	private ClientCtr mysocket;
	private Friend request;



	public ConfirmView(ClientCtr mysocket, Friend request) {
		SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.mysocket=mysocket;
		this.request=request;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("From: "+request.getRequest().getUsername());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(28, 121, 494, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date request: "+request.getDaterequest());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(28, 212, 510, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Confirm ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(5, 5, 538, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SENT_CONFIRM_FRIEND,request);
				 mysocket.sendData(data);
		}
		});
		
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirm.setBounds(39, 372, 143, 53);
		contentPane.add(btnConfirm);
		
		JButton btnRefuse = new JButton("Refuse");
		btnRefuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectWrapper data= new ObjectWrapper(ObjectWrapper.SENT_REFUSE_FRIEND,request);
				mysocket.sendData(data);
			
			}
		});
		btnRefuse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRefuse.setBounds(333, 372, 143, 53);
		contentPane.add(btnRefuse);
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_CONFIRM_FRIEND, this));
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_REFUSE_FRIEND, this));
		
	
		
		
	}
	public void receiveConfirm(ObjectWrapper data) {
	     
         	String rs=(String) data.getData();
         	if(rs.equals("ok")) {
         		JOptionPane.showMessageDialog(contentPane, "Confirm Successfully!");
         		dispose();
         	}
         	else {
         		JOptionPane.showMessageDialog(contentPane, "Error!");
         		dispose();
         	}
         
	
		
	}
	public void receiveRefuse(ObjectWrapper data) {
	   
         	String rs=(String) data.getData();
         	if(rs.equals("ok")) {
         		JOptionPane.showMessageDialog(contentPane, "Refused");
         		dispose();
         		
         	}
         	else {
         		JOptionPane.showMessageDialog(contentPane, "Error!");
         		dispose();
         	}
         
	
		
	}
}
