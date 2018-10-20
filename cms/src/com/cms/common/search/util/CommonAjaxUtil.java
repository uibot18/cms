package com.cms.common.search.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.application.util.AppUtil;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;

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
		response.setContentType("application/json");
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
		System.err.println(result_json.toString());
		out.write(result_json.toString());
		out.close();
		}catch(Exception e) {
			
		}
	}
}
