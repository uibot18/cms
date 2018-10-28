package com.application.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class AjaxUtil {

	public static void sendResponse(HttpServletRequest request, HttpServletResponse response, Object data) {
		PrintWriter writer=null;
		try {
			writer=response.getWriter();
			writer.write( new JSONObject(data).toString() );
		}catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			if(writer!=null) { writer.flush(); writer.close();}
		}
	}
}
