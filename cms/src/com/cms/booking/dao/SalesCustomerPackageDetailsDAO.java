package com.cms.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.util.AppDateUtil;
import com.cms.booking.bean.SalesCustomerPackageDetailsDO;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;

public class SalesCustomerPackageDetailsDAO {

	private static final String SELECT="select   sales_package_id, sales_id, package_id, process_starts_from, process_ends_on, bool_overide, bool_delete_status, created_user, created_date, update_user, update_date from sales_customer_package_details ";
	private static final String INSERT="insert into sales_customer_package_details( sales_package_id, sales_id, package_id, process_starts_from, process_ends_on, bool_overide, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  sales_customer_package_details set  sales_id=?, package_id=?, process_starts_from=?, process_ends_on=?, bool_overide=?, bool_delete_status=?,  update_user=?, update_date=Now() WHERE sales_package_id=? ";
	private static final String DELETE_BY_SALES_ID="delete from sales_customer_package_details WHERE sales_id=? ";

	public static int insert(Connection preCon, SalesCustomerPackageDetailsDO dto) {
		int insertId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getSalesPackageId() );
			stmt.setInt(i++, dto.getSalesId() );
			stmt.setInt(i++, dto.getPackageId() );
			stmt.setString(i++, dto.getProcessStartsFrom() );
			stmt.setString(i++, dto.getProcessEndsOn() );
			stmt.setBoolean(i++, dto.isBoolOveride() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { insertId=rs.getInt(1); }
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return insertId;
	}

	public static boolean insert(Connection preCon, List<SalesCustomerPackageDetailsDO> customerPackageList, int salseId) {

		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			for (SalesCustomerPackageDetailsDO dto : customerPackageList) {
				int i=1;
				stmt.setInt(i++, dto.getSalesPackageId() );
				stmt.setInt(i++, salseId );
				stmt.setInt(i++, dto.getPackageId() );
				stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessStartsFrom()+" 00:00:00", true, true) );
				stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessEndsOn()+" 00:00:00", true, true) );
				stmt.setBoolean(i++, dto.isBoolOveride() );
				stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
				stmt.setString(i++, dto.getCreatedUser() );
				stmt.setString(i++, dto.getUpdateUser() );
				stmt.addBatch();
			}

			stmt.executeBatch();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return false;
	} 

	public static boolean update(Connection preCon, SalesCustomerPackageDetailsDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(UPDATE);
			stmt.setInt(i++,dto.getSalesId());
			stmt.setInt(i++,dto.getPackageId());
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessStartsFrom()+" 00:00:00", true, true) );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getProcessEndsOn()+" 00:00:00", true, true) );
			stmt.setBoolean(i++,dto.isBoolOveride());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getSalesPackageId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<SalesCustomerPackageDetailsDO> getSalesCustomerPackageDetails(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<SalesCustomerPackageDetailsDO> dtoList =getSalesCustomerPackageDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<SalesCustomerPackageDetailsDO>(); }
		return dtoList;
	}
	public static List<SalesCustomerPackageDetailsDO> getSalesCustomerPackageDetailsBySaleId(Connection preCon, int saleId, boolean needChild) {
		String query=SELECT+" WHERE sales_id="+saleId;
		List<SalesCustomerPackageDetailsDO> dtoList =getSalesCustomerPackageDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<SalesCustomerPackageDetailsDO>(); }
		return dtoList;
	}

	public static SalesCustomerPackageDetailsDO getSalesCustomerPackageDetailsBySalesPackageId(Connection preCon, int salesPackageId, boolean needChild) {
		String query=SELECT+" WHERE sales_package_id="+salesPackageId;
		List<SalesCustomerPackageDetailsDO> dtoList =getSalesCustomerPackageDetails(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<SalesCustomerPackageDetailsDO>(); }
		return dtoList.size()>0?dtoList.get(0): new SalesCustomerPackageDetailsDO();
	}

	private  static List<SalesCustomerPackageDetailsDO> getSalesCustomerPackageDetails(Connection preCon, String query, boolean needChild) {
		List<SalesCustomerPackageDetailsDO> dtos=new ArrayList<SalesCustomerPackageDetailsDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }
		return dtos;
	}

	private static SalesCustomerPackageDetailsDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		SalesCustomerPackageDetailsDO dto=new SalesCustomerPackageDetailsDO();
		try {
			int i=1;
			dto.setSalesPackageId(rs.getInt(i++));
			dto.setSalesId(rs.getInt(i++));
			dto.setPackageId(rs.getInt(i++));
			dto.setProcessStartsFrom( AppDateUtil.convertToAppDate(rs.getString(i++), false, true) );
			dto.setProcessEndsOn( AppDateUtil.convertToAppDate(rs.getString(i++), false, true) );
			dto.setBoolOveride(rs.getBoolean(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(rs.getString(i++));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(rs.getString(i++));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean deleteBySalesId(Connection preCon, int saleId) {
		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement(DELETE_BY_SALES_ID);
			stmt.setInt(1, saleId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}


}