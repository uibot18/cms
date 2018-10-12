package com.cms.common.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
	
	public static void close(Object...objects) {
		try {
			for (Object object : objects) {
				if(object!=null) {
					if(object.getClass().getSimpleName().equals("Connection")) { ((Connection) object).close(); }
					else if(object.getClass().getSimpleName().equals("PreparedStatement")) { ((PreparedStatement) object).close(); }
					else if(object.getClass().getSimpleName().equals("Statement")) { ((Statement) object).close(); }
					else if(object.getClass().getSimpleName().equals("Statement")) { ((Statement) object).close(); }
					else if(object.getClass().getSimpleName().equals("ResultSet")) { ((ResultSet) object).close(); }
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
}
