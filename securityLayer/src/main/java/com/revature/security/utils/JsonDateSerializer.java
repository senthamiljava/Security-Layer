package com.revature.security.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<LocalDateTime> {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

  @Override
  public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    String formattedDateTime = date.format(formatter);
    gen.writeString(formattedDateTime);
  }

}
