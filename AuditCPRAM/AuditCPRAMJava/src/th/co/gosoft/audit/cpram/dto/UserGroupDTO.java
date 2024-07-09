package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class UserGroupDTO extends StandardAttributeDTO{
	private int userGroupId;
	private String userGroupName; 
	private String authenType;
	private List<UserDTO> userId;
	private List<MenuDTO> userGroupMenuId;
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getAuthenType() {
		return authenType;
	}
	public void setAuthenType(String authenType) {
		this.authenType = authenType;
	}
	public List<UserDTO> getUserId() {
		return userId;
	}
	public void setUserId(List<UserDTO> userId) {
		this.userId = userId;
	}
	public List<MenuDTO> getUserGroupMenuId() {
		return userGroupMenuId;
	}
	public void setUserGroupMenuId(List<MenuDTO> userGroupMenuId) {
		this.userGroupMenuId = userGroupMenuId;
	}

}
