package Controller;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.Statement;

import Model.Game;

public class GameDAO extends DAO{

	public GameDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean createGame(Game game) {
	 String sql="INSERT INTO gametest.tblgame(idplayer1,idplayer2,starttime,endtime,result) VALUES(?,?,?,?,?)";
		 
		 try {
			 PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			 ps.setInt(1, game.getPlayer1().getId());
			 ps.setInt(2,game.getPlayer2().getId());
			 ps.setString(3, game.getStartTime());
			 ps.setString(4, game.getEndTime());
			 ps.setString(5, game.getResult());
			// ps.setString(2,);
			 ps.executeUpdate();
			  ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                game.setId(generatedKeys.getInt(1));
	            }
			 return true;
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return false;
		
	}
	public boolean updateGame(Game game) {
	     String sql = "UPDATE gametest.tblgame SET starttime=?, endtime =?, result=?,idplayer1=?,idplayer2=? WHERE id=?";
	     try {
	    	 PreparedStatement ps=con.prepareStatement(sql);
	    	 ps.setString(1,game.getStartTime());
	    	 ps.setString(2,game.getEndTime());
	    	ps.setString(3,game.getResult());
	    	ps.setInt(4, game.getPlayer1().getId());
	    	 ps.setInt(5,game.getPlayer2().getId());
	    	 ps.setInt(6, game.getId());
	    	 
	    	ps.executeUpdate();
	    	
	     }
	     catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     return true;
	}
	

}
