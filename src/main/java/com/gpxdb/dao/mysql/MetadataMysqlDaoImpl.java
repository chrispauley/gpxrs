package com.gpxdb.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.gpxdb.model.*;

import com.gpxdb.dao.MetadataDao;


public class MetadataMysqlDaoImpl implements MetadataDao {
	private Connection _con;

	// singleton DAO
	private static MetadataMysqlDaoImpl metadataDAO = null;

	public static MetadataDao getGPXDAO() {
		if (metadataDAO == null) {
			metadataDAO = new MetadataMysqlDaoImpl();
		}
		return metadataDAO;
	}

	/**
	 * Instantiates a new metadata mysql dao impl.
	 */
	public MetadataMysqlDaoImpl() {
		super();
	}

	/**
	 * Instantiates a new metadata mysql dao impl.
	 *
	 * @param con the con
	 */
	public MetadataMysqlDaoImpl(Connection con) {
		super();
		_con = con;
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.MetadataDao#getMetadata(com.gpx.model.castor.Gpx)
	 */
	public void getMetadata(Gpx gpx) {
		Metadata metadata;

		if (gpx.getMetadata() != null) {
			metadata = gpx.getMetadata();
		} else {
			metadata = new Metadata();
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		long metid = 0;
		long perid = 0;
		long bndid = 0;
		long crtid = 0;

		try {
			ps = _con.prepareStatement("SELECT * FROM metadata WHERE gpxid=?");
			ps.setLong(1, gpx.getValueObjectId());
			rs = ps.executeQuery();
			while (rs.next()) {
				metadata = new Metadata();
				metadata.setName(rs.getString("name"));
				metadata.setDesc(rs.getString("desc"));
				metadata.setKeywords(rs.getString("keywords"));
				metadata.setTime(rs.getTimestamp("time"));
				metid = rs.getLong("metid");
				perid = rs.getLong("perid");
				bndid = rs.getLong("bndid");
				crtid = rs.getLong("crtid");
				metadata.setValueObjectId(metid);
				if (perid > 0) {
					metadata.setAuthor(getPersonData(metid));
				}
				if (rs.getTime("time") != null) {
					metadata.setTime(rs.getTimestamp("time"));
				}
				getMetadataLinks(metadata);
				if (rs.getLong("extid") > 0) {
					// getExtensionData(metadata);
					// metadata.setExtensions(extensions);
				}
				if (bndid > 0) {
					metadata.setBounds(getBounds(bndid));
				}
				if (crtid > 0) {
					metadata.setCopyright(getCopyright(crtid));
				}
				gpx.setMetadata(metadata);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return;
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.MetadataDao#updateMetadata(long, com.gpx.model.castor.Metadata)
	 */
	public void updateMetadata(long gpxid, Metadata metadata) {

		long metid = 0;
		Gpx gpx = new Gpx();
		gpx.setValueObjectId(gpxid);
		gpx.setMetadata(metadata);

		try {
			PreparedStatement ps = _con.prepareStatement("SELECT metid FROM metadata"
					+ " WHERE gpxid=?");
			ps.setLong(1, gpxid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				metid = rs.getLong("metid");
			}
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteMetadata(?)}");
			cs.setLong(1, metid);
			cs.execute();
			cs.close();
//
//			this.deleteMetadata(metid);
			this.insertMetadata(gpx);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.MetadataDao#deleteMetadata(long)
	 */
	public void deleteMetadata(long metid)  {
		PreparedStatement ps;

		try {
			String copyrightSql = "delete copyright from copyright where crtid in "
					+ "(select crtid from metadata where metadata.metid=?)";
			ps = _con.prepareStatement(copyrightSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			String boundsSql = "delete bounds from bounds where metid=?";
			ps = _con.prepareStatement(boundsSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			String linkSql = "delete link from link where metid=?";
			ps = _con.prepareStatement(linkSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			/* delete person email, person link, then person */
			String emailSql = "delete email from email where emailid in "
					+ "(select p.emailid from person p where p.metid in "
					+ "(select m.metid from metadata m where m.metid=?))";
			ps = _con.prepareStatement(emailSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			String personLinksSql = "delete link l from link l where l.perid in "
					+ "(select p.perid from person p where p.metid in "
					+ "(select m.metid from metadata m where m.metid=?))";
			ps = _con.prepareStatement(personLinksSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			String personSql = "delete person from person where metid=?";
			ps = _con.prepareStatement(personSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			String extensionSql = "delete extension e from extension e where e.extid in "
					+ "(select m.extid from metadata m where m.metid=?)";
			ps = _con.prepareStatement(extensionSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

			String metadataSql = "delete metadata m from metadata m where m.metid=?";
			ps = _con.prepareStatement(metadataSql);
			ps.setLong(1, metid);
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.MetadataDao#updateMetadata(com.gpx.model.castor.Metadata)
	 */
	public void updateMetadata(Metadata metadata) {
		long metid = metadata.getValueObjectId();
		long gpxid = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT gpxid FROM metadata "
					+ "WHERE metid=" + metid);
			while (rs.next()) {
				gpxid=rs.getLong("gpxid");
			}
			rs.close();

			CallableStatement cs;
			cs = _con.prepareCall("{call deleteMetadata(?)}");
			cs.setLong(1, metid);
			cs.execute();
			cs.close();
			
			Gpx gpx = new Gpx();
			gpx.setValueObjectId(gpxid);
			gpx.setMetadata(metadata);
			this.addMetadata(gpx);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private Person getPersonData(long metid) throws SQLException {
		Person author = null;
		long emailid = 0;
		long linkid = 0;
		long perid = 0;

		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM person "
				+ "WHERE metid=" + metid);
		while (rs.next()) {
			author = new Person();
			author.setValueObjectId(metid);
			author.setName(rs.getString("name"));
			emailid = rs.getLong("emailid");
			linkid = rs.getLong("linkid");
			perid = rs.getLong("perid");
		}
		rs.close();
		if (emailid > 0) {
			author.setEmail(this.getEmail(emailid));
		}
		if (linkid > 0) {
			author.setLink(this.getPersonLink(perid));
		}
		return author;
	}

	private Bounds getBounds(long bndid) throws SQLException {
		Bounds bounds = null;
		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM bounds "
				+ "WHERE bndid=" + bndid);
		while (rs.next()) {
			bounds = new Bounds();
			bounds.setValueObjectId(bndid);
			bounds.setMinlat(rs.getBigDecimal("minlat"));
			bounds.setMinlon(rs.getBigDecimal("minlon"));
			bounds.setMaxlat(rs.getBigDecimal("maxlat"));
			bounds.setMaxlon(rs.getBigDecimal("maxlon"));
		}
		rs.close();
		statement.close();
		return bounds;
	}

	private Link getPersonLink(long perid) throws SQLException {
		Link link = null;
		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM link "
				+ "WHERE perid=" + perid);
		while (rs.next()) {
			link = new Link();
			link.setValueObjectId(rs.getLong("linkid"));
			link.setHref(rs.getString("href"));
			link.setText(rs.getString("text"));
			link.setType(rs.getString("type"));
		}
		rs.close();
		statement.close();
		return link;
	}

	private Email getEmail(long emailid) throws SQLException {
		Email email = null;

		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM email "
				+ "WHERE emailid=" + emailid);
		while (rs.next()) {
			email = new Email();
			email.setValueObjectId(emailid);
			email.setId(rs.getString("id"));
			email.setDomain(rs.getString("domain"));
		}
		rs.close();
		statement.close();
		return email;
	}

	private Copyright getCopyright(long crtid) throws SQLException,
			ParseException {
		Copyright copyright = null;

		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM copyright "
				+ "WHERE crtid=" + crtid);
		while (rs.next()) {
			copyright = new Copyright();
			copyright.setValueObjectId(crtid);
			copyright.setAuthor(rs.getString("author"));
//			if (rs.getString("year") != null) {
//				GYear gyear = new GYear(rs.getString("year"));
////				copyright.setYear(value)
////				copyright.setYear(gyear);
//			}
			/* @Todo Fix gyear */
			copyright.setLicense(rs.getString("license"));
		}
		rs.close();
		statement.close();
		return copyright;
	}

	private void getMetadataLinks(Metadata metadata) throws SQLException {
		Link link = null;

		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM link "
				+ "WHERE metid=" + metadata.getValueObjectId());
		while (rs.next()) {
			link = new Link();
			link.setValueObjectId(rs.getLong("linkid"));
			link.setHref(rs.getString("href"));
			link.setText(rs.getString("text"));
			link.setType(rs.getString("type"));
			metadata.getLink().add(link);
		}
		rs.close();
		statement.close();
		return;
	}

	/**
	 * Insert metadata.
	 *
	 * @param gpx the gpx
	 * @throws SQLException the sQL exception
	 */
	public void insertMetadata(Gpx gpx) throws SQLException {
		if (gpx.getMetadata() == null) {
			return;
		}
		PreparedStatement ps;
		Metadata m = gpx.getMetadata();
		long gpxid = gpx.getValueObjectId();
		long metid = 0;
		long bndid = 0;
		long crtid = 0;

		ps = _con
				.prepareStatement("INSERT INTO metadata "
						+ "(gpxid, name, `desc`, time, keywords) VALUES (?, ?, ?, ?, ?)");
		ps.setLong(1, gpxid);
		ps.setString(2, m.getName());
		ps.setString(3, m.getDesc());
		if (m.getTime() != null) {
			java.sql.Timestamp metaTimestamp = new java.sql.Timestamp(m
					.getTime().getTime());
			ps.setTimestamp(4, metaTimestamp);
		} else {
			ps.setTimestamp(4, null);
		}
		ps.setString(5, m.getKeywords());
		ps.executeUpdate();
		ps.close();

		metid = this.getLastUpdateId(_con);
		m.setValueObjectId(metid);
		ps = _con.prepareStatement("UPDATE gpx SET metid=? WHERE gpxid=?");
		ps.setLong(1, metid);
		ps.setLong(2, gpxid);
		ps.executeUpdate();
		ps.close();

		if (m.getAuthor() != null) {
			savePersonData(metid, m.getAuthor());
		}

		if (m.getBounds() != null) {
			ps = _con.prepareStatement("INSERT INTO bounds "
					+ "(metid, minlat, minlon, maxlat, maxlon) "
					+ "VALUES (?, ?, ?, ?, ?)");
			ps.setLong(1, metid);
			ps.setBigDecimal(2, m.getBounds().getMinlat());
			ps.setBigDecimal(3, m.getBounds().getMinlon());
			ps.setBigDecimal(4, m.getBounds().getMaxlat());
			ps.setBigDecimal(5, m.getBounds().getMaxlon());
			ps.executeUpdate();
			ps.close();
			bndid = this.getLastUpdateId(_con);
			ps = _con
					.prepareStatement("UPDATE metadata SET bndid=? WHERE gpxid=?");
			ps.setLong(1, bndid);
			ps.setLong(2, gpxid);
			ps.executeUpdate();
			ps.close();
		}

		if (m.getCopyright() != null) {
			ps = _con
					.prepareStatement("INSERT INTO copyright "
							+ "(metid, author, year, license) "
							+ "VALUES (?, ?, ?, ?)");
			ps.setLong(1, metid);
			ps.setString(2, m.getCopyright().getAuthor());
			String gyear = "";
			if (m.getCopyright() != null) {
				if (m.getCopyright().getYear() != null) {
					gyear = m.getCopyright().getYear().toString();
				}
			}
			ps.setString(3, gyear);
			ps.setString(4, m.getCopyright().getLicense());
			ps.executeUpdate();
			ps.close();
			crtid = this.getLastUpdateId(_con);
			ps = _con
					.prepareStatement("UPDATE metadata SET crtid=? WHERE metid=?");
			ps.setLong(1, crtid);
			ps.setLong(2, metid);
			ps.executeUpdate();
			ps.close();
		}
		if (m.getLink() != null) {
			ps = _con.prepareStatement("INSERT INTO link "
					+ "(metid, href, text, type) VALUES (?, ?, ?, ?)");
			Link link = null;
			for (int i = 0; i < m.getLink().size(); i++) {
				link = m.getLink().get(i);
				ps.setLong(1, metid);
				ps.setString(2, link.getHref());
				ps.setString(3, link.getText());
				ps.setString(4, link.getType() + "");
				ps.executeUpdate();
			}
			ps.close();
		}
	}

	private Person savePersonData(long metid, Person author)
			throws SQLException {

		long emailid = 0;
		long linkid = 0;
		long perid = 0;

		PreparedStatement ps = _con.prepareStatement("INSERT INTO person "
				+ "(metid, name) VALUES (?, ?)");
		ps.setLong(1, metid);
		ps.setString(2, author.getName());
		ps.executeUpdate();
		perid = this.getLastUpdateId(_con);
		author.setValueObjectId(perid);
		ps.close();

		ps = _con.prepareStatement("UPDATE metadata SET perid=? WHERE metid=?");
		ps.setLong(1, perid);
		ps.setLong(2, metid);
		ps.executeUpdate();
		ps.close();

		if (author.getEmail() != null) {
			PreparedStatement ps2 = _con.prepareStatement("INSERT INTO email "
					+ "(id, domain) VALUES (?, ?)");
			ps2.setString(1, author.getEmail().getId());
			ps2.setString(2, author.getEmail().getDomain());
			ps2.executeUpdate();
			emailid = this.getLastUpdateId(_con);
			author.getEmail().setValueObjectId(emailid);
			ps2.close();

			PreparedStatement ps3 = _con.prepareStatement("UPDATE person "
					+ " SET emailid=? WHERE perid=?");
			ps3.setLong(1, emailid);
			ps3.setLong(2, perid);
			ps3.executeUpdate();
			ps3.close();
		}

		if (author.getLink() != null) {
			PreparedStatement ps4 = _con.prepareStatement("INSERT INTO link "
					+ "(perid, href, text, type) VALUES (?, ?, ?, ?)");
			ps4.setLong(1, perid);
			ps4.setString(2, author.getLink().getHref());
			ps4.setString(3, author.getLink().getText());
			ps4.setString(4, author.getLink().getType() + "");
			ps4.executeUpdate();
			linkid = this.getLastUpdateId(_con);
			author.getLink().setValueObjectId(linkid);
			ps4.close();

			PreparedStatement ps5 = _con.prepareStatement("UPDATE person "
					+ " SET linkid=? WHERE perid=?");
			ps5.setLong(1, linkid);
			ps5.setLong(2, perid);
			ps5.executeUpdate();
			ps5.close();
		}
		return author;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.MetadataDao#addMetadata(org.gpxdb.model.Gpx)
	 */
	public void addMetadata(Gpx gpx) {
		try {
			this.insertMetadata(gpx);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private long getLastUpdateId(Connection con) throws SQLException {
		long result = 0;
		ResultSet resultset = null;
		Statement statement = con.createStatement();
		resultset = statement.executeQuery("SELECT LAST_INSERT_ID()");
		while (resultset.next()) {
			result = resultset.getInt(1);
		}
		resultset.close();
		statement.close();
		return result;
	}

	public Metadata findByGpxid(long gpxid) {
		Metadata metadata = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long metid = 0;
		long perid = 0;
		long bndid = 0;
		long crtid = 0;

		try {
			ps = _con.prepareStatement("SELECT * FROM metadata WHERE gpxid=?");
			ps.setLong(1, gpxid);
			rs = ps.executeQuery();
			while (rs.next()) {
				metadata = new Metadata();
				metadata.setName(rs.getString("name"));
				metadata.setDesc(rs.getString("desc"));
				metadata.setKeywords(rs.getString("keywords"));
				metadata.setTime(rs.getTimestamp("time"));
				metid = rs.getLong("metid");
				perid = rs.getLong("perid");
				bndid = rs.getLong("bndid");
				crtid = rs.getLong("crtid");
				metadata.setValueObjectId(metid);
				if (perid > 0) {
					metadata.setAuthor(getPersonData(metid));
				}
				if (rs.getTime("time") != null) {
					metadata.setTime(rs.getTimestamp("time"));
				}
				getMetadataLinks(metadata);
				if (rs.getLong("extid") > 0) {
					// getExtensionData(metadata);
					// metadata.setExtensions(extensions);
				}
				if (bndid > 0) {
					metadata.setBounds(getBounds(bndid));
				}
				if (crtid > 0) {
					metadata.setCopyright(getCopyright(crtid));
				}
				
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return metadata;
	}
}
