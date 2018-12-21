package com.cms.navigation.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import com.application.util.AjaxModel;
import com.application.util.AjaxUtil;
import com.application.util.AppUtil;
import com.application.util.PageAlertType;
import com.cms.navigation.bean.MenuNavigationDO;
import com.cms.navigation.dao.MenuNavigationDAO;
import com.cms.user.login.LoginDetail;
import com.cms.user.login.util.LoginUtil;

public class NavigationCreationController {

	public static void doNavigationSave(HttpServletRequest request, HttpServletResponse response) {

		MenuNavigationDO menunavDO=costructDTO( request, response);
		if(menunavDO!=null) {
			String duplicate_check=MenuNavigationDAO.duplicatecheck(null,menunavDO);
			if(duplicate_check.isEmpty()) {
				if(menunavDO.getNavigationId()==0) {
					//				insert
					int menunavId=MenuNavigationDAO.insert(null, menunavDO);
					if(menunavId!=0) {
						menunavDO =MenuNavigationDAO.getMenuNavigationByNavigationId(null, menunavId, true);
						request.setAttribute(PageAlertType.SUCCESS.getType(), "Navigation Detail Successfully Saved..!");
					}else {
						request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Navigation Details..!");
					}
				}else {
					//update	
					if( MenuNavigationDAO.update(null, menunavDO) ) {
						menunavDO =MenuNavigationDAO.getMenuNavigationByNavigationId(null, menunavDO.getNavigationId(), true);
						request.setAttribute(PageAlertType.SUCCESS.getType(), "Navigation Detail Successfully Saved..!");
					}else {
						request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Navigation Details..!");
					}
				}
			}
			else {
				request.setAttribute(PageAlertType.ERROR.getType(), duplicate_check);
			}
		}else {
			request.setAttribute(PageAlertType.ERROR.getType(), "Failed to Save Navigation Details..!");
		}

		if(menunavDO==null) { menunavDO=new MenuNavigationDO(); }
		request.setAttribute("menunavDO", menunavDO);

	}

	private static MenuNavigationDO costructDTO(HttpServletRequest request, HttpServletResponse response) {

		LoginDetail loginDetail = LoginUtil.getLoginDetail(request);
		String loginId=loginDetail.getLoginId();

		int navigationId=AppUtil.getNullToInteger( request.getParameter("navigationId") );
		int parent_navigationId=AppUtil.getNullToInteger( request.getParameter("parent_navigationId") );
		String navigationName=AppUtil.getNullToEmpty( request.getParameter("navigationName") );
		String is_menu=AppUtil.getNullToEmpty( request.getParameter("isMenu") );
		int menuId=AppUtil.getNullToInteger( request.getParameter("menuId") );
		int menuOrder=AppUtil.getNullToInteger( request.getParameter("menuOrder") );

		MenuNavigationDO menunavDo=new MenuNavigationDO();
		menunavDo.setNavigationId(navigationId);
		menunavDo.setParentNavigationId(parent_navigationId);
		menunavDo.setMenuId(menuId);
		menunavDo.setNavigationName(navigationName);
		menunavDo.setMenuOrder( menuOrder );
		menunavDo.setBoolIsMenu(is_menu.equalsIgnoreCase("1")?true:false);
		menunavDo.setCreatedUser(loginId);
		menunavDo.setUpdateUser(loginId);


		return menunavDo;
	}

	public static void doNavigationEdit(HttpServletRequest request, HttpServletResponse response) {

		int navigationId=AppUtil.getNullToInteger( request.getParameter("navigationId") );

		MenuNavigationDO menunavDO =MenuNavigationDAO.getMenuNavigationByNavigationId(null, navigationId, false);
		if(menunavDO==null) { menunavDO=new MenuNavigationDO(); }
		request.setAttribute("menunavDO", menunavDO);


	}

