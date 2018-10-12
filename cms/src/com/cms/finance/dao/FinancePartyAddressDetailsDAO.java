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
import com.cms.finance.bean.FinancePartyAddressDetailsDO;

public class FinancePartyAddressDetailsDAO {

	private final static String SELECT="select address_id, ledger_id, ref_type, ref_id, address_type_id, doo_no, street_name, road_name, area_name, land_mark, city, state, pincode, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from finance_party_address_details ";
	private final static String INSERT="insert into finance_party_address_details(address_id, ledger_id, ref_type, ref_id, address_type_id, doo_no, street_name, road_name, area_name, land_mark, city, state, pincode, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() )  ";
	private final static String DELETE_BY_LEDGER_ID="delete from finance_party_address_details where ledger_id=?";


	public static ArrayList<FinancePartyAddressDetailsDO> getFinancePartyAddressDetailsList(Connection preCon, boolean needChild) {
		String query=SELECT;
		ArrayList<FinancePartyAddressDetailsDO> addressList =getFinancePartyAddressDetails(preCon, query, needChild); 
		if(addressList==null) { addressList=new ArrayList<FinancePartyAddressDetailsDO>(); }
		return addressList;
	}
	
	public static ArrayList<FinancePartyAddressDetailsDO> getFinancePartyAddressDetailsListBySubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT+" WHERE 0=0 "+AppUtil.getNullToEmpty(subQry);
		ArrayList<FinancePartyAddressDetailsDO> addressList =getFinancePartyAddressDetails(preCon, query, needChild); 
		if(addressList==null) { addressList=new ArrayList<FinancePartyAddressDetailsDO>(); }
		return addressList;
	}
	public static ArrayList<FinancePartyAddressDetailsDO> getFinancePartyAddressDetailsListByLedgerAndRef(Connection preCon, int ledgerId, String refTye, int refId, boolean needChild) {
		String query=SELECT+" WHERE ledger_id= "+ledgerId+" AND ref_type='"+refTye+"' AND ref_id="+refId;
		ArrayList<FinancePartyAddressDetailsDO> addressList =getFinancePartyAddressDetails(preCon, query, needChild); 
		if(addressList==null) { addressList=new ArrayList<FinancePartyAddressDetailsDO>(); }
		return addressList;
	}
	
	public static ArrayList<FinancePartyAddressDetailsDO> getFinancePartyAddressDetails(Connection preCon, String query, boolean needChild) {
		ArrayList<FinancePartyAddressDetailsDO> dtos=new ArrayList<FinancePartyAddressDetailsDO>();
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
	
	public static int insert(Connection preCon, FinancePartyAddressDetailsDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getAddresId() );
			stmt.setInt(i++, dto.getLedgerId() );
			stmt.setString(i++, dto.getRefType() );
			stmt.setInt(i++, dto.getRefId() );
			stmt.setInt(i++, dto.getAddressTypeId() );
			stmt.setString(i++, dto.getDooNo() );
			stmt.setString(i++, dto.getStreetName() );
			stmt.setString(i++, dto.getRoadName() );
			stmt.setString(i++, dto.getAreaName() );
			stmt.setString(i++, dto.getLandMark() );
			stmt.setString(i++, dto.getCity() );
			stmt.setString(i++, dto.getState() );
			stmt.setString(i++, dto.getPincode() );

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
	private static FinancePartyAddressDetailsDO constructDTO( ResultSet rs, boolean needChild ) {

		FinancePartyAddressDetailsDO dto=new FinancePartyAddressDetailsDO();
		try {
			int i=1;
			dto.setAddresId( rs.getInt( i++ ) );
			dto.setLedgerId( rs.getInt( i++ ) );
			dto.setRefType( rs.getString( i++ ) );
			dto.setRefId( rs.getInt( i++ ) );
			dto.setAddressTypeId( rs.getInt( i++ ) );
			dto.setDooNo( rs.getString( i++ ) );
			dto.setStreetName( rs.getString( i++ ) );
			dto.setRoadName( rs.getString( i++ ) );
			dto.setAreaName( rs.getString( i++ ) );
			dto.setLandMark( rs.getString( i++ ) );
			dto.setCity( rs.getString( i++ ) );
			dto.setState( rs.getString( i++ ) );
			dto.setPincode( rs.getString( i++ ) );
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}


	public static boolean insert(Connection preCon, ArrayList<FinancePartyAddressDetailsDO> dtos, int ledgetId, int refId) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			for (FinancePartyAddressDetailsDO dto : dtos) {
				int i=1;
				stmt.setInt(i++, dto.getAddresId() );
				stmt.setInt(i++, ledgetId );
				stmt.setString(i++, dto.getRefType() );
				stmt.setInt(i++, refId );
				stmt.setInt(i++, dto.getAddressTypeId() );
				stmt.setString(i++, dto.getDooNo() );
				stmt.setString(i++, dto.getStreetName() );
				stmt.setString(i++, dto.getRoadName() );
				stmt.setString(i++, dto.getAreaName() );
				stmt.setString(i++, dto.getLandMark() );
				stmt.setString(i++, dto.getCity() );
				stmt.setString(i++, dto.getState() );
				stmt.setString(i++, dto.getPincode() );
				System.out.println("Address => Insert: "+stmt.toString());
				stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getUpdateUser() );
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			return true;
			/*rs=stmt.getGeneratedKeys();
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
