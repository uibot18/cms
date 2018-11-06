package com.cms.common.test;

import com.application.util.AppUtil;
import com.cms.task.dao.TaskProcessMasterDAO;

public class Test{
	public static void main(String[] args)  {
//		DevUtil.generateDOAndDAO(DBConnection.getConnection(), "task_process_child");
		
		System.out.println(TaskProcessMasterDAO.getTaskProcessMasterByProcessMasterId(null, 6, true));
		
	}
	
	
	
}
class AA implements Cloneable{
	public int a;
	
}