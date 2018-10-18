package com.cms.common.master.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.common.master.bean.CommonDocumentStoreDO;

public class CommonDocumentStoreDAO {

	private final static String SELECT="select document_id, document_type_id, name_in_document, document_no, document_issue_date, valid_upto, ref_type, ref_id, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from common_document_store ";
	private final static String INSERT=" insert into common_document_store(document_id, document_type_id, name_in_document, document_no, document_issue_date, valid_upto, ref_type, ref_id, bool_delete_status, created_user, created_date, update_user, update_date ) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() )  ";
	private final static String DELETE_BY_REF_TYPE_AND_REF_ID="delete from common_document_store where ref_type=? and ref_id=?";


	public static ArrayList<CommonDocumentStoreDO> getCommonDocumentStoreList(Connection preCon, boolean needChild) {
		String query=SELECT;
		ArrayList<CommonDocumentStoreDO> docStoreList=getCommonDocumentStores(preCon, query, needChild);
		if(docStoreList==null) { docStoreList=new ArrayList<CommonDocumentStoreDO>(); }
		return docStoreList;

	}

	public static ArrayList<CommonDocumentStoreDO> getCommonDocumentStoreListBySubQry(Connection preCon, String subQry,  boolean needChild) {
		String query=SELECT+" WHERE 0=0 "+AppUtil.getNullToEmpty(subQry);
		ArrayList<CommonDocumentStoreDO> docStoreList=getCommonDocumentStores(preCon, query, needChild);
		if(docStoreList==null) { docStoreList=new ArrayList<CommonDocumentStoreDO>(); }
		return docStoreList;

	}
	private static ArrayList<CommonDocumentStoreDO> getCommonDocumentStores(Connection preCon, String query, boolean needChild) {
		ArrayList<CommonDocumentStoreDO> dtos=new ArrayList<CommonDocumentStoreDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		System.out.println("getCommonDocumentStores => query:"+query);
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( rs, needChild) );	}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return dtos;

	}


	public static int insert(Connection preCon, CommonDocumentStoreDO dto) {

		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt( i++, dto.getDocumentId() );
			stmt.setInt( i++, dto.getDocumentTypeId() );
			stmt.setString( i++, dto.getNameInDocument() );
			stmt.setString( i++, dto.getDocumentNo() );
			stmt.setString( i++, AppDateUtil.convertToDBDate(dto.getDocumentIssueDate(), false, true) );
			stmt.setString( i++, AppDateUtil.convertToDBDate(dto.getValidUpto(), false, true) );
			stmt.setString( i++, dto.getRefType() );
			stmt.setInt( i++, dto.getRefId() );
			stmt.setBoolean( i++, dto.isBoolDeleteStatus());
			stmt.setString( i++, dto.getCreatedUser());
			stmt.setString( i++, dto.getUpdateUser());

			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, con  ); }
		return insertId;

	}
	private static CommonDocumentStoreDO constructDTO( ResultSet rs, boolean needChild ) {

		CommonDocumentStoreDO dto=new CommonDocumentStoreDO();
		try {
			int i=1;
			dto.setDocumentId( rs.getInt( i++ ) );
			dto.setDocumentTypeId( rs.getInt( i++ ) );
			dto.setNameInDocument( rs.getString( i++ ) );
			dto.setDocumentNo( rs.getString( i++ ) );
			dto.setDocumentIssueDate( AppDateUtil.convertToAppDate( rs.getString( i++ ) , false, true) );
			dto.setValidUpto( AppDateUtil.convertToAppDate( rs.getString( i++ ) , false, true) );
			dto.setRefType( rs.getString( i++ ) );
			dto.setRefId( rs.getInt( i++ ) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { }
		return dto;
	}


	public static boolean insert(Connection preCon, ArrayList<CommonDocumentStoreDO> docList, int ledgerId, int customerId) {

		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			for (CommonDocumentStoreDO dto : docList) {
				int i=1;
				stmt.setInt( i++, dto.getDocumentId() );
				stmt.setInt( i++, dto.getDocumentTypeId() );
				stmt.setString( i++, dto.getNameInDocument() );
				stmt.setString( i++, dto.getDocumentNo() );
				stmt.setString( i++, AppDateUtil.convertToDBDate(dto.getDocumentIssueDate() , false, true));
				stmt.setString( i++, AppDateUtil.convertToDBDate(dto.getValidUpto(), false, true) );
				stmt.setString( i++, dto.getRefType() );
				stmt.setInt( i++, customerId );
				stmt.setBoolean( i++, dto.isBoolDeleteStatus());
				stmt.setString( i++, dto.getCreatedUser());
				stmt.setString( i++, dto.getUpdateUser());
				System.out.println("DOc > insert:"+stmt.toString());
				stmt.addBatch();
			}

			stmt.executeBatch();
			return true;

			/*stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }*/

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return false;


	}

	public static boolean deleteByRefTypeAndRefId(Connection preCon, String refType, int refId) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;

			stmt=con.prepareStatement(DELETE_BY_REF_TYPE_AND_REF_ID);
			stmt.setString(1, refType);
			stmt.setInt(2, refId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

}
