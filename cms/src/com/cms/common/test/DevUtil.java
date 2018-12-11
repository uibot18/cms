package com.cms.common.test;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.application.util.AppDateUtil;
import com.application.util.AppUtil;

public class DevUtil {

	public static void generateDOAndDAO( Connection con, String tableName ) {
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from "+tableName);
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();

			List<String> sqlColumnList=new LinkedList<String>();
			List<String> dataTypeList=new LinkedList<String>();
			List<String> fieldNamesList=new LinkedList<String>();
			List<String> fieldNamesListPojo=new LinkedList<String>();
			for (int i=1; i<= columnCount; i++) {
				sqlColumnList.add( metaData.getColumnName(i) );
				dataTypeList.add( (DBDataType.getDBDataTypeBysqlType(""+metaData.getColumnTypeName(i))).getJavaBaic() );

				fieldNamesList.add( AppUtil.dbToJavCase(metaData.getColumnName(i), false) );
				fieldNamesListPojo.add( AppUtil.dbToJavCase(metaData.getColumnName(i), true) );
			}
			generateDO(tableName, sqlColumnList, dataTypeList, fieldNamesList, fieldNamesListPojo);
			generateDAO(tableName, sqlColumnList, dataTypeList, fieldNamesList, fieldNamesListPojo);
		} catch (Exception e) { e.printStackTrace(); }
	}


	private static void generateDO( String tableName, List<String> sqlColumnList, List<String> dataTypeList,
			List<String> fieldNamesList, List<String> fieldNamesListPojo) {
		StringBuffer pojoFile=new StringBuffer();		
		StringBuffer getterSetter=new StringBuffer();
		pojoFile.append("public class "+AppUtil.dbToJavCase(tableName, true)+"DO { \n\n");

		int i=0;
		for (String field : fieldNamesList) {

			String dataType=dataTypeList.get(i);
			String pojoName=fieldNamesListPojo.get(i);

			pojoFile.append("private "+dataType+" "+field+"");
			if(dataType.equalsIgnoreCase("string")) {
				pojoFile.append("=\"\";\n");
			}else if(dataType.equalsIgnoreCase("boolean")) {
				pojoFile.append(";\n");
			}else {
				pojoFile.append("=0;\n");
			}

			getterSetter.append("public void set"+pojoName+"("+dataType+" "+field+"){\n");
			getterSetter.append("this."+field+"="+field+";\n");
			getterSetter.append("}\n");

			getterSetter.append("public "+dataType+ " get"+pojoName+"(){\n");
			getterSetter.append("return this."+field+";\n");
			getterSetter.append("}\n");
			i++;
		}
		pojoFile.append("\n\n");
		pojoFile.append(getterSetter);
		pojoFile.append("\n}");
		generateFile(AppUtil.dbToJavCase(tableName, true)+"DO.java", pojoFile.toString());

	}

	private static void generateDAO(String tableName, List<String> sqlColumnList, List<String> dataTypeList, List<String> fieldNamesList, List<String> fieldNamesListPojo) {

		String doName=AppUtil.dbToJavCase(tableName, true)+"DO";
		String tableName_java=AppUtil.dbToJavCase(tableName, true);

		String selectQuery="select  ";
		String insertQuery="insert into "+tableName+"(";
		String insertQueryValues=" values( ";
		String updateQuery="update  "+tableName+" set ";

		StringBuffer constructDTOMethod=new StringBuffer();
		constructDTOMethod.append("private static "+doName+" constructDTO( Connection con, ResultSet rs, boolean needChild ) {\n");
		constructDTOMethod.append(doName+" dto=new "+doName+"();\n");
		constructDTOMethod.append("try {\n");
		constructDTOMethod.append("int i=1;\n");

		StringBuffer daoInsertMethod=new StringBuffer();
		daoInsertMethod.append("public static int insert(Connection preCon, "+doName+" dto) {\n");
		daoInsertMethod.append("int insertId=0;\n");
		daoInsertMethod.append("Connection con=null;\n");
		daoInsertMethod.append("PreparedStatement stmt=null;\n");
		daoInsertMethod.append("ResultSet rs=null;\n");
		daoInsertMethod.append("try {\n");
		daoInsertMethod.append("con=preCon==null?DBConnection.getConnection():preCon;\n");
		daoInsertMethod.append("stmt=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);\n");
		daoInsertMethod.append("int i=1;\n");


		StringBuffer daoUpdateMethod=new StringBuffer();
		daoUpdateMethod.append("public static boolean update(Connection preCon, "+doName+" dto) {\n");
		daoUpdateMethod.append("Connection con=null;\n");
		daoUpdateMethod.append("PreparedStatement stmt=null;\n");
		daoUpdateMethod.append("int i=1;\n");
		daoUpdateMethod.append("try {\n");
		daoUpdateMethod.append("con=preCon==null?DBConnection.getConnection():preCon;\n");
		daoUpdateMethod.append("stmt=con.prepareStatement(UPDATE);\n");

		String sqlKeyColumn="";
		String keyField="";
		String keyFieldDataType="";

		int i=0;
		for (String field : fieldNamesList) {
			String sqlColumnName=sqlColumnList.get(i);
			String dataType=AppUtil.dbToJavCase(dataTypeList.get(i), true);
			String pojoName=fieldNamesListPojo.get(i);

			selectQuery+=" "+sqlColumnName+",";
			insertQuery+=" "+sqlColumnName+",";
			insertQueryValues+=" ?,";

			if( i==0) { 
				sqlKeyColumn=sqlColumnName; 
				keyField=field;
				keyFieldDataType=dataTypeList.get(i);
			}
			else{ 
				updateQuery+=" "+sqlColumnName+"=?,";
				daoUpdateMethod.append("stmt.set"+dataType+"(i++, dto.get"+pojoName+"());\n");
			}

			if(pojoName.equalsIgnoreCase("CreatedDate") || pojoName.equalsIgnoreCase("UpdateDate")) {
				constructDTOMethod.append("dto.set"+pojoName+"(AppDateUtil.convertToAppDate(rs.get"+dataType+"(i++), true, true));\n");
			}else {
				constructDTOMethod.append("dto.set"+pojoName+"(rs.get"+dataType+"(i++));\n");
			}
			
			daoInsertMethod.append("stmt.set"+dataType+"(i++, dto.get"+pojoName+"() );\n");

			i++;
		}
		selectQuery=selectQuery.substring(0, selectQuery.length()-1)+" from "+tableName;
		insertQuery=insertQuery.substring(0,insertQuery.length()-1)+") "+insertQueryValues.substring(0, insertQueryValues.length()-1)+" )";
		updateQuery=updateQuery.substring(0, updateQuery.length()-1)+ " WHERE "+sqlKeyColumn+"=?";

		constructDTOMethod.append("} catch (SQLException e) { e.printStackTrace(); }\n");
		constructDTOMethod.append("finally { }\n");
		constructDTOMethod.append("return dto;\n");
		constructDTOMethod.append("} \n");

		daoInsertMethod.append("stmt.execute();\n");
		daoInsertMethod.append("rs=stmt.getGeneratedKeys();\n");
		daoInsertMethod.append("if(rs.next()) { insertId=rs.getInt(1); }\n");
		daoInsertMethod.append("} catch (Exception e) { e.printStackTrace(); }\n");
		daoInsertMethod.append("finally { DBUtil.close( rs, stmt, preCon==null?con:null); }\n");
		daoInsertMethod.append("return insertId;\n");
		daoInsertMethod.append("}\n");

		daoUpdateMethod.append("stmt.set"+AppUtil.dbToJavCase(keyFieldDataType, true)+"(i++,dto.get"+AppUtil.dbToJavCase(keyField, true)+"());\n");
		daoUpdateMethod.append("int rowAffect=stmt.executeUpdate();\n");
		daoUpdateMethod.append("if(rowAffect!=0) { return true; }\n");
		daoUpdateMethod.append("} catch (Exception e) { e.printStackTrace(); } \n");
		daoUpdateMethod.append("finally { DBUtil.close( stmt, preCon==null?con:null  ); }\n");
		daoUpdateMethod.append("return false;\n");
		daoUpdateMethod.append("}\n");

		StringBuffer daoFileData=new StringBuffer();
		daoFileData.append("public class "+AppUtil.dbToJavCase(tableName, true)+"DAO {\n\n");
		daoFileData.append( "private static final String SELECT=\"" +selectQuery+" \";\n");
		daoFileData.append( "private static final String INSERT=\"" +insertQuery+" \";\n");
		daoFileData.append( "private static final String UPDATE=\"" +updateQuery+" \";\n\n");

		daoFileData.append( daoInsertMethod+"\n");
		daoFileData.append(daoUpdateMethod+"\n");

		//		Get All DTO
		daoFileData.append("public static List<"+doName+"> get"+tableName_java+"(Connection preCon, boolean needChild) {\n");
		daoFileData.append("String query=SELECT;\n");
		daoFileData.append("List<"+doName+"> dtoList =get"+tableName_java+"(preCon, query, needChild);\n");
		daoFileData.append("if( dtoList==null ) { dtoList=new ArrayList<"+doName+">(); }\n");
		daoFileData.append("return dtoList;\n");
		daoFileData.append("}\n\n");

		//		Get  DTO By Key Column
		daoFileData.append("public static "+doName+" get"+tableName_java+"By"+AppUtil.dbToJavCase(keyField, true)+"(Connection preCon, "+keyFieldDataType+" "+keyField+", boolean needChild) {\n");
		daoFileData.append("String query=SELECT+\" WHERE "+sqlKeyColumn+"=\"+"+keyField+";\n");
		daoFileData.append("List<"+doName+"> dtoList =get"+tableName_java+"(preCon, query, needChild);\n");
		daoFileData.append("if( dtoList==null ) { dtoList=new ArrayList<"+doName+">(); }\n");
		daoFileData.append("return dtoList.size()>0?dtoList.get(0): new "+doName+"();\n");
		daoFileData.append("}\n\n");

		//		getList
		daoFileData.append("private  static List<"+doName+"> get"+tableName_java+"(Connection preCon, String query, boolean needChild) {\n");
		daoFileData.append("List<"+doName+"> dtos=new ArrayList<"+doName+">();\n");
		daoFileData.append("Connection con=null;\n");
		daoFileData.append("Statement stmt=null;\n");
		daoFileData.append("ResultSet rs=null;\n");
		daoFileData.append("try {\n");
		daoFileData.append("con=preCon==null?DBConnection.getConnection():preCon;\n");
		daoFileData.append("stmt=con.createStatement();\n");
		daoFileData.append("rs=stmt.executeQuery( query );\n");
		daoFileData.append("while(rs.next()) { dtos.add(constructDTO( con, rs, needChild) );	}\n");
		daoFileData.append("} catch (Exception e) { e.printStackTrace(); }\n");
		daoFileData.append("finally { DBUtil.close( rs, stmt, preCon==null?con:null ); }\n");
		daoFileData.append("return dtos;\n");
		daoFileData.append("}\n\n");

		//		Construct DTO
		daoFileData.append(constructDTOMethod);

		daoFileData.append("\n\n}");

		generateFile(AppUtil.dbToJavCase(tableName, true)+"DAO.java", daoFileData.toString());

	}

	private static void generateFile(String fileName, String content)  {
		try {
//			FileOutputStream fos=new FileOutputStream("H:\\Madhan Project\\"+fileName);
			FileOutputStream fos=new FileOutputStream("C:\\Users\\SWEET INDHU\\Desktop\\devUtil\\"+fileName);
			fos.write(content.getBytes());fos.flush();fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
