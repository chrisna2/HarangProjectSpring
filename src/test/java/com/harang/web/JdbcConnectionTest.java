package com.harang.web;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;

public class JdbcConnectionTest {

	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/harang";
	private static final String USER = "harang";
	private static final String PW = "1111";
	
	@Test
	public void testOracleConnection() throws Exception{
		
		Class.forName(MYSQL_DRIVER);
			try(Connection con = DriverManager.getConnection(MYSQL_URL,USER,PW)){
				assertNotNull("테스트 성공",con);
			}
			catch(Exception err){
				err.printStackTrace();
			}
	}
	
	@Test
	@Ignore 
	public void testJndiConnection(){
	
		try{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			Connection con = ds.getConnection();
			System.out.println(con);
					
		}
		catch(Exception err){
			err.printStackTrace();
		}
		
	}
	
}
