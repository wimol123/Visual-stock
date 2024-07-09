package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarStatusDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class CarDAO extends StandardAttributeDAO{
	
	private final static Logger logger = Logger.getLogger(CarDAO.class);	

	public CarDAO(Connection connection) {
		super(connection);
	}
	
	public List<CarDTO> getCarList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT c.car_id, c.car_no, check_plan.checklist_plan_id, check_plan.checklist_title, check_plan.checklist_plan_no, check_plan.plan_date, check_plan.supplier_company, c.due_date, car_state.car_status_id, car_state.car_status_name, car_state.status_color
			FROM car c 
			LEFT JOIN car_status car_state ON c.car_status_id = car_state.car_status_id
			LEFT JOIN checklist_plan check_plan ON c.checklist_plan_id = check_plan.checklist_plan_id
			WHERE 1=1 AND c.enable = 'Y'*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT c.car_id, c.car_no, c.enable, check_plan.checklist_plan_id, check_plan.checklist_title, check_plan.checklist_plan_no, check_plan.plan_date, check_plan.supplier_company, c.due_date, car_state.car_status_id, car_state.car_status_name, car_state.status_color ");
			query.append("FROM ").append(DBConst.TABLE_Car).append(" c ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Car_Status).append(" car_state ").append(" ON c.car_status_id = car_state.car_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ").append(" ON c.checklist_plan_id = check_plan.checklist_plan_id ");
			query.append("WHERE 1=1 AND c.enable = 'Y'");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by c.car_no asc limit %s,%s", startRecord,numOfRows));
			}
			
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<CarDTO> carDTOs = new ArrayList<>();
			
			while(resultSet.next()) {
				CarDTO carDTO = new CarDTO();
				carDTO.setCarId(resultSet.getInt("car_id"));
				carDTO.setCarNo(resultSet.getString("car_no"));
				carDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				
				carDTO.setChecklistPlanId(new ChecklistPlanDTO());
				carDTO.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				carDTO.getChecklistPlanId().setChecklistTitle(resultSet.getString("checklist_title"));
				carDTO.getChecklistPlanId().setChecklistPlanNo(resultSet.getString("checklist_plan_no"));
				carDTO.getChecklistPlanId().setPlanDate(resultSet.getDate("plan_date"));
				carDTO.getChecklistPlanId().setPlanTime(resultSet.getTime("plan_date"));
				carDTO.getChecklistPlanId().setSupplierCompany(resultSet.getString("supplier_company"));
				carDTO.setDueDate(resultSet.getDate("due_date"));
				carDTO.setDueTime(resultSet.getTime("due_date"));
				
				carDTO.setCarStatusId(new CarStatusDTO());
				carDTO.getCarStatusId().setCarStatusId(resultSet.getInt("car_status_id"));
				carDTO.getCarStatusId().setCarStatusName(resultSet.getString("car_status_name"));
				carDTO.getCarStatusId().setStatusColor(resultSet.getString("status_color"));
				carDTOs.add(carDTO);
			}
			
			return carDTOs;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public boolean updateCar(CarDTO carDTO) {
		try {
			
			//UPDATE `car` SET `car_status_id` = '2', `enable` = 'N', `update_by` = '2', `update_date` = 'now()' WHERE (`car_id` = '2');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Car).append(" SET ");
			query.append("car_status_id = ?, enable = ?, update_by = ?, update_date = now() ");
			query.append("WHERE (car_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, NullUtils.cvStr(carDTO.getCarStatusId().getCarStatusId()));
			preparedStatement.setString(index++, NullUtils.cvStr(carDTO.getEnable()));
			preparedStatement.setInt(index++, carDTO.getUpdateBy());
			preparedStatement.setInt(index++, carDTO.getCarId());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public int countCarList(String queryWhereClause) {
		int totalCar = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Car).append(" c ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Car_Status).append(" car_state ").append(" ON c.car_status_id = car_state.car_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ").append(" ON c.checklist_plan_id = check_plan.checklist_plan_id ");
			query.append("WHERE 1=1 AND c.enable = 'Y' ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalCar = resultSet.getInt("total");
			}
			return totalCar;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalCar;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
