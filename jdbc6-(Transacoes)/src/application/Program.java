package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);//fica a cargo do programador confirmar essa atualizaçao nos dados
			st=conn.createStatement();
			
			int rows = st.executeUpdate("update seller set BaseSalary = 2090 where departmentId = 1");
			
			//int x = 1;
			//if(x<2) {
				//throw new SQLException("fake error");
			//}
			int rows2 = st.executeUpdate("update seller set BaseSalary = 3090 where departmentId = 2");
			conn.commit();//confirma o fim da transaçao
			
			System.out.println("rows 1 : "+ rows+"rows2: "+rows2);
			
		} catch (SQLException e) {
			try {
				conn.rollback();//volta ao estado inicial do banco caso de algum erro no meio da transaçao
				
				throw new DbException("Transaction rolled back, error: "+e.getMessage());//essa mensagem é caso ele de erro na tranaçao e caia no rollback
			} catch (SQLException e1) {
				//esse bloco é caso de um erro no rollback
				throw new DbException("Rollback Error: "+e1.getMessage());
			}
			
		}finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		
		}
	}

}
