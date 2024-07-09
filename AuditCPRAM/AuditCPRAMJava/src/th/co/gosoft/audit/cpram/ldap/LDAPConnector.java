package th.co.gosoft.audit.cpram.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;

public class LDAPConnector {

	public static String NAME = "name";
    public static String DEPARTMENT = "department";
    public static String COMPANY = "company";
    public static String MAIL = "mail";
    public static String MEMBEROF = "memberOf";
    public static String NameThai = "description";
    public static String ID = "employeeID";
    private static String returnedAtts[] = {NAME, DEPARTMENT, COMPANY, MAIL, MEMBEROF, NameThai, ID};
    
    //private static String searchBase = "dc=7eleven,dc=cp,dc=co,dc=th";
//    private static String searchBase = "dc=cpram,dc=cp,dc=co,dc=th";
    
    public LDAPDataObject LdapConnection(String user, String password) throws Exception {

        DirContext ctx;
        javax.naming.directory.BasicAttributes bAttrs;
        Hashtable env;
        LDAPDataObject lData = null;

        // CP All
//        String conserver = "ldap://10.151.18.47/";
//        String domain = "7eleven.cp.co.th";
        
        // CPRAM
        //String conserver = "ldap://tarcpramad01.cpram.cp.co.th/";
        //String domain = "cpram.cp.co.th";
        //String searchBase = "dc=cpram,dc=cp,dc=co,dc=th";
        String conserver = ConfigurationSystemManager.getInstance().getLdapSever();
        String domain = ConfigurationSystemManager.getInstance().getLdapDomain();
        String searchBase = ConfigurationSystemManager.getInstance().getLdapSearchBase();
        
		//System.out.println("ldap properties : " + searchBase + " , " + conserver + " , " + domain);
        
        
        //Binding Parameter
        env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, conserver);

        env.put(Context.SECURITY_PRINCIPAL, user + "@" + domain); // displsy name in 2kserver for internal login        
        env.put(Context.SECURITY_CREDENTIALS, password); // password for internal login
         
        LdapContext ctxGC = null;
        try {
            //Bind to Ldap Server

            String searchFilter = "(sAMAccountName=" + user + ")";
            SearchControls searchCtls = new SearchControls();
            searchCtls.setReturningAttributes(returnedAtts);
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            
            ctxGC = new InitialLdapContext(env, null);
            NamingEnumeration answer = ctxGC.search(searchBase, searchFilter, searchCtls);
            
            SearchResult sr = (SearchResult) answer.next();
            Attributes attrs = sr.getAttributes();
            
            String key = "";
            Object value = "";
            if (attrs != null) {
                lData = new LDAPDataObject();
                NamingEnumeration ne = attrs.getAll();
                while (ne.hasMore()) {
                    Attribute attr = (Attribute) ne.next();
                    key = attr.getID();
                    if (LDAPDataObject.KEY_COMPANY.equalsIgnoreCase(key)) {
                        lData.setCompany(String.valueOf(attr.get()));
                    } else if (LDAPDataObject.KEY_DEPARTMENT.equalsIgnoreCase(key)) {
                        lData.setDepartment(String.valueOf(attr.get()));
                    } else if (LDAPDataObject.KEY_EMAIL.equalsIgnoreCase(key)) {
                        lData.setEmail(String.valueOf(attr.get()));
                    } else if (LDAPDataObject.KEY_EMPLOYEEID.equalsIgnoreCase(key)) {
//                         lData.setEmployeeID("0000081");
                        lData.setEmployeeID(String.valueOf(attr.get()));
                    } else if (LDAPDataObject.KEY_MEMBEROF.equalsIgnoreCase(key)) {
                        lData.setMemberOf(String.valueOf(attr.get()));
                    } else if (LDAPDataObject.KEY_NAME.equalsIgnoreCase(key)) {
                        lData.setName(String.valueOf(attr.get()));
                    } else if (LDAPDataObject.KEY_NAME_THAI.equalsIgnoreCase(key)) {
                        lData.setNameThai(String.valueOf(attr.get()));
                    }
                    //  System.out.println("key:" + key + " value:" + attr.get());
                }
                return lData;
            } else {
                return lData;
            }


        } catch (Exception ex) {
        	ex.printStackTrace();
            throw ex;
         
        }
    }
    
    public boolean checkUserExitsInLDAP(String username) throws Exception {
    	DirContext ctx;
        javax.naming.directory.BasicAttributes bAttrs;
        Hashtable env;
        LDAPDataObject lData = null;
        
        String conserver = ConfigurationSystemManager.getInstance().getLdapSever();
        String domain = ConfigurationSystemManager.getInstance().getLdapDomain();
        String searchBase = ConfigurationSystemManager.getInstance().getLdapSearchBase();
        String usernameLdap = ConfigurationSystemManager.getInstance().getLdapUsername();
        String passwordLdap = ConfigurationSystemManager.getInstance().getLdapPassword();
        
      //Binding Parameter
        env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, conserver);

        env.put(Context.SECURITY_PRINCIPAL, usernameLdap + "@" + domain); // displsy name in 2kserver for internal login        
        env.put(Context.SECURITY_CREDENTIALS, passwordLdap); // password for internal login
        
        LdapContext ctxGC = null;
        
        try {
        	
        	String searchFilter = "(sAMAccountName=" + username + ")";
            SearchControls searchCtls = new SearchControls();
            searchCtls.setReturningAttributes(returnedAtts);
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            
            ctxGC = new InitialLdapContext(env, null);
            NamingEnumeration answer = ctxGC.search(searchBase, searchFilter, searchCtls);
            return answer.hasMore();                       
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
            //throw ex;
        	return false;
        }
        
    }
}
