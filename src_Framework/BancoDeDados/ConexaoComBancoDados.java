package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.AssertionFailedError;

public class ConexaoComBancoDados {

	public String buscaUltimoPlReservadoSQ_PLANO(String Plano) {

		String query =""; // <-- DIGITE A QUERY DESEJADA

		Connection conexao = ObterConexao();
		Statement statement = null;

		try {
			statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (resultSet.next()) {
				String resultado = resultSet.getString("SQ_PLANO");

				return resultado;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.fail("Erro ao buscar último id do plano reservado - SQ_PLANO");
		return query;
	}
	
	public int buscaUltimoPlReservado() {

		String query =""; // <-- DIGITE A QUERY DESEJADA

		Connection conexao = ObterConexao();
		Statement statement = null;

		try {
			statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (resultSet.next()) {
				String resultado = resultSet.getString("NO_PLANO");
				int tamanhoMax = resultado.length();
				String resultadoFinalString = resultado.substring(16, tamanhoMax);
				int resultadoFinalInteger = Integer.parseInt(resultadoFinalString);
				resultadoFinalInteger++;
				return resultadoFinalInteger;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.fail("Erro ao buscar último id do plano reservado");
		return 0;
	}
	
	public static Connection ObterConexao() {

		Connection conexao = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conexao = DriverManager.getConnection(

					"jdbc:oracle:thin:@XXXXXX:XXXX/XXXXXXXX", "XXXXXXX", "XXXXXXX");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return conexao;

	}

}
