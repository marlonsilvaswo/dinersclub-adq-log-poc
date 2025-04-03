package pe.dinersclub.adqlog.db;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.inject.Inject;

public class ConnectionDB {

	@Inject
	static DataSource ds;

	public static Connection getConnectionServerAS400() {
		// OBTIENE LA CONEXION DEL SERVIDOR DE APLICACIONES
		Connection conn = null;
		try {
			InitialContext c = new InitialContext();
			// DataSource jdbcFactory = (DataSource) c.lookup("java:/jdbc/dco");

			DataSource jdbcFactory = ds;

			conn = jdbcFactory.getConnection();
		} catch (NamingException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
