package com.cms.finance.dao;

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
import com.cms.finance.bean.FinancePartyContactDetailsDO;

public class FinancePartyContactDetailsDAO {

	private final static String SELECT="select contact_id, ledger_id, ref_type, ref_id, contact_type_id, std_code1, phone1, extn1, std_code2, phone2, extn2, country_code1, mobile1, country_code2, mobile2, email1, email2, website, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from finance_party_contact_details ";
	private final static String INSERT="insert into finance_party_contact_details(contact_id, ledger_id, ref_type, ref_id, contact_type_id, std_code1, phone1, extn1, std_code2, phone2, extn2, country_code1, mobile1, country_code2, mobile2, email1, email2, website, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() )  ";
	private final static String DELETE_BY_LEDGER_ID="delete from finance_party_contact_details where ledger_id=?";


	public static ArrayList<FinancePartyContactDetailsDO> getFinancePartyContactDetailsList(Connection preCon, boolean needChild) {
		String query=SELECT;
		ArrayList<FinancePartyContactDetailsDO> contactList=getFinancePartyContactDetails(preCon, query, needChild);
		if(contactList==null) { contactList=new ArrayList<FinancePartyContactDetailsDO>(); }
		return contactList;
	}
	public static ArrayList<FinancePartyContactDetailsDO> getFinancePartyContactDetailsListBySubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT+" WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		ArrayList<FinancePartyContactDetailsDO> contactList=getFinancePartyContactDetails(preCon, query, needChild);
		if(contactList==null) { contactList=new ArrayList<FinancePartyContactDetailsDO>(); }
		return contactList;
	}
	public static FinancePartyContactDetailsDO getFinancePartyContactDetailsByLedgerIdAndRef(Connection preCon, int ledgerId, String refType, int refId, boolean needChild) {
		String query=SELECT+" WHERE ledger_id= "+ledgerId+" AND ref_type='"+refType+"' AND ref_id="+refId;
		ArrayList<FinancePartyContactDetailsDO> contactList=getFinancePartyContactDetails(preCon, query, needChild);
		if(contactList==null) { contactList=new ArrayList<FinancePartyContactDetailsDO>(); }
		return contactList.size()>0?contactList.get(0): new FinancePartyContactDetailsDO();
	}

	public static ArrayList<FinancePartyContactDetailsDO> getFinancePartyContactDetails(Connection preCon, String query, boolean needChild) {
		ArrayList<FinancePartyContactDetailsDO> dtos=new ArrayList<FinancePartyContactDetailsDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( rs, needChild) );	}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return dtos;

	}
	
	public static int insert(Connection preCon, FinancePartyContactDetailsDO dto, int ledgerId, int refId) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getContactId() );
			stmt.setInt(i++, ledgerId );
			stmt.setString(i++, dto.getRefType() );
			stmt.setInt(i++, refId );
			stmt.setInt(i++, dto.getContactTypeId() );
			stmt.setString(i++, dto.getStdCode1() );
			stmt.setString(i++, dto.getPhone1() );
			stmt.setString(i++, dto.getExtn1() );
			stmt.setString(i++, dto.getStdCode2() );
			stmt.setString(i++, dto.getPhone2() );
			stmt.setString(i++, dto.getExtn2() );
			stmt.setString(i++, dto.getCountryCode1() );
			stmt.setString(i++, dto.getMobile1() );
			stmt.setString(i++, dto.getCountryCode2() );
			stmt.setString(i++, dto.getMobile2() );
			stmt.setString(i++, dto.getEmail1() );
			stmt.setString(i++, dto.getEmail2() );
			stmt.setString(i++, dto.getWebsite() );

			stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
			
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return insertId;
		
	}
	private static FinancePartyContactDetailsDO constructDTO( ResultSet rs, boolean needChild ) {

		FinancePartyContactDetailsDO dto=new FinancePartyContactDetailsDO();
		try {
			int i=1;
			dto.setContactId( rs.getInt( i++ ) );
			dto.setLedgerId( rs.getInt( i++ ) );
			dto.setRefType( rs.getString( i++ ) );
			dto.setRefId( rs.getInt( i++ ) );
			dto.setContactTypeId( rs.getInt( i++ ) );
			dto.setStdCode1( rs.getString( i++ ) );
			dto.setPhone1( rs.getString( i++ ) );
			dto.setExtn1( rs.getString( i++ ) );
			dto.setStdCode2( rs.getString( i++ ) );
			dto.setPhone2( rs.getString( i++ ) );
			dto.setExtn2( rs.getString( i++ ) );
			dto.setCountryCode1( rs.getString( i++ ) );
			dto.setMobile1( rs.getString( i++ ) );
			dto.setCountryCode2( rs.getString( i++ ) );
			dto.setMobile2( rs.getString( i++ ) );
			dto.setEmail1( rs.getString( i++ ) );
			dto.setEmail2( rs.getString( i++ ) );
			dto.setWebsite( rs.getString( i++ ) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}


	public static boolean insert(Connection preCon, ArrayList<FinancePartyContactDetailsDO> dtos, int ledgerId, int customerId) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			for (FinancePartyContactDetailsDO dto : dtos) {
				int i=1;
				stmt.setInt(i++, dto.getContactId() );
				stmt.setInt(i++, ledgerId );
				stmt.setString(i++, dto.getRefType() );
				stmt.setInt(i++, customerId );
				stmt.setInt(i++, dto.getContactTypeId() );
				stmt.setString(i++, dto.getStdCode1() );
				stmt.setString(i++, dto.getPhone1() );
				stmt.setString(i++, dto.getExtn1() );
				stmt.setString(i++, dto.getStdCode2() );
				stmt.setString(i++, dto.getPhone2() );
				stmt.setString(i++, dto.getExtn2() );
				stmt.setString(i++, dto.getCountryCode1() );
				stmt.setString(i++, dto.getMobile1() );
				stmt.setString(i++, dto.getCountryCode2() );
				stmt.setString(i++, dto.getMobile2() );
				stmt.setString(i++, dto.getEmail1() );
				stmt.setString(i++, dto.getEmail2() );
				stmt.setString(i++, dto.getWebsite() );
				stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getUpdateUser() );
				System.out.println("Contac=> insert:"+stmt.toString());
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
	public static boolean deleteByLedgerId(Connection preCon, int ledgerId) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;

			stmt=con.prepareStatement(DELETE_BY_LEDGER_ID);
			stmt.setInt(1,ledgerId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

}
