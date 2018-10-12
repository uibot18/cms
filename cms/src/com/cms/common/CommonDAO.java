package com.cms.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;

public class CommonDAO {

	public static List<Map<String, Object>> generateResultList(Connection preCon, String query) {

		List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		System.out.println("query:"+query);
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			ResultSetMetaData rsm=rs.getMetaData();
			int counumnCount=rsm.getColumnCount();

			while(rs.next()) { 
				resultList.add( constructDataMap(rs, counumnCount ) );
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return resultList;

	}

	private static Map<String, Object> constructDataMap( ResultSet rs, int colunmnCount ) {

		Map<String, Object> dataMap=new HashMap<String, Object>();
		try {
			for (int i = 1; i <=colunmnCount; i++) {
				dataMap.put("COL#"+i, ""+rs.getString(i) );
			}
		} catch (Exception e) { e.printStackTrace(); }
		return dataMap;
	}

}
