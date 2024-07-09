package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class MenuDTO extends StandardAttributeDTO {
  
	private int menuId;
	private int menuParentId;
	private String menuName;
	private String menuUrl;
	private String menuIcon;
	private int menuOrder;
	private int menuSubOrder;
	private int coutSubMenu;
	private List<UserGroupDTO> userGroupId;
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(int menuParentId) {
		this.menuParentId = menuParentId;
	}
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public int getMenuSubOrder() {
		return menuSubOrder;
	}
	public void setMenuSubOrder(int menuSubOrder) {
		this.menuSubOrder = menuSubOrder;
	}
	public int getCoutSubMenu() {
		return coutSubMenu;
	}
	public void setCoutSubMenu(int coutSubMenu) {
		this.coutSubMenu = coutSubMenu;
	}
	public List<UserGroupDTO> getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(List<UserGroupDTO> userGroupId) {
		this.userGroupId = userGroupId;
	}

}
