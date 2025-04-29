package pe.dinersclub.adqlog.repositorio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;

import pe.dinersclub.adqlog.model.BeanLog;
import pe.dinersclub.adqlog.repositorio.interfaces.LogOperacionesInterface;

//@Repository
public class LogOperacionesImpl implements LogOperacionesInterface {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean registrarLogOperacion(BeanLog beanLog, DataSource ds) {
		Connection conn = null;
		CallableStatement cs = null;
		StringBuilder sbSQL = new StringBuilder();
		boolean estadoEvento = false;
		int sizeParametroConsulta = 500;
		try {
			// DB2 RDS
			// sbSQL.append("{CALL ").append("RDSADMIN");
			// sbSQL.append(".").append("SP_WSC_REGISTRARLOG").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			// DB2 DINERS
			sbSQL.append("{CALL ").append("DOOLB");
			sbSQL.append(".").append("SP_WSC_REGISTRARLOG").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			boolean multipleRegistro = false;

			if (beanLog.getParametrosConsulta().length() > sizeParametroConsulta) {
				multipleRegistro = true;
			}

			// conn = ConnectionDB.getConnectionServerAS400();

			// conn = ds.getConnection();

			try {
				Class.forName("com.ibm.as400.access.AS400JDBCDriver");
				conn = DriverManager.getConnection("jdbc:as400://DESARROLLO;transaction isolation=none;user=ARQDIGITAL;password= ARQDIGITAL; libraries=DACLB,DOOLB");
				System.out.println("Catalog: " + conn.getCatalog());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			cs = conn.prepareCall(sbSQL.toString());
			cs.setString(1, beanLog.getIdentificador());
			cs.setString(2, beanLog.getIdUsuario());
			cs.setString(3, beanLog.getSolicitud().length() > 200 ? beanLog.getSolicitud().substring(0, 200)
					: beanLog.getSolicitud());
			cs.setString(4, beanLog.getNombreOperacion().length() > 100 ? beanLog.getNombreOperacion().substring(0, 100)
					: beanLog.getNombreOperacion());
			cs.setString(5,
					beanLog.getMetodo().length() > 50 ? beanLog.getMetodo().substring(0, 50) : beanLog.getMetodo());
			cs.setString(6, beanLog.getCodigoMensaje());
			cs.setString(7,
					beanLog.getDescripcionMensaje().length() > 250 ? beanLog.getDescripcionMensaje().substring(0, 250)
							: beanLog.getDescripcionMensaje());
			cs.setLong(8, beanLog.getDuracion());
			cs.setString(9, beanLog.getConsulta().length() > 100 ? beanLog.getConsulta().substring(0, 100)
					: beanLog.getConsulta());
			cs.setString(10, (multipleRegistro ? "" : beanLog.getParametrosConsulta()));
			cs.setString(11, beanLog.getTipoMensaje());
			cs.setString(12, beanLog.getFechaRegistro());
			cs.setString(13, beanLog.getHoraRegistro());
			cs.setString(14, beanLog.getAplicacion());
			cs.setBoolean(15, beanLog.isEsPadre());
			cs.setString(16, beanLog.getSolicitudOrigen());
			cs.setString(17, beanLog.getDireccionIP());

			if (multipleRegistro) {

				String parametroConsultaTotal = beanLog.getParametrosConsulta();
				String parametroConsultaParcial = "";

				boolean esPrimeraVez = true;

				while (!parametroConsultaTotal.isEmpty()) {

					if (beanLog.isEsPadre()) {

						if (esPrimeraVez) {
							esPrimeraVez = false;
						} else {
							// EN LA SEGUNDA ITERACION EL CAMPO ES PADRE SE VUELVE FALSO
							cs.setBoolean(15, false);
						}

					}

					if (parametroConsultaTotal.length() > sizeParametroConsulta) {

						parametroConsultaParcial = parametroConsultaTotal.substring(0, 500);
						parametroConsultaTotal = parametroConsultaTotal.substring(500, parametroConsultaTotal.length());

					} else {

						parametroConsultaParcial = parametroConsultaTotal;
						parametroConsultaTotal = "";

					}

					cs.setString(10, parametroConsultaParcial);
					cs.executeUpdate();
				}

			} else {

				cs.executeUpdate();

			}

			estadoEvento = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Error al registrar log de operaciones: " + beanLog);
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
		return estadoEvento;
	}

}
