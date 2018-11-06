package com.cms.holiday.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.holiday.bean.AdminHolidayDetailsDO;
import com.cms.holiday.dao.AdminHolidayDetailsDAO;

public class HolidayCreationController {

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {
		
		int holidayId=AppUtil.getNullToInteger( request.getParameter("holidayId")  );
		AdminHolidayDetailsDO holidayDO=AdminHolidayDetailsDAO.getAdminHolidayDetailsByHolidayId(null, holidayId, false);
		request.setAttribute("holidayDO", holidayDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {
		
		AdminHolidayDetailsDO holidayDO=constructDO(request, response);
		
		if(holidayDO.getHolidayId()==0) {
//			insert
			int holidayeId =AdminHolidayDetailsDAO.insert(null, holidayDO);
			if(holidayeId!=0) {
				System.out.println("Holiday Inderted...Id:"+holidayeId);
				holidayDO=AdminHolidayDetailsDAO.getAdminHolidayDetailsByHolidayId(null, holidayeId, false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Holiday Successfully Saved..!");
			}else {
				System.out.println("Failed to indert holiday..!");
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Holiday..!");
			}
		}else {
//			update
			if(AdminHolidayDetailsDAO.update(null, holidayDO)) {
				System.out.println("Holiday Updated...Id:");
				holidayDO=AdminHolidayDetailsDAO.getAdminHolidayDetailsByHolidayId(null, holidayDO.getHolidayId(), false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Holiday Successfully Saved..!");
			}else {
				System.out.println("Failed to Update holiday..");
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Holiday..!");
			}
		}
		request.setAttribute("holidayDO", holidayDO);
		
	}

	private static AdminHolidayDetailsDO constructDO(HttpServletRequest request, HttpServletResponse response) {
		
		String loginId="Admin";
		AdminHolidayDetailsDO holidayDO=new AdminHolidayDetailsDO();
		
		holidayDO.setHolidayId( AppUtil.getNullToInteger( request.getParameter("holidayId") ) );
		holidayDO.setHolidayName( AppUtil.getNullToEmpty( request.getParameter("holidayName") ) );
		holidayDO.setHolidaySubType( AppUtil.getNullToEmpty( request.getParameter("holidaySubType") ) );
		holidayDO.setHolidayTypeId( AppUtil.getNullToInteger( request.getParameter("holidayType") ) );
		holidayDO.setHoliday( AppUtil.getNullToEmpty( request.getParameter("holidayDate") ) );
		holidayDO.setCreatedUser(loginId);
		holidayDO.setUpdateUser(loginId);
		return holidayDO;
	}
	public static void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String loginId="Admin";
		int holidayId=AppUtil.getNullToInteger( request.getParameter("holidayId")  );
		AdminHolidayDetailsDO holidayDO=new AdminHolidayDetailsDO();
		holidayDO.setBoolDeleteStatus(true);
		holidayDO.setUpdateUser(loginId);
		holidayDO.setHolidayId(holidayId);
		
		AjaxModel model=new AjaxModel();
		if(AdminHolidayDetailsDAO.deleteupdate(null, holidayDO)) {
			model.setMessage(" Holiday Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete Holiday");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
	}
	
}
