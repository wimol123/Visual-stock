package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.internal.StringUtil;

import com.mysql.jdbc.Statement;


import th.co.gosoft.audit.cpram.dto.MaterialDTO;
import th.co.gosoft.audit.cpram.dto.PoAcceptedDTO;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;


public class MaterialDAO extends StandardAttributeDAO {
	MaterialDTO a = new MaterialDTO();
	public MaterialDAO(Connection connection) {
		super(connection);
	}
	
	private final static Logger logger = Logger.getLogger(MaterialDAO.class);
	
	public List<MaterialDTO> getMaterialList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select * from material");
			
			logger.info("MaterialDAO.getMaterialList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<MaterialDTO> materialList = new ArrayList<>();
			while(resultSet.next()) {
				MaterialDTO material = new MaterialDTO();
				material.setMaterialCode(resultSet.getString("material_code"));
				material.setMaterialName(resultSet.getString("material_name"));
				materialList.add(material);
			}
			
			return materialList;
		} catch (SQLException e) {
			logger.error("Material.getMaterialList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("Material.getMaterialList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
}
