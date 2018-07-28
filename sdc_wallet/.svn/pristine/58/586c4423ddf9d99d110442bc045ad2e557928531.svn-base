package com.utils.servlet;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public abstract class PropertiesUtils {

	private static final String UNKNOWN_ERR = "未知错误";

	private static final Logger logger = Logger
			.getLogger(PropertiesUtils.class);

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static String loadProperties(int retCode) throws Exception {
		Properties props = new Properties();

		String retMsg = "";
		InputStream is = null;
		try {
			Resource rs = resourceLoader.getResource("retcode.properties");

			is = rs.getInputStream();

			props.load(is);

			retMsg = props.getProperty(String.valueOf(retCode), UNKNOWN_ERR);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Could not load properties from classpath:"
					+ ex.getMessage());
		} finally {
			if (is != null) {
				is.close();
			}
		}

		return retMsg;
	}
}
