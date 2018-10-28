package com.cms.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.util.AppDateUtil;
import com.cms.booking.bean.SalesCustomerBookingFormDO;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.sun.management.jmx.TraceNotification;
import com.sun.org.apache.regexp.internal.recompile;

public class SalesCustomerBookingFormDAO {

	private static final String SELECT="select   sale_id, sale_date, customer_id, description, bool_delete_status, created_user, created_date, update_user, update_date from sales_customer_booking_form ";
	private static final String INSERT="insert into sales_customer_booking_form( sale_id, sale_date, customer_id, description, bool_delete_status, created_user, created_date, update_user, update_date)  values(  ?, ?, ?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private static final String UPDATE="update  sales_customer_booking_form set  sale_date=?, customer_id=?, description=?, bool_delete_status=?, created_user=?, created_date=NOW(), update_user=?, update_date=NOW() WHERE sale_id=? ";

	public static int insert(Connection preCon, SalesCustomerBookingFormDO dto) {
		int saleId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getSaleId() );
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getSaleDate(), false, true) );
			stmt.setInt(i++, dto.getCustomerId() );
			stmt.setString(i++, dto.getDescription() );
			stmt.setBoolean(i++, dto.getBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) { 
				saleId=rs.getInt(1); 
				if(saleId!=0) {
					if(!SalesCustomerPackageDetailsDAO.insert(con, dto.getCustomerPackageList(), saleId) ) {
						System.out.println("Package Insertion Failed..."); con.rollback(); return 0; 
					}
				}else { System.out.println("Booking Insertion Failed..."); con.rollback(); return 0; }
			}else { System.out.println("Booking Insertion Failed..."); con.rollback(); return 0; }
			con.commit();
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null); }
		return saleId;
	}

	public static boolean update(Connection preCon, SalesCustomerBookingFormDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;
		int i=1;
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(i++, AppDateUtil.convertToDBDate(dto.getSaleDate(), false, true));
			stmt.setInt(i++,dto.getCustomerId());
			stmt.setString(i++,dto.getDescription());
			stmt.setBoolean(i++,dto.getBoolDeleteStatus());
			stmt.setString(i++,dto.getCreatedUser());
			stmt.setString(i++,dto.getUpdateUser());
			stmt.setInt(i++,dto.getSaleId());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { 
				if(SalesCustomerPackageDetailsDAO.deleteBySalesId(con, dto.getSaleId())) {
					if(!SalesCustomerPackageDetailsDAO.insert(con, dto.getCustomerPackageList(), dto.getSaleId())) { System.out.println("Failed insert Package detail.."); con.rollback(); return false; }
				}else { System.out.println("Failed to delete Package.."); con.rollback(); return false; }
			}else { System.out.println("Failed to update booking..."); con.rollback(); return false; }
			con.commit();
			return true;
		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static List<SalesCustomerBookingFormDO> getSalesCustomerBookingForm(Connection preCon, boolean needChild) {
		String query=SELECT;
		List<SalesCustomerBookingFormDO> dtoList =getSalesCustomerBookingForm(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<SalesCustomerBookingFormDO>(); }
		return dtoList;
	}

	public static SalesCustomerBookingFormDO getSalesCustomerBookingFormBySaleId(Connection preCon, int saleId, boolean needChild) {
		String query=SELECT+" WHERE sale_id="+saleId;
		List<SalesCustomerBookingFormDO> dtoList =getSalesCustomerBookingForm(preCon, query, needChild);
		if( dtoList==null ) { dtoList=new ArrayList<SalesCustomerBookingFormDO>(); }
		return dtoList.size()>0?dtoList.get(0): new SalesCustomerBookingFormDO();
	}

	private  static List<SalesCustomerBookingFormDO> getSalesCustomerBookingForm(Connection preCon, String query, boolean needChild) {
		List<SalesCustomerBookingFormDO> dtos=new ArrayList<SalesCustomerBookingFormDO>();
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

	private static SalesCustomerBookingFormDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {
		SalesCustomerBookingFormDO dto=new SalesCustomerBookingFormDO();
		try {
			int i=1;
			dto.setSaleId(rs.getInt(i++));
			dto.setSaleDate(AppDateUtil.convertToAppDate(rs.getString(i++), false, true));
			dto.setCustomerId(rs.getInt(i++));
			dto.setDescription(rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true));
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate(AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			if(needChild) {
				dto.setCustomerPackageList( SalesCustomerPackageDetailsDAO.getSalesCustomerPackageDetailsBySaleId(con, dto.getSaleId(), needChild));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	} 


}