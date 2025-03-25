package pe.dinersclub.adqlog.db;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionDB {

	public static Connection getConnectionServerAS400() {
		// OBTIENE LA CONEXION DEL SERVIDOR DE APLICACIONES
		Connection conn = null;
		try {
			InitialContext c = new InitialContext();
			DataSource jdbcFactory = (DataSource) c.lookup("java:/jdbc/dco");
			conn = jdbcFactory.getConnection();
		} catch (NamingException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
