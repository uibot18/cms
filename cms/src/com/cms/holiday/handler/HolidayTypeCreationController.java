package com.cms.holiday.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.holiday.bean.AdminHolidayTypeDO;
import com.cms.holiday.dao.AdminHolidayTypeDAO;

public class HolidayTypeCreationController {

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {
		
		int holidayTypeId=AppUtil.getNullToInteger( request.getParameter("holidayTypeId")  );
		AdminHolidayTypeDO holidayTypeDO=AdminHolidayTypeDAO.getAdminHolidayTypeByHolidayTypeId(null, holidayTypeId, false);
		request.setAttribute("holidayTypeDO", holidayTypeDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {
		
		AdminHolidayTypeDO holidayTypeDO=constructDO(request, response);
		
		if(holidayTypeDO.getHolidayTypeId()==0) {
//			insert
			int holidayTypeId =AdminHolidayTypeDAO.insert(null, holidayTypeDO);
			if(holidayTypeId!=0) {
				holidayTypeDO=AdminHolidayTypeDAO.getAdminHolidayTypeByHolidayTypeId(null, holidayTypeId, false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Holiday Type Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Holiday Type..!");
			}
		}else {
//			update
			if(AdminHolidayTypeDAO.update(null, holidayTypeDO)) {
				holidayTypeDO=AdminHolidayTypeDAO.getAdminHolidayTypeByHolidayTypeId(null, holidayTypeDO.getHolidayTypeId(), false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Holiday Type Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Holiday Type..!");
			}
		}
		request.setAttribute("holidayTypeDO", holidayTypeDO);
		
	}

	private static AdminHolidayTypeDO constructDO(HttpServletRequest request, HttpServletResponse response) {
		
		String loginId="Admin";
		AdminHolidayTypeDO holidayTypeDO=new AdminHolidayTypeDO();
		
		holidayTypeDO.setHolidayTypeId( AppUtil.getNullToInteger( request.getParameter("holidayTypeId") ) );
		holidayTypeDO.setHolidayType( AppUtil.getNullToEmpty( request.getParameter("holidayType") ) );
		holidayTypeDO.setCreatedUser(loginId);
		holidayTypeDO.setUpdateUser(loginId);
		return holidayTypeDO;
	}
	
	public static String formHolidayTypeOption( String holidayTypeIds ) {
		holidayTypeIds=AppUtil.getNullToEmpty(holidayTypeIds);
		Map<String, String> holidayTypeMap=AdminHolidayTypeDAO .getHolidayTypeMap(null);
		return AppUtil.formOption(holidayTypeMap, holidayTypeIds);
	}
	
}
