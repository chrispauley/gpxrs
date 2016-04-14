package com.gpxdb.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public abstract class GpxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DEFAULT_DATASOURCE_NAME = "jdbc/gpxdb";
	private String _datasourceName;
	private DataSource _dataSource;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this._datasourceName = config.getInitParameter("datasourceName");
		if (_datasourceName == null) {
			_datasourceName = DEFAULT_DATASOURCE_NAME;
		}
		if (_dataSource == null) {
			try {
				Context env = (Context) new InitialContext()
						.lookup("java:comp/env");

				_dataSource = (DataSource) env.lookup(_datasourceName);

				if (_dataSource == null)
					throw new ServletException("`" + _datasourceName
							+ "' is an unknown DataSource");
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}
	}
	
	public final String getParam(HttpServletRequest request,
			String name, String defval) {
		String param = request.getParameter(name);
		return (param != null ? param : defval);
	}

	public final long getParam(HttpServletRequest request, String name,
			long defval) {
		String param = request.getParameter(name);
		long value = defval;
		if (param != null) {
			try {
				value = Long.parseLong(param);
			} catch (NumberFormatException ignore) {
			}
		}
		return value;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String output = getParam(request, "output", "xml");
		String gpxname = request.getParameter("name");
		long gpxid = getParam(request, "gpxid", 0);
		String strStart = "0";
		String strLimit = "25";

		try {
			if (gpxid > 0) {
				if (output.equalsIgnoreCase("xml")) {
					//this.showGpxXml(gpxid, request, response);
				} else if (output.equalsIgnoreCase("json")) {
					//showGpxJson(request, response);
				} else {
					//showGpxHTML(gpxid, request, response);
				}

			} else {
				if (gpxname != null && gpxname.length() > 0) {
					//showGpxByName(gpxname, request, response);
				}
				strStart = request.getParameter("start");
				strLimit = request.getParameter("limit");
				if (strStart != null && strLimit != null) {
					//this.showPagedGpxList(request, response);
				} else {
					//this.showAllGpx(request, response);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
		}

	}

}
