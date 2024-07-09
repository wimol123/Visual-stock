package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SystemSequenceDAO extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(SystemSequenceDAO.class);
	
	public SystemSequenceDAO(Connection connection) {
		super(connection);
	}
	
	
	public List<SystemSequenceDTO> getSystemSequenceList(Integer startRecord, Integer numOfRows, String queryWhereClause) {
		try {
			/*SELECT sys_sequence.seq_key, sys_sequence.seq_value, sys_sequence.seq_digits, sys_sequence.use_key_prefix, sys_sequence.delimit, sys_sequence.seq_description, sys_sequence.enable
			FROM system_sequence sys_sequence
			WHERE 1=1 AND sys_sequence.enable = 'Y'*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sys_sequence.seq_key, sys_sequence.seq_value, sys_sequence.seq_digits, sys_sequence.use_key_prefix, sys_sequence.delimit, sys_sequence.seq_description, sys_sequence.enable, sys_sequence.update_by ");
			query.append("FROM ").append(DBConst.TABLE_System_Sequence).append(" sys_sequence ");
			query.append("WHERE 1=1 AND sys_sequence.enable = 'Y' ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by sys_sequence.seq_key asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(" FOR UPDATE;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			//preparedStatement.set
			resultSet = preparedStatement.executeQuery();
			
			List<SystemSequenceDTO> systemSequenceDTOs = new ArrayList<>();
			while(resultSet.next()) {
				SystemSequenceDTO systemSequenceDTO = new SystemSequenceDTO();
				systemSequenceDTO.setSeqKey(resultSet.getString("seq_key"));
				systemSequenceDTO.setSeqValue(resultSet.getInt("seq_value"));
				systemSequenceDTO.setSeqDigits(resultSet.getInt("seq_digits"));
				systemSequenceDTO.setUseKeyPrefix(NullUtils.cvChar(resultSet.getString("use_key_prefix")));
				systemSequenceDTO.setDelimit(NullUtils.cvChar(resultSet.getString("delimit")));
				systemSequenceDTO.setSeqDescription(resultSet.getString("seq_description"));
				systemSequenceDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				systemSequenceDTO.setUpdateBy(resultSet.getInt("update_by"));
				systemSequenceDTOs.add(systemSequenceDTO);
			}
			return systemSequenceDTOs;
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int updateSystemSequence(SystemSequenceDTO systemSequenceDTO) {
		try {
			
			//UPDATE `system_sequence` SET `seq_key` = 'CAR2', `seq_value` = '2', `seq_digits` = '5', `use_key_prefix` = 'N', `delimit` = '-', `enable` = 'N', `update_by` = '2', `update_date` = 'now()' WHERE (`seq_key` = 'CAR');
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_System_Sequence);
			query.append(" SET ");
			query.append("seq_value = seq_value+1, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (seq_key = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, systemSequenceDTO.getUpdateBy());
			preparedStatement.setString(index++, systemSequenceDTO.getSeqKey());
			
			rowAffective = preparedStatement.executeUpdate();
			return rowAffective;
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countSystemSequenceList(String queryWhereClause) {
		int totalSystemSequence = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_System_Sequence).append(" sys_sequence ");
			query.append("WHERE 1=1 AND sys_sequence.enable = 'Y' ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalSystemSequence = resultSet.getInt("total");
			}
			return totalSystemSequence;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalSystemSequence;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
