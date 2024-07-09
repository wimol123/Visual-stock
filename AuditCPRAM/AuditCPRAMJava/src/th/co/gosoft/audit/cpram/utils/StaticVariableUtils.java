package th.co.gosoft.audit.cpram.utils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;

public class StaticVariableUtils {
	
	public static final String Session_Key = "sessionAuthen";
	
	public static final String timeOfEffective = "00:00:01";
	
	public static final String timeOfExpire = "23:59:00";
	
	public static final String evalTypeTopic = "2";
	
	public static final String evalTypeSubTopic = "3";
	
	public static final String evalTypeQuestionGroup = "4";
	
	public static final String evalTypeQuestion = "5";
	
	public static final String sequenceKeyChecklistPlan = ConfigurationSystemManager.getInstance().getSequenceChecklistPlanKey();
	
	public static final String sequenceKeyCAR = ConfigurationSystemManager.getInstance().getSequenceCarKey();
	
}
