package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ClientCtr;
import Model.ObjectWrapper;
import Model.Player;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class FriendListView extends JFrame {

	private JPanel contentPane;
	private ClientCtr mysocket;
	private Player player;
	private ArrayList<Player> listfriend;
	private ArrayList<Integer> playerOnline;
	private ArrayList<Integer> playerBusy;
	private JTable tbllistfriend;

	public FriendListView(ClientCtr mysocket, Player player) {
		this.mysocket=mysocket;
		this.player=player;
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl = new JLabel("List Friend of "+ player.getUsername());
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl.setBounds(67, 0, 422, 63);
		contentPane.add(lbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 513, 254);
		contentPane.add(scrollPane);
		
		tbllistfriend = new JTable();
		scrollPane.setViewportView(tbllistfriend);
		tbllistfriend.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				  int column = tbllistfriend.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
	                int row = e.getY() / tbllistfriend.getRowHeight(); // get the row of the button
	 
	                // *Checking the row or column is valid or not
	                if (row < tbllistfriend.getRowCount() && row >= 0 && column < tbllistfriend.getColumnCount() && column >= 0) {
	                    //search and delete all existing previous view
	                    ObjectWrapper existed = null;
	                    for(ObjectWrapper func: mysocket.getActiveFunction())
	                       if(func.getData() instanceof DetailView) {
	                            ((DetailView)func.getData()).dispose();
	                            existed = func;
	                        }
	                   if(existed != null)
	                        mysocket.getActiveFunction().remove(existed);
	                     
	                    //create new instance
	                    (new DetailView(mysocket,player,listfriend.get(row),playerOnline)).setVisible(true);
	                    dispose();
	                }
			}
			
		});
		
		ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SENT_FRIEND_LIST, player);
		mysocket.sendData(data);
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FRIEND_LIST, this));
		
		mysocket.sendData(new ObjectWrapper(ObjectWrapper.PLAYER_ONLINE, null));
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.PLAYER_ONLINE, this));
		mysocket.sendData(new ObjectWrapper(ObjectWrapper.PLAYER_BUSY, null));
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.PLAYER_BUSY, this));
	}
	
	 
	 public void receivedDataProcessing(ObjectWrapper data) {
		 if(data.getData() instanceof ArrayList<?>) {
			 listfriend=(ArrayList<Player>) data.getData();
		
		 
			 
			
		 }
	 }
			 
		 
	 public void receivedDataProcessingPlayerOnline(ObjectWrapper data) {
		 if(data.getData() instanceof ArrayList<?> ) {
			 playerOnline=(ArrayList<Integer>) data.getData();
		      String[] columnNames = {"Id", "Name","Status"};
	            String[][] value = new String[listfriend.size()][columnNames.length];
	            for (int i = 0; i < listfriend.size(); i++) {
	                value[i][0] = listfriend.get(i).getId()+" ";
	                value[i][1] = listfriend.get(i).getUsername();
	                
	               if (playerOnline.contains(listfriend.get(i).getId())) {
	                    value[i][2] = "Online";
	            } else {
	                    value[i][2] = "Offline";
	            }
	               

	          }
	            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
	                @Override
	                public boolean isCellEditable(int row, int column) {
	                    //unable to edit cells
	                    return false;
	                }
	            };
	            tbllistfriend.setModel(tableModel);
	            
			 
		 }
		 else {
        	 tbllistfriend.setModel(null);
        }
		 
		 
     }
	 public void receivedDataProcessingPlayerBusy(ObjectWrapper data) {
		 if(data.getData() instanceof ArrayList<?> ) {
			 playerBusy=(ArrayList<Integer>) data.getData();
		      String[] columnNames = {"Id", "Name","Status"};
	            String[][] value = new String[listfriend.size()][columnNames.length];
	            for (int i = 0; i < listfriend.size(); i++) {
	                value[i][0] = listfriend.get(i).getId()+" ";
	                value[i][1] = listfriend.get(i).getUsername();
	                
	               if (playerBusy.contains(listfriend.get(i).getId())) {
	                    value[i][2] = "Busy";
	               } 
	               else if(playerOnline.contains(listfriend.get(i).getId())) {
	            	   value[i][2] = "Online";
		            } else {
		                    value[i][2] = "Offline";
		            }
	               
	               

	          }
	            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
	                @Override
	                public boolean isCellEditable(int row, int column) {
	                    //unable to edit cells
	                    return false;
	                }
	            };
	            tbllistfriend.setModel(tableModel);
	            
			 
		 }
		 else {
        	 tbllistfriend.setModel(null);
        }
		 
		 
     }
		 
		 
     
	 
		 
	 
}
	

