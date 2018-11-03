package com.cms.booking.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.booking.bean.SalesCustomerBookingFormDO;
import com.cms.booking.bean.SalesCustomerPackageDetailsDO;
import com.cms.booking.dao.SalesCustomerBookingFormDAO;
import com.cms.cms_package.handler.PackageCreationController;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;
import com.cms.service.handler.ServiceCreationController;
import com.cms.task.config.dao.TaskConfigMasterDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class SalesCustomerBookingCreationHandler {

	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {
		
		int saleId=AppUtil.getNullToInteger( request.getParameter("saleId")  );
		SalesCustomerBookingFormDO bookingDO=SalesCustomerBookingFormDAO.getSalesCustomerBookingFormBySaleId(null, saleId, true);
		request.setAttribute("bookingDO", bookingDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {
		
		SalesCustomerBookingFormDO bookingDO=constructDO(request, response);
		
		if(bookingDO.getSaleId()==0) {
//			insert
			int saleId =SalesCustomerBookingFormDAO.insert(null, bookingDO);
			if(saleId!=0) {
				bookingDO=SalesCustomerBookingFormDAO.getSalesCustomerBookingFormBySaleId(null, saleId, true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Booking Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Booking..!");
			}
		}else {
//			update
			if(SalesCustomerBookingFormDAO.update(null, bookingDO)) {
				bookingDO=SalesCustomerBookingFormDAO.getSalesCustomerBookingFormBySaleId(null, bookingDO.getSaleId(), true);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Booking Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Booking..!");
			}
		}
		request.setAttribute("bookingDO", bookingDO);
		
	}

	private static SalesCustomerBookingFormDO constructDO(HttpServletRequest request, HttpServletResponse response) {
		
		LoginDetail loginDetail =LoginUtil.getLoginDetail(request);
		String loginId=loginDetail.getLoginId();
		SalesCustomerBookingFormDO bookingDO=new SalesCustomerBookingFormDO();
		
		bookingDO.setSaleId( AppUtil.getNullToInteger( request.getParameter("saleId")) );
		bookingDO.setCustomerId( AppUtil.getNullToInteger( request.getParameter("customerName")) );
		bookingDO.setSaleDate( AppUtil.getNullToEmpty( request.getParameter("bookingDate"), "01/01/1000") );
		
		String[] rowArr =request.getParameterValues("childRows");
		List<SalesCustomerPackageDetailsDO> packageList=new ArrayList<SalesCustomerPackageDetailsDO>();
		for (int i = 0; i < rowArr.length; i++) {
			String sno=rowArr[i];
			SalesCustomerPackageDetailsDO packageDO=new SalesCustomerPackageDetailsDO();
			packageDO.setPackageId( AppUtil.getNullToInteger( request.getParameter("packageName_"+sno)));
			packageDO.setProcessStartsFrom( AppUtil.getNullToEmpty(request.getParameter("wef_"+sno)) );
			packageDO.setProcessEndsOn( AppUtil.getNullToEmpty(request.getParameter("endsOn_"+sno)) );
			packageDO.setBoolOveride( Boolean.parseBoolean( AppUtil.getNullToEmpty(request.getParameter("overide_"+sno), "false")));
			packageDO.setCreatedUser(loginId);
			packageDO.setUpdateUser(loginId);
			packageList.add( packageDO );
		}
		
		bookingDO.setCustomerPackageList(packageList);
		
		bookingDO.setCreatedUser(loginId);
		bookingDO.setUpdateUser(loginId);
		return bookingDO;
	}
	
	public static String generatePackageTable(HttpServletRequest request, SalesCustomerBookingFormDO mstDO) {
		
		StringBuffer table=new StringBuffer();
		
		List<SalesCustomerPackageDetailsDO> packageList=mstDO.getCustomerPackageList();
		if(packageList==null) { packageList=new ArrayList<SalesCustomerPackageDetailsDO>(); }
		if(packageList.size()==0) {
			packageList.add(new SalesCustomerPackageDetailsDO());
		}
		int sno=1;
		for (SalesCustomerPackageDetailsDO childDO : packageList) {
			table.append( generatePackageRow(request, sno, childDO) );
			sno++;
		}
		return table.toString();
	}
	
	private static String generatePackageRow(HttpServletRequest request, int sno, SalesCustomerPackageDetailsDO childDO ) {
		if(childDO==null) { childDO=new SalesCustomerPackageDetailsDO(); }
		
		CommonMasterDO packageDO =CommonMasterDAO.getCommonMasterByCmnMasterId(null, childDO.getPackageId(), false);
		if(packageDO==null) { packageDO=new CommonMasterDO(); }
		StringBuffer row=new StringBuffer();
		row.append("<tr id='row_"+sno+"'>");
		row.append("<td><span class='sno'>"+sno+"</span><input name='childRows' type='hidden' value='"+sno+"'></td>");
		
		row.append("<td><div class='form-group'>");
		row.append("<select id='serviceName_"+sno+"' class='form-control input-sm serviceName' placeholder='Service Name' name='serviceName_"+sno+"'>");
		row.append("<option>-- please Select --</option>"+ServiceCreationController.serviceOption("", ""+packageDO.getParentId()));
		row.append("</select>");
		row.append("</div></td>");
		
		row.append("<td><div class='form-group'>");
		row.append("<select id='packageName_"+sno+"' class='form-control input-sm packageName' placeholder='Package Name' name='packageName_"+sno+"'>");
		row.append("<option>-- please Select --</option>"+PackageCreationController.packageOption(""+packageDO.getParentId(), ""+packageDO.getCmnMasterId()));
		row.append("</select>");
		row.append("</div></td>");
		
		row.append("<td><div class='form-group'>");
		row.append("<input type='text' id='wef_"+sno+"' class='form-control input-sm wef' placeholder='W.E.F' name='wef_"+sno+"' value='"+childDO.getProcessStartsFrom()+"' required='required'>");
		row.append("</div></td>");
		
		row.append("<td><div class='form-group'>");
		row.append("<input type='text' id='endsOn_"+sno+"' class='form-control input-sm endsOn' placeholder='Booking Date' name='endsOn_"+sno+"' value='"+childDO.getProcessEndsOn()+"' required='required'>");
		row.append("</div></td>	");
		
		row.append("<td><div class='form-check'>");
		row.append("<input type='checkbox' name='overide_"+sno+"' class='form-check-input overide' value='true' id='overide_"+sno+"' "+(childDO.isBoolOveride()?"checked":"")+">");
		row.append("<label class='form-check-label' for='overide_"+sno+"'>Overide</label>");
		row.append("</div></td>");
		
		row.append("<td><span style='cursor:pointer;' id='del_row_"+sno+"' class='del_row'>Delete</span></td>");
		
		row.append("</tr>");
		
		return row.toString();
		
	}

	public static void doPackageLoad(HttpServletRequest request, HttpServletResponse response) {
		int sno=AppUtil.getNullToInteger(request.getParameter("sno"));
		AjaxModel model=new AjaxModel();
		model.setData( generatePackageRow(request, sno, new SalesCustomerPackageDetailsDO()));
		AjaxUtil.sendResponse(request, response, model);
	}
	
	public static void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String loginId="Admin";
		int saleId=AppUtil.getNullToInteger( request.getParameter("saleId")  );
		SalesCustomerBookingFormDO bookingDO=new SalesCustomerBookingFormDO(); 
		bookingDO.setCreatedUser(loginId);
		bookingDO.setBoolDeleteStatus(true);
		bookingDO.setSaleId(saleId);
		AjaxModel model=new AjaxModel();
		
		if(SalesCustomerBookingFormDAO.deleteupdate(null, bookingDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");
			model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
	}
}
