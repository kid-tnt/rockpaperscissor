package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.ObjectWrapper;
import Model.Player;
import Model.Rank;
import Controller.ClientCtr;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RankView extends JFrame {

	private JPanel contentPane;
	private JTable tblrank;
	private ClientCtr mysocket;
	private ArrayList<Rank> rank;
	private Player player;
	public RankView( ClientCtr mysocket,Player player) {
		this.mysocket=mysocket;
		this.player=player;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rank");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(5, 5, 513, 52);
		contentPane.add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("Hi "+ player.getUsername());
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setBounds(5, 85, 261, 30);
        contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 141, 508, 315);
		contentPane.add(scrollPane);
		
		tblrank = new JTable();
		scrollPane.setViewportView(tblrank);
	 /*  tblrank.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblrank.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblrank.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tblrank.getRowCount() && row >= 0 && column < tblrank.getColumnCount() && column >= 0) {
                    (new DetailViewR(mysocket,player, rank.get(row))).setVisible(true);
                   // dispose();
                }
            }
        });
        */
		/*  String[] columnNames = {"Id", "username","Score"};
          String[][] value = new String[rank.size()][columnNames.length];
          for(int i=0; i<rank.size(); i++){
              value[i][0] = rank.get(i).getId() +"";
              value[i][1] = rank.get(i).getUsername();
              value[i][2] = rank.get(i).getScore()+"";
          }
          DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
              @Override
              public boolean isCellEditable(int row, int column) {
                 //unable to edit cells
                 return false;
              }
          };
          tblrank.setModel(tableModel);
          */
		ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SENT_RANK, null);
		mysocket.sendData(data);
		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_RANK, this));
          

     
         
		
	}
	public void receiveDataRank(ObjectWrapper data) {
		if(data.getData() instanceof ArrayList<?> ) {
			rank=(ArrayList<Rank>) data.getData();
			 String[] columnNames = {"Id", "username","Score"};
	         String[][] value = new String[rank.size()][columnNames.length];
	         for(int i=0; i<rank.size(); i++){
	             value[i][0] = rank.get(i).getId() +"";
	             value[i][1] = rank.get(i).getPlayer().getUsername();
	             value[i][2] = rank.get(i).getScore()+"";
	         }
	         DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
	             @Override
	             public boolean isCellEditable(int row, int column) {
	                //unable to edit cells
	                return false;
	             }
	         };
	         tblrank.setModel(tableModel);
		}
		
	 
	     
		
	}
		
	
	
}
