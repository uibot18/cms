package com.cms.common.test;

public enum DBDataType {
	BIGINT("bigint","long","Long"),
	BINARY("binary","int","Integer"),
	BIT("bit","int","Integer"),
	BLOB("blob","Object","Object"),
	BOOL("bool","boolean","Boolean"),
	BOOLEAN("boolean","boolean","Boolean"),
	CHAR("char","char","Character"),
	DATE("date","String","String"),
	DATETIME("datetime","String","String"),
	DECIMAL("decimal","float","Float"),
	DOUBLE("double","double","Double"),
	ENUM("enum","String","String"),
	FLOAT("float","float","Float"),
	INT("int","int", "Integer"),
	INTEGER("integer","int", "Integer"),
	LONG_BLOB("longblob","Object","Object"),
	LONG_TEXT("longtext","String","String"),
	MEDIUM_BLOB("mediumblob","Object","Object"),
	MEDIUM_INT("mediumint","int","Integer"),
	MEDIUM_TEXT("mediumtext","String","String"),
	NUMERIC("numeric","double","Double"),
	REAL("real","float","Float"),
	SET("set","Object","Object"),
	SMALL_INT("smallint","int","Integer"),
	TEXT("text","String","String"),
	TIME("time","String","String"),
	TIMESTAMP("timestamp","String","String"),
	TINY_BLOB("tinyblob","Object","Object"),
	TINY_INT("tinyint","boolean","Boolean"),
	TINY_TEXT("tinytext","String","String"),
	VARBINARY("varbinary","int","Integer"),
	VARCHAR("varchar","String","String"),
	YEAR("year","String","String"),
	;
	private String sqlType;
	private String javaBaic;
	private String javaObject;

	DBDataType(String sqlType, String javaBaic, String javaObject){
		this.sqlType=sqlType;
		this.javaBaic=javaBaic;
		this.javaObject=javaObject;
	}

	public String getSqlType() {
		return sqlType;
	}

	public String getJavaBaic() {
		return javaBaic;
	}

	public String getJavaObject() {
		return javaObject;
	}

	public static DBDataType getDBDataTypeBysqlType(String sqlType) {

		for (DBDataType dbDataType : DBDataType.values()) {
			if(dbDataType.sqlType.equalsIgnoreCase(sqlType)) { return dbDataType; }
		}
		return null;

	}
}
