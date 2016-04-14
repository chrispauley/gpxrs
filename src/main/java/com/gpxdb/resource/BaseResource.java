package com.gpxdb.resource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseResource {
	private final static String DEFAULT_DATASOURCE_NAME = "jdbc/gpxdb";
	private String _datasourceName;
	private DataSource _dataSource;

	/**
	 * @return DataSource
	 */
	protected DataSource getDS() {
		if (_dataSource == null) {
			if (this._datasourceName == null) {
				// Lookup from context, or use the default.
				this._datasourceName = DEFAULT_DATASOURCE_NAME;
			}

			try {
				Context env = (Context) new InitialContext()
						.lookup("java:comp/env");
				if (env != null) {
					_dataSource = (DataSource) env.lookup(_datasourceName);
				}
			} catch (NamingException e) {
				System.err.println("Configuration Error: Fix the datasource name!!!");
				e.printStackTrace();
			}
		}
		return _dataSource;
	}

	protected Connection getConnection() throws SQLException {
		return this._dataSource.getConnection();
	}

}
