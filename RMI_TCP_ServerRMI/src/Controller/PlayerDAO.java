package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Player;
public class PlayerDAO extends DAO{
	 public PlayerDAO() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
		public Player getplayerbyid(int inid) {
			
			
			String sql="SELECT *From gametest.tblplayer where id=?";
			try {
				Player player=new Player();
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, inid);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					
					player.setId(rs.getInt("id"));
					player.setUsername(rs.getString("username"));
					player.setRating(rs.getInt("rating"));
					
				}
				return player;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}
	 public boolean  isExistName(Player pl) {
		 String sql="SELECT * FROM gametest.tblplayer where username=? ";
		 
		 try {
			 PreparedStatement ps=con.prepareStatement(sql);
			 ps.setString(1, pl.getUsername());
			 ResultSet re=ps.executeQuery();
			 if(re.next()) {
				 return true;
			 }
			
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		 return false;
		 
	 }
	 public boolean checkLogin(Player player) {
		 String sql="SELECT * FROM gametest.tblplayer WHERE username=? and password=?";
		 
		 try {
			 PreparedStatement ps=con.prepareStatement(sql);
			 ps.setString(1, player.getUsername());
			 ps.setString(2, player.getPasword());
			 ResultSet rs= ps.executeQuery();
			 if(rs.next()) {
				 player.setId(rs.getInt("id"));
				 return true;
				 
			 }
			 
			
		}
	
		
		 catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
		 return false;
		 
	
	}
	 public boolean addPlayer(Player player) {
		 String sql="INSERT INTO gametest.tblplayer(username, password) VALUES(?,?)";
		 
		 try {
			 PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			 ps.setString(1,player.getUsername());
			 ps.setString(2,player.getPasword());
			 ps.executeUpdate();
			  ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                player.setId(generatedKeys.getInt(1));
	            }
			 return true;
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return false;
	 }
	
	 public ArrayList<Player> listfriend(Player player){
		 ArrayList<Player> list=new ArrayList<>();
		 String sql="(SELECT accept as ID FROM gametest.tblfriend where (dateaccept is not null and request=? )) UNION "
				+ "(SELECT request as ID FROM gametest.tblfriend where (dateaccept is not null and accept=? ))";
		 		
		 try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ps.setInt(2, player.getId());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Player	pler=new PlayerDAO().getplayerbyid(rs.getInt("ID"));
				list.add(pler);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		 return list;
	 }
	 public ArrayList<Player> searchPlayer(String s){
		 ArrayList<Player> listplayer=new ArrayList<>();
		 String sql="SELECT *From gametest.tblplayer where username LIKE ?";
				  try{
			            PreparedStatement ps = con.prepareStatement(sql);
			            ps.setString(1, "%" + s + "%"); 
			            ResultSet rs = ps.executeQuery();
			 
			            while(rs.next()){
			                Player pl = new Player();
			                pl.setId(rs.getInt("id"));
			                pl.setUsername(rs.getString("username"));
			                listplayer.add(pl);
			              
			            }
			        }catch(Exception e){
			            e.printStackTrace();
			        }  
			//	  System.out.println("chay qua DAO search Player");
			        return listplayer;
	 }
	   

	}

	     
	
	 
	


