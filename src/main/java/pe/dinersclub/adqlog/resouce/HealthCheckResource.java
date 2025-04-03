package pe.dinersclub.adqlog.resouce;


import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.dinersclub.adqlog.model.BeanLog;
import pe.dinersclub.adqlog.repositorio.impl.LogOperacionesImpl;

@Path("/health")
public class HealthCheckResource {
	
	@Inject
	DataSource ds;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> get() throws SQLException
	{
		
		String status = "ok";
		
		try( var conn = ds.getConnection() ){
			
			if(conn.isValid(15)) {
				status = "valid";	
			}else {
				status = "invalid";
			}
			
		}
		
		BeanLog beanLog = new BeanLog();
		beanLog.setAplicacion("LOG");
		beanLog.setCodigoMensaje("1");
		beanLog.setDescripcionMensaje("DESC MSG INSERT");
		beanLog.setTipoMensaje("INSERT");
		beanLog.setConsulta("A");
		beanLog.setIdUsuario("DINERS");
		beanLog.setIdentificador("123");
		beanLog.setSolicitud("SOL 1");
		beanLog.setSolicitudOrigen("ORIGEM");
		
		LogOperacionesImpl a = new LogOperacionesImpl();
		a.registrarLogOperacion(beanLog, ds);
		
		return Map.of("health", status);
	}
}
