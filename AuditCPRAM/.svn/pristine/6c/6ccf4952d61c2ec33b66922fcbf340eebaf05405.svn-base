package th.co.gosoft.audit.cpram.relational;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dao.AppointDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class SupplierProductAddressMappingRelation {
	
	private final static Logger logger = Logger.getLogger(SupplierProductAddressMappingRelation.class);
	
	public static boolean checkSupplierProductAddressMappingRelation(Connection connection, SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		AppointDAO appointDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		StringBuilder queryWhereClause = null;
		boolean checkHasMember = false;
		try {
			
			appointDAO = new AppointDAO(connection);
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			queryWhereClause = new StringBuilder();
			
			queryWhereClause.append(" AND a.location_id = '").append(supplierProductAddressMappingDTO.getId()).append("' ");
			List<AppointDTO> appointDTOs = appointDAO.getAppointList(0, appointDAO.countAppointList(queryWhereClause.toString()), queryWhereClause.toString());
			if(appointDTOs != null) {
				if(!appointDTOs.isEmpty()) {
					checkHasMember = true;
				}
			}
			
			if(!checkHasMember) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND check_plan.location_id = '").append(supplierProductAddressMappingDTO.getId()).append("' ");
				List<ChecklistPlanDTO> checklistPlanDTOs = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString());
				if(checklistPlanDTOs != null) {
					if(!checklistPlanDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}
			
			return checkHasMember;
		}catch (Exception e) {
			logger.error("SupplierProductAddressMappingRelation.checkSupplierProductAddressMappingRelation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
}
