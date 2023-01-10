package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;

		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into seller"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) values" + "(?, ?, ?, ?, ?)"// o ? é chamado de placeholdeR
					,Statement.RETURN_GENERATED_KEYS);//esse comando retorna o codigo do novo elemento inserido no banco 
			st.setString(1, "Carl Purple");// o primeiro ? pelo valor ao lado
			st.setString(2, "carl@gmail.com");// o 2 ? pelo valor ao lado
			st.setDate(3, new java.sql.Date(sdf.parse("21/09/2022").getTime()));// o 3? é um tipo SQL.Date
			st.setDouble(4, 2500.00);// o 4? por um valor double ao lado
			st.setInt(5, 4);

			int rowsAffected = st.executeUpdate();// o resultado desse update é um inteiro indicando quantas linhas
													// foram alteradas, a variavel é só para saber as linhas afetadas

			if(rowsAffected>0) {
				//para pegar o codigo do novo registro inserido é o comando abaixo
				ResultSet rs =st.getGeneratedKeys();//retorna um objeto do tipo ResultSet
				
				while(rs.next()) {
					int id = rs.getInt(1);//indicando a captaçao da primeira coluna apenas pois é a coluna do ID
					System.out.println("Done!: ID = "+id);
				}
			}else {
				System.out.println("None Rows Affected");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
