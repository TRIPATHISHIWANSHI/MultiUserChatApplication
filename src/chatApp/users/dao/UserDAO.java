package chatApp.users.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import chatApp.users.dto.UserDTO;
import chatApp.users.utils.Encryption;

public class UserDAO {

	public boolean isLogin(UserDTO userDTO) throws NoSuchAlgorithmException, Exception, SQLException {
		Connection con=null;
		PreparedStatement ptsmt=null;
		ResultSet rs=null;
		final String SQL="select userid from users where userid=? and password=?";
		try {
			
		
		con=CommonDAO.createConnection();
		ptsmt=con.prepareStatement(SQL);
		ptsmt.setString(1,userDTO.getUserID());
		String encryptedPwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
		ptsmt.setString(2, encryptedPwd);
		rs=ptsmt.executeQuery();
		return rs.next();
//		if(rs.next()) {//check if reacord comes or not
//			return true;
//		}
//		else {
//			return false;
//		}
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(ptsmt!=null) {
				ptsmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		System.out.println("Rec "+userDTO.getUserID()+" USER ID PASSWORD"+ userDTO.getPassword());
		Connection connection=null;
		Statement stmt=null;//interface for query
		try {
		connection=CommonDAO.createConnection();//step 1-connection created
		//step-2:query
		stmt=connection.createStatement();
		int record=stmt.executeUpdate("insert into users (userid,password) values ('"+userDTO.getUserID()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
		return record;//finally will run still
		}
		finally {
			if(stmt!=null) {
		stmt.close();
			}
			if(connection!=null) {
		connection.close();
			}
		}

		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
