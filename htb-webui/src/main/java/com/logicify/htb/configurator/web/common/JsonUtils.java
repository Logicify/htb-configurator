package com.logicify.htb.configurator.web.common;

import com.fasterxml.jackson.core.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Provides helper methods for serializing\deserializing domain objects
 * <p/>
 * User: Dmitry Berezovsky (LOGICIFY\corvis)
 * Date: 8/19/13
 * Time: 1:51 AM
 */
public class JsonUtils
{
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
  public static final String TYPE_JSON = "application/json";


  private static JsonFactory jsonFactory = new JsonFactory();

  public static String toJsonString(Object obj)
  {
    if (obj == null) return "null";
    StringWriter stringWriter = new StringWriter();
    try {
      jsonFactory.createGenerator(stringWriter).writeObject(obj);
      stringWriter.flush();
      return stringWriter.toString();
    } catch (IOException e) {
      LOGGER.error("Unable to generate json", e);
      return null;
    }
  }

  public static Object fromJsonString(String json, Class objectType)
  {
    if (json == null) {
      return null;
    }
    try {
      return jsonFactory.createParser(json).readValueAs(objectType);
    } catch (IOException e) {
      LOGGER.error("Unable to parse json", e);
      return null;
    }
  }
}
