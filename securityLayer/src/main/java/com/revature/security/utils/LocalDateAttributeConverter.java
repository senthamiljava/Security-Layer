package com.revature.security.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter for changing localDateTime to Time stamp and vice versa
 * 
 * @author Siva
 *
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime localDate) {
    return localDate == null ? null : Timestamp.valueOf(localDate);
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
    return sqlDate == null ? null : sqlDate.toLocalDateTime();
  }
}
