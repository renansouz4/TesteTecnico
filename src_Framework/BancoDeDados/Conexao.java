
package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Report.ExtentReport;

public class Conexao {
	
	private static String host;
	private static String port;
	private static String sid;
	private static String user;
	private static String senha;
	List<Integer> listagem = new ArrayList<Integer>();

	public Conexao(String host, String port, String sid, String user, String senha) {
        Conexao.host = host;
        Conexao.port = port;
        Conexao.sid = sid;
        Conexao.user = user;
        Conexao.senha = senha;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Conexao.host = host;
    } 

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        Conexao.port = port;
    }

    public static String getSid() {
        return sid;
    }

    public static void setSid(String sid) {
        Conexao.sid = sid;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Conexao.user = user;
    }

    public static String getSenha() {
        return senha;
    } 

    public static void setSenha(String senha) {
        Conexao.senha = senha;

    }
    
	public int consultaBanco(ExtentReport reportHTML) throws SQLException {
		String query = ""; // <-- DIGITAR QUERY DESEJADA
		
		reportHTML.test.log(LogStatus.INFO, "Realizando a consulta : " + query);

		Connection conexao = ObterConexao();

		Statement statement = null;
		statement = conexao.createStatement();
		ResultSet resultSet = null;
		resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			return resultSet.getInt("min");
		}
		return 0;
	} 
    
    public static Connection ObterConexao() throws SQLException{
    	
		Connection conexao = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");		
						
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + sid , user , senha);

			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return conexao;
	}
    
    public boolean ContidoBanco(int num,ExtentReport reportHTML) throws SQLException {
    	
    	Connection conexao = ObterConexao();
    	
    	String query = ""; // <-- DIGITE A QUERY DESEJADA
    	
    	Statement statement = null;
    	statement = conexao.createStatement();    	
    	ResultSet resultSet = null;
    	resultSet = statement.executeQuery(query);
    	
    	while(resultSet.next()) {
			int confere = resultSet.getInt("sq");
			if (confere == num) {
	    		return true;
			}
		}   	
		return false;
    }
    
    public String Descricao(int num,ExtentReport reportHTML) throws SQLException {
    	
    	Connection conexao = ObterConexao();
    	
    	String query = ""; // <-- DIGITE A QUERY DESEJADA
    	reportHTML.test.log(LogStatus.INFO, "Realizando a consulta : " + query);
    	
    	Statement statement = null;
    	statement = conexao.createStatement();    	
    	ResultSet resultSet = null;
    	resultSet = statement.executeQuery(query);
    	
    	while(resultSet.next()) {
			String confere = resultSet.getString("no");
			return confere;
		}   	
		return null;
    }
}