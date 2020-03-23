package com.panda.bbs.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class JDBCUtils {

	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql-config");
	}

	public static DataSource getDataSource() {
		return dataSource;
	}


}

