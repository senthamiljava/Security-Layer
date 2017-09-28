package com.revature.security.boot.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DataAccessUtils {

  private static DataAccessUtils instance;
  private static Logger logger = Logger.getLogger(DataAccessUtils.class);

  /**
   * This method used to getInstance.
   * 
   * @return INSTANCE
   */
  public static DataAccessUtils getInstance() {
    if (instance == null) {
      instance = new DataAccessUtils();
    }
    return instance;
  }

  private String dataAccessPropFile =
      "com/revature/shared/data/access/dataAccessMessages.properties";

  /**
   * This method used to getPropertyFileValue.
   * 
   * @return value
   */
  public String getPropertyFileValue(String keyName) {
    String value = null;
    Properties prop = new Properties();
    try {
      InputStream propertiesFile =
          DataAccessUtils.class.getClassLoader().getResourceAsStream(dataAccessPropFile);
      if (propertiesFile != null) {
        prop.load(propertiesFile);
        String tempString = prop.getProperty(keyName);
        if (tempString != null && !tempString.equalsIgnoreCase("")) {
          value = tempString;
        } else {
          value = keyName;
        }
      }
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
    }
    return value;
  }

}
