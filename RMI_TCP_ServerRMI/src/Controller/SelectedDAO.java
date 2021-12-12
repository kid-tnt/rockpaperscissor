package Controller;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import Model.Selected;

public class SelectedDAO extends DAO {

	public SelectedDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean createSelected(Selected se) {
		 String sql="INSERT INTO gametest.tblselected(idweapon, idparticipant,idgame) VALUES(?,?,?)";
			 
			 try {
				// System.out.println("chya qua DAO");
				 PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				 ps.setInt(1,se.getWeapon().getId());
				 ps.setInt(2,se.getParticipant().getId());
				 ps.setInt(3,se.getGame().getId());
				 ps.executeUpdate();
				  ResultSet generatedKeys = ps.getGeneratedKeys();
		            if (generatedKeys.next()) {
		            	se.setId(generatedKeys.getInt(1));
		            }
				 return true;
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			 return false;
			
		}
	public boolean updateSelected(Selected s) {
	    String sql = "UPDATE gametest.tblselected SET idweapon=?, idparticipant =?, idgame=? WHERE id=?";
	    try {
	   	 PreparedStatement ps=con.prepareStatement(sql);
	   	 ps.setInt(1,s.getWeapon().getId());
	   	 ps.setInt(2,s.getParticipant().getId());
	   	ps.setInt(3,s.getGame().getId());
	   	 ps.setInt(4,s.getId());
	   	ps.executeUpdate();
	   	
	    }
	    catch(Exception e) {
	   	 e.printStackTrace();
	    }
	    return true;
	}


}
