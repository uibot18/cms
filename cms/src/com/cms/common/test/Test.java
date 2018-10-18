package com.cms.common.test;

import com.cms.common.db.connection.DBConnection;

public class Test{
	public static void main(String[] args)  {
		DevUtil.generateDOAndDAO(DBConnection.getConnection(), "task_questionaire_details");
		DevUtil.generateDOAndDAO(DBConnection.getConnection(), "task_questionaire_child");

		
	}
}