package Controller;

import java.sql.PreparedStatement;



import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Model.Friend;
import Model.Player;
public class FriendDAO extends DAO {
	public FriendDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Friend getFriendById(int inid) {
		String sql="SELECT *From gametest.tblfriend where id=?";
		try {
			Friend fr=new Friend();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, inid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				fr.setId(rs.getInt("id"));
				fr.setRequest(new PlayerDAO().getplayerbyid(rs.getInt("request")));
				fr.setAccept(new PlayerDAO().getplayerbyid(rs.getInt("accept")));
				fr.setDateaccept(rs.getString("dateaccept"));
				fr.setDaterequest(rs.getString("daterequest"));
				
				
			}
			return fr;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	 public boolean isfriend(Player request,Player accept){
		 ArrayList<Player> listfriend=new PlayerDAO().listfriend(request);
		 for(Player pl:listfriend) {
			 if(pl.getId()==accept.getId()) {
				 return true;
			 }
		 }
		 
		 return false;
	
	
	 }
	 public boolean addFriend(Player request,Player accept) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 String sql="INSERT INTO gametest.tblfriend(request,accept,daterequest) VALUES(?,?,?)";
		 
		 try {
			 PreparedStatement ps= con.prepareStatement(sql);
			 ps.setInt(1,request.getId());
			 ps.setInt(2,accept.getId());
			 ps.setString(3, sdf.format(new Date()));
			 ps.executeUpdate();
			   return true;
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return false;
	 }
	 public ArrayList<Friend> listrequest(Player player){// show request to you
		 ArrayList<Friend> list=new ArrayList<>();
		 String sql="SELECT * FROM gametest.tblfriend where dateaccept is null and accept=?";
		 		
		 try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Friend pler=new Friend();
				pler.setId(rs.getInt("id"));
				//pler.setRequest(rs.getInt("request"));
				pler.setRequest(new PlayerDAO().getplayerbyid(rs.getInt("request")));
				pler.setAccept(new PlayerDAO().getplayerbyid(rs.getInt("accept")));
				pler.setDaterequest(rs.getString("daterequest"));
				list.add(pler);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		 return list; 
		
		 
	 }
	 public ArrayList<Friend> listYourSentRequest(Player player){// show your request to another
		 ArrayList<Friend> list=new ArrayList<>();
		 String sql="SELECT * FROM gametest.tblfriend where dateaccept is null and request=?";
		 		
		 try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Friend pler=new Friend();
				pler.setId(rs.getInt("id"));
				//pler.setRequest(rs.getInt("request"));
				pler.setRequest(new PlayerDAO().getplayerbyid(rs.getInt("request")));
				pler.setAccept(new PlayerDAO().getplayerbyid(rs.getInt("accept")));
				pler.setDaterequest(rs.getString("daterequest"));
				list.add(pler);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		 return list; 
		
		 
	 }
	 public boolean updateFriend(Friend fr) {// accept friend
		 String sql="UPDATE gametest.tblfriend SET dateaccept=? where id=?";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			 fr.setDateaccept(sdf.format(new Date()));
		 try {
			 PreparedStatement ps=con.prepareStatement(sql);
			 ps.setString(1, fr.getDateaccept());
			 ps.setInt(2, fr.getId());
			 ps.executeUpdate();
			 return true;
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return false;
		 
	 }
	 public boolean deleteRequest(Friend fr) {// refuse request; delete request from you too
		 String sql="DELETE FROM gametest.tblfriend where id=?";
		 try {
			 PreparedStatement ps=con.prepareStatement(sql);
			
			 ps.setInt(1, fr.getId());
			 ps.executeUpdate();
			 return true;
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return false;
		 
	 }
	

}
