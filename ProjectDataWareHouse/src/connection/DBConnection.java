package connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.Config;
import log.Log;

public abstract class DBConnection {

	protected String url = "";
	protected String user = "root";
	protected String pass = "1234567890@";

	public DBConnection() {

	}

	public DBConnection(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Config> loadAllConfs() throws SQLException {

		List<Config> listConfig = new ArrayList<Config>();
		Connection conn = getConn();
		String selectConfig = "select * from config";
		PreparedStatement ps = conn.prepareStatement(selectConfig);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Config conf = new Config();
			conf.setIdConf(rs.getInt("idConfig"));
			conf.setServerSou(rs.getString("serverSou"));
			conf.setPort(rs.getInt("port"));
			conf.setUserSou(rs.getString("userSou"));
			conf.setPassSou(rs.getString("passSou"));
			conf.setDirSou(rs.getString("directorySou"));
			conf.setFieldName(rs.getString("fieldName"));
			conf.setDelimeterSou(rs.getString("delimeterSou"));
			conf.setFormatSou(rs.getString("formatSou"));
			conf.setServerDes(rs.getString("serverDes"));
			conf.setDBNameDes(rs.getString("DBNameDes"));
			conf.setUseDes(rs.getString("userDes"));
			conf.setPassDes(rs.getString("passDes"));

			listConfig.add(conf);
		}
		close(conn);
		return listConfig;
	}

	public List<Log> getLogs(String sql) throws SQLException {

		List<Log> lstLogs = new ArrayList<>();
		Connection conn = getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Log log = new Log();
			log.setIdLog(rs.getInt("idlog"));
			log.setIdConfig(rs.getInt("idConfig"));
			log.setState(rs.getString("state"));
			log.setDateUserInsertLog(rs.getDate("dateUserInsert"));
			log.setNumColumn(rs.getInt("numColumn"));
			log.setFileName(rs.getString("fileName"));

			lstLogs.add(log);
		}
		close(conn);
		return lstLogs;
	}

	public void writeLogs() throws SQLException {
		List<Config> listConfig = loadAllConfs();
		Log log;
		String sql = "insert into log (idlog, idConfig, state, numColumn, fileName, dateUserInsert) "
				+ "values (?,?,?,?,?,?)";
		try {
			for (Config cf : listConfig) {
				log = new Log();
				Connection con = getConn();
				PreparedStatement ptpm = con.prepareStatement(sql);

				int idLog = cf.getIdConf();
				int idConfig = cf.getIdConf();
				String state = "ER";
				int numColumn = 0;

				// fileName để tạm thành Directory Name
				String fileName = cf.getDirSou();
				long millis = System.currentTimeMillis();
				Date dateUserInsert = new Date(millis);
				ptpm.setInt(1, idLog);
				ptpm.setInt(2, idConfig);
				ptpm.setString(3, state);
				ptpm.setInt(4, numColumn);
				ptpm.setString(5, fileName);
				ptpm.setDate(6, dateUserInsert);

				ptpm.executeUpdate();
				System.out.println("writed...........success");
			}

		} catch (SQLException e) {
			System.out.println("failllll");
			e.printStackTrace();
		}
	}
}
