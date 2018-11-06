package com.cms.service.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.common.master.CmnGroupName;
import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;

public class ServiceCreationController {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		CommonMasterDO cmnMstDO =new CommonMasterDO();
		cmnMstDO.setCmnGroupId( CmnGroupName.SERVICE.getGroupId() );
		cmnMstDO.setParentId(0);
		cmnMstDO.setLevelNo(1);
		request.setAttribute("serviceDO", cmnMstDO);
	}
	
	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int serviceId=AppUtil.getNullToInteger( request.getParameter("serviceId")  );
		CommonMasterDO cmnMstDO =CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceId, false);
		request.setAttribute("serviceDO", cmnMstDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {

		CommonMasterDO serviceDO=constructDO(request, response);

		if(serviceDO.getCmnMasterId()==0) {
			//			insert
			int serviceId =CommonMasterDAO.insert(null, serviceDO);
			if(serviceId!=0) {
				System.out.println("Service Inderted...Id:"+serviceId);
				serviceDO=CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceId, false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Service Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Service..!");
			}
		}else {
			//			update
			if(CommonMasterDAO.update(null, serviceDO)) {
				System.out.println("Service Updated...Id:");
				serviceDO=CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceDO.getCmnMasterId(), false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Service Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Service..!");
			}
		}
		request.setAttribute("serviceDO", serviceDO);

	}

	private static CommonMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";
		CommonMasterDO cmnMstDO=new CommonMasterDO();

		cmnMstDO.setCmnMasterId( AppUtil.getNullToInteger( request.getParameter("serviceId") ) );
		cmnMstDO.setCmnGroupId( AppUtil.getNullToInteger( request.getParameter("groupId") ) );
		cmnMstDO.setParentId( AppUtil.getNullToInteger( request.getParameter("parentId") ) );
		cmnMstDO.setCmnMasterName( AppUtil.getNullToEmpty( request.getParameter("serviceName") ) );
		cmnMstDO.setLevelNo( AppUtil.getNullToInteger( request.getParameter("levelNo") )  );
		cmnMstDO.setCreatedUser(loginId);
		cmnMstDO.setUpdateUser(loginId);

		return cmnMstDO;
	}

	public static String serviceOption( String parentIds, String selServiceIds ) {
		parentIds=AppUtil.getNullToEmpty(parentIds, "0");
		String subQry=" AND cmn_group_id="+CmnGroupName.SERVICE.getGroupId() +" AND parent_id=0 AND bool_delete_status=0 ";
		if(!parentIds.isEmpty() && !parentIds.equals("0") ) { subQry+=" AND parent_id in("+parentIds+")"; }
		Map<String, String> map=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		
		return AppUtil.formOption(map, selServiceIds);
	}
	public static String customerserviceOption( String parentIds, String selServiceIds, int salesId ) {
		parentIds=AppUtil.getNullToEmpty(parentIds, "0");
		String subQry=" AND cmn_group_id="+CmnGroupName.SERVICE.getGroupId() +" AND parent_id=0 AND bool_delete_status=0 ";
		if(!parentIds.isEmpty() && !parentIds.equals("0") ) { subQry+=" AND parent_id in("+parentIds+")"; }
		
		subQry+=" AND cmn_master_id IN( " + 
				"	SELECT  parent_id FROM sales_customer_package_details a, common_master b " + 
				"	WHERE a.package_id=b.cmn_master_id AND a.bool_delete_status=0 AND sales_id=" +salesId +
				")";
		
		Map<String, String> map=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		
		return AppUtil.formOption(map, selServiceIds);
	}
	
	public static void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String loginId="Admin";
		int serviceId=AppUtil.getNullToInteger( request.getParameter("serviceId")  );
		CommonMasterDO cmnMstDO =new CommonMasterDO();
		cmnMstDO.setBoolDeleteStatus(true);
		cmnMstDO.setUpdateUser(loginId);
		cmnMstDO.setCmnMasterId(serviceId);
		
		AjaxModel model=new AjaxModel();
		if(CommonMasterDAO.deleteupdate(null, cmnMstDO)) {
			model.setMessage(" Service Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete Service");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);
		
	}
}
