package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Friend;
import Model.ObjectWrapper;
import Model.Player;
import Controller.ClientCtr;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RequestView extends JFrame {

	private JPanel contentPane;
	private JTable tblrequest;
	private ClientCtr mysocket;
	private Player pl;
	private ArrayList<Friend> listrequest;
	

	/**
	 * Launch the application.
	 */

	public RequestView(ClientCtr mysocket,Player pl) {
	//	SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.mysocket=mysocket;
		this.pl=pl;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Friend Request to "+ pl.getUsername());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(5, 5, 548, 52);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 102, 543, 371);
		contentPane.add(scrollPane);
		
		tblrequest = new JTable();
		scrollPane.setViewportView(tblrequest);
         tblrequest.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                 int column = tblrequest.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                 int row = e.getY() / tblrequest.getRowHeight(); // get the row of the button
  
                 // *Checking the row or column is valid or not
                 if (row < tblrequest.getRowCount() && row >= 0 && column < tblrequest.getColumnCount() && column >= 0) {
                     (new ConfirmView(mysocket,listrequest.get(row))).setVisible(true);
                    // dispose();
                 }
             }
         });
 		ObjectWrapper data=new ObjectWrapper(ObjectWrapper.SENT_LIST_REQUEST,pl);
 		mysocket.sendData(data);
 		mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_LIST_REQUEST, this));
        
         
		  
	}
	public void receiveDataListRequest(ObjectWrapper data) {
		if(data.getData() instanceof ArrayList<?>) {
			listrequest=(ArrayList<Friend>) data.getData();
			 String[] columnNames = {"Id", "username","Date request"};
	         String[][] value = new String[listrequest.size()][columnNames.length];
	         for(int i=0; i<listrequest.size(); i++){
	             value[i][0] = listrequest.get(i).getId() +"";
	             value[i][1] = listrequest.get(i).getRequest().getUsername();
	             value[i][2] = listrequest.get(i).getDaterequest();
	         }
	         DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
	             @Override
	             public boolean isCellEditable(int row, int column) {
	                //unable to edit cells
	                return false;
	             }
	         };
	         tblrequest.setModel(tableModel);
			
		}
	}
}
