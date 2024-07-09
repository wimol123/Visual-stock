package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.dto.MenuDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class MenuDAO extends StandardAttributeDAO{
	
	public MenuDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(MenuDAO.class);

	
	
	public List<MenuDTO> getMenuByGroupId(MenuDTO menuDTO){
		try {
			/*SELECT m.menu_id, m.menu_parent_id, m.user_group_id, m.menu_name, m.menu_url, m.menu_order, m.menu_icon, m.enable, (SELECT COUNT(*) from menu m2 WHERE m.menu_id=m2.menu_parent_id) count_sub_menu
			FROM user_group usr_group 
			LEFT JOIN user_group_menu_mapping usr_group_map ON usr_group.user_group_id = usr_group_map.user_group_id
			LEFT JOIN menu m ON usr_group_map.menu_id = m.menu_id
			WHERE m.enable='Y' and m.menu_parent_id=0 and usr_group.user_group_id=3
			ORDER BY m.menu_order;*/
			connection.setAutoCommit(false);
			/*(SELECT COUNT(*) from USER_GROUP_MENU m2 WHERE m.user_group_menu_id=m2.user_group_menu_parent_id)*/
			StringBuilder subQueryCountSubMenu = new StringBuilder();
			subQueryCountSubMenu.append("SELECT COUNT(*) FROM ").append(DBConst.TABLE_Menu).append(" m2 ");
			subQueryCountSubMenu.append("WHERE m.menu_id = m2.menu_parent_id AND m2.enable='Y' ");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT m.menu_id, m.menu_parent_id, usr_group.user_group_id, m.menu_name, m.menu_url, m.menu_order, m.menu_icon, m.enable, ");
			query.append("(").append(subQueryCountSubMenu.toString()).append(") count_sub_menu ");
			query.append("FROM ").append(DBConst.TABLE_User_Group).append(" usr_group ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User_Group_Menu_Mapping).append(" usr_group_map ");
			query.append("ON usr_group.user_group_id = usr_group_map.user_group_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Menu).append(" m ");
			query.append("ON usr_group_map.menu_id = m.menu_id ");
			query.append("WHERE m.enable='Y' and m.menu_parent_id=0 and usr_group.user_group_id = ? and usr_group_map.enable='Y' ");
			query.append("ORDER BY m.menu_order;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, menuDTO.getUserGroupId().get(0).getUserGroupId());
			resultSet = preparedStatement.executeQuery();
			
			List<MenuDTO> userGroupMenuDTOs = new ArrayList<>();
			while(resultSet.next()) {
				MenuDTO userGroupMenu = new MenuDTO();
				userGroupMenu.setMenuId(resultSet.getInt("menu_id"));
				userGroupMenu.setMenuParentId(resultSet.getInt("menu_parent_id"));
				userGroupMenu.setMenuName(resultSet.getString("menu_name"));
				userGroupMenu.setMenuUrl(resultSet.getString("menu_url"));
				userGroupMenu.setMenuOrder(resultSet.getInt("menu_order"));
				userGroupMenu.setMenuIcon(resultSet.getString("menu_icon"));
				userGroupMenu.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				userGroupMenu.setCoutSubMenu(resultSet.getInt("count_sub_menu"));
				
				UserGroupDTO userGroup = new UserGroupDTO();
				userGroup.setUserGroupId(resultSet.getInt("user_group_id"));
				userGroupMenu.setUserGroupId(new ArrayList<>());
				userGroupMenu.getUserGroupId().add(userGroup);
				
				userGroupMenuDTOs.add(userGroupMenu);
			}
			return userGroupMenuDTOs;
			
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
	
	public List<MenuDTO> getSubMenuByMenuId(MenuDTO menuDTO) {
		try {
			/*SELECT m.menu_id, m.menu_parent_id, usr_group.user_group_id, m.menu_name, m.menu_url, m.menu_order, m.enable
			FROM user_group usr_group 
			LEFT JOIN user_group_menu_mapping usr_group_map ON usr_group.user_group_id = usr_group_map.user_group_id
			LEFT JOIN menu m ON usr_group_map.menu_id = m.menu_id
			WHERE m.enable='Y' and m.menu_parent_id=7 and usr_group.user_group_id=1 ORDER BY m.menu_sub_order;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT m.menu_id, m.menu_parent_id, usr_group.user_group_id, m.menu_name, m.menu_url, m.menu_order, m.menu_icon, m.enable ");
			query.append("FROM ").append(DBConst.TABLE_User_Group).append(" usr_group ");			
			query.append("LEFT JOIN ").append(DBConst.TABLE_User_Group_Menu_Mapping).append(" usr_group_map ");
			query.append("ON usr_group.user_group_id = usr_group_map.user_group_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Menu).append(" m ");
			query.append("ON usr_group_map.menu_id = m.menu_id ");
			query.append("WHERE m.enable='Y' and m.menu_parent_id=? and usr_group.user_group_id=? ORDER BY m.menu_sub_order;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, menuDTO.getMenuId());
			preparedStatement.setInt(2, menuDTO.getUserGroupId().get(0).getUserGroupId());
			resultSet = preparedStatement.executeQuery();
			
			List<MenuDTO> userGroupMenuDTOs = new ArrayList<>();
			while(resultSet.next()) {
				MenuDTO userGroupMenu = new MenuDTO();
				userGroupMenu.setMenuId(resultSet.getInt("menu_id"));
				userGroupMenu.setMenuParentId(resultSet.getInt("menu_parent_id"));
				userGroupMenu.setMenuName(resultSet.getString("menu_name"));
				userGroupMenu.setMenuUrl(resultSet.getString("menu_url"));
				userGroupMenu.setMenuOrder(resultSet.getInt("menu_order"));
				userGroupMenu.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				userGroupMenu.setMenuIcon(resultSet.getString("menu_icon"));
				
				userGroupMenu.setUserGroupId(new ArrayList<>());
				UserGroupDTO userGroupId = new UserGroupDTO();
				userGroupId.setUserGroupId(resultSet.getInt("user_group_id"));
				userGroupMenu.getUserGroupId().add(userGroupId);
				
				userGroupMenuDTOs.add(userGroupMenu);
			}
			return userGroupMenuDTOs;
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
	
}
