package ework.utils;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import ework.genericactions.GenericFileActions;

public class DBConnectionPool {
	private static DataSource datasource = null;
	private static Connection con = null;
	private static PoolProperties p = new PoolProperties();
	
	public static void createDBPool(ConfigFile cf)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, URISyntaxException {
		
		DBConnectionPool.createDBPool(cf.getDBDriver(), cf.getDBUrl(), cf.getDBU(),
				cf.getDBP(), cf.getPoolName(), cf.getDBThreads(), cf.getPoolTimeout(), cf.getConnectionTimeout());
	}
	
	public static void createSmallDBPool(ConfigFile cf)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, URISyntaxException {
		
		DBConnectionPool.createDBPool(cf.getDBDriver(), cf.getDBUrl(), cf.getDBU(),
				cf.getDBP(), cf.getPoolName(), 1, cf.getPoolTimeout(), cf.getConnectionTimeout());
	}
	
	public static void createDBPool(String strdriver, String jdbcurl, String uname,
			String pword, String poolname, int minconn, int pooltimeout, int conntimeout)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, URISyntaxException {
		
		String trustpass = GenericFileActions.getProperty("ks");
			
		System.setProperty("javax.net.ssl.trustStore", GenericFileActions.getResourceFile("truststore").getAbsolutePath());
        System.setProperty("javax.net.ssl.trustStorePassword", trustpass);
        		  
		if (DBConnectionPool.con == null) {
			
			  p.setUrl(jdbcurl);
	          p.setDriverClassName(strdriver);
	          p.setUsername(uname);
	          p.setPassword(pword);
	          p.setJmxEnabled(true);
	          p.setTestWhileIdle(false);
	          p.setTestOnBorrow(true);
	          p.setValidationQuery("SELECT 1");
	          p.setTestOnReturn(false);
	          p.setValidationInterval(60000);
	          p.setTimeBetweenEvictionRunsMillis(60000);
	          p.setMaxActive(100);
	          p.setInitialSize(10);
	          p.setMaxWait(10000);
	          p.setRemoveAbandonedTimeout(120);
	          p.setMinEvictableIdleTimeMillis(60000);
	          p.setMinIdle(10);
	          p.setLogAbandoned(true);
	          p.setRemoveAbandoned(true);
	          p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
	                  + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
	                  + "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");
	
	          
	          datasource = new DataSource();
	          datasource.setPoolProperties(p);

		}
	}
	
	
	public static Connection getConnection() throws SQLException {
		 con = datasource.getConnection();
		if (con == null) {
			throw new SQLException("Custom exception: Connection timed out.");
		}
		
		return con;
	}
	
	public static void closePool()  {
		if (DBConnectionPool.con != null) {
			try {
				DBConnectionPool.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnectionPool.con = null;
		}
	}
}
