package Controller;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


import Model.Game;
import Model.Participant;
import Model.Player;
import Model.Rank;
import Controller.PlayerDAO;

public class ParticipantDAO extends DAO {
public ArrayList<Rank> showRank(){
	 ArrayList<Rank> list=new ArrayList<>();
	 String sql="SELECT a.id,a.username, b.Score From"
	 		+ " (SELECT idplayer,sum(score) as Score From gametest.tblparticipant group by idplayer) b, gametest.tblplayer a "
	 		+ "where a.id=b.idplayer Order by Score Desc";
	 		
	 try {
		PreparedStatement ps= con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			Rank rank= new Rank();
			rank.setId(rs.getInt("id"));
			rank.setPlayer(new PlayerDAO().getplayerbyid(rs.getInt("id")));

			rank.setScore(rs.getFloat("Score"));
			list.add(rank);
			
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	 
	 return list;
	
	
	
}
public boolean createParticipant(Participant p) {//create a Participant and set player id 
	 String sql="INSERT INTO gametest.tblparticipant (idplayer,idgame,score) VALUES(?,?,?)";
	 
	 try {
		 PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		 ps.setInt(1,p.getPlayer().getId());
		 ps.setInt(2,p.getGame().getId());
		 ps.setFloat(3,p.getScore());
		 ps.executeUpdate();
		  ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
               p.setId(generatedKeys.getInt(1));
            }
		 return true;
	
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	 return false;
	
}
public boolean updateParticipant(Participant p) {
    String sql = "UPDATE gametest.tblparticipant SET idplayer=?, idgame =?, score=? WHERE id=?";
    try {
   	 PreparedStatement ps=con.prepareStatement(sql);
   	 ps.setInt(1,p.getPlayer().getId());
   	 ps.setInt(2,p.getGame().getId());
   	ps.setFloat(3,p.getScore());
   	 ps.setInt(4,p.getId());
   	ps.executeUpdate();
   	
    }
    catch(Exception e) {
   	 e.printStackTrace();
    }
    return true;
}

	

}