	public static String parentNavigationOption(String selValues,String navigation_id) {

		Map<String, String> menuMap =MenuNavigationDAO.loadParentnavigationMap(null, navigation_id);
		if(menuMap==null) { menuMap=new HashMap<String, String>(); }
		return AppUtil.formOption(menuMap, selValues);

	}

	public static void doNavigationDelete(HttpServletRequest request, HttpServletResponse response) {

		int navigationId=AppUtil.getNullToInteger( request.getParameter("navigationId") );
		String loginid="Admin";
		MenuNavigationDO menunavDO =new MenuNavigationDO();
		menunavDO.setNavigationId(navigationId);
		menunavDO.setBoolDeleteStatus(true);
		menunavDO.setUpdateUser(loginid);
		AjaxModel model=new AjaxModel();
		if(MenuNavigationDAO.deleteupdate(null, menunavDO)) {
			model.setMessage(" Deleted Successfully");
		}else {
			model.setMessage(" Unable to Delete");model.setErrorExists(true);
		}
		AjaxUtil.sendResponse(request, response, model);

	}
	
	
	/*Search listing*/
	public static void OutnavDisplay(JspWriter out,String sno,String childSno,String navi_id,HashMap<String,Map<String, Object>> navMap,Map<String,String> ParentMap,String formname) throws IOException{
		
		
		String childIDS = AppUtil.getNullToEmpty(""+ParentMap.get(""+navi_id));
		
		List<String> naviationIds  = AppUtil.convertStrArrayToOrderedList(childIDS.split(","));
		
		if(naviationIds.size()>0){
			int i=1;
			for(String nav_id : naviationIds){
				
				String newchildSno="";
				if(sno.equalsIgnoreCase(""))
					newchildSno=""+i;
				else
					newchildSno=sno+"."+i;
				
				Map<String, Object> map=navMap.get(""+nav_id);
				int parentid=AppUtil.getNullToInteger( (String)map.get("COL#3") );
				
				String parent=parentid!=0? "data-tt-parent-id='"+parentid+"'":"";
				
				String rowvalue="";
				rowvalue=geNavigationList(newchildSno,parent,map,formname);
				out.print(rowvalue);
				i++;
				if(ParentMap.containsKey(""+nav_id))
				{
					String masterhildlist=AppUtil.getNullToEmpty(""+ParentMap.get(""+navi_id));
					List<String> masterchilds  = AppUtil.convertStrArrayToOrderedList(masterhildlist.split(","));
					
					if(masterchilds!=null && masterchilds.size()!=0){
						if(masterchilds.size()>0){							
							OutnavDisplay(out,newchildSno,newchildSno,nav_id+"",navMap,ParentMap,formname);
						}
				}
			
			
			}
		 }
		
	  }
	}
	
	
	public static String geNavigationList(String sno,String parent,Map<String, Object> searchData,String fromname) {
		StringBuffer sb=new StringBuffer();
		
		
		
		/* SELECT cmn_master_id, cmn_group_id, parent_id, cmn_master_name AS service_name, level_no */
		int nav_id=AppUtil.getNullToInteger( (String)searchData.get("COL#1") );
		String nav_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#2") );
		String menu_name=AppUtil.getNullToEmpty( (String)searchData.get("COL#11") );
		
	
sb.append("<tr data-tt-id='"+nav_id+"' "+parent+">");
sb.append("	<th scope='row'>"+sno+"</th>");
sb.append("<td>"+nav_name+"</td>");
sb.append("	<td>"+menu_name+"</td>");
sb.append("<td>");
sb.append("	<a data-target='#CMS-POPUP-MODE' data-toggle='modal'  data-url='navigation?action=edit&navigationId="+nav_id+"' href='#'>Edit</a> &nbsp;&nbsp;");
sb.append("<a class='"+fromname+"_delete' href='javascript:;' ahref='navigation?action=delete&navigationId="+nav_id+"'>delete</a></td>");
sb.append("<tr>");

		
		
		return sb.toString();
	}
	
	
}
