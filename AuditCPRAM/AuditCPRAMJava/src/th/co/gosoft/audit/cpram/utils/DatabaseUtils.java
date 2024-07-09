package th.co.gosoft.audit.cpram.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class DatabaseUtils {

	private final static Logger logger = Logger.getLogger(DatabaseUtils.class);
	
//	private static final String DRIVER = PropertiesUtils.getProperties("db.driver"); 
	private static final String DRIVER = "com.mysql.jdbc.Driver"; //local
	
//	private static final String URL = PropertiesUtils.getProperties("db.url");
//	private static final String DB_NAME = PropertiesUtils.getProperties("db.name");
//	private static final String USERNAME = PropertiesUtils.getProperties("db.username");
//	private static final String PASSWORD = PropertiesUtils.getProperties("db.password");
	
	private static final String URL = "10.182.236.159";
	private static final String DB_NAME = "audit_supplier_cpram";
	private static final String USERNAME = "auditcpram";
	private static final String PASSWORD = "auditcpram";
	private static final String PORT = "3309"; 
	
	
	
//	private static final String DATA_SOURCE = "java:/"+PropertiesUtils.getProperties("db.datasource");
//	private static final String DATA_SOURCE = "java:/AUDIT_SUPPLIER_CPRAM"; //local

	
	
//	public static Connection createConnectionWithoutAutoCommit() {
//		Connection connection = null;
//		try {
//			Class.forName(DRIVER);
//			connection = DriverManager.getConnection("jdbc:mysql://"+URL+"/"+DB_NAME+"?characterEncoding=UTF-8", USERNAME, PASSWORD);
//			connection.setAutoCommit(false);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		} 
//		return connection;
//	}
//
//	public static Connection createConnectionWithAutoCommit() {
//		Connection connection = null;
//		try {
//			Class.forName(DRIVER);
//			connection = DriverManager.getConnection("jdbc:mysql://"+URL+"/"+DB_NAME+"?characterEncoding=UTF-8", USERNAME, PASSWORD);
//			connection.setAutoCommit(true);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		} 	
//		return connection;
//	}

	public static Connection connectToDatasource() {
//		DataSource dataSource = null;
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			String fullUrl = "jdbc:mysql://"+URL+":"+PORT+"/"+ DB_NAME+"?useSSL=false";
			
			connection = DriverManager.getConnection(fullUrl, USERNAME, PASSWORD);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		return connection;
	}
	
	
	public static Connection connectToDatasourceWithoutAutoCommit() {
		Connection connection = null;
		try {
			connection = connectToDatasource();
			connection.setAutoCommit(false);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return connection;
	}

	public static Connection connectToDatasourceWithAutoCommit() {
		Connection connection = null;
		try {
			connection = connectToDatasource();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		return connection;
	}

	public static PreparedStatement createPreparedStatementFromSql(Connection connection, String sqlStatement)
			throws Throwable {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sqlStatement);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		return preparedStatement;
	}

	public static void setParameterToPreparedStatement(PreparedStatement preparedStatement, List<Object> allParameter)
			throws Throwable {
		int parameterIndex = 1;
		try {
			for (Object parameter : allParameter) {
				preparedStatement.setObject(parameterIndex, parameter);
				parameterIndex++;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
	}
	
	public static void closeResourceDB(ResultSet resultSet, PreparedStatement preparedStatement) {
		try {
			
			if(resultSet != null)
				resultSet.close();
			resultSet = null;
			
			if(preparedStatement != null)
				preparedStatement.close();
			preparedStatement = null;
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static void closeAllConnection(ResultSet rs, PreparedStatement ps, Connection con) {
        try {        	
            if (rs != null) {
                rs.close();
            }
            rs=null;
            if (ps != null) {
                ps.close();
            }
            ps=null;
            if (con != null) {
                con.close();
            }
            con=null;
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
	}
	
	public static void closeConnection(Connection connection) {
		try {
			
			for(int r = 1; r<=3; r++ ) {
				if(connection != null) {
					connection.close();
					if(connection.isClosed()) {
						connection = null;
						break;
					}
				}
			}
			/*if(connection != null) {
				connection.close();				
			}	*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);			
			throw new RuntimeException(e.getMessage(), e);
		} 
	}

	public static void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
	}
	
	public static void commit(Connection connection) {
		try {
			if(connection != null) {
				connection.commit();
			}			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static void rollback(Connection connection) {
        try {        	
        	if(connection != null) {
        		connection.rollback();
        	}            
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
    }
}