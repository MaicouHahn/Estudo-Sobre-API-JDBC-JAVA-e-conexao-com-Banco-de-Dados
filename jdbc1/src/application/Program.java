package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		
		Connection conn = DB.getConnection();//cria um objeto passando a conexao(Lembrando que DB.getConnection retorna um objeto da classe Connection)
		//comandos da API 
		//Statement é para montar um comando SQL para mexer no banco de dados ex: um select, um create, um delete
		//resultset é um objeto contendo o resultado da consulta na forma de tabela
		DB.closeConnection();//chama o fechamento da conexao
	}

}
