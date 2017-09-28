package com.revature.security.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Method to deserialize the json date
 * 
 * @modify by Siva, Avinash
 *
 */
@Component
public class CustomJsonDateDeserializer extends JsonDeserializer<LocalDateTime> {
  private static final String DATE_RANGE = "Date should be 1 to 31";
  private static final String MONTH_RANGE = "Month should be 1 to 12";

  @Override
  public LocalDateTime deserialize(JsonParser jsonparser,
      DeserializationContext deserializationcontext) throws IOException {

    String date = jsonparser.getText();
    String[] dateTimeArray = date.split(" ");
    String[] dateArray = dateTimeArray[0].split("-");

    int day = Integer.parseInt(dateArray[0]);
    int month = Integer.parseInt(dateArray[1]);
    int year = Integer.parseInt(dateArray[2]);
    int hour = 0;
    int minute = 0;

    if (dateTimeArray.length > 1) {
      String[] timeArray = dateTimeArray[1].split(":");
      hour = Integer.parseInt(timeArray[0]);
      minute = Integer.parseInt(timeArray[1]);
    }

    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);

    Optional.ofNullable(day).filter(days -> days >= 0 && days <= 31)
        .orElseThrow(() -> new IllegalArgumentException(DATE_RANGE));

    Optional.ofNullable(month).filter(months -> months >= 0 && months <= 12)
        .orElseThrow(() -> new IllegalArgumentException(MONTH_RANGE));
    return dateTime;
  }
}
