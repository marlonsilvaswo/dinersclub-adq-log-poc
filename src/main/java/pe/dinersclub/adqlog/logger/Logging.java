package pe.dinersclub.adqlog.logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import pe.dinersclub.adqlog.model.BeanLog;
import pe.dinersclub.adqlog.util.UtilObjectMapper;

public class Logging {

	private final Logger logger = Logger.getLogger(Logging.class.getName());
	private static Logging logging;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private Formatter formatter = new Formatter() {
		@Override
		public String format(LogRecord record) {
			String message = formatMessage(record);
			return String.format("[%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL] %2$s%n", record.getMillis(), message);
		}
	};

	public static Logging GetInstance() {
		if (logging == null) {
			logging = new Logging();
		}
		return logging;
	}

	public Logging() {

	}

	public void print(String mensaje) {
		FileHandler fh = null;
		try {
			BeanLog beanLog = UtilObjectMapper.getObjectMapper().readValue(mensaje, BeanLog.class);

			String path = (beanLog.getAplicacion() != null && !beanLog.getAplicacion().isEmpty()
					? "log/" + beanLog.getAplicacion() + "/."
					: "log/.");
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.mkdirs();
			}
			fh = new FileHandler(file.getCanonicalPath() + "/" + format.format(new Date().getTime()) + ".log", true);

			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			logger.addHandler(fh);
			logger.info(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fh != null)
				fh.close();
		}
	}
}
