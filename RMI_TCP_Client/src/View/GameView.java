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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends JFrame {

	private JPanel contentPane;
	private int count=15;
	private Timer timer;
	private JButton btnRock, btnPaper, btnScissor;
	private ImageIcon selected;
	private ClientCtr socket;
	private Game g;

	private Player player;

	private JLabel lblresult,lblselect1,lblselect2;
	public GameView( ClientCtr socket,Player player,Game p) {
		this.socket=socket;
		this.player=player;
		this.g=p;
		//System.out.print(player.getId()+"Test gameView ID");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnTimer = new JPanel();
		pnTimer.setBounds(5, 10, 767, 63);
		contentPane.add(pnTimer);
		pnTimer.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Time: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(50, 10, 147, 44);
		pnTimer.add(lblNewLabel_1);
		
		JLabel lbltimer = new JLabel("");
		lbltimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltimer.setBounds(282, 10, 304, 51);
		pnTimer.add(lbltimer);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setBounds(672, 12, 85, 44);
		pnTimer.add(btnExit);
		
		JPanel pnMainGame = new JPanel();
		pnMainGame.setBounds(5, 187, 772, 256);
		contentPane.add(pnMainGame);
		pnMainGame.setLayout(null);
		
		JPanel pnplayer1 = new JPanel();
		pnplayer1.setBounds(10, 10, 362, 236);
		pnMainGame.add(pnplayer1);
		pnplayer1.setLayout(null);
		
		JLabel lblname1 = new JLabel("Player1 "+ p.getPlayer1().getPlayer().getUsername());
		lblname1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblname1.setHorizontalAlignment(SwingConstants.CENTER);
		lblname1.setBounds(33, 10, 296, 44);
		pnplayer1.add(lblname1);
		
		lblselect1 = new JLabel("");
		lblselect1.setHorizontalAlignment(SwingConstants.CENTER);
		//lblselect1.setIcon(new ImageIcon(GameView.class.getResource("/images/rock.png")));
	
		lblselect1.setBounds(10, 49, 342, 177);
		pnplayer1.add(lblselect1);
		
		JPanel pnplayer2 = new JPanel();
		pnplayer2.setBounds(382, 10, 380, 236);
		pnMainGame.add(pnplayer2);
		pnplayer2.setLayout(null);
		
		JLabel lblname2 = new JLabel("Player2 "+ p.getPlayer2().getPlayer().getUsername());
		lblname2.setHorizontalAlignment(SwingConstants.CENTER);
		lblname2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblname2.setBounds(47, 10, 296, 44);
		pnplayer2.add(lblname2);
		
		lblselect2 = new JLabel("");
		lblselect2.setHorizontalAlignment(SwingConstants.CENTER);
		lblselect2.setBounds(10, 49, 342, 177);
		pnplayer2.add(lblselect2);
		
		JPanel pnresult = new JPanel();
		pnresult.setBounds(5, 453, 767, 77);
		contentPane.add(pnresult);
		pnresult.setLayout(null);
		
		lblresult = new JLabel("");
		lblresult.setHorizontalAlignment(SwingConstants.CENTER);
		lblresult.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblresult.setBounds(10, 10, 725, 57);
		pnresult.add(lblresult);
		
		JPanel pnSelect = new JPanel();
		pnSelect.setBounds(15, 83, 757, 85);
		contentPane.add(pnSelect);
		pnSelect.setLayout(null);
		
		 btnRock = new JButton("Rock");
		btnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPaper.setEnabled(false);
				btnScissor.setEnabled(false);
				btnRock.setEnabled(false);
				timer.stop();
			
				selected =new ImageIcon(GameView.class.getResource("/images/rock.png"));
				if(p.getPlayer1().getPlayer().getId()==player.getId()) {
					lblselect1.setIcon(selected);	
					p.setSe1(1);
				}
				else {
					lblselect2.setIcon(selected);
					p.setSe2(1);
				}
			//	se.setParticipant(p);	
			
				socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_SELECTED_WEAPON,p));
				socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_GET_RESULT, p));
				
			}
		});
		btnRock.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRock.setBounds(10, 10, 121, 55);
		pnSelect.add(btnRock);
		
		btnPaper = new JButton("Paper");
		btnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPaper.setEnabled(false);
				btnRock.setEnabled(false);
				btnScissor.setEnabled(false);
				timer.stop();
				selected =new ImageIcon(GameView.class.getResource("/images/paper.png"));
				if(p.getPlayer1().getPlayer().getId()==player.getId()) {
					lblselect1.setIcon(selected);
					p.setSe1(2);
				
				}
				else {
					lblselect2.setIcon(selected);
					p.setSe2(2);
				}
				socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_SELECTED_WEAPON,p));
				socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_GET_RESULT, p));
			}
		});
		btnPaper.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPaper.setBounds(297, 10, 121, 55);
		pnSelect.add(btnPaper);
		btnScissor = new JButton("Scissor");
		btnScissor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRock.setEnabled(false);
				btnPaper.setEnabled(false);
				btnScissor.setEnabled(false);
				timer.stop();
				selected =new ImageIcon(GameView.class.getResource("/images/scisors.png"));
				if(p.getPlayer1().getPlayer().getId()==player.getId()) {
					lblselect1.setIcon(selected);
					p.setSe1(3);
					
				}
				
				else {
					lblselect2.setIcon(selected);
					p.setSe2(3);
				
				}
				socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_SELECTED_WEAPON,p));
				socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_GET_RESULT, p));
			}
		});
		btnScissor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnScissor.setBounds(594, 10, 121, 55);
		pnSelect.add(btnScissor);
		 count=15;
		timer=new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lbltimer.setText(count-- +" ");
				if(count==0) {
					lbltimer.setText(0+"");
					lbltimer.setText("Time up!!!");
					timer.stop();
					btnRock.setEnabled(false);
					btnPaper.setEnabled(false);
					btnScissor.setEnabled(false);
					//set random weapon
					int rand= new Random().nextInt(3)+1;
					if(rand==1) {
					//	wp=new Weapon(1, "Rock","Bua");
						selected =new ImageIcon(GameView.class.getResource("/images/rock.png"));
						
					}
					if(rand==2) {
					//	wp=new Weapon(2, "Paper", "Giay");
						selected =new ImageIcon(GameView.class.getResource("/images/paper.png"));
					}
					if(rand==3) {
					//	wp=new Weapon(3, "Scissor", "Keo");
						selected =new ImageIcon(GameView.class.getResource("/images/scisors.png"));
					}
					//Selected serd=new Selected();
					if(p.getPlayer1().getPlayer().getId()==player.getId()) {
						lblselect1.setIcon(selected);
						p.setSe1(rand);
					}
					else {
						lblselect2.setIcon(selected);
						p.setSe2(rand);
						
					
				}
					socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_SELECTED_WEAPON,p));
					socket.sendData(new ObjectWrapper(ObjectWrapper.SENT_GET_RESULT, p));
					
				
				
				}
			}
		});
		
		timer.start();
		socket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_RESULT, this));
		
		
		
		
		
	}
	public void receiveResult(ObjectWrapper data) {
		//System.out.println("receiveResult");
		Game tmp=(Game) data.getData();
	//	System.out.println(tmp.getResult());
		lblresult.setText(tmp.getResult());
			lblresult.repaint();
			if(player.getId()==g.getPlayer1().getPlayer().getId()) {
				if(tmp.getSe2()==1) {
					lblselect2.setIcon(new ImageIcon(GameView.class.getResource("/images/rock.png")));
				}
				if(tmp.getSe2()==2) {
					lblselect2.setIcon(new ImageIcon(GameView.class.getResource("/images/paper.png")));
				}
				if(tmp.getSe2()==3) {
					lblselect2.setIcon(new ImageIcon(GameView.class.getResource("/images/scisors.png")));
				}
			}
			else {
				if(tmp.getSe1()==1) {
					lblselect1.setIcon(new ImageIcon(GameView.class.getResource("/images/rock.png")));
				}
				if(tmp.getSe1()==2) {
					lblselect1.setIcon(new ImageIcon(GameView.class.getResource("/images/paper.png")));
				}
				if(tmp.getSe1()==3) {
					lblselect1.setIcon(new ImageIcon(GameView.class.getResource("/images/scisors.png")));
				}
			}
			
			//contentPane.repaint();
			
		}
}
