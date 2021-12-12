package View;

import java.awt.BorderLayout;




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ClientCtr;
import Model.IPAddress;
import Model.ObjectWrapper;
import Model.Player;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class LoginView extends JFrame {


	private JPanel contentPane;
	private JTextField txtusername;
	private static ClientCtr myControl;
	 private static IPAddress serverAddress = new IPAddress("localhost",8888);
	 private JPasswordField txtpassword;
	// private ArrayList<Player> listplayer;

	public LoginView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.addWindowListener( new WindowAdapter(){
	           public void windowClosing(WindowEvent e) {     
	        	   myControl.closeConnection();
	             System.exit(0);
	             }
	          });
		setBounds(100, 100, 715, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(111, 23, 470, 51);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 126, 681, 304);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 22, 200, 37);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 83, 200, 37);
		panel.add(lblNewLabel_1_1);
		
		txtusername = new JTextField();
		txtusername.setBounds(261, 22, 251, 37);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player=new Player();
				player.setUsername(txtusername.getText());
				player.setPasword(txtpassword.getText());
				  
	            ObjectWrapper data=new ObjectWrapper(ObjectWrapper.LOGIN_PLAYER,player);
	            myControl.sendData(data);
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnlogin.setBounds(341, 151, 104, 44);
		panel.add(btnlogin);
		
		JLabel lblNewLabel_2 = new JLabel("You don't have a account? ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(72, 242, 313, 37);
		panel.add(lblNewLabel_2);
		
		JButton btnsignup = new JButton("Sign Up");
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpView(myControl).setVisible(true);
				
				
			}
		});
		btnsignup.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnsignup.setBounds(341, 242, 110, 37);
		panel.add(btnsignup);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(261, 83, 251, 37);
		txtpassword.setEchoChar('*');
		panel.add(txtpassword);
		myControl=new ClientCtr(this, serverAddress);
		myControl.openConnection();
		myControl.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_PLAYER, this));
	}
	   public void receivedDataProcessing(ObjectWrapper data) {
		   /*if(data.getData() instanceof Player) {
			   
				  Player player=(Player) data.getData();
				ProfilePlayerView pp=new ProfilePlayerView(myControl,player);
				pp.setVisible(true);
				
		
				this.dispose();
			   }
			   else {
				   JOptionPane.showMessageDialog(this, "Incorrect username and/or password!");
				   
			   }
		   */
		   if(data.getData() instanceof Player) {
			   
			 
				Player player=(Player) data.getData();
				
					ProfilePlayerView pp=new ProfilePlayerView(myControl,player);
					pp.setVisible(true);
				//JOptionPane.showMessageDialog(contentPane, "ok");
				
				
			
				this.dispose();
			   }
			   else {
				   JOptionPane.showMessageDialog(this, "Incorrect username and/or password!");
				   
			   }
		   
	   }

	
	 public static void main(String[] args) {
		
		 
	       LoginView view = new LoginView();
	      view.setVisible(true);
	      
	       
	    }
}
