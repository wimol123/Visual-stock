package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import th.co.gosoft.audit.cpram.utils.DatabaseUtils;

public class StandardAttributeDAO {
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	public StandardAttributeDAO(Connection connection) {
		this.connection = connection;
	}
	
	protected void closeResourceDB() {
		DatabaseUtils.closeResourceDB(resultSet, preparedStatement);
	}

}
