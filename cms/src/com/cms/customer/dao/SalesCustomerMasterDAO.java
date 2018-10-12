package com.cms.customer.dao;

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
import com.cms.common.master.dao.CommonDocumentStoreDAO;
import com.cms.customer.bean.SalesCustomerMasterDO;
import com.cms.finance.LedgerRefType;
import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.dao.FinanceLedgerMasterDAO;
import com.cms.finance.dao.FinancePartyAddressDetailsDAO;
import com.cms.finance.dao.FinancePartyContactDetailsDAO;
import com.cms.finance.dao.FinancePartyPersonalDetailsDAO;

public class SalesCustomerMasterDAO {

	private final static String SELECT="select customer_id, ledger_id, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"from sales_customer_master  ";
	private final static String INSERT="insert into sales_customer_master(customer_id, ledger_id, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, NOW(), ?, NOW() ) ";
	private final static String UPDATE_LEDGER_ID="update sales_customer_master set ledger_id=? where customer_id=?";
	private final static String UPDATE="update sales_customer_master set update_user=?, update_date=NOW() where customer_id=?";


	public static ArrayList<SalesCustomerMasterDO> getSalesCustomerMasterAll(Connection preCon, boolean needChild) {
		String query=SELECT;
		ArrayList<SalesCustomerMasterDO> customerList =getSalesCustomerMasters(preCon, query, needChild);
		if( customerList==null ) { customerList=new ArrayList<SalesCustomerMasterDO>(); }
		return customerList;

	}
	public static SalesCustomerMasterDO getSalesCustomerMasterBySubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT +" WHERE 0=0 "+AppUtil.getNullToEmpty(subQry);
		ArrayList<SalesCustomerMasterDO> customerList =getSalesCustomerMasters(preCon, query, needChild);
		if( customerList==null ) { customerList=new ArrayList<SalesCustomerMasterDO>(); }
		return customerList.size()>0?customerList.get(0): new SalesCustomerMasterDO();
	}
	public static SalesCustomerMasterDO getSalesCustomerMasterByCustomerId(Connection preCon, int customerId, boolean needChild) {
		String query=SELECT +" WHERE customer_id="+customerId;
		ArrayList<SalesCustomerMasterDO> customerList =getSalesCustomerMasters(preCon, query, needChild);
		if( customerList==null ) { customerList=new ArrayList<SalesCustomerMasterDO>(); }
		return customerList.size()>0?customerList.get(0): new SalesCustomerMasterDO();
	}

	public static ArrayList<SalesCustomerMasterDO> getSalesCustomerMasters(Connection preCon, String query, boolean needChild) {
		ArrayList<SalesCustomerMasterDO> dtos=new ArrayList<SalesCustomerMasterDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():con;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return dtos;
	}


	public static int insert(Connection preCon, SalesCustomerMasterDO dto) {

		int customerId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getCustomerId() );
			stmt.setInt(i++, dto.getLedgerId() );
			stmt.setBoolean( i++, dto.isBoolDeleteStatus());
			stmt.setString( i++, dto.getCreatedUser());
			stmt.setString( i++, dto.getUpdateUser());
			System.out.println("Customer=> insert: "+stmt.toString());
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) {
				customerId=rs.getInt(1); 
				if(customerId!=0) {
					FinanceLedgerMasterDO ledgerDO= dto.getLedgerMasterDO(); 
					if( ledgerDO!=null ) { 
						ledgerDO.setRef_id(customerId);
						int ledgerId=FinanceLedgerMasterDAO.insert(con, ledgerDO);
						if(ledgerId!=0) {
							if(!updateLedgerId(con, customerId, ledgerId)) {System.out.println("Failed to update Ledger Id..!"); con.rollback(); return 0; }
							if(FinancePartyPersonalDetailsDAO.insert(con, dto.getPersonalDO(), ledgerId) ==0) { System.out.println("Failed to insert addressPersonal Detail..!"); con.rollback(); return 0;  }
							if(!FinancePartyAddressDetailsDAO.insert(con, dto.getAddressDetailList(), ledgerId, customerId ) ) { System.out.println("Failed to insert address Detail..!"); con.rollback(); return 0; }
							if(!FinancePartyContactDetailsDAO.insert(con, dto.getContactDetailList(), ledgerId, customerId ) ) { System.out.println("Failed to insert Contact Detail..!"); con.rollback(); return 0; }
							if(!CommonDocumentStoreDAO.insert(con, dto.getDocList(), ledgerId, customerId ) ) { System.out.println("Failed to insert Doc Detail..!"); con.rollback(); return 0; }
						}else { System.out.println("failed to insert Ledger"); con.rollback(); return 0; }

					}else { System.out.println("ledger Do is null"); con.rollback(); return 0; }
				}else { System.out.println("Customer insertion failed..!"); con.rollback(); return 0; }
			}
			con.commit();
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return customerId;

	}

	public static boolean updateLedgerId(Connection preCon, int customerId, int ledgerId) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;

			stmt=con.prepareStatement(UPDATE_LEDGER_ID);
			stmt.setInt(1,ledgerId);
			stmt.setInt(2,customerId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}


	private static SalesCustomerMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {

		SalesCustomerMasterDO dto=new SalesCustomerMasterDO();
		try {
			int i=1;
			dto.setCustomerId(rs.getInt(i++));
			dto.setLedgerId(rs.getInt(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 
			if( needChild ) {
				String subQry=" AND ledger_id="+dto.getLedgerId()+" AND ref_type='"+LedgerRefType.CUSTOMER.getType()+"' AND ref_id="+dto.getCustomerId();
				dto.setLedgerMasterDO( FinanceLedgerMasterDAO.getFinanceLedgerMasterBysubQry(con, subQry, needChild)  );
				dto.setAddressDetailList( FinancePartyAddressDetailsDAO.getFinancePartyAddressDetailsListBySubQry(con, subQry, needChild) );
				dto.setContactDetailList( FinancePartyContactDetailsDAO.getFinancePartyContactDetailsListBySubQry(con, subQry, needChild) );
				subQry=" AND ref_type='"+LedgerRefType.CUSTOMER.getType()+"' AND ref_id="+dto.getCustomerId();
				dto.setDocList( CommonDocumentStoreDAO.getCommonDocumentStoreListBySubQry(con, subQry, needChild) );
				dto.setPersonalDO( FinancePartyPersonalDetailsDAO.getFinancePartyPersonalDetailBySubQry(con, " AND ledger_id="+dto.getLedgerId(), needChild) );
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}
	public static boolean update(Connection preCon, SalesCustomerMasterDO dto) {
		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);

			stmt=con.prepareStatement(UPDATE);
			stmt.setString( 1, dto.getUpdateUser());
			stmt.setInt(2, dto.getCustomerId() );
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { 
				
				if(!FinanceLedgerMasterDAO.update( con, dto.getLedgerMasterDO() )) { System.out.println("Failed to Update Ledger..!"); con.rollback(); return false;  }
				if( FinancePartyPersonalDetailsDAO.deleteByLedgerId(con, dto.getLedgerId()) ) {
					if(FinancePartyPersonalDetailsDAO.insert(con, dto.getPersonalDO(), dto.getLedgerId()) ==0) { System.out.println("Failed to insert addressPersonal Detail..!"); con.rollback(); return false;  }
				}else { System.out.println("Failed to delete Person detail..!"); con.rollback(); return false;   }
				
				if( FinancePartyAddressDetailsDAO.deleteByLedgerId(con, dto.getLedgerId()) ) {
					if(!FinancePartyAddressDetailsDAO.insert(con, dto.getAddressDetailList(), dto.getLedgerId(), dto.getCustomerId()) ) { System.out.println("Failed to insert address Detail..!"); con.rollback(); return false; }
				}else { System.out.println("Failed to delete Address detail..!"); con.rollback(); return false;   }
				
				if( FinancePartyContactDetailsDAO.deleteByLedgerId(con, dto.getLedgerId()) ) {
					if(!FinancePartyContactDetailsDAO.insert(con, dto.getContactDetailList(), dto.getLedgerId(), dto.getCustomerId() ) ) { System.out.println("Failed to insert Contact Detail..!"); con.rollback(); return false; }
				}else { System.out.println("Failed to delete contact detail..!"); con.rollback(); return false;   }
				
				if( CommonDocumentStoreDAO.deleteByRefTypeAndRefId( con, LedgerRefType.CUSTOMER.getType(), dto.getCustomerId() ) ) {
					if(!CommonDocumentStoreDAO.insert(con, dto.getDocList(), dto.getLedgerId(), dto.getCustomerId() ) ) { System.out.println("Failed to insert Doc Detail..!"); con.rollback(); return false; }
				}else { System.out.println("Failed to delete Doc detail..!"); con.rollback(); return false;   }
				
			}else  { System.out.println("Failed to Update customer detail..!"); con.rollback(); return false;   }
			con.commit();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

}
