package com.ldap;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class TestLDAP {

	public static void main(String[] args) throws Exception {
		
		LdapContext context	= null;
		String host = "ds.cisco.com";
		String port = "389";

		String bindDn = "CN=ecmadsync.gen,OU=Generics,OU=Cisco Users,DC=cisco,DC=com";
		String bindPwd = "ecmadsync_gen";
				
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

		// set security , note using simple clear text
		// authentication
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		// env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_PRINCIPAL, bindDn);
		env.put(Context.SECURITY_CREDENTIALS, bindPwd);

		env.put("java.naming.ldap.attributes.binary", "objectGUID");

		// connect to my domain controller
		env.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port.trim());

		context	= new InitialLdapContext(env, null);
		
		String searchBase 				= "OU=Mailer,OU=Cisco Groups,DC=cisco,DC=com";
		String userSpecificSearchFilter	= "(&(objectClass=*)(cn=people-manager))";
		
		int counter			= 1;
		boolean endString 	= true;
		int loopValue 		= 0;
		while(endString)
		{
		
			int startValue 	= loopValue * 1000;
			int endvalue 	= (loopValue + 1) * 1000;
			
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String range = "member;range=" + startValue+"-"+endvalue;
	
			String[] returnedAttrs = new String[]{range};
			
			searchCtls.setReturningAttributes(returnedAttrs);
			
			NamingEnumeration<SearchResult> results = context.search(searchBase, userSpecificSearchFilter, searchCtls);
	
			if(results != null && results.hasMoreElements()){
	
					SearchResult sr = (SearchResult) results.next();
	
					//System.out.println(sr.getName());
					
					Attributes attributes = sr.getAttributes(); 
					//System.out.println(attributes);
					
					
					if(sr.getAttributes().toString().contains("{member;range="+startValue+"-*")){
						endString = false;
						range	= "member;range="+startValue+"-*";
					}
					
					Attribute membersAttr = attributes.get(range);
					Enumeration val 		= membersAttr.getAll();
					//System.out.println("val"   +val);
					
					while (val.hasMoreElements()){
						
						String value = val.nextElement().toString();
						System.out.println(counter + " : " + value);
						counter++;
					}					
					
			}
			
			loopValue++;
			
		}
	}
	
}
