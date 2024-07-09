package th.co.gosoft.audit.cpram.relational;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;


import th.co.gosoft.audit.cpram.dao.ChecklistDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeMappingDAO;
import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class ProductTypeRelational {	
	private final static Logger logger = Logger.getLogger(ProductTypeRelational.class);
	
	public static boolean checkProductTypeRelation(Connection connection, ProductTypeDTO productTypeDTO) {
		ChecklistDAO checklistDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		boolean checkHasMember = false;
		try {
			
			checklistDAO = new ChecklistDAO(connection);
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);			
			StringBuilder queryWhereClause = new StringBuilder();
			
			queryWhereClause.append(" AND pt.product_type_id = '").append(productTypeDTO.getProductTypeId()).append("' ");
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistList(0, checklistDAO.countChecklistList(queryWhereClause.toString()), queryWhereClause.toString());
			if(checklistDTOs != null) {
				if(!checklistDTOs.isEmpty()) {
					checkHasMember = true;
				}				
			}
			
			if(!checkHasMember) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND check_plan.product_type_id = '").append(productTypeDTO.getProductTypeId()).append("' ");
				List<ChecklistPlanDTO> checklistPlanDTOs = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString());
				if(checklistPlanDTOs != null ) {
					if(!checklistPlanDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}
			
			if(!checkHasMember) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND sup_map.product_type_id = '").append(productTypeDTO.getProductTypeId()).append("' ");
				List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierListMappingProductType(0, supplierProductTypeAddressMappingDAO.countSupplierListMappingProductType(queryWhereClause.toString()), queryWhereClause.toString());
				if(supplierProductAddressMappingDTOs != null) {
					if(!supplierProductAddressMappingDTOs.isEmpty()) {
						checkHasMember = true;
					}
				}
			}
			
			if(!checkHasMember) {
				checkHasMember = supplierProductTypeMappingDAO.checkMemberProductType(productTypeDTO);
			}
			
			return checkHasMember;
			
		}catch (Exception e) {
			logger.error("ProductTypeRelational.checkProductTypeRelation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
}
