package pe.dinersclub.adqlog.resouce;

import java.util.Map;

import javax.sql.DataSource;

import org.jboss.resteasy.reactive.RestQuery;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.dinersclub.adqlog.model.BeanLog;
import pe.dinersclub.adqlog.model.Extension;
import pe.dinersclub.adqlog.repositorio.impl.LogOperacionesImpl;

@Path("/v1")
public class HealthCheckResource {
	
	@Inject
	DataSource ds;
	
	@POST
    @Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendMultipart(Extension nome) {
		
		System.out.println(nome.getName());
		
		return "OK";
		
	}
	
	
	@POST
	@Path("/health")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> post(@RestQuery String idade) 
	{
		
		System.out.println(idade);
		
		String status = "ok";
		
		/*try( var conn = ds.getConnection() ){
			
			if(conn.isValid(15)) {
				status = "valid";	
			}else {
				status = "invalid";
			}
			
		} catch (java.sql.SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}*/
		
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
		beanLog.setFechaRegistro("20250426");
		
		LogOperacionesImpl a = new LogOperacionesImpl();
		a.registrarLogOperacion(beanLog, ds);
		
		return Map.of("health", status);
	}
}
