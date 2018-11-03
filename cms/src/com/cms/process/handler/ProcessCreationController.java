package com.cms.process.handler;

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

public class ProcessCreationController {

	public static void doAdd(HttpServletRequest request, HttpServletResponse response) {
		CommonMasterDO cmnMstDO =new CommonMasterDO();
		cmnMstDO.setCmnGroupId( CmnGroupName.PROCESS.getGroupId() );
		cmnMstDO.setParentId(0);
		cmnMstDO.setLevelNo(1);
		request.setAttribute("processDO", cmnMstDO);
	}
	
	public static void doEdit(HttpServletRequest request, HttpServletResponse response) {

		int serviceId=AppUtil.getNullToInteger( request.getParameter("processId")  );
		CommonMasterDO cmnMstDO =CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceId, false);
		request.setAttribute("processDO", cmnMstDO);
	}

	public static void doSave(HttpServletRequest request, HttpServletResponse response) {

		CommonMasterDO serviceDO=constructDO(request, response);

		if(serviceDO.getCmnMasterId()==0) {
			//			insert
			int serviceId =CommonMasterDAO.insert(null, serviceDO);
			if(serviceId!=0) {
				System.out.println("Process Inderted...Id:"+serviceId);
				serviceDO=CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceId, false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Process Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Process..!");
			}
		}else {
			//			update
			if(CommonMasterDAO.update(null, serviceDO)) {
				serviceDO=CommonMasterDAO.getCommonMasterByCmnMasterId(null, serviceDO.getCmnMasterId(), false);
				request.setAttribute(PageAlertType.SUCCESS.getType(), "Process Successfully Saved..!");
			}else {
				request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Process..!");
			}
		}
		request.setAttribute("processDO", serviceDO);

	}

	private static CommonMasterDO constructDO(HttpServletRequest request, HttpServletResponse response) {

		String loginId="Admin";
		CommonMasterDO cmnMstDO=new CommonMasterDO();

		cmnMstDO.setCmnMasterId( AppUtil.getNullToInteger( request.getParameter("processId") ) );
		cmnMstDO.setCmnGroupId( AppUtil.getNullToInteger( request.getParameter("groupId") ) );
		cmnMstDO.setParentId( AppUtil.getNullToInteger( request.getParameter("packageName") ) );
		cmnMstDO.setCmnMasterName( AppUtil.getNullToEmpty( request.getParameter("processName") ) );
		cmnMstDO.setLevelNo( AppUtil.getNullToInteger( request.getParameter("levelNo") )  );
		cmnMstDO.setCreatedUser(loginId);
		cmnMstDO.setUpdateUser(loginId);

		return cmnMstDO;
	}

	public static String processOption( String parentIds, String selServiceIds ) {
		String subQry=" AND cmn_group_id="+CmnGroupName.PROCESS.getGroupId() +" AND bool_delete_status=0 ";
		if(!parentIds.isEmpty() && !parentIds.isEmpty() ) { subQry=" AND parent_id in("+parentIds+")"; }
		Map<String, String> map=CommonMasterDAO.getCommonDetMapBySubQry(null, subQry);
		
		return AppUtil.formOption(map, selServiceIds);
	}
	public static void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String loginId="Admin";
		int serviceId=AppUtil.getNullToInteger( request.getParameter("processId")  );
		CommonMasterDO cmnMstDO =new CommonMasterDO();
		cmnMstDO.setBoolDeleteStatus(true);
		cmnMstDO.setUpdateUser(loginId);
		cmnMstDO.setCmnMasterId(serviceId);
		
		AjaxModel model=new AjaxModel();
		if(CommonMasterDAO.deleteupdate(null, cmnMstDO)) {
			model.setMessage(" Process Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete Process");model.setErrorExists(true);
		}
		
		AjaxUtil.sendResponse(request, response, model);
	}
	
}
