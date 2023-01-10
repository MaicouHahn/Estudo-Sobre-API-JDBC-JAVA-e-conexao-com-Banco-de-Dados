package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"update seller " + "set BaseSalary = BaseSalary + ? " + "where (DepartmentId = ?)");

			st.setDouble(1, 200.00);
			st.setInt(2, 2);

			int rowsAffected = st.executeUpdate();

			System.out.println("Linhas Afetadas: " + rowsAffected);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		
		}
	}

}
