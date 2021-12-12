package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ClientCtr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Model.ObjectWrapper;
import Model.Player;

public class SignUpView extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpassword;
	private JPasswordField txtrepassword;
	private ClientCtr mysocket;



	/**
	 * Create the frame.
	 */
	public SignUpView(ClientCtr mysocket) {
		
		this.mysocket=mysocket;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setBounds(5, 5, 588, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(37, 76, 90, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(37, 152, 90, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Re-password:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(37, 239, 146, 33);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("I accept the term of  use & privacy policy ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(37, 321, 361, 33);
		contentPane.add(lblNewLabel_2);
		
		txtusername = new JTextField();
		txtusername.setBounds(223, 73, 216, 33);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setEchoChar('*');
		txtpassword.setBounds(223, 152, 216, 33);
		contentPane.add(txtpassword);
		
		txtrepassword = new JPasswordField();
		txtrepassword.setEchoChar('*');
		txtrepassword.setBounds(223, 239, 216, 33);
		contentPane.add(txtrepassword);
		
		JCheckBox accept = new JCheckBox("");
		accept.setSelected(true);
		accept.setBounds(364, 329, 93, 21);
		contentPane.add(accept);
		
		JButton btnsignup = new JButton("Sign Up");
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtusername==null|| txtusername.getText().length()==0 || txtpassword==null || txtpassword.getText().length()==0
						|| txtrepassword==null || txtrepassword.getText().length()==0) {
					JOptionPane.showMessageDialog(contentPane, "Please fill in all the information!");
					
					
				}
				else if(!txtpassword.getText().equals(txtrepassword.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Re-password doesn't match!");
				
				}
				else if(!accept.isSelected()) {
					
					JOptionPane.showMessageDialog(contentPane, "You must accept the terms of use to continue!");
				}
				else {
					Player player =new Player();
					player.setUsername(txtusername.getText());
					player.setPasword(txtpassword.getText());
					ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SIGNUP_PLAYER,player);
					mysocket.sendData(data);
				}
				
			}
		});
		btnsignup.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnsignup.setBounds(223, 364, 156, 46);
		contentPane.add(btnsignup);
		 mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SIGNUP_PLAYER, this));
	}
	   public void receivedDataProcessing(ObjectWrapper data) {
		   if(data.getData().equals("ok")) {
			   JOptionPane.showMessageDialog(this, "SingUp succesfully !");
			  this.dispose();
		   }
		   else if(data.getData().equals("err")) {
			   JOptionPane.showMessageDialog(this, "Username was used! Please choose another username!");
		   }
		   else {
			   JOptionPane.showMessageDialog(this, "SingUp fail!");
			   
		   }
		   
	   }
}
