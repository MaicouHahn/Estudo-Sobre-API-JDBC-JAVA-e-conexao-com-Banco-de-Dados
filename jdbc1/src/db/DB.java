package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe para obter e fechar uma conexão com o banco de dados

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {// metodo para fazer a conexão com o banco de dados

		if (conn == null) {// se nao estiver conectado

			try {
				Properties props = loadProperties();// pega as propriedades do banco de dados usando o metodo abaixo
				String url = props.getProperty("dburl");// passa a url de db.properties para uma string que sera usada
														// abaixo
				conn = DriverManager.getConnection(url, props);// conn recebe a url(dburl) e as propriedades de
																// conexão(props)
				/*
				 * OBS: Conectar ao banco de dados em JDBC é instanciar um objeto do tipo
				 * Connection que é exatamente o que foi feito na linha de cima
				 */

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}

		return conn;
	}

	public static void closeConnection() {// metodo para fechar o banco de dados

		try {

			if (conn != null) {//testa se a conexao é diferente de nula, ou seja esta aberta
				conn.close();//e entao fecha a conexao
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	private static Properties loadProperties() { // metodo para carregar as propriedades dentro do arquivo db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);// ele faz a leitura dos dados e guarda dentro de props
			return props;

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}

}
