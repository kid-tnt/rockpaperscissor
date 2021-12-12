package View;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ClientCtr;
import Model.ObjectWrapper;
import Model.Player;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SearchPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField txtkey;
	private JTable tblresult;
	private ClientCtr mysocket;
	private Player pl;
	private ArrayList<Player> listplayer;
	private ArrayList<Integer> listonline;

	/**
	 * Launch the application.
	 */

	public SearchPlayer(ClientCtr mysocket, Player pl) {
		this.mysocket=mysocket;
		this.pl=pl;
		listplayer=new ArrayList<Player>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 20, 590, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Player");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(29, 10, 524, 57);
		panel.add(lblNewLabel);
		
		JPanel pnSearch = new JPanel();
		pnSearch.setBounds(10, 107, 607, 86);
		contentPane.add(pnSearch);
		pnSearch.setLayout(null);
		
		txtkey = new JTextField();
		txtkey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtkey.setBounds(186, 22, 260, 42);
		pnSearch.add(txtkey);
		txtkey.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("username:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(0, 22, 111, 42);
		pnSearch.add(lblNewLabel_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if((txtkey.getText() == null)||(txtkey.getText().length() == 0))
		                return;
		 
				 mysocket.sendData(new ObjectWrapper(ObjectWrapper.SEARCH_PLAYER, txtkey.getText().trim()));
			}
		});
		btnSearch.setBounds(480, 27, 95, 30);
		pnSearch.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 216, 597, 257);
		contentPane.add(scrollPane);
		
		tblresult = new JTable();
		scrollPane.setViewportView(tblresult);
		   tblresult.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                int column = tblresult.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
	                int row = e.getY() / tblresult.getRowHeight(); // get the row of the button
	 
	                // *Checking the row or column is valid or not
	                if (row < tblresult.getRowCount() && row >= 0 && column < tblresult.getColumnCount() && column >= 0) {
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
	                    new DetailSearchView (mysocket,pl, listplayer.get(row)).setVisible(true);
	                    dispose();
	                }
	            }
	        });
		 mysocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SEARCH_PLAYER, this));

		
	}
	
	public void receivedData(ObjectWrapper data) {
	        if(data.getData() instanceof ArrayList<?>) {
	            listplayer = (ArrayList<Player>)data.getData();
	            System.out.println(listplayer.size()+"Test size listplayer");
	            String[] columnNames = {"Id", "userame"};
	            String[][] value = new String[listplayer.size()][columnNames.length];
	            for(int i=0; i<listplayer.size(); i++){
	                value[i][0] = listplayer.get(i).getId() +"";
	                value[i][1] = listplayer.get(i).getUsername();
	              
	            }
	            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
	                @Override
	                public boolean isCellEditable(int row, int column) {
	                   //unable to edit cells
	                   return false;
	                }
	            };
	            tblresult.setModel(tableModel);
	        }else {
	            tblresult.setModel(null);
	        }
	    }
	
}
