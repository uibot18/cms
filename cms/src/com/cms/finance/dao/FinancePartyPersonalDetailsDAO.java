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
import com.cms.finance.bean.FinancePartyPersonalDetailsDO;

public class FinancePartyPersonalDetailsDAO {

	private final static String SELECT="select personal_id, ledger_id, party_type, salutation, first_name, middle_name, last_name, relation_type_id, salutation_rel, first_name_rel, middle_name_rel, last_name_rel, dob, age, gender, marital_status, blood_group, bool_residing_with_primary, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from finance_party_personal_details ";
	private final static String INSERT="insert into finance_party_personal_details(personal_id, ledger_id, party_type, salutation, first_name, middle_name, last_name, relation_type_id, salutation_rel, first_name_rel, middle_name_rel, last_name_rel, dob, age, gender, marital_status, blood_group, bool_residing_with_primary, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() )  ";
	private final static String DELETE_BY_LEDGER_ID="delete from finance_party_personal_details where ledger_id=?";

	public static ArrayList<FinancePartyPersonalDetailsDO> getFinancePartyPersonalDetailsList(Connection preCon, boolean needChild) {
		String query=SELECT;
		ArrayList<FinancePartyPersonalDetailsDO> personalDetList=getFinancePartyPersonalDetails(preCon, query, needChild);
		if( personalDetList==null ) { personalDetList=new ArrayList<FinancePartyPersonalDetailsDO>(); }
		return personalDetList;
	}
	
	public static FinancePartyPersonalDetailsDO getFinancePartyPersonalDetailBySubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT+" WHERE 0=0 "+AppUtil.getNullToEmpty(subQry);
		ArrayList<FinancePartyPersonalDetailsDO> personalDetList=getFinancePartyPersonalDetails(preCon, query, needChild);
		if( personalDetList==null ) { personalDetList=new ArrayList<FinancePartyPersonalDetailsDO>(); }
		return personalDetList.size()>0?personalDetList.get(0): new FinancePartyPersonalDetailsDO();
	}
	public static FinancePartyPersonalDetailsDO getFinancePartyPersonalDetailByLedgerId(Connection preCon, int ledgerId, boolean needChild) {
		String query=SELECT+" WHERE ledger_id= "+ledgerId;
		ArrayList<FinancePartyPersonalDetailsDO> personalDetList=getFinancePartyPersonalDetails(preCon, query, needChild);
		if( personalDetList==null ) { personalDetList=new ArrayList<FinancePartyPersonalDetailsDO>(); }
		return personalDetList.size()>0?personalDetList.get(0): new FinancePartyPersonalDetailsDO();
	}
	
	
	private static ArrayList<FinancePartyPersonalDetailsDO> getFinancePartyPersonalDetails(Connection preCon, String query, boolean needChild) {
		ArrayList<FinancePartyPersonalDetailsDO> dtos=new ArrayList<FinancePartyPersonalDetailsDO>();
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

	
	public static int insert(Connection preCon, FinancePartyPersonalDetailsDO dto, int ledgerId) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getPersonalId() );
			stmt.setInt(i++, ledgerId );
			stmt.setInt(i++, dto.getPartyType() );
			stmt.setInt(i++, dto.getSalutation() );
			stmt.setString(i++, dto.getFirstName() );
			stmt.setString(i++, dto.getMiddleName() );
			stmt.setString(i++, dto.getLastName() );
			stmt.setInt(i++, dto.getRelationTypeId() );
			stmt.setInt(i++, dto.getSalutationRel() );
			stmt.setString(i++, dto.getFirstNameRel() );
			stmt.setString(i++, dto.getMiddleNameRel() );
			stmt.setString(i++, dto.getLastNameRel() );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getDob(), false, true) );
			stmt.setInt(i++, dto.getAge() );
			stmt.setInt(i++, dto.getGender() );
			stmt.setString(i++, dto.getMaritalStatus() );
			stmt.setString(i++, dto.getBloodGroup() );
			stmt.setBoolean(i++, dto.isBoolResidingWithPrimary() );
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
	

	private static FinancePartyPersonalDetailsDO constructDTO( ResultSet rs, boolean needChild ) {

		FinancePartyPersonalDetailsDO dto=new FinancePartyPersonalDetailsDO();
		try {
			int i=1;
			dto.setPersonalId( rs.getInt( i++ ) );
			dto.setLedgerId( rs.getInt( i++ ) );
			dto.setPartyType( rs.getInt( i++ ) );
			dto.setSalutation( rs.getInt( i++ ) );
			dto.setFirstName( rs.getString( i++ ) );
			dto.setMiddleName( rs.getString( i++ ) );
			dto.setLastName( rs.getString( i++ ) );
			dto.setRelationTypeId( rs.getInt( i++ ) );
			dto.setSalutationRel( rs.getInt( i++ ) );
			dto.setFirstNameRel( rs.getString( i++ ) );
			dto.setMiddleNameRel( rs.getString( i++ ) );
			dto.setLastNameRel( rs.getString( i++ ) );
			dto.setDob( AppDateUtil.convertToAppDate( rs.getString( i++ ) , false, true) );
			dto.setAge( rs.getInt( i++ ) );
			dto.setGender( rs.getInt( i++ ) );
			dto.setMaritalStatus( rs.getString( i++ ) );
			dto.setBloodGroup( rs.getString( i++ ) );
			dto.setBoolResidingWithPrimary( rs.getBoolean( i++ ) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
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
