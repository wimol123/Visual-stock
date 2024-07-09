package th.co.gosoft.audit.cpram.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.model.UserModel;

public class SessionUtils {
	
	private HttpServletRequest _httpServletRequest;
	private HttpSession _session;
	
	public SessionUtils(HttpServletRequest httpServletRequest) {
		this._httpServletRequest = httpServletRequest;
	}
	
	public void setSession(String key, Object object) {
		try {
			_session = _httpServletRequest.getSession(true);
			_session.setAttribute(key, object);
		}catch (Exception e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	public <T> T getObjectSession(String attribute, Class<T> type){
		if(!StringUtils.isNullOrEmpty(attribute)) {
			try {		
				if(type == UserDTO.class) {
					UserModel userModel = (UserModel)_httpServletRequest.getSession().getAttribute(attribute);
					return type.cast(TransformModel.transUserModel(userModel));					
				}else {
					return type.cast(_httpServletRequest.getSession().getAttribute(attribute));
				}			
				
			}catch(Exception e) {				
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	
	public int getUserIdSession(String attribute) {
		int _userIdSession = 0;
		if(!StringUtils.isNullOrEmpty(attribute)) {
			try {
				
				UserModel userModel = getObjectSession(attribute, UserModel.class);
				if(userModel != null) {
					
					_userIdSession = Integer.parseInt(userModel.getUserId().toString().trim());
					return _userIdSession;					
				}				
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		return _userIdSession;
	}
	
}
