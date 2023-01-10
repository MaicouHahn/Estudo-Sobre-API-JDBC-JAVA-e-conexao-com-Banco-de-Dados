package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null;	
		Statement st=null;
		ResultSet rs = null;
		
		try {
			
			conn=DB.getConnection();//conecta o banco
			st = conn.createStatement(); //criada a instanciação do Statement
			rs=st.executeQuery("Select * from department");//esse metodo recebe a string do comando SQL enquanto o Resultset
			//que é usado para retornar os dados em forma de tabela recebe o valor dessa consulta
			
			while(rs.next()) {//o next retorna falso caso seja o ultimo elemento, entao ele funciona até o ultimo elemento, entao ele para sozinho
				System.out.println(rs.getInt("Id")+", "+rs.getString("Name"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//metodos para fechar a conexao com o banco de dados todos feitos na classe DB
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
			
		}
		
	}

}
