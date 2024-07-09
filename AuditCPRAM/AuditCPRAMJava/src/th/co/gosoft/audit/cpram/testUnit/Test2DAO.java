package th.co.gosoft.audit.cpram.testUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.XAConnection;

import th.co.gosoft.audit.cpram.utils.DatabaseUtils;

public class Test2DAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public Test2DAO(Connection connection) {
		this.connection = connection;
	}

	public void closeConnection() {
		DatabaseUtils.closeResourceDB(resultSet, preparedStatement);
	}
	
	public void test() {
		try {
			
			
			StringBuilder quBuilder = new StringBuilder();
			quBuilder.append("INSERT INTO `audit_supplier_cpram_final`.`user_group` (`user_group_name`, `authen_type`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('fghdfghdfghdfgh', 'dfghdfgh', 'Y', '1', '2018-12-22 21:40:38', '1', '2018-12-22 21:40:38');");
			
			preparedStatement = connection.prepareStatement(quBuilder.toString());
			preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		finally {
			closeConnection();
		}
	}
	
}
