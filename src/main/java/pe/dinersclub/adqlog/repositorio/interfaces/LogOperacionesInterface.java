package pe.dinersclub.adqlog.repositorio.interfaces;

import javax.sql.DataSource;

import pe.dinersclub.adqlog.model.BeanLog;

public interface LogOperacionesInterface {

	public boolean registrarLogOperacion(BeanLog beanLog, DataSource ds);
}
