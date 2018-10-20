package com.cms.common.search.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.application.util.AppUtil;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.employee.dao.AdmEmployeeMasterDAO;

public class CommonAjaxUtil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommonAjaxUtil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=AppUtil.getNullToEmpty( request.getParameter("action") );
		System.out.println(action);
		
		if(action.equalsIgnoreCase("ajax")) {
			doajaxprocess(request,response);
		}
		
		
	}

	

	public static void doajaxprocess( HttpServletRequest request, HttpServletResponse response ) {


		String type=AppUtil.getNullToEmpty(request.getParameter("type"));
		int parent_id=AppUtil.getNullToInteger(request.getParameter("parentid"),0);
		
		String subqry="";
		try {
	/*	response.setContentType("application/json");*/
		PrintWriter out=response.getWriter();
		JSONObject result_json=new JSONObject();
		if(type.equalsIgnoreCase("cmn_master")) {
			
			if(!"0".equalsIgnoreCase(""+parent_id)) {
				subqry=" and parent_id in ("+parent_id+")";
				List<CommonMasterDO> common_master_list=CommonMasterDAO.getCommonMasterBySubqry(null,subqry,false);
				common_master_list=common_master_list==null?new ArrayList<CommonMasterDO>():common_master_list;
				JSONArray json_array=new JSONArray();
				
				
				for(CommonMasterDO master_bean: common_master_list) {
					JSONObject sub_json=new JSONObject();
					String id=AppUtil.getNullToEmpty(master_bean.getCmnMasterId()+"");
					String value=AppUtil.getNullToEmpty(master_bean.getCmnMasterName());
					sub_json.put("id", id);
					sub_json.put("value", value);
					json_array.put(sub_json);
				}
				result_json.put("result",json_array);
				
			}
		}
		else if(type.equalsIgnoreCase("employee")) {
			String department=AppUtil.getNullToEmpty(request.getParameter("department"), "0");
			String designation=AppUtil.getNullToEmpty(request.getParameter("designation"),"0");
			
			 subqry= " and a.department_id ="+department+" AND a.designation_id="+designation+" ";
			Map<String,String> emp_name_map=AdmEmployeeMasterDAO.EmpNameMapBySubry(null,subqry);
			if(emp_name_map==null) { emp_name_map=new HashMap<String, String>(); }
			result_json.put("option", AppUtil.getNullToEmpty( AppUtil.formOption(emp_name_map, "")));
			
		}
		System.err.println("res:"+result_json.toString());
		out.write(result_json.toString());
		out.flush();
		out.close();
		}catch(Exception e) {
			
		}
	}
	
	public static String commonmasteroptionbyparentId( String parentIds, String selServiceIds ) {
		String subQry="";
		if(!parentIds.isEmpty() && !parentIds.isEmpty() && !parentIds.equalsIgnoreCase("0")) { subQry=" AND parent_id in("+parentIds+")  AND bool_delete_status=0 "; }
		Map<String, String> map=new HashMap<String, String>();  
		if(!subQry.equalsIgnoreCase("")&&!parentIds.equalsIgnoreCase("0")) {map=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);}
		
		return AppUtil.formOption(map, selServiceIds);
	}
}
