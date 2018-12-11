package com.cms.common.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.TreeSet;

import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;

public class Test{
	public static void main(String[] args)  {
	//DevUtil.generateDOAndDAO(DBConnection.getConnection(), "rights_template");
		TreeSet< String> set= new TreeSet<>();
		set.add("AA");
		System.out.println("FF: "+set.first());
		System.out.println("LL: "+set.last());
		//String [] arr=null;
		
		//System.out.println("Length: "+arr.length);
	}
	
	
private static void getData(Integer i) {
	i++;
	System.out.println("i:ssss"+i);
}
	
	
	
	
	
	public static boolean gwnerateTask(Connection preCon, int taskProcessMasterId ) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement("CALL task_config_det_by_id("+taskProcessMasterId+",0,0,0,0,0,1,@a);");
			int a =stmt.executeUpdate();
			
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return false;
	}
	
	
	
	
}
class AA implements Cloneable{
	public int a;
	
}