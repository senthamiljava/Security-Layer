package com.revature.security.boot.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is for get the service messages from the service properties
 * 
 * @author MUTHU G.K
 *
 */
public class ServiceMessages {

  private static final Logger LOG = LogManager.getLogger(ServiceMessages.class);

  private ServiceMessages() {}

  public static String getMsg(String key) {
    if (StringUtils.isBlank(key)) {
      return key;
    }

    String value = null;
    Properties prop = new Properties();

    try {
      InputStream propertiesFile =
          ServiceMessages.class.getClassLoader().getResourceAsStream("ServiceMsg.properties");

      if (propertiesFile != null) {
        prop.load(propertiesFile);
        value = prop.getProperty(key);
      }
    } catch (IOException ex) {
      LOG.error(ex.getMessage(), ex);
    }

    return StringUtils.isBlank(value) ? key : value;
  }
}
