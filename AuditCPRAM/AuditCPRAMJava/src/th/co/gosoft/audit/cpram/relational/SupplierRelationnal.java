package th.co.gosoft.audit.cpram.relational;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dao.AppointDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dao.SupplierAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class SupplierRelationnal {
	private final static Logger logger = Logger.getLogger(SupplierRelationnal.class);
	
	public static boolean checkSupplierRelational(Connection connection, SupplierDTO supplierDTO) {
		SupplierAddressMappingDAO supplierAddressMappingDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		AppointDAO appointDAO = null;
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		StringBuilder queryWhereClause = null;
		boolean checkHasMember = false;
		
		try {
			
			supplierAddressMappingDAO = new SupplierAddressMappingDAO(connection);
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			appointDAO = new AppointDAO(connection);
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			
			
			
			AddressDTO addressDTOsSupplier = supplierAddressMappingDAO.getAddresWithSupplierId(supplierDTO);
			if(addressDTOsSupplier != null) {
				if(addressDTOsSupplier.getAddressId() != 0) {
					int countAddressMapping = supplierAddressMappingDAO.countAddressId(addressDTOsSupplier);
					if(countAddressMapping > 1) {
						checkHasMember = true;
					}
				}
			}
			
			
			if(!checkHasMember) {
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND sup.supplier_id = '").append(supplierDTO.getSupplierId()).append("' ");			
				List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierListMappingProductType(0, supplierProductTypeAddressMappingDAO.countSupplierListMappingProductType(queryWhereClause.toString()), queryWhereClause.toString());
				if(supplierProductAddressMappingDTOs != null) {
					if(!supplierProductAddressMappingDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}
			
			
			if(!checkHasMember) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND a.supplier_id = '").append(DBConst.TABLE_Appoint).append("' ");
				List<AppointDTO> appointDTOs = appointDAO.getAppointList(0, appointDAO.countAppointList(queryWhereClause.toString()), queryWhereClause.toString());
				if(appointDTOs != null) {
					if(!appointDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}			
			
			
			if(!checkHasMember) {
				checkHasMember = supplierProductTypeMappingDAO.checkMemberSupplier(supplierDTO);
			}
			
			if(!checkHasMember) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND check_plan.supplier_id = '").append(supplierDTO.getSupplierId()).append("' ");
				List<ChecklistPlanDTO> checklistPlanDTOs = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString());
				if(checklistPlanDTOs != null) {
					if(!checklistPlanDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}
			
			if(!checkHasMember) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND s.supplier_id = '").append(supplierDTO.getSupplierId()).append("' ");
				List<UserDTO> userDTOs = supplierUserMappingDAO.getUserContractSupplier(0, supplierUserMappingDAO.countUserContractSupplier(queryWhereClause.toString()), queryWhereClause.toString());
				if(userDTOs != null) {
					if(!userDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}
			
			return checkHasMember;
			
		}catch (Exception e) {
			logger.error("SupplierRelationnal.checkSupplierRelational() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
}
