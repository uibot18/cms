package com.cms.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;
import com.cms.employee.bean.AdmEmployeeMasterDO;
import com.cms.finance.LedgerRefType;
import com.cms.finance.bean.FinanceLedgerMasterDO;
import com.cms.finance.dao.FinanceLedgerMasterDAO;
import com.cms.finance.dao.FinancePartyAddressDetailsDAO;
import com.cms.finance.dao.FinancePartyContactDetailsDAO;
import com.cms.finance.dao.FinancePartyPersonalDetailsDAO;

public class AdmEmployeeMasterDAO {

	private final static String SELECT="SELECT emp_id, ledger_id, reporting_to, department_id, department_child_id, designation_id, esi_no, epf_no, uan_no, bank_branch_id, bank_account_no, bool_delete_status, created_user, created_date, update_user, update_date " + 
			"FROM admin_employee_master ";
	private final static String INSERT="insert into admin_employee_master(emp_id, ledger_id, reporting_to, department_id, department_child_id, designation_id, esi_no, epf_no, uan_no, bank_branch_id, bank_account_no, bool_delete_status, created_user, created_date, update_user, update_date) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW()) ";
	private final static String UPDATE_LEDGER_ID="update admin_employee_master set ledger_id=? where emp_id=?";
	
	private final static String UPDATE=" update admin_employee_master set update_user=?, update_date=NOW() where emp_id=?";

	public static ArrayList<AdmEmployeeMasterDO> getAdmEmployeeMasterList(Connection preCon, boolean needChild) {
		String query=SELECT;
		return loadAdmEmployeeMasters(preCon, query, needChild);
	}

