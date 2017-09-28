package com.revature.security.utils;

public class AppConstants {

  public static final String EMPLOYEE_IMAGE_DOWNLOAD_URL = "employee/{id}/empImage";
  public static final String DEFAULT_TIME_ZONE = "UTC";

  private AppConstants() {}

  public static String getEnv(String key) {
    if (key == null || key.trim().length() == 0) {
      return null;
    }
    return System.getenv(key);
  }
}
