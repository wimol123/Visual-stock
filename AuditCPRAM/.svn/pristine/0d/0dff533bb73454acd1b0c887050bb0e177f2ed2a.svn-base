package th.co.gosoft.audit.cpram.utils;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.SystemSequenceDAO;
import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;

public class SystemSequenceGenerator {
	
	private final static Logger logger = Logger.getLogger(SystemSequenceGenerator.class);
	private static SystemSequenceGenerator systemSequenceGeneratorInstance = null;
	private static Object mutex = new Object();
	/*private Connection connection;
	public SystemSequenceGenerator(Connection connection) {
		this.connection = connection;
	}*/
	private SystemSequenceGenerator() {}
	public static SystemSequenceGenerator getInstance() {
		synchronized (mutex) {
			if(systemSequenceGeneratorInstance == null) {
				synchronized (mutex) {
					if(systemSequenceGeneratorInstance == null) {
						systemSequenceGeneratorInstance = new SystemSequenceGenerator();
					}
				}
			}
		}
		return systemSequenceGeneratorInstance;
	}
	
	public String generator(Connection connection, String seqKey) {
		String stringResultGenerator = "";
		SystemSequenceDAO systemSequenceDAO = null;
		try {		
			
			StringBuilder queryWhereClause = new StringBuilder();
			
			
			
			systemSequenceDAO = new SystemSequenceDAO(connection);
			
			queryWhereClause.append(" AND sys_sequence.seq_key = '").append(seqKey).append("' ");
			List<SystemSequenceDTO> systemSequenceForUpdate = systemSequenceDAO.getSystemSequenceList(0, systemSequenceDAO.countSystemSequenceList(queryWhereClause.toString()), queryWhereClause.toString());
						
			systemSequenceDAO.updateSystemSequence(systemSequenceForUpdate.get(0));			
			
			List<SystemSequenceDTO> systemSequenceDTOs = systemSequenceDAO.getSystemSequenceList(0, systemSequenceDAO.countSystemSequenceList(queryWhereClause.toString()), queryWhereClause.toString());
			if(systemSequenceDTOs != null && !systemSequenceDTOs.isEmpty()) {
				if(!StringUtils.isNullOrEmpty(systemSequenceDTOs.get(0).getUseKeyPrefix().toString())) {
					
					
					LocalDateTime localDateTime = LocalDateTime.now();
					int years = localDateTime.getYear();
					
					if(systemSequenceDTOs.get(0).getUseKeyPrefix().equals('Y')) {
						stringResultGenerator = String.format("%s%s%s/%s", systemSequenceDTOs.get(0).getSeqKey().trim(), systemSequenceDTOs.get(0).getDelimit().toString().trim(), years, org.apache.commons.lang.StringUtils.leftPad(NullUtils.cvStr(systemSequenceDTOs.get(0).getSeqValue()), systemSequenceDTOs.get(0).getSeqDigits(), "0"));
					}else {						
						stringResultGenerator = String.format("%s/%s", years, org.apache.commons.lang.StringUtils.leftPad(NullUtils.cvStr(systemSequenceDTOs.get(0).getSeqValue()), systemSequenceDTOs.get(0).getSeqDigits(), "0"));
					}			
					
				}
			}			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
		return stringResultGenerator;
	}
	

}