	public static ArrayList<AdmEmployeeMasterDO> getFinanceLedgerMasterListBysubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT + "WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		return loadAdmEmployeeMasters(preCon, query, needChild);
	}

	public static AdmEmployeeMasterDO getAdmEmployeeMasterBysubQry(Connection preCon, String subQry, boolean needChild) {
		String query=SELECT + "WHERE 0=0 "+AppUtil.getNullToEmpty( subQry );
		ArrayList<AdmEmployeeMasterDO> masterList =loadAdmEmployeeMasters(preCon, query, needChild);
		return masterList.size()>0?masterList.get(0):new AdmEmployeeMasterDO();
	}
	
	public static AdmEmployeeMasterDO getAdmEmployeeMasterByEmpId(Connection preCon, int empId, boolean needChild) {
		String query=SELECT + "WHERE emp_id= "+empId;
		ArrayList<AdmEmployeeMasterDO> masterList =loadAdmEmployeeMasters(preCon, query, needChild);
		return masterList.size()>0?masterList.get(0):new AdmEmployeeMasterDO();
	}

	public static  ArrayList<AdmEmployeeMasterDO> loadAdmEmployeeMasters(Connection preCon, String query, boolean needChild){
		ArrayList<AdmEmployeeMasterDO> dtos=new ArrayList<AdmEmployeeMasterDO>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query );
			while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return dtos;
	}


	public static int insert(Connection preCon, AdmEmployeeMasterDO dto) {
		int employeeId=0;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			stmt.setInt(i++, dto.getEmpId() );
			stmt.setInt(i++, dto.getLedgerId() );
			stmt.setInt(i++, dto.getReportingTo() );
			stmt.setInt(i++, dto.getDepartmentId() );
			stmt.setInt(i++, dto.getDepartmentChildId() );
			stmt.setInt(i++, dto.getDesignationId() );
			stmt.setString(i++, dto.getEsiNo() );
			stmt.setString(i++, dto.getEpfNo() );
			stmt.setString(i++, dto.getUanNo() );
			stmt.setInt(i++, dto.getBankBranchId() );
			stmt.setString(i++, dto.getBankAccountNo() );
			stmt.setBoolean(i++, dto.isBoolDeleteStatus() );
			stmt.setString(i++, dto.getCreatedUser() );
			stmt.setString(i++, dto.getUpdateUser() );
			System.out.println("Employee => insert:"+stmt.toString() );
			stmt.execute();
			rs=stmt.getGeneratedKeys();
			if(rs.next()) {
				employeeId=rs.getInt(1);
				if(employeeId!=0) {
					FinanceLedgerMasterDO ledgerDO= dto.getLedgerMstDO();
					if( ledgerDO!=null ) { 
						ledgerDO.setRef_id(employeeId);
						int ledgerId=FinanceLedgerMasterDAO.insert(con, ledgerDO);
						if(ledgerId!=0) {
							if(!updateLedgerId(con, employeeId, ledgerId)) {System.out.println("Failed to update Ledger Id..!"); con.rollback(); return 0; }
							if(FinancePartyPersonalDetailsDAO.insert(con, dto.getPersonalDO(), ledgerId) ==0) { System.out.println("Failed to insert addressPersonal Detail..!"); con.rollback(); return 0;  }
							if(!FinancePartyAddressDetailsDAO.insert(con, dto.getAddressList(), ledgerId, employeeId ) ) { System.out.println("Failed to insert address Detail..!"); con.rollback(); return 0; }
							if(FinancePartyContactDetailsDAO.insert(con, dto.getContactDO(), ledgerId, employeeId )==0 ) { System.out.println("Failed to insert Contact Detail..!"); con.rollback(); return 0; }
							//if(!CommonDocumentStoreDAO.insert(con, dto.getDocList(), ledgerId, employeeId ) ) { System.out.println("Failed to insert Doc Detail..!"); con.rollback(); return 0; }
						}else { System.out.println("failed to insert Ledger"); con.rollback(); return 0; }
					}else { System.out.println("Failed to indert Ledger details"); con.rollback(); return 0; }
				}else { System.out.println("Failed to indert Employee details"); con.rollback(); return 0; }
			}
			con.commit();
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( rs, stmt, preCon==null?con:null  ); }
		return employeeId;

	}
	private static AdmEmployeeMasterDO constructDTO( Connection con, ResultSet rs, boolean needChild ) {

		AdmEmployeeMasterDO dto=new AdmEmployeeMasterDO();
		try {
			int i=1;
			dto.setEmpId( rs.getInt(i++));
			dto.setLedgerId( rs.getInt(i++));
			dto.setReportingTo( rs.getInt(i++));
			dto.setDepartmentId( rs.getInt(i++));
			dto.setDepartmentChildId( rs.getInt(i++));
			dto.setDesignationId( rs.getInt(i++));
			dto.setEsiNo( rs.getString(i++));
			dto.setEpfNo( rs.getString(i++));
			dto.setUanNo( rs.getString(i++));
			dto.setBankBranchId( rs.getInt(i++));
			dto.setBankAccountNo( rs.getString(i++));
			dto.setBoolDeleteStatus(rs.getBoolean(i++));
			dto.setCreatedUser(rs.getString(i++));
			dto.setCreatedDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) );
			dto.setUpdateUser(rs.getString(i++));
			dto.setUpdateDate( AppDateUtil.convertToAppDate(rs.getString(i++), true, true) ); 

			if(needChild) {
				dto.setLedgerMstDO( FinanceLedgerMasterDAO.getFinanceLedgerMasterByLedgerId(con, dto.getLedgerId(), false) );
				dto.setPersonalDO( FinancePartyPersonalDetailsDAO.getFinancePartyPersonalDetailByLedgerId(con, dto.getLedgerId(), needChild) );
				dto.setAddressList( FinancePartyAddressDetailsDAO.getFinancePartyAddressDetailsListByLedgerAndRef(con, dto.getLedgerId(), LedgerRefType.EMPLOYEE.getType(), dto.getEmpId(), needChild) );
				dto.setContactDO( FinancePartyContactDetailsDAO.getFinancePartyContactDetailsByLedgerIdAndRef(con, dto.getLedgerId(), LedgerRefType.EMPLOYEE.getType(), dto.getEmpId(), needChild) );
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { }
		return dto;
	}

	public static boolean update(Connection preCon, AdmEmployeeMasterDO dto) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			con.setAutoCommit(false);
			stmt=con.prepareStatement(UPDATE);
			stmt.setString(1,dto.getUpdateUser());
			stmt.setInt(2,dto.getEmpId() );
			System.out.println("Ledger Update: "+stmt.toString());
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { 
				if(FinancePartyPersonalDetailsDAO.deleteByLedgerId(con, dto.getLedgerId())) {
					if(FinancePartyPersonalDetailsDAO.insert(con, dto.getPersonalDO(), dto.getLedgerId() ) ==0) { System.out.println("Failed to insert addressPersonal Detail..!"); con.rollback(); return false;  }
				}else { System.out.println("Failed to delete Personal Detail..!"); con.rollback(); return false; }
				
				if(FinancePartyAddressDetailsDAO.deleteByLedgerId(con, dto.getLedgerId())) {
					if(!FinancePartyAddressDetailsDAO.insert(con, dto.getAddressList(), dto.getLedgerId(), dto.getEmpId() ) ) { System.out.println("Failed to insert address Detail..!"); con.rollback(); return false; }
				}else { System.out.println("Failed to delete Address Detail..!"); con.rollback(); return false; }
				
				if(FinancePartyContactDetailsDAO.deleteByLedgerId(con, dto.getLedgerId())) {
					if(FinancePartyContactDetailsDAO.insert(con, dto.getContactDO(), dto.getLedgerId(), dto.getEmpId() )==0 ) { System.out.println("Failed to insert Contact Detail..!"); con.rollback(); return false; }
				}else { System.out.println("Failed to delete Contact Detail..!"); con.rollback(); return false; }
				
			}else { System.out.println("Failed to Update employee..!"); con.rollback(); return false; }

			con.commit();
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}

	public static boolean updateLedgerId(Connection preCon, int employeeId, int ledgerId) {

		Connection con=null;
		PreparedStatement stmt=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;

			stmt=con.prepareStatement(UPDATE_LEDGER_ID);
			stmt.setInt(1,ledgerId);
			stmt.setInt(2,employeeId);
			int rowAffect=stmt.executeUpdate();
			if(rowAffect!=0) { return true; }

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return false;
	}
	
	public static Map<String, String> loadAllEmpNameMap(Connection preCon , String empIds) {
		Map<String, String> empMap=new LinkedHashMap<>();
		empIds=AppUtil.getNullToEmpty(empIds, "0");
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String query=" SELECT a.emp_id, CONCAT(b.first_name,' ', b.middle_name, ' ', b.last_name) AS emp_name " + 
				"FROM admin_employee_master a, finance_party_personal_details b "+ 
				"WHERE a.ledger_id=b.ledger_id AND a.bool_delete_status=0 AND b.bool_delete_status=0 ";
		if(!empIds.equals("0") ) { query+=" AND a.emp_id IN("+empIds+")"; }
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query  );
			while(rs.next()) {
				empMap.put(""+rs.getInt(1), ""+rs.getString(2) );
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return empMap;
	}
	
	public static Map<String, String> loadAllEmpNameMap(Connection preCon , String empIds, String empIdsExclude) {
		Map<String, String> empMap=new LinkedHashMap<>();
		empIds=AppUtil.getNullToEmpty(empIds, "0");
		empIdsExclude=AppUtil.getNullToEmpty(empIdsExclude, "0");
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String query=" SELECT a.emp_id, CONCAT(b.first_name,' ', b.middle_name, ' ', b.last_name) AS emp_name " + 
				"FROM admin_employee_master a, finance_party_personal_details b "+ 
				"WHERE a.ledger_id=b.ledger_id AND a.bool_delete_status=0 AND b.bool_delete_status=0 ";
		if(!empIds.equals("0") ) { query+=" AND a.emp_id IN("+empIds+")"; }
		if(!empIdsExclude.equals("0") ) { query+=" AND a.emp_id NOT IN("+empIdsExclude+")"; }
		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.createStatement();
			rs=stmt.executeQuery( query  );
			while(rs.next()) {
				empMap.put(""+rs.getInt(1), ""+rs.getString(2) );
			}

		} catch (Exception e) { e.printStackTrace(); }
		finally { DBUtil.close( stmt, preCon==null?con:null  ); }
		return empMap;
	}

}
