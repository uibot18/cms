package com.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.common.master.bean.CommonMasterDO;
import com.cms.common.master.dao.CommonMasterDAO;

public class CommonData {

	public static Map<String, CommonMasterDO> commonMasterData=new HashMap<String, CommonMasterDO>();
	
	static{
		init();
	}
	public static void init(){
		List<CommonMasterDO> commonMasterList = CommonMasterDAO.getCommonMaster(null, false);
		if(commonMasterList!=null) {
			for (CommonMasterDO commonMasterDO : commonMasterList) {
				commonMasterData.put(""+commonMasterDO.getCmnMasterId(), commonMasterDO);
			}
		}
	}
}
