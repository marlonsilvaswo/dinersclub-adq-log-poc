package pe.dinersclub.adqlog.repositorio.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;

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
			sbSQL.append("{CALL ").append("RDSADMIN");
			sbSQL.append(".").append("SP_WSC_REGISTRARLOG").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			boolean multipleRegistro = false;

			if (beanLog.getParametrosConsulta().length() > sizeParametroConsulta) {
				multipleRegistro = true;
			}

			//conn = ConnectionDB.getConnectionServerAS400();
			conn = ds.getConnection();

			cs = conn.prepareCall(sbSQL.toString());
			cs.setString(1, beanLog.getIdentificador());
			cs.setString(2, beanLog.getIdUsuario());
			cs.setString(3, beanLog.getSolicitud().length() > 200 ? beanLog.getSolicitud().substring(0, 200) : beanLog.getSolicitud());
			cs.setString(4, beanLog.getNombreOperacion().length() > 100 ? beanLog.getNombreOperacion().substring(0, 100) : beanLog.getNombreOperacion());
			cs.setString(5, beanLog.getMetodo().length() > 50 ? beanLog.getMetodo().substring(0, 50) : beanLog.getMetodo());
			cs.setString(6, beanLog.getCodigoMensaje());
			cs.setString(7, beanLog.getDescripcionMensaje().length() > 250 ? beanLog.getDescripcionMensaje().substring(0, 250) : beanLog.getDescripcionMensaje());
			
			//BigDecimal duracion = new BigDecimal(1);
			cs.setString(8, "8");
			cs.setString(9, beanLog.getConsulta().length() > 100 ? beanLog.getConsulta().substring(0, 100) : beanLog.getConsulta());
			cs.setString(10, (multipleRegistro ? "" : beanLog.getParametrosConsulta()));
			cs.setString(11, beanLog.getTipoMensaje());
			cs.setString(12, beanLog.getFechaRegistro());
			cs.setString(13, beanLog.getHoraRegistro());
			cs.setString(14, beanLog.getAplicacion());
			
			//BigDecimal padre = new BigDecimal(2);
			cs.setString(15, "9");
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
