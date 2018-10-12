package com.cms.common.search.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.common.CommonDAO;
import com.cms.common.search.SearchEnum;

public class SearchUtil {

	public static void generateSearhResult(HttpServletRequest request, HttpServletResponse response, String query){
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("resultList", CommonDAO.generateResultList(null, query) );
		request.setAttribute(SearchEnum.RESULT_MAP.getKeyName(), resultMap);
	}
	
	
}
