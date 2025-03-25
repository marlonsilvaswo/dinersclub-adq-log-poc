//package pe.dinersclub.adqlog.jms;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.support.JmsMessageHeaderAccessor;
//import org.springframework.stereotype.Component;
//
//import pe.dinersclub.adqlog.model.BeanLog;
//import pe.dinersclub.adqlog.repositorio.interfaces.LogOperacionesInterface;
//import pe.dinersclub.adqlog.util.UtilObjectMapper;
//
//@Component
//public class JmsOyente {
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@Autowired
//	private LogOperacionesInterface logOperacionesInterface;
//
//	@JmsListener(destination = "${jms.cola.escucha}")
//	public void miMensaje(String mensajeJson, JmsMessageHeaderAccessor headerAccessor) {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					BeanLog beanLog = UtilObjectMapper.getObjectMapper().readValue(mensajeJson, BeanLog.class);
//					if (!logOperacionesInterface.registrarLogOperacion(beanLog)) {
//						logger.error("===ERROR AL REGISTRAR EN EL LOG DE OPERACIONES===");
//					}
//					//Logging.GetInstance().print(mensajeJson);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		}).start();
//	}
//}