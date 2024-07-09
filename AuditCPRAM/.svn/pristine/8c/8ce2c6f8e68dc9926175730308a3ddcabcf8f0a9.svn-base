package th.co.gosoft.audit.cpram.testUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;

public class TestDAO {
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public TestDAO(Connection connection) {
		this.connection = connection;
	}

	public void closeConnection() {
		//DatabaseUtils.closeAllConnection(resultSet, preparedStatement, connection);
		//DatabaseUtils.closeResourceDB(resultSet, preparedStatement);
	}
	
	public void testConnection() {
		try {
			
			//connection.getConnection().commit();
			
			
			//connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO `audit_supplier_cpram_final`.`user` (`employee_id`, `user_group_id`, `username`, `password`, `fullname`, `description`, `email`, `telephone`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2222', '1', 'adminnnnn', '11111111111111', 'tttttttttt', 'tttttttttt', 'test', '9485360958-286', 'Y', '1', '2019-02-22 09:42:29', '1', '2019-02-22 09:42:29');");
			PreparedStatement ps = connection.prepareStatement(query.toString());
			ps.executeUpdate();
			
			
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
	
	public List<SystemSequenceDTO> LockRowQuery() {
		try {
			connection.setAutoCommit(true);
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM system_sequence WHERE seq_key = 'CHP' FOR UPDATE;");
			preparedStatement = connection.prepareStatement(query.toString());
			
			resultSet = preparedStatement.executeQuery();
			Thread.sleep(5000);
			List<SystemSequenceDTO> systemSequenceDTOs = new ArrayList<>();
			while(resultSet.next()) {
				SystemSequenceDTO systemSequenceDTO = new SystemSequenceDTO();
				systemSequenceDTO.setSeqKey(resultSet.getString("seq_key"));
				systemSequenceDTOs.add(systemSequenceDTO);
			}
			return systemSequenceDTOs;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}finally {
			closeConnection();
		}
	}
	
	public List<SystemSequenceDTO> Select() {
		try {
			connection.setAutoCommit(true);
			
			StringBuilder query = new StringBuilder();
									
			query.append("SELECT * FROM system_sequence WHERE seq_key = 'CHP' FOR UPDATE;");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
		
			List<SystemSequenceDTO> systemSequenceDTOs = new ArrayList<>();
			while(resultSet.next()) {
				SystemSequenceDTO systemSequenceDTO = new SystemSequenceDTO();
				systemSequenceDTO.setSeqKey(resultSet.getString("seq_key"));
				systemSequenceDTOs.add(systemSequenceDTO);
			}
			return systemSequenceDTOs;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}finally {
			closeConnection();
		}
	}
	
}
